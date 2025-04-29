package kr.hhplus.be.server.point.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.SystemException;
import jakarta.transaction.TransactionManager;
import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.point.infra.redis.LettuceProvider;
import lombok.extern.slf4j.Slf4j;

/*
 * 기존 구현하였던 서비스의 Transactional 어노테이션 등
 * 환경설정, 계층구조 등을 바꾸지 않고 Redis 도메인과 Service 도메인을 별도로 두어 Facade화 하여 서비스 조합의 관점으로 구축
 * */
@Service
@Slf4j
public class PointFacadeWriterService {
	
	/*
	 * 프록시를 빈형태로 만들어준 포인트 서비스 프록시를 호출해야 트랜잭션이 호출됨
	 * 즉 Transactional을 적용하기 위해선 pointWriterService를 호출해야 하며
	 * 이를 Lock이 더 우선적(상위계층에서)으로 트랜잭션 서비스를 Wrap하여 모든 트랜잭션의 원자성을 보장한다.
	 * */
	
	@Autowired
	LettuceProvider lettuceProvider;
	
	@Autowired
	PointWriterService pointWriterService;
	
	@Autowired
	TransactionManager transactionManager;
	
	public void charge(PointDTO pointDTO) {
		/*
		 * redis에서 userId 유무를 확인하여
		 * 있으면 트랜잭션을 중단합니다.
		 * */
		if(lettuceProvider.lock(pointDTO.getUserId())) {
			log.info("lock이 있다면 트랜잭션을 아예 중단합니다.");
			try {
				transactionManager.rollback();
			} catch (IllegalStateException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SecurityException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SystemException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else {
			/*
			 * 없을 경우에 트랜잭션을 진행합니다.
			 * */
			try {
				/*
				 * 트랜잭션 전에 분산락 설정
				 * */
				lettuceProvider.lock(pointDTO.getUserId());
				pointWriterService.charge(pointDTO);
			} finally {
				/*
				 * 트랜잭션 진행 후 분산락 해제
				 * */
				lettuceProvider.unlock(pointDTO.getUserId());
			}
		}
		
	}
}
