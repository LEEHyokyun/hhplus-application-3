package kr.hhplus.be.server.point.domain.service.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import annotation.UnitTest;
import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.point.domain.service.PointWriterService;
import kr.hhplus.be.server.point.infra.jpa.PointReaderRepository;

@UnitTest
public class PointWriterUnitTest {
	
	@Autowired
	PointWriterService pointService;
	
	@Autowired
	PointReaderRepository pointReaderRepository;
	
	/*
	 * point와 pointhistory 도메인은 서로 분리하지 않고 동일시
	 * 이에 따라 단위 테스트 범위로 간주
	 * */
	
	@Test
	@DisplayName("[단위테스트] 포인트 액수만큼 충전한다.")
	void chargeTest() throws Exception {
		/*
		 * given
		 * - 테스트에 사용할 변수 및 입력값을 정의한다.
		 * - 동작을 확인하기 위한 Mokito 정의도 포함(Database(Repository)의 객체를 Mokito화하여 사용)
		 * */
		long userId = 1L;
		long chargePoint = 100L;
		long expectedPoint = 200L;
		
		/*
		 * when
		 * - 실제 동작이 이루어진다.
		 * - 동작에 따른 상태 변화를 기억하거나, 대조군으로 활용하기 위한 과정이다.
		 * - 검증 대상의 동작 하나만 기술한다.
		 * */
		pointService.charge(PointDTO.standardPointDTOOf(userId, chargePoint));
		long actualPoint = pointReaderRepository.findByUserId(userId).getPoint();
		
		/*
		 * Then
		 * - 최종적으로 테스트를 검증한다.
		 * - 테스트 과정을 종합한다.
		 * */
		assertEquals(expectedPoint, actualPoint);
	}
	
	@Test
	@DisplayName("[단위테스트] 포인트 액수만큼 결제한다.")
	void useTest() throws Exception {
		/*
		 * given
		 * - 테스트에 사용할 변수 및 입력값을 정의한다.
		 * - 동작을 확인하기 위한 Mokito 정의도 포함(Database(Repository)의 객체를 Mokito화하여 사용)
		 * */
		long userId = 1L;
		long originalPoint = 400L;
		long usePoint = 100L;
		long expectedPoint = 300L;
		
		/*
		 * when
		 * - 실제 동작이 이루어진다.
		 * - 동작에 따른 상태 변화를 기억하거나, 대조군으로 활용하기 위한 과정이다.
		 * - 검증 대상의 동작 하나만 기술한다.
		 * */
		pointService.use(PointDTO.standardPointDTOOf(1L, usePoint));
		long actualPoint = pointReaderRepository.findByUserId(userId).getPoint();
		
		/*
		 * Then
		 * - 최종적으로 테스트를 검증한다.
		 * - 테스트 과정을 종합한다.
		 * */
		assertEquals(expectedPoint, actualPoint);
	}
	
}
