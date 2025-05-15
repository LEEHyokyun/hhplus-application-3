package kr.hhplus.be.server.product.domain.redis;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import annotation.UnitTest;

@UnitTest
public class ProductRedisTemplateProviderUnitTest {
	
	@Autowired
	ProductRedisTemplateProvider productRedisTemplateProvider;
	
	@Test
	@DisplayName("[단위테스트] Redis와의 정상적인 통신을 확인하며 인기상품 5개르 조회해온다.")
	void hotSaledProductSearchTest() {
		/*
		 * given
		 * - 테스트에 사용할 변수 및 입력값을 정의한다.
		 * - 동작을 확인하기 위한 Mokito 정의도 포함(Database(Repository)의 객체를 Mokito화하여 사용)
		 * */
		long expectedSize = 5L;
		
		/*
		 * when
		 * - 실제 동작이 이루어진다.
		 * - 동작에 따른 상태 변화를 기억하거나, 대조군으로 활용하기 위한 과정이다.
		 * - 검증 대상의 동작 하나만 기술한다.
		 * */
		long actualSize = productRedisTemplateProvider.getHotSaledProductIds().size();
		
		/*
		 * Then
		 * - 최종적으로 테스트를 검증한다.
		 * - 테스트 과정을 종합한다.
		 * */
		assertEquals(expectedSize, actualSize);
	}
}
