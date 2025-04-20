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
	 * 빌더패턴을 위한 DTO 생성자 정의
	 * */
	private PointDTO(PointBuilder builder) {
        this.userId = builder.userId;
        this.point = builder.point;
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
	
	/*
	 * OCP
	 * DTO의 의미를 최대한 유지할 수 있도록
	 * 또한 일부 속성 수정에 대해
	 * 빌더패턴을 활용하여 포인트 충전에 대한 DTO를 제공
	 * */
	//Builder Class for Builder Pattern
    public static class PointBuilder{
 
        // required parameters
        private Long userId;
        private Long point;
 		
        //basic Builder
        public PointBuilder(Long userId, Long point){
            this.userId = userId;
            this.point = point;
        }
        
        //mapper Builder
        public PointBuilder(PointDTO pointDTO){
            this.userId = pointDTO.userId;
            this.point = pointDTO.point;
        }
        
        public PointBuilder setChargedPointBuilder(Long chargePoint) {
            this.point = this.point + chargePoint;
            return this;
        }
		
        public PointBuilder setUsedPointBuilder(Long usedPoint) {
            this.point = this.point - usedPoint;
            return this;
        }
        
        public PointDTO build(){
            return new PointDTO(this);
        }
 
    }
}
