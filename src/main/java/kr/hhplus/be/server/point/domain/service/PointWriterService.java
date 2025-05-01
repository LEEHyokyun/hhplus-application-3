package kr.hhplus.be.server.point.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.point.infra.jpa.PointWriterRepository;
import kr.hhplus.be.server.point.interfaces.DistributedPointLock;

@Service
public class PointWriterService {

	@Autowired
	private PointWriterRepository pointWriterRepository;
	
	/*
	 * 분산락 AOP를 활용한 구현
	 * 이때 내부 Around Advice 및 order로 인해 가장 먼저 분산락 AOP가 실행되며 그 후 트랜잭션 진행합니다.
	 * */
	@DistributedPointLock(key = "#pointDTO.getUserId()")
	@Transactional
	public void charge(PointDTO pointDTO) {
		pointWriterRepository.charge(pointDTO);
	}
	
	@Transactional
	public void use(PointDTO pointDTO) {
		pointWriterRepository.use(pointDTO);
	}
}
