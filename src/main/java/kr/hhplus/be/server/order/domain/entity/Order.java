package kr.hhplus.be.server.order.domain.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import kr.hhplus.be.server.product.domain.entity.Product;
import lombok.Data;

@Entity
@Data
public class Order {
	/*
	 * 영속성 계층에 대한 Entity
	 * */
	@Id
	private Long orderId;
	
	private Long productId;
	
	private Long userId;
	
	private Long orderQuantity;
	
	private Timestamp createdAt;
	
	private Timestamp modifiedAt;
	
	/*
	 * Order Entity를 정적으로 생성하여 Mapper에서 엔티티 변환이 이루어지도록 구성
	 * */
	private Order(Long orderId, Long productId, Long userId, Long orderQuantity, Timestamp createdAt, Timestamp modifiedAt) {
		this.orderId = orderId;
		this.productId = productId;
		this.userId = userId;
		this.orderQuantity = orderQuantity;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public static Order standardOrderEntityOf(Long orderId, Long productId, Long userId, Long orderQuantity, Timestamp createdAt, Timestamp modifiedAt) {
		return new Order(orderId, productId, userId, orderQuantity, createdAt, modifiedAt);
	}
}
