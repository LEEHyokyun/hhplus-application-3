package kr.hhplus.be.server.point.domain.service.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import annotation.UnitTest;
import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.point.domain.service.PointWriterService;
import kr.hhplus.be.server.point.infra.jpa.PointReaderRepository;

@UnitTest
public class PointConcurrencyUnitTest {
	
	private static final int MAX_THREAD = 5;
	
	@Autowired
	PointWriterService pointWriterService;
	
	@Autowired
	PointReaderRepository pointReaderRepository;
	
	@Test
	@DisplayName("[단위테스트] 동시에 한명의 사용자가 여러번 포인트 결제한다.")
	void useTest() throws Exception {
		
		/*
		 * given
		 * - 테스트에 사용할 변수 및 입력값을 정의한다.
		 * - 동작을 확인하기 위한 Mokito 정의도 포함(Database(Repository)의 객체를 Mokito화하여 사용)
		 * */
		CountDownLatch doneSignal = new CountDownLatch(MAX_THREAD);
	    ExecutorService executorService = Executors.newFixedThreadPool(MAX_THREAD);
	    AtomicInteger successCount = new AtomicInteger();
	    
		long userId = 1L;
		long originalPoint = 25L;
		long usePoint = 5L;
		long expectedPoint = 0L;
		
		/*
		 * when
		 * - 실제 동작이 이루어진다.
		 * - 동작에 따른 상태 변화를 기억하거나, 대조군으로 활용하기 위한 과정이다.
		 * - 검증 대상의 동작 하나만 기술한다.
		 * */
		for (int i = 0; i < MAX_THREAD; i++) {
            executorService.execute(() -> {
                try {
                	//서비스 동작에 대한 확인
                    successCount.getAndIncrement();
                    pointWriterService.charge(PointDTO.standardPointDTOOf(userId, 5L));
                } catch(Exception e){
                	
                }finally {
                	//Thread 실행 횟수 확인
                    doneSignal.countDown();
                }
		
		/*
		 * Then
		 * - 최종적으로 테스트를 검증한다.
		 * - 테스트 과정을 종합한다.
		 * */
        Long actualPoint = pointReaderRepository.findByUserId(userId).getPoint();
		assertEquals(expectedPoint, actualPoint);
        });
	}
  }
}
