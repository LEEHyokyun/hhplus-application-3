package kr.hhplus.be.server.point.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.point.infra.PointRepository;

@Service
public class PointService {

	@Autowired
	private PointRepository pointRepository;
	
	public PointDTO charge(Long userId, Long chargePoint) throws Exception {
		
		return pointRepository.charge(PointDTO.standardPointDTOOf(userId, chargePoint));
	}
	
}
