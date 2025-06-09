package kr.hhplus.be.server.platform.domain.service.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import annotation.UnitTest;
import kr.hhplus.be.server.platform.model.PlatformDTO;
import kr.hhplus.be.server.platform.service.PlatformSenderService;

@UnitTest
public class PlatformSenderUnitTest {
	@Autowired
	private PlatformSenderService platformSenderService;
	
	@Test
	@DisplayName("[단위테스트] 데이터 전송한다.")
	void pointSearchTest() {
		/*
		 * given
		 * - 테스트에 사용할 변수 및 입력값을 정의한다.
		 * - 동작을 확인하기 위한 Mokito 정의도 포함(Database(Repository)의 객체를 Mokito화하여 사용)
		 * */
		//PlatformDTO platformDTO = new PlatformDTO();
		long orderId = 1L;
		
		/*
		 * when
		 * - 실제 동작이 이루어진다.
		 * - 동작에 따른 상태 변화를 기억하거나, 대조군으로 활용하기 위한 과정이다.
		 * - 검증 대상의 동작 하나만 기술한다.
		 * */
		platformSenderService.sendData(String.valueOf(orderId));
		
		/*
		 * Then
		 * - 최종적으로 테스트를 검증한다.
		 * - 테스트 과정을 종합한다.
		 * */
		verify(platformSenderService, atLeastOnce());
	}
}
