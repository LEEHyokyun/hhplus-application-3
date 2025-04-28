package kr.hhplus.be.server.point.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
	
	public void charge(PointDTO pointDTO) {
		/*
		 * redis에서 userId 유무를 확인하여
		 * 있으면 3초동안 계속 요청하고, 없으면 포인트를 충전한다.
		 * */
		while(!lettuceProvider.lock(pointDTO.getUserId())) {
			log.info("spin lock은 주기적으로 redis에 요청합니다. redis 부하를 줄일 수 있는 방안 중 하나입니다.");
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		try {
			pointWriterService.charge(pointDTO);
		} finally {
			lettuceProvider.unlock(pointDTO.getUserId());
		}
	}
}
