package kr.hhplus.be.server.platform.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.order.infra.jpa.OrderReaderRepository;
import kr.hhplus.be.server.platform.model.PlatformDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PlatformSenderService {
	
	@Autowired
	private OrderReaderRepository orderReaderRepository;
	/*
	 * 프로듀서 측에서 발행한 메시지 타입과
	 * 컨슈머 측에서 전달받는 메시지 타입은 기본적으로 일치해야 합니다.
	 * 따라서 문자열을 메시지로 주었다면 마찬가지로 문자열 형태로 받아야 합니다.
	 * DTO로 받게되면 동작하지 않습니다.
	 * 
	 * ** 더 구체적인 메시지 발행 시 "RECORD"를 활용합니다.
	 * */
	
	/*
	public void sendData(OrderDTO orderDTO) {
		log.info("*********************");
		log.info("데이터 외부 전송 mock API");
		log.info("*********************");
	}
	*/
	
	/*
	 * platform topic으로 발행되어
	 * platform consumer group에 메시지를 발행한 프로듀서에 반응하는 리스너입니다.
	 * 
	 * */
	/*
	@KafkaListener(topics="platform", groupId="platform")
	public void sendData(String orderId) {
		log.info("*********************");
		log.info("데이터 외부 전송 mock API");
		log.info("주문 정보를 전송합니다 : {}", orderId);
		log.info("*********************");
	}
	*/
	
	/*
	 * 기존 단순 문자열 메시지를 받는 과정에서
	 * record 형태로 메시지를 받도록 하여 동적인 형태로 전달받도록 구성합니다.
	 * "consume"하는 형태와 "실제 처리 부분을 분리하여 작성"
	 * 
	 * */
	@KafkaListener(topics="platform", groupId="platform")
	public void sendData(String orderId) {
		log.info("*********************");
		log.info("데이터 외부 전송 mock API");
		log.info("주문 정보를 전송합니다 : {}", orderId);
		log.info("*********************");
	}
}
