package kr.hhplus.be.serve.point.domain.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import annotation.UnitTest;
import kr.hhplus.be.server.point.domain.model.PointDTO;

@UnitTest
public class PointDTOUnitTest {
	
	@Test
	@DisplayName("[단위테스트] 포인트 정책(10000포인트 이상 충전 불가)에 위반한 포인트 객체를 생성할 수 없다.")
	void couldNotOver10000PointBeCreatedTest() {
		/*
		 * given
		 * - 테스트에 사용할 변수 및 입력값을 정의한다.
		 * - 동작을 확인하기 위한 Mokito 정의도 포함(Database(Repository)의 객체를 Mokito화하여 사용)
		 * */
		long userId = 1L;
		long chargePoint = 15000L;
		
		/*
		 * when
		 * - 실제 동작이 이루어진다.
		 * - 동작에 따른 상태 변화를 기억하거나, 대조군으로 활용하기 위한 과정이다.
		 * - 검증 대상의 동작 하나만 기술한다.
		 * */
		
		/*
		 * Then
		 * - 최종적으로 테스트를 검증한다.
		 * - 테스트 과정을 종합한다.
		 * */
		Assertions.assertThrows(Exception.class, ()->{
			PointDTO.standardPointDTOOf(userId, chargePoint);
		});
	}
	
	@Test
	@DisplayName("[단위테스트] 포인트 정책에 위반한 포인트 객체를 생성할 수 없다.")
	void couldNotlessThan0PointBeCreatedTest() {
		/*
		 * given
		 * - 테스트에 사용할 변수 및 입력값을 정의한다.
		 * - 동작을 확인하기 위한 Mokito 정의도 포함(Database(Repository)의 객체를 Mokito화하여 사용)
		 * */
		long userId = 1L;
		long chargePoint = -1000;
		
		/*
		 * when
		 * - 실제 동작이 이루어진다.
		 * - 동작에 따른 상태 변화를 기억하거나, 대조군으로 활용하기 위한 과정이다.
		 * - 검증 대상의 동작 하나만 기술한다.
		 * */
		
		/*
		 * Then
		 * - 최종적으로 테스트를 검증한다.
		 * - 테스트 과정을 종합한다.
		 * */
		Assertions.assertThrows(Exception.class, ()->{
			PointDTO.standardPointDTOOf(userId, chargePoint);
		});
	}
}
