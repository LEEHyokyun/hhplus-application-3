package kr.hhplus.be.server.point.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.point.domain.model.PointResponseDTO;
import kr.hhplus.be.server.point.domain.service.PointReaderService;
import kr.hhplus.be.server.point.domain.service.PointWriterService;

@RestController
@RequestMapping("/point")
public class PointController {
	
	@Autowired
	PointWriterService pointWriterService;
	
	@Autowired
	PointReaderService pointReaderService;
	
	@GetMapping("/search/{userId}")
	PointDTO search(@PathVariable Long userId) {
		return pointReaderService.search(userId);
	}
	
	@PostMapping("/charge")
	PointResponseDTO<PointDTO> charge(@RequestBody PointDTO pointDTO) {
		pointWriterService.charge(pointDTO);
		return new PointResponseDTO<PointDTO>(HttpStatus.OK, "OK", pointDTO);
	}
	
	@PostMapping("/use")
	PointResponseDTO<PointDTO> use(@RequestBody PointDTO pointDTO) {
		pointWriterService.use(pointDTO);
		return new PointResponseDTO<PointDTO>(HttpStatus.OK, "OK", pointDTO);
	}
}
