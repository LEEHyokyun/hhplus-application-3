package kr.hhplus.be.server.point.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.point.infra.jpa.PointReaderRepository;

public class PointReaderService {
	
	@Autowired
	PointReaderRepository pointReaderRepository;
	
	@Transactional(readOnly = true)
	public PointDTO search(Long userId) {
		return pointReaderRepository.findByUserId(userId);
	}
}
