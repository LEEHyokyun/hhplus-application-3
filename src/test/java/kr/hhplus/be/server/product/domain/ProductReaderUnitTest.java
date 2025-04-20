package kr.hhplus.be.server.product.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import annotation.UnitTest;
import kr.hhplus.be.server.point.domain.service.PointReaderService;
import kr.hhplus.be.server.product.domain.service.ProductReaderService;

@UnitTest
public class ProductReaderUnitTest {
	@Autowired
	private ProductReaderService productReaderService;
	
	@Test
	@DisplayName("[단위테스트] 상품 세부내용을 조회한다.")
	void productSearchTest() {
		/*
		 * given
		 * - 테스트에 사용할 변수 및 입력값을 정의한다.
		 * - 동작을 확인하기 위한 Mokito 정의도 포함(Database(Repository)의 객체를 Mokito화하여 사용)
		 * */
		long productId = 1L;
		String expectedProductName = "NIKE";
		
		/*
		 * when
		 * - 실제 동작이 이루어진다.
		 * - 동작에 따른 상태 변화를 기억하거나, 대조군으로 활용하기 위한 과정이다.
		 * - 검증 대상의 동작 하나만 기술한다.
		 * */
		String actualProductName = productReaderService.search(productId).getProductName();
		
		/*
		 * Then
		 * - 최종적으로 테스트를 검증한다.
		 * - 테스트 과정을 종합한다.
		 * */
		assertEquals(expectedProductName, actualProductName);
	}
	
	@Test
	@DisplayName("[단위테스트] 인기상품(최근 3일 내 상위 5품목) 세부내용을 조회한다.")
	void hotSaledProductSearchTest() {
		/*
		 * given
		 * - 테스트에 사용할 변수 및 입력값을 정의한다.
		 * - 동작을 확인하기 위한 Mokito 정의도 포함(Database(Repository)의 객체를 Mokito화하여 사용)
		 * */
		int expectedSize = 5;
		
		/*
		 * when
		 * - 실제 동작이 이루어진다.
		 * - 동작에 따른 상태 변화를 기억하거나, 대조군으로 활용하기 위한 과정이다.
		 * - 검증 대상의 동작 하나만 기술한다.
		 * */
		int actualSize = productReaderService.searchHotSaled().size();
		
		/*
		 * Then
		 * - 최종적으로 테스트를 검증한다.
		 * - 테스트 과정을 종합한다.
		 * */
		assertEquals(expectedSize, actualSize);
	}
}
