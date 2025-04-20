package kr.hhplus.be.server.order.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.order.application.OrderMapper;
import kr.hhplus.be.server.order.domain.model.OrderDTO;
import kr.hhplus.be.server.order.infra.jpa.OrderWriterRepository;
import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.point.domain.service.PointWriterService;

@Service
@Transactional
public class OrderWriterFacadeService {
	
	@Autowired
	private OrderWriterService orderWriterService;
	
	@Autowired
	private PointWriterService pointWriterService;
	
	@Autowired
	private OrderMapper orderMapper;
	
	@Autowired
	private OrderWriterRepository orderWriterRepository;
	
	public void orderPay(OrderDTO orderDTO) throws Exception {
		
		/*
		 * 트랜잭션
		 * = 1.주문
		 * = 2.결제
		 * */
		
		/*
		 * 결합도가 너무 높아진다는 판단이 들어
		 * 비즈니스 로직의 결합을 영속성 계층으로 전환하도록 구성 변경
		 * 최종적으로 로직을 변경해야 한다면 영속성 계층에서만 변경하면 됨
		 * */
		
		/*
		* orderWriterService.order(orderDTO);
		*
		* //orderDTO -> pointDTO
		* PointDTO pointDTO = orderMapper.toPointDomainFromOrderDomain(orderDTO);
		*
		* pointWriterService.use(pointDTO);
		*/
		
		orderWriterRepository.orderpay(orderDTO);
	}
}
