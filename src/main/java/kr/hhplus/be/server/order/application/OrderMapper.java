package kr.hhplus.be.server.order.application;

import java.sql.Timestamp;

import kr.hhplus.be.server.order.domain.entity.Order;
import kr.hhplus.be.server.order.domain.entity.OrderHistory;
import kr.hhplus.be.server.order.domain.model.OrderDTO;
import kr.hhplus.be.server.point.domain.model.PointDTO;

/*
 * 상태변환매개체 : VO의 범주
 * */
public class OrderMapper {
	
	/*
	 * Order 도메인 모델 계층과 다른 속성들을
	 * Order 영속성 계층에 맞게 변환해주는 Mapper
	 * Order 영속성 계층에서 필요로 하는 속성 변환이므로 Order에게 그 책임이 존재한다.
	 * */
	public static Order toOrderEntityFromOrderDTO(OrderDTO orderDTO) {
		return Order.standardOrderEntityOf(orderDTO.getOrderId(), orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getOrderQuantity(), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
	}
	
	public static OrderHistory toOrderHistoryEntityFromOrderDomain(OrderDTO orderDTO) {
		return OrderHistory.standardOrderHistoryEntityOf(orderDTO.getOrderId(), orderDTO.getOrderId(), orderDTO.getUserId(), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
	}
	
	public static PointDTO toPointDomainFromOrderDomain(OrderDTO orderDTO) throws Exception {
		return PointDTO.standardPointDTOOf(orderDTO.getUserId(), orderDTO.getOrderQuantity());
	}
}
