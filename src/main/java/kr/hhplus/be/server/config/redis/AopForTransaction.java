package kr.hhplus.be.server.config.redis;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;


/*
 * 각 도메인에서 Redis Distributed Lock 사용 시
 * AopForTransaction을 사용하여 트랜잭션을 분리하여야 합니다.
 * - 도메인 상관없이 모든 도메인에서 공통적으로 사용해야 하는 트랜잭션 분리 클래스이기에 config 도메인으로 설정
 * - Distributed 어노테이션 사용 시 Spring context에서 트랜잭션 흐름을 joinPoint로 전달합니다.
 * - 이 역시 AOP 형태로 주입받으므로 반드시 외부에서 사용해야 합니다.
 * */
@Component
public class AopForTransaction {
	@Transactional(propagation = Propagation.REQUIRES_NEW)
	public Object proceed(final ProceedingJoinPoint joinPoint) throws Throwable {
		/*
		 * 메소드를 실행하면서 Spring concontext로 부터 전달받을 트랜잭션 객체
		 * */
		return joinPoint.proceed();
	}
}
