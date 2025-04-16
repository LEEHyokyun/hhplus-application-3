package kr.hhplus.be.server.order.domain.model;

import kr.hhplus.be.server.point.domain.model.PointDTO;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class OrderDTO {
	/*
	 * 1. test에 기반하여 Order 도메인에서 필요한 식별자 및 객체 속성을 정의한다.
	 * 2. 그 이후 필요한 변환 mapper가 필요하다면 
	 * 3. 정적 팩토리 메소드를 사용하여 상황에 맞는 도메인 객체를 생성하도록 한다.
	 * */
	private Long orderId;
	private Long productId;
	private Long userId;
	private Long orderQuantity;
	
	/*
	 * DTO 생성자 정의
	 * */
	private OrderDTO(Long orderId, Long productId, Long userId, Long orderQuantity) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.orderQuantity = orderQuantity;
    }
	
	/*
	 * DTO를 정적으로 생성하여 팩토리 메소드의 장점을 활용하기 위한 팩토리 패턴
	 * */
	public static OrderDTO standardOrderDTOOf(Long ordeId, Long productId, Long userId, Long orderQuantity) {
		
		return new OrderDTO(userId, productId, userId, orderQuantity);
	}
}
