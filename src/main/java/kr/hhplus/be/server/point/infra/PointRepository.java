package kr.hhplus.be.server.point.infra;

import kr.hhplus.be.server.point.domain.model.PointDTO;

public interface PointRepository {
	/*
	 * point domain에서의 charge, use는 내부 로직에는 관심이 없다.
	 * 자신들의 도메인에 부합하는 DTO만 넘겨주기만 하면 된다.
	 * */
	PointDTO charge(PointDTO pointDTO) throws Exception;
}
