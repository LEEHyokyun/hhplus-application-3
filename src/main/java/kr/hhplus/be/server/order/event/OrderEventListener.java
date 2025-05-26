package kr.hhplus.be.server.order.event;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import kr.hhplus.be.server.common.key.ProductEnum;
import kr.hhplus.be.server.order.domain.model.OrderCommitEvent;
import kr.hhplus.be.server.order.domain.redis.OrderRedisTemplateProvider;
import lombok.extern.slf4j.Slf4j;

/*
 * Order COMMIT 이후 후행동작을 정의하기 위한 리스너
 * 컴포넌트로 등록필수
 * - AOP에 의한 Transactional 종료 이후 다시 Component에 의해 새로운 컨텍스트 생성
 * - 기본적으로 Bean으로 등록을 해야 Spring에서 제공하는 Event Listener / Publisher 동작 가능
 * */
@Component
@Slf4j
public class OrderEventListener {
	
	@Autowired
	private OrderRedisTemplateProvider orderRedisTemplateProvider;
	
	/*
	 * CASE 1 : AFTER COMMIT : 트랜잭션 커밋 이후
	 * 관심이벤트 : Order Commit Event
	 * */
	@TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void handleAfterCommit(OrderCommitEvent event) {
        // Logic to execute after the transaction commits
		/*
		 * 주문서비스 동작 이후 로그가 출력되는지 확인
		 * */
		log.info("**************************");
		log.info("주문 서비스를 구독하여 후행서비스를 진행하는 리스너");
		log.info("**************************");
		
		/*
		 * 주문이 성공하였을때만
		 * 랭킹 정보를 누적하는 orderRedisTemplateProvider를 동작하도록 구성합니다.
		 * */
		orderRedisTemplateProvider.setProductRanking(ProductEnum.HOT_SALE_PRODUCT.key(), event.getProductId(), event.getOrderQuantity());
    }
	
	/*
	 * CASE 2 : AFTER ROLLBACK : 트랜잭션 롤백 이후
    @TransactionalEventListener(phase = TransactionPhase.AFTER_ROLLBACK)
    public void handleAfterRollback(OrderCommitEvent event) {
        // Logic to execute after the transaction rolls back
    }
    */
	
    /*
     * CASE 3 : BEFORE COMMIT : 트랜잭션 커밋 이전
    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void handleBeforeCommit(OrderCommitEvent event) {
        // Logic to execute before the transaction commits
    }
    */
    
    /*
     * CASE 4 : AFTER COMPLETION : 트랜잭션의 롤백 및 커밋 이후
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMPLETION)
    public void handleAfterCompletion(OrderCommitEvent event) {
       //Logic to execute after the transaction completes (commit or rollback)
    }
    */
}
