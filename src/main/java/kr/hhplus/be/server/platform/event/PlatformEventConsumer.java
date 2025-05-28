package kr.hhplus.be.server.platform.event;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Component;

import kr.hhplus.be.server.platform.service.PlatformSenderService;
import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class PlatformEventConsumer {
	
	@Autowired
	private PlatformSenderService platformSenderService;
	
	/*
	 * platform 도메인에서 Event Consumer 컴포넌트를 별도 생성하여
	 * Kafka로부터 메시지를 Record 형태로 받아 consuming 하는 로직을 별도 생성합니다.
	 * 의존받는 서비스만 주입하여 consume - 후행로직처리를 별도의 과정으로 실행할 수 있도록 합니다.
	 * */
	@KafkaListener(topics = "platform", groupId = "platform")
	public void platformConsume(ConsumerRecord<String, String> record, Acknowledgment jointProcess) {
		
		/*
		 * platformService를 DI받습니다.
		 * */
		platformSenderService.sendData(record.value());
		
		/*
		 * 수동으로 확실히 커밋하도록 하여 offset 유실 및 누락을 방지합니다.
		 * acknowledge 하는 과정 자체가 "정상적으로 메시지를 처리하고 offset를 확실히 처리했다는 것"을 의미합니다.
		 * */
		
		jointProcess.acknowledge();
		
		/*
		 * acknowledge 커밋하기전에 오류가 발생한다면
		 * try-catch로 예외발생문을 처리하지 않아도 카프카는 자체적으로 해당 컨슈머에게 메시지를 재발행합니다.
		 * 
		 * max-poll-interval-ms: 60000의 환경설정을 해주었으므로
		 * 서비스 처리를 1분이상 지체할 경우 자동으로 메시지를 재전송합니다.
		 * */
	}
	
	
}
