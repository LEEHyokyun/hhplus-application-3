package kr.hhplus.be.server.config.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import kr.hhplus.be.server.common.topic.PlatformEnum;
import lombok.RequiredArgsConstructor;

/*
 * 카프카 프로듀서
 * 메시지를 발행하기 위해 전역적인 infra로 구성하여 활용합니다.
 * */
@Component
@RequiredArgsConstructor
public class KafkaProducer {
	/*
	 * producer 서비스가 메시지를 발행할때 사용하는 메소드를 정의합니다.
	 * 전역적으로 활용할 수 있기에 config 도메인에서 구성해주는게 좋습니다.
	 * */
	private final KafkaTemplate<String, String> kafkaTemplate;
	
	/*
	 * 외부 데이터 플랫폼에 전송하기 위한 컨슈머와
	 * 이를 위해 설정한 토픽을 기반으로
	 * 메시지 발행합니다.
	 * */
	public void publish(String message) {
		kafkaTemplate.send(PlatformEnum.PLATFORM.topic(), message);
	}
}
