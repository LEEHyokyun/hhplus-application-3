package kr.hhplus.be.server.point.infra.jpa;

import org.springframework.beans.factory.annotation.Autowired;

import jakarta.persistence.EntityManager;
import jakarta.persistence.LockModeType;
import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.point.domain.type.TransactionType;
import kr.hhplus.be.server.user.application.UserMapper;
import kr.hhplus.be.server.user.domain.entity.User;
import kr.hhplus.be.server.user.domain.entity.UserHistory;
import kr.hhplus.be.server.user.infra.jpa.UserHistoryWriterRepository;
import kr.hhplus.be.server.user.infra.jpa.UserWriterRepository;

public class PointCustomWriterRepositoryImpl implements PointCustomWriterRepository{
	
	@Autowired
	private EntityManager entityManager;
	
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
		
		/*
		 * Version이 명시되어 있으므로 트랜잭션 동안 버전관리를 진행한다.
		 * */
		//point entity
		PointDTO pointEntity = new PointDTO.PointBuilder(pointReaderRepository.findByUserId(pointDTO.getUserId()))
										.setChargedPointBuilder(pointDTO.getPoint())
										.build();
		
		
//		PointDTO pointEntity = entityManager.find(new PointDTO.PointBuilder(pointReaderRepository.findByUserId(pointDTO.getUserId()))
//															  .setChargedPointBuilder(pointDTO.getPoint())
//															  .build().getClass()
//												  , pointDTO.getUserId()
//												  , LockModeType.PESSIMISTIC_WRITE
//												  );
		
		
		//point entity -> user entity
		User userEntity = UserMapper.toUserEntityFromPointDomain(pointEntity);
		
		/*
		 * 데이터를 수정하는 시점에서 버전 변경을 감지할 경우 Exception을 발생하여
		 * 최초 트랜잭션만 성공, 나머지 트랜잭션은 모두 실패로 처리한다.
		 * */
		//charge
		userWriterRepository.save(userEntity);
		
		/*
		 * 이 부분은 동시성 제어 대상이 아님
		 * 다만 Transactional 어노테이션에 의해 최종적으로 commit 후 lock까지 모두 풀림
		 * */
		//point entity -> user history entity
		UserHistory userHistoryEntity = UserMapper.toUserHistoryEntityFromPointDomain(pointDTO, TransactionType.CHARGE.toString());
		
		//insert
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
