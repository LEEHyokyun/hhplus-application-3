package kr.hhplus.be.server.order.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.order.domain.model.OrderDTO;
import kr.hhplus.be.server.order.infra.jpa.OrderWriterRepository;

@Service
@Transactional
public class OrderWriterService {
	
	@Autowired
	private OrderWriterRepository orderWriterRepository;
	
	public void order(OrderDTO orderDTO) {
		orderWriterRepository.order(orderDTO);
	}
}
