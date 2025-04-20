package kr.hhplus.be.server.product.domain.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

@Entity
@Data
public class Product {
	/*
	 * 영속성 계층에 대한 Entity
	 * */
	@Id
	@OneToMany
	private Long productId;
	
	@ColumnDefault("NIKE")
	private String productName;
	
	private long productPrice;
	
	private Timestamp createdAt;
	
	private Timestamp modifiedAt;
	
	/*
	 * User Entity를 정적으로 생성하여 Mapper에서 엔티티 변환이 이루어지도록 구성
	 * */
	private Product(Long productId, String productName, Long productPrice, Timestamp createdAt, Timestamp modifiedAt) {
		this.productId = productId;
		this.productName = productName;
		this.productPrice = productPrice;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public static Product standardProductEntityOf(Long productId, String productName, Long productPrice, Timestamp createdAt, Timestamp modifiedAt) {
		return new Product(productId, productName, productPrice, createdAt, modifiedAt);
	}
}
