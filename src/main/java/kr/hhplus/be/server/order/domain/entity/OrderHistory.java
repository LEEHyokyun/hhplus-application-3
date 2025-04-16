package kr.hhplus.be.server.order.domain.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import kr.hhplus.be.server.product.domain.entity.Product;

public class OrderHistory {
	/*
	 * 영속성 계층에 대한 Entity
	 * */
	@Id
	private Long orderHistoryId;
	
	private Long orderId;
	
	private Long userId;
	
	private Timestamp createdAt;
	
	private Timestamp modifiedAt;
	
	/*
	 * Order Entity를 정적으로 생성하여 Mapper에서 엔티티 변환이 이루어지도록 구성
	 * */
	private OrderHistory(Long orderHistoryId, Long orderId, Long userId, Timestamp createdAt, Timestamp modifiedAt) {
		this.orderHistoryId = orderHistoryId;
		this.orderId = orderId;
		this.userId = userId;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public static OrderHistory standardOrderHistoryEntityOf(Long orderHistoryId, Long orderId, Long userId, Timestamp createdAt, Timestamp modifiedAt) {
		return new OrderHistory(orderHistoryId, orderId, userId, createdAt, modifiedAt);
	}
}
