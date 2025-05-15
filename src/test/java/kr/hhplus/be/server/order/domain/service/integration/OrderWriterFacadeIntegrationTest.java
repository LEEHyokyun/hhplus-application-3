package kr.hhplus.be.server.order.domain.service.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import kr.hhplus.be.server.common.key.ProductEnum;
import kr.hhplus.be.server.order.domain.model.OrderDTO;
import kr.hhplus.be.server.order.domain.service.OrderWriterFacadeService;

@SpringBootTest
public class OrderWriterFacadeIntegrationTest {
	
	@Autowired
	OrderWriterFacadeService orderWriterFacadeService;
	
	@Autowired
	RedisTemplate<String,String> redisTemplate;
	
	@Test
	@DisplayName("[통합테스트] 주문을 한 후에 Redis의 ranking 정보가 누적이 되었는지 확인한다.")
	void redisRankingScoringTest() throws Exception {
		/*
		 * given
		 * - 테스트에 사용할 변수 및 입력값을 정의한다.
		 * - 동작을 확인하기 위한 Mokito 정의도 포함(Database(Repository)의 객체를 Mokito화하여 사용)
		 * */
		Double expectedScore = 50D;
		OrderDTO orderDTO = OrderDTO.standardOrderDTOOf(1L, 1L, 1L, 50L);
		
		/*
		 * when
		 * - 실제 동작이 이루어진다.
		 * - 동작에 따른 상태 변화를 기억하거나, 대조군으로 활용하기 위한 과정이다.
		 * - 검증 대상의 동작 하나만 기술한다.
		 * */
		orderWriterFacadeService.orderPay(orderDTO);
		Double actualScore = redisTemplate.opsForZSet().score(ProductEnum.HOT_SALE_PRODUCT.key(), 1L);
		
		/*
		 * Then
		 * - 최종적으로 테스트를 검증한다.
		 * - 테스트 과정을 종합한다.
		 * */
		assertEquals(expectedScore, actualScore);
	}
}
