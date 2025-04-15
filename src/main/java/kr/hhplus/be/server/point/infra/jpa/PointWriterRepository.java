package kr.hhplus.be.server.point.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.point.domain.model.PointDTO;

@Repository
public interface PointWriterRepository extends JpaRepository<PointDTO, Long>, PointCustomWriterRepository{
	/*
	 * point domain에서의 charge, use는 내부 로직에는 관심이 없다.
	 * 자신들의 도메인에 부합하는 DTO만 넘겨주기만 하면 된다.
	 * */
}
