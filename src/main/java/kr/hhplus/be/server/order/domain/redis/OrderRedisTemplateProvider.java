package kr.hhplus.be.server.order.domain.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

/*
 * Redis Sorted Set 자료구조에 접근하여 
 * 상위 도메인이 필요한 기능을 제공하는 클래스입니다.
 * 도메인 별로 필요한 Redis 자료구조와 기능이 다를 것이라 판단하여
 * 도메인 하위 Redis TemplateProvider를 구성하였습니다.
 * */
public class OrderRedisTemplateProvider {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	/*
	 * Redis에 HOT_SALE_PRODUCT를 key 값으로 하는
	 * Sorted Set자료구조를 저장합니다.
	 * Value : ProductId, Ranking : score
	 * 주문할때마다 누적하는 것을 목적으로 구성하였습니다.
	 * */
	public Double setProductRanking(String key, long productId, long score) {
		/*
		 * Redis의 자료구조에서 데이터를 얻기위해 사용하는 객체
		 * */
		ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
		
		return zSetOps.incrementScore(key, String.valueOf(productId), score);
	}
}
