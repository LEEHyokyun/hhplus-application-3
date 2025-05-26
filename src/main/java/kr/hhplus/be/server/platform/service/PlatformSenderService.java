package kr.hhplus.be.server.platform.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.platform.model.PlatformDTO;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional
@Slf4j
public class PlatformSenderService {
	
	@Autowired
	private PlatformSenderService platformSenderService;
	
	public void sendData(PlatformDTO platformDTO) {
		log.info("*********************");
		log.info("데이터 외부 전송 mock API");
		log.info("*********************");
	}
}
