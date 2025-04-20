package kr.hhplus.be.server.order.infra.jpa;

import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;

import kr.hhplus.be.server.order.application.OrderMapper;
import kr.hhplus.be.server.order.domain.entity.Order;
import kr.hhplus.be.server.order.domain.entity.OrderHistory;
import kr.hhplus.be.server.order.domain.model.OrderDTO;
import kr.hhplus.be.server.point.infra.jpa.PointWriterRepository;
import kr.hhplus.be.server.user.application.UserMapper;
import kr.hhplus.be.server.user.domain.entity.User;
import kr.hhplus.be.server.user.domain.entity.UserHistory;
import kr.hhplus.be.server.user.infra.jpa.UserHistoryWriterRepository;
import kr.hhplus.be.server.user.infra.jpa.UserWriterRepository;

public class OrderCustomWriterRepositoryImpl implements OrderCustomWriterRepository{
	
	//private final JPAQueryFactory jpaQueryFactory;
	
	@Autowired
	private OrderWriterRepository orderWriterRepository;
	
	@Autowired
	private OrderHistoryWriterRepository orderHistoryWriterRepository;
	
	@Autowired
	private UserWriterRepository userWriterRepository;
	
	@Autowired
	private UserHistoryWriterRepository userHistoryWriterRepository;

	
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
		orderWriterRepository.save(orderEntity);
		orderHistoryWriterRepository.save(orderHistoryEntity);
		
	}
	
	@Override
	public void orderpay(OrderDTO orderDTO) {
		//DTO -> entity
		Order orderEntity = OrderMapper.toOrderEntityFromOrderDTO(orderDTO);
		OrderHistory orderHistoryEntity = OrderHistory.standardOrderHistoryEntityOf(orderEntity.getOrderId(), orderEntity.getOrderId(), orderEntity.getUserId(), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
		
		//order
		orderWriterRepository.save(orderEntity);
		orderHistoryWriterRepository.save(orderHistoryEntity);
		
		//DTO -> entity
		User userEntity = UserMapper.toUserEntityFromOrderDomain(orderDTO);
		UserHistory userHistoryEntity = UserMapper.toUserHistoryEntityFromOrderDomain(orderDTO);
		
		//pay
		userWriterRepository.save(userEntity);
		userHistoryWriterRepository.save(userHistoryEntity);
		
	}

}
