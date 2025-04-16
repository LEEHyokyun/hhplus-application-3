package kr.hhplus.be.server.point.infra.jpa;

import org.springframework.beans.factory.annotation.Autowired;

import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.point.domain.type.TransactionType;
import kr.hhplus.be.server.user.application.UserMapper;
import kr.hhplus.be.server.user.domain.entity.User;
import kr.hhplus.be.server.user.domain.entity.UserHistory;
import kr.hhplus.be.server.user.infra.jpa.UserHistoryWriterRepository;
import kr.hhplus.be.server.user.infra.jpa.UserWriterRepository;

public class PointCustomWriterRepositoryImpl implements PointCustomWriterRepository{
	
	//private final JPAQueryFactory jpaQueryFactory;
	@Autowired
	private PointReaderRepository pointReaderRepository;
	
	@Autowired
	private PointWriterRepository pointWriterRepository;
	
	@Autowired
	private UserWriterRepository userWriterRepository;
	
	@Autowired
	private UserHistoryWriterRepository userWriterHistoryRepository;
	
	/*
	 * QClass 오류 : 조회쿼리에 한해 mybatis로 대체
	 * */
	
	/*
	 * DIP/ISP
	 * 최종적인 구현 및 엔티티 변환의 책임을 impl에서 진행한다.
	 * Impl의 구현이 변경되어도 PointService에 의존성 영향을 미치지 않는다.
	 * */
	
	/*
	 * point와 pointhistory 도메인은 서로 분리하지 않고 동일시
	 * 이에 따라 단위 테스트 범위로 간주
	 * */
	@Override
	public void charge(PointDTO pointDTO) {
		
		//point entity
		PointDTO pointEntity = new PointDTO.PointBuilder(pointReaderRepository.findByUserId(pointDTO.getUserId()))
										.setChargedPointBuilder(pointDTO.getPoint())
										.build();
		
		//point entity -> user entity
		User userEntity = UserMapper.toUserEntityFromPointDomain(pointEntity);
		UserHistory userHistoryEntity = UserMapper.toUserHistoryEntityFromPointDomain(pointDTO, TransactionType.CHARGE.toString());
		
		//charge
		userWriterRepository.save(userEntity);
		userWriterHistoryRepository.save(userHistoryEntity);

	}
	
	@Override
	public void use(PointDTO pointDTO) {
		//point entity
		PointDTO pointEntity = new PointDTO.PointBuilder(pointReaderRepository.findByUserId(pointDTO.getUserId()))
										.setUsedPointBuilder(pointDTO.getPoint())
										.build();
		
		//point entity -> user entity
		User userEntity = UserMapper.toUserEntityFromPointDomain(pointEntity);
		UserHistory userHistoryEntity = UserMapper.toUserHistoryEntityFromPointDomain(pointDTO, TransactionType.CHARGE.toString());
		
		//charge
		userWriterRepository.save(userEntity);
		userWriterHistoryRepository.save(userHistoryEntity);
	}
}
