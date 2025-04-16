package kr.hhplus.be.server.order.infra.jpa;

import org.springframework.beans.factory.annotation.Autowired;

import kr.hhplus.be.server.order.application.OrderMapper;
import kr.hhplus.be.server.order.domain.entity.Order;
import kr.hhplus.be.server.order.domain.entity.OrderHistory;
import kr.hhplus.be.server.order.domain.model.OrderDTO;

public class OrderCustomWriterRepositoryImpl implements OrderCustomWriterRepository{
	
	//private final JPAQueryFactory jpaQueryFactory;
	
	@Autowired
	private OrderWriterRepository orderWriterRepository;
	
	@Autowired
	private OrderHistoryWriterRepository orderHistoryWriterRepository;
	
	/*
	 * QClass 오류 : 조회쿼리에 한해 mybatis로 대체
	 * */
	
	/*
	 * DIP/ISP
	 * 최종적인 구현 및 엔티티 변환의 책임을 impl에서 진행한다.
	 * Impl의 구현이 변경되어도 OrderService에 의존성 영향을 미치지 않는다.
	 * */
	
	/*
	 * order와 orderhistory 도메인은 서로 분리하지 않고 동일시
	 * 이에 따라 단위 테스트 범위로 간주
	 * */
	@Override
	public void order(OrderDTO orderDTO) {
		//DTO -> entity
		Order orderEntity = OrderMapper.toOrderEntityFromOrderDTO(orderDTO);
		OrderHistory orderHistoryEntity = OrderMapper.toOrderHistoryEntityFromOrderDomain(orderDTO);
		
		//order
		orderWriterRepository.order(orderDTO);
		orderHistoryWriterRepository.save(orderHistoryEntity);
		
	}
	
}
