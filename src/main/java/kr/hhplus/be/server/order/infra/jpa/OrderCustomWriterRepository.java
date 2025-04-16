package kr.hhplus.be.server.order.infra.jpa;

import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.order.domain.model.OrderDTO;

@Repository
public interface OrderCustomWriterRepository {
	void order(OrderDTO orderDTO);
}
