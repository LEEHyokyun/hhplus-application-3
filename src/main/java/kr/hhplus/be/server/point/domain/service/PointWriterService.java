package kr.hhplus.be.server.point.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.point.infra.jpa.PointWriterRepository;

@Service
public class PointWriterService {

	@Autowired
	private PointWriterRepository pointWriterRepository;
	
	@Transactional
	public void charge(PointDTO pointDTO) {
		pointWriterRepository.charge(pointDTO);
	}
	
	@Transactional
	public void use(PointDTO pointDTO) {
		pointWriterRepository.use(pointDTO);
	}
}
