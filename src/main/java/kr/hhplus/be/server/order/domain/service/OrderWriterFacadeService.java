package kr.hhplus.be.server.order.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.common.key.ProductEnum;
import kr.hhplus.be.server.event.order.object.OrderCommitEvent;
import kr.hhplus.be.server.order.application.OrderMapper;
import kr.hhplus.be.server.order.domain.model.OrderDTO;
import kr.hhplus.be.server.order.domain.redis.OrderRedisTemplateProvider;
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
	
	@Autowired
	private OrderRedisTemplateProvider orderRedisTemplateProvider;
	
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;
	
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
		
		/*
		 * 인기상품조회를 위해
		 * 주문 후 redis ranking 데이터를 누적
		 * 주문량 = score
		 * */
		//orderWriterRepository.orderpay(orderDTO);
		
		/*
		 * 학습목적으로 주문 메인로직을 
		 * order, charge 서비스 호출로 분리
		 * */
		orderWriterService.order(orderDTO);
		pointWriterService.charge(orderMapper.toPointDomainFromOrderDomain(orderDTO));
		
		/*
		 * 후행 서비스는 OrderEventListener에서 동작하도록 구성
		 * 메인 로직은 이벤트를 발행하기만 하도록 구성합니다.
		 * 이벤트 발행 시 메인로직 정보가 담긴 이벤트 객체를 전달
		 * */
		/*
		 * 주문이 성공하였을때만 누적하도록 하며,
		 * 성공하지 못하였다면 Ranking 정보는 누적하지 않습니다.
		 * */
		//orderRedisTemplateProvider.setProductRanking(ProductEnum.HOT_SALE_PRODUCT.key() , orderDTO.getProductId(), orderDTO.getOrderQuantity());
		applicationEventPublisher.publishEvent(new OrderCommitEvent(orderDTO.getOrderId(), orderDTO.getOrderQuantity()));
	}
}
