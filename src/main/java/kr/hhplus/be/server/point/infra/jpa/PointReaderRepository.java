package kr.hhplus.be.server.point.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.point.domain.model.PointDTO;

@Repository
public interface PointReaderRepository extends JpaRepository<PointDTO, Long>, PointCustomReaderRepository{
	
	PointDTO findByUserId(Long userId);
	
}
