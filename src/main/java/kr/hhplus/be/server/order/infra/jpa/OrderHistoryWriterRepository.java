package kr.hhplus.be.server.order.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import kr.hhplus.be.server.order.domain.entity.OrderHistory;

public interface OrderHistoryWriterRepository extends JpaRepository<OrderHistory, Long>{

}
