package annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@ExtendWith(MockitoExtension.class)
public @interface UnitTest {
	/*
	 * UNIT TEST 어노테이션
	 * - 어노테이션 타입 : TYPE
	 * - 어노테이션 실행 단계 : RUNTIME
	 * - 테스트 확장 기능 : Mokito
	 * */
}
