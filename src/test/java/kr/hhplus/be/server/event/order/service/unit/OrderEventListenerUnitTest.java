package kr.hhplus.be.server.event.order.service.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import java.sql.Timestamp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

import annotation.UnitTest;
import kr.hhplus.be.server.order.domain.entity.Order;
import kr.hhplus.be.server.order.domain.model.OrderDTO;
import kr.hhplus.be.server.order.domain.redis.OrderRedisTemplateProvider;
import kr.hhplus.be.server.order.domain.service.OrderWriterFacadeService;

@UnitTest
public class OrderEventListenerUnitTest {
	
	@Autowired
	private OrderWriterFacadeService orderWriterFacadeService;
	
	@Autowired
	private OrderRedisTemplateProvider orderRedisTemplateProvider;
	
	@Test
	@DisplayName("[단위테스트] 주문 서비스를 호출하였을때 후행 동작으로 Redis 이벤트가 동작한다.")
	void OrderEventTest() throws Exception {
		/*
		 * given
		 * - 테스트에 사용할 변수 및 입력값을 정의한다.
		 * - 동작을 확인하기 위한 Mokito 정의도 포함(Database(Repository)의 객체를 Mokito화하여 사용)
		 * */
		OrderDTO orderDTO = this.orderDTO();
		Order orderEntity = Order.standardOrderEntityOf(orderDTO.getOrderId(), orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getOrderQuantity(), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
		
		/*
		 * when
		 * - 실제 동작이 이루어진다.
		 * - 동작에 따른 상태 변화를 기억하거나, 대조군으로 활용하기 위한 과정이다.
		 * - 검증 대상의 동작 하나만 기술한다.
		 * */
		orderWriterFacadeService.orderPay(orderDTO);
		
		/*
		 * Then
		 * - 최종적으로 테스트를 검증한다.
		 * - 테스트 과정을 종합한다.
		 * */
		verify(orderRedisTemplateProvider, atLeastOnce());
	}
	
	//orderDTO stub
    private OrderDTO orderDTO() {  
        return OrderDTO.standardOrderDTOOf(1L, 1L, 1L, 1000L);  
    }  

}
