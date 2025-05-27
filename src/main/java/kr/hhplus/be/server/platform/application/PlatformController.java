package kr.hhplus.be.server.platform.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.platform.model.PlatformDTO;
import kr.hhplus.be.server.platform.model.PlatformResponseDTO;
import kr.hhplus.be.server.platform.service.PlatformSenderService;

@RestController
@RequestMapping("/platform")
public class PlatformController {
	
	@Autowired
	PlatformSenderService platformSenderService;
	
	/*
	@PostMapping("/platform")
	PlatformResponseDTO<PlatformDTO> sendData(@RequestBody PlatformDTO platformDTO){
		platformSenderService.sendData(platformDTO);
		return new PlatformResponseDTO<PlatformDTO>(HttpStatus.OK, "OK", platformDTO);
	}
	*/
	
	/*
	 * 컨슈머 메시지 발행이 정상적으로 이루어질 수 있도록
	 * 전달받는 메시지 형태를 String 형태로 구성합니다.
	 * */
	@PostMapping("/platform")
	PlatformResponseDTO<String> sendData(String orderId){
		platformSenderService.sendData(orderId);
		return new PlatformResponseDTO<String>(HttpStatus.OK, "OK", orderId);
	}
}
