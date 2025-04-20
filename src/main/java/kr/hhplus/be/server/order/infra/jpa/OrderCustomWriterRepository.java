package kr.hhplus.be.server.order.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.order.domain.entity.Order;
import kr.hhplus.be.server.order.domain.model.OrderDTO;

@Repository
public interface OrderCustomWriterRepository {
	void order(OrderDTO orderDTO);
	
	void orderpay(OrderDTO orderDTO);
}
