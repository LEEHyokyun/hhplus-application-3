package kr.hhplus.be.server.product.domain.redis;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;

import kr.hhplus.be.server.common.key.ProductEnum;

/*
 * Redis Sorted Set 자료구조에 접근하여 
 * 상위 도메인이 필요한 기능을 제공하는 클래스입니다.
 * 도메인 별로 필요한 Redis 자료구조와 기능이 다를 것이라 판단하여
 * 도메인 하위 Redis TemplateProvider를 구성하였습니다.
 * */
public class ProductRedisTemplateProvider {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	//start Index : 0 ~ end Index : 10
	private long sIndex = 0;
	private long eIndex = 10;
	
	/*
	 * Redis에 이미 저장되어있는(정렬이 되어있는) 5개의 인기상품을 조회하므로
	 * 매개변수없이 그대로 Redis에 있는 인기상품 정보를 조회해오도록 메소드를 구성합니다.
	 * */
	public Set<String> getHotSaledProductIds(){
		/*
		 * Redis의 자료구조에서 데이터를 얻기위해 사용하는 객체
		 * */
		ZSetOperations<String, String> zSetOps = redisTemplate.opsForZSet();
		
		return zSetOps.reverseRange(ProductEnum.HOT_SALE_PRODUCT.key(), sIndex, eIndex);
	}
	
}
