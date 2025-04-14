package kr.hhplus.be.server.point.domain.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class PointDTO {
	/*
	 * 1. test에 기반하여 Point 도메인에서 필요한 식별자 및 객체 속성을 정의한다.
	 * 2. 그 이후 필요한 변환 mapper가 필요하다면 
	 * 3. 정적 팩토리 메소드를 사용하여 상황에 맞는 도메인 객체를 생성하도록 한다.
	 * */
	private Long userId;
	private Long point;
	
	/*
	 * DTO 생성자 정의
	 * */
	private PointDTO(Long userId, Long point) {
        this.userId = userId;
        this.point = point;
    }
	
	/*
	 * DTO를 정적으로 생성하여 팩토리 메소드의 장점을 활용하기 위한 팩토리 패턴
	 * */
	public static PointDTO standardPointDTOOf(Long userId, Long point) throws Exception {
		
		/*
		 * 10000 point 이상은 정책 위반
		 * Exception 발생
		 * */
		if(point > 10000)
			throw new Exception();
		
		if(point < 0)
			throw new Exception();
		
		return new PointDTO(userId, point);
	}
	
	public void getTest() {
		
	}
}
