package kr.hhplus.be.server.point.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.point.domain.model.PointDTO;
import kr.hhplus.be.server.point.domain.model.PointResponseDTO;
import kr.hhplus.be.server.point.domain.service.PointFacadeWriterService;

/*
 * Redis 도메인 분리
 * 동시성 책임에 대한 이관을 위해 별도로 구성
 * */
@RestController
@RequestMapping("/point/redis")
public class PointRedisController {
	
	@Autowired
	PointFacadeWriterService pointFacadeService;
	
	@PostMapping("/charge")
	PointResponseDTO<PointDTO> charge(@RequestBody PointDTO pointDTO){
		pointFacadeService.charge(pointDTO);
		return new PointResponseDTO<PointDTO>(HttpStatus.OK, "OK", pointDTO);
	}
}
