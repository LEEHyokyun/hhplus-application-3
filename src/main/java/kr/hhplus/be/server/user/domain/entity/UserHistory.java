package kr.hhplus.be.server.user.domain.entity;

import java.sql.Timestamp;

import org.hibernate.annotations.ColumnDefault;

import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

public class UserHistory {
	/*
	 * 영속성 계층에 대한 Entity
	 * */
	@Id
	@OneToMany(mappedBy = "user" )
	private Long userId;
	
	@ColumnDefault("LEEHYOKYUN")
	private String userName;
	
	private Long point;
	
	private String transactionType;
	
	private Timestamp createdAt;
	
	private Timestamp modifiedAt;
	
	/*
	 * User Entity를 정적으로 생성하여 Mapper에서 엔티티 변환이 이루어지도록 구성
	 * */
	private UserHistory(Long userId, String userName, Long point, String transactionType, Timestamp createdAt, Timestamp modifiedAt) {
		this.userId = userId;
		this.userName = userName;
		this.point = point;
		this.transactionType = transactionType;
		this.createdAt = createdAt;
		this.modifiedAt = modifiedAt;
	}
	
	public static UserHistory standardUserHistoryEntityOf(Long userId, String userName, Long point, String transactionType, Timestamp createdAt, Timestamp modifiedAt) {
		return new UserHistory(userId, userName, point, transactionType, createdAt, modifiedAt);
	}
}
