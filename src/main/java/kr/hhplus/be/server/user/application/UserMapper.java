package kr.hhplus.be.server.user.application;

import java.sql.Timestamp;

import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.user.domain.entity.User;
import kr.hhplus.be.server.user.domain.entity.UserHistory;

/*
 * 상태변환매개체 : VO의 범주
 * */
public class UserMapper {
	
	/*
	 * Point 도메인 모델 계층과 다른 속성들을
	 * User 영속성 계층에 맞게 변환해주는 Mapper
	 * User 영속성 계층에서 필요로 하는 속성 변환이므로 User에게 그 책임이 존재한다.
	 * */
	public static User toUserEntityFromPointDomain(PointDTO pointDTO) {
		return User.standardUserEntityOf(pointDTO.getUserId(), "", pointDTO.getPoint(), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
	}
	
	public static UserHistory toUserHistoryEntityFromPointDomain(PointDTO pointDTO, String transactionType) {
		return UserHistory.standardUserHistoryEntityOf(pointDTO.getUserId(), "", pointDTO.getPoint(), transactionType, Timestamp.valueOf(String.valueOf(System.currentTimeMillis())), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
	}
}
