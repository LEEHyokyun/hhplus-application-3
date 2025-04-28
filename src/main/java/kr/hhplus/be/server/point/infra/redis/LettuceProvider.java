package kr.hhplus.be.server.point.infra.redis;

import java.time.Duration;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class LettuceProvider {
		
		/*
		 * redis key값은 userId로 설정
		 * */
		
	 	private final RedisTemplate<String, String> redisTemplate;

	    public Boolean lock(Long key) {
	    	/*
	    	 * Lettuce : Spin Lock
	    	 * redis key 값을 userId로 설정하며, key값이 있다면 false를 반환한다.
	    	 * 3000ms 후 redis의 key는 만료(삭제).
	    	 * */
	        return redisTemplate
	                .opsForValue()
	                .setIfAbsent(this.getKey(key), "lock", Duration.ofMillis(3_000));
	    }

	    public Boolean unlock(Long key) {
	    	/*
	    	 * redis key 값을 제거하여 분산락 상태를 초기화한다.
	    	 * */
	        return redisTemplate.delete(this.getKey(key));
	    }
	    
	    private String getKey(Long key) {
	    	/*
	    	 * userId type인 Long을 String 형태로 변환한다.
	    	 * */
	    	return String.valueOf(key);
	    }
}
