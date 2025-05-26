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
	
	@PostMapping("/platform")
	PlatformResponseDTO<PlatformDTO> sendData(@RequestBody PlatformDTO platformDTO){
		platformSenderService.sendData(platformDTO);
		return new PlatformResponseDTO<PlatformDTO>(HttpStatus.OK, "OK", platformDTO);
	}
	
}
