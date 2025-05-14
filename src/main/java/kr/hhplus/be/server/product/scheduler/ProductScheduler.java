package kr.hhplus.be.server.product.scheduler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import kr.hhplus.be.server.product.domain.model.ProductEnum;

@Component
public class ProductScheduler {
	
	@Autowired
	private RedisTemplate<String, String> redisTemplate;
	
	/*
	 * 매달 1일부터 3일 간격으로 00시에 실행
	 * Redis "HOT_SALE_PRODUCT" key에 해당하는 Sorted Set 데이터 초기화
	 * */
	@Scheduled(cron = "0 0 00 1/3 * ?")
	public boolean hotSaledProductEvict() {
		return redisTemplate.delete(ProductEnum.HOT_SALE_PRODUCT.key());
	}
}
