package kr.hhplus.be.server.point.infra.redis;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import kr.hhplus.be.server.config.redis.AopForTransaction;
import kr.hhplus.be.server.config.redis.SpringELParser;
import kr.hhplus.be.server.point.interfaces.DistributedPointLock;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/*
 * Distributed lock 어노테이션을 적용하였을때
 * AOP 횡단관심사를 설정하여 해당 어노테이션 전/후로 횡단 관심사를 실행하도록 하는 프록시객체 => @Aspect / @Component
 * 이 Proxy 객체에 설정된 횡단 관심사는 가장 먼저 실행되도록 설정 => @Order
 * 이는 별도로 설정한 Redis 도메인에서 해당 기능 제공하는 클래스로 간주
 * */

@Order(1)
@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class DistributedPointLockProxyClass {
	
	/*
	 * 생성자 기반 DI
	 * - Autowired를 사용하지 않고도 생성자 기반의 의존성을 주입받을 수 있다.
	 * */
	private final RedissonClient redissonClient;
	private final AopForTransaction aopForTransaction;
	
	/*
	 * Distributed Lock 어노테이션을 사용한 메소드는
	 * AOP를 통해 Proxy 객체가 작동하여 메소드 실행 전 후(Around) 해당 관심사를 실행하게 됨
	 * */
	@Around("@annotation(kr.hhplus.be.server.point.interfaces.DistributedPointLock)")
    public Object lock(final ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        
        DistributedPointLock distributedPointLock = method.getAnnotation(DistributedPointLock.class);
        //distributedPointLock.
        /*
         * 서비스에서 Distributed Lock 어노테이션을 사용하여 lock name을 지정하는 방법 사용
         * */
        
        /*
         * SPEL을 사용하지 않을 경우 메소드에서 전달받은 매개변수로 key값을 설정할 수 있습니다.
         * 하지만 매개변수가 달라질 경우 동적으로 key값을 구성하기가 복잡해지게 됩니다.
         * */
        //Object[] args = joinPoint.getArgs();
        //String key = args[1].toString();
        
        /*
         * SPEL을 사용할 경우 동적인 key값 매핑 및 구성이 가능해집니다.
         * - SPEL을 사용하여 distributed key 값을 동적으로 파싱하여 구성
         * */
        String key = String.valueOf(SpringELParser.getDynamicValue(signature.getParameterNames(), joinPoint.getArgs(), distributedPointLock.key()));
        
        /*
         * 해당 key 값에 대해 pub/sub 방식으로 분산락 획득을 시도합니다.
         * - pub : 해당 key값에 대한 이벤트를 리스닝
         * - sub : 해당 key값에 대한 이벤트 발생 시 반응하여 동작 수행
         * */
        RLock rLock = redissonClient.getLock(key); 
        
        /*
         * 락을 먼저 획득한 이후에 tryLock의 이중 안전장치를 구성해줍니다.
         * - getLock 실패 : 트랜잭션 종료
         * - getLock 성공 : 트랜잭션을 락 획득 후 트랜잭션 진행
         * */
        try {
            boolean available = rLock.tryLock(distributedPointLock.waitTime(), distributedPointLock.leaseTime(), distributedPointLock.timeUnit());  // (2)
            if (!available) {
                return false;
            }
            	
            /*
             * 락 획득 후 트랜잭션 진행합니다.
             * 외부 클래스를 통해 별도 Transational 동작을 수행하는 AOP가 생성되어 메소드의 트랜잭션을 보장합니다.
             * */
            return aopForTransaction.proceed(joinPoint); 
        } catch (InterruptedException e) {
            throw new InterruptedException();
        } finally {
            try {
            	/*
            	 * 트랜잭션 종료 시 성공 실패여부 상관없이 반드시 락을 해제합니다.
            	 * */
                rLock.unlock();
            } catch (IllegalMonitorStateException e) {
                log.info("Redisson Lock Already UnLock {} " , key);
            }
        }
    }
}
