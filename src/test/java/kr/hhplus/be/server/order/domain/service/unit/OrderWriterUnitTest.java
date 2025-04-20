package kr.hhplus.be.server.order.domain.service.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import annotation.UnitTest;
import jakarta.transaction.Transactional;
import kr.hhplus.be.server.order.domain.model.OrderDTO;
import kr.hhplus.be.server.order.domain.service.OrderWriterService;
import kr.hhplus.be.server.order.infra.jpa.OrderReaderRepository;

@UnitTest
public class OrderWriterUnitTest {

	@Autowired
	OrderWriterService orderWriterService;
	
	@Autowired
	OrderReaderRepository orderReaderRepository;
	
	/*
	 * order와 orderhistory 도메인은 서로 분리하지 않고 동일시
	 * 이에 따라 단위 테스트 범위로 간주
	 * */
	
	@Test
	@Transactional
	@DisplayName("[단위테스트] 주문한다.")
	void orderTest() {
		/*
		 * given
		 * - 테스트에 사용할 변수 및 입력값을 정의한다.
		 * - 동작을 확인하기 위한 Mokito 정의도 포함(Database(Repository)의 객체를 Mokito화하여 사용)
		 * */
		long orderId = 1L;
		long productId = 1L;
		long userId = 1L;
		long orderQuantity = 100L;
		long expectedOrderQuantity = 100L;
		
		/*
		 * when
		 * - 실제 동작이 이루어진다.
		 * - 동작에 따른 상태 변화를 기억하거나, 대조군으로 활용하기 위한 과정이다.
		 * - 검증 대상의 동작 하나만 기술한다.
		 * */
		orderWriterService.order(OrderDTO.standardOrderDTOOf(orderId, productId, userId, orderQuantity));
		long actualOrderQuantity = orderReaderRepository.findByOrderId(orderId).getOrderQuantity();
		
		/*
		 * Then
		 * - 최종적으로 테스트를 검증한다.
		 * - 테스트 과정을 종합한다.
		 * */
		assertEquals(expectedOrderQuantity, actualOrderQuantity);
	}
}
