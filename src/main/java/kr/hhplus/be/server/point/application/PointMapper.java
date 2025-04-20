package kr.hhplus.be.server.point.application;

import java.sql.Timestamp;

import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.user.domain.entity.User;

public class PointMapper {
	/*
	 * User 영속성 계층과 다른 속성들을
	 * Point 도메인에 맞게 변환해주는 Mapper
	 * Point 도메인에서 필요로 하는 속성 변환이므로 Point에게 그 책임이 존재한다.
	 * */
	public static PointDTO toPointDomainFromUserEntity(User user) throws Exception {
		return PointDTO.standardPointDTOOf(user.getUserId(), user.getPoint());
	}
}
