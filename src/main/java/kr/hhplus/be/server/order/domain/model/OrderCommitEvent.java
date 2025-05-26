package kr.hhplus.be.server.order.domain.model;

/*
 * 주문까지 COMMIT 완료 시
 * 이벤트를 전달하기 위한 POJO 객체
 * */
public class OrderCommitEvent {
	//order Id
	private final Long productId;
	
	//user Id
	private final Long orderQuantity;
	
	/*
	 * 생성자 주입
	 * */
	public OrderCommitEvent(Long productId, Long orderQuantity) {
		this.productId = productId;
		this.orderQuantity = orderQuantity;
	}
	
	/*
	 * Event 객체에서 
	 * 주문정보와 사용자정보를 전달받기 위함
	 * */
	public Long getProductId() {
		return this.productId;
	}
	
	public Long getOrderQuantity() {
		return this.orderQuantity;
	}
}
