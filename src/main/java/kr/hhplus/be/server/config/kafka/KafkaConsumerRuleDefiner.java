package kr.hhplus.be.server.config.kafka;

import java.lang.management.PlatformLoggingMXBean;

import org.apache.kafka.common.TopicPartition;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.listener.CommonErrorHandler;
import org.springframework.kafka.listener.DeadLetterPublishingRecoverer;
import org.springframework.kafka.listener.DefaultErrorHandler;
import org.springframework.stereotype.Component;
import org.springframework.util.backoff.FixedBackOff;

import kr.hhplus.be.server.common.topic.PlatformEnum;
import lombok.AllArgsConstructor;

/*
 * offset 유실방지 대책 : 재시도 횟수 제한 및 실패 메시지의 DLQ 처리
 * ErrorHadnler와 DLQ 등
 * 메시지 발행 실패 후 재시도 및 메시지 발행 재시도 최대 횟수 제한 등
 * Consumer의 정책을 
 * Spring 2.5버전 이전에는 setRetryTemplate을 사용하였으나 현재는 decprecated.
 * setHandlerError를 활용하여 Consumer 정책을 정의하도록 구성합니다.
 * */
@Component
@AllArgsConstructor
public class KafkaConsumerRuleDefiner {
	
	/*
	 * reference for : Listenr Container
	 * 즉 Kafka 리스너는 Rule Definer를 참조하게 되는 형태
	 * Listner Container에 정책정보와 에러 핸들링 정보가 모두 들어있습니다.
	 * */
	
	/*
	 * Spring은 애플리케이션 컨텍스트를 초기화할 때: 모든 @Bean, @Component, @Service, @Configuration 등을 스캔
	 * 필요한 순서로 의존성 주입 (Dependency Injection) 수행
 	 * 정의된 순서와 무관하게 Spring이 필요한 순서로 자동 주입을 해주기에, 빈 순서 상관없이 필요 빈을 등록하기만 하면 됩니다.
 	 * consumerFactory의 정보는 아래에서 등록한 consumerFactory 빈의 정보로 메서드 이름의 빈을 그대로 활용하면 됩니다.
	 * */
	
	/*
	 * factory 1 : kafka listener가 내부적으로 사용하는 Container Listener 정보가 담겨져 있는 정보
	 * */
	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, String> kafkaListenerContainerFactory(
	        DefaultKafkaConsumerFactory<String, String> consumerFactory,
	        KafkaTemplate<String, String> kafkaTemplate
	) {
	    ConcurrentKafkaListenerContainerFactory<String, String> factory =
	            new ConcurrentKafkaListenerContainerFactory<>();
	    
	    //kafka consumer 정보가 담겨져있는 정보
	    factory.setConsumerFactory(consumerFactory);

	    // 메시지 발행이 실패했을 경우에 대한 정책 정보 반영
	    factory.setCommonErrorHandler(this.commonErrorHandler(kafkaTemplate));
	    return factory;
	}
	
	/*
	 * factory 2 : kafka consumer의 기본정보가 담겨져있는 정보
	 * reference for : application.yml
	 * */
	@Bean
	public DefaultKafkaConsumerFactory<String, String> consumerFactory(KafkaProperties kafkaProperties) {
	    return new DefaultKafkaConsumerFactory<>(kafkaProperties.buildConsumerProperties());
	}
	
	/*
	 * factory 3 : Error Handling(*메시지 커밋 실패시에 대한 정책 정보)
	 * */
	@Bean
	public CommonErrorHandler commonErrorHandler(KafkaTemplate<String, String> kafkaTemplate) {
	    /* 
		 * 3회 재시도 후 DLQ topic으로 전송하여 커밋하지 않은 메시지들을 쌓아놓고 이후에 처리하도록 설정합니다.
		 */
	    var recoverer = new DeadLetterPublishingRecoverer(kafkaTemplate,
	            (record, ex) -> new TopicPartition(PlatformEnum.DLQ.topic(), record.partition()));

	    var backOff = new FixedBackOff(60000L, 3L); // 1분 간격, 재시도 최대 횟수 3회로 제한

	    return new DefaultErrorHandler(recoverer, backOff);
	}
	
}
