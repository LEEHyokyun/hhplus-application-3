package kr.hhplus.be.server.order.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.order.domain.entity.Order;

@Repository
public interface OrderWriterRepository extends JpaRepository<Order, Long>, OrderCustomWriterRepository{
	
}
