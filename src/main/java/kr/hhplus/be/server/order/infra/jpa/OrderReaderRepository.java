package kr.hhplus.be.server.order.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.order.domain.entity.Order;

@Repository
public interface OrderReaderRepository extends JpaRepository<Order, Long>{
	Order findByOrderId(Long orderId);
}
