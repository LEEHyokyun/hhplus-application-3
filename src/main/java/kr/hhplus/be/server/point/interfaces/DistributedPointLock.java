package kr.hhplus.be.server.point.interfaces;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/*
 * Point 도메인에서 사용하는 DistributedLock 인터페이스
 * - Target : method
 * - JVM load 및 RUNTIME 환경에서 사용 : RETENTION TYPE = RUNTIME
 * */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedPointLock {
	
	 /*
     * 락의 key값
     * = userId
     */
    String key();
    
    /*
     * 락의 시간 단위
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
    
    /*
     * 락을 기다리는 시간 (default - 3s)
     * 락 획득을 위해 waitTime 만큼 대기한다
     */
    long waitTime() default 3L;

    /*
     * 락 해제를 위한 임계 시간 (default - 3s)
     * 락을 획득한 이후 leaseTime 이 지나면 락을 해제한다
     */
    long leaseTime() default 3L;
    
}
