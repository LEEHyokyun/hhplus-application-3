package kr.hhplus.be.server.point.infra.jpa;

import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.point.domain.model.PointDTO;

@Repository
public interface PointCustomWriterRepository {
	
	void charge(PointDTO pointDTO);
	
	void use(PointDTO pointDTO);
}
