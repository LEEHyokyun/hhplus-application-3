package kr.hhplus.be.server.order.application;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.order.domain.model.OrderDTO;
import kr.hhplus.be.server.order.domain.model.OrderResponseDTO;
import kr.hhplus.be.server.order.domain.service.OrderWriterFacadeService;
import kr.hhplus.be.server.order.domain.service.OrderWriterService;

@RestController
@RequestMapping("/order")
public class OrderController {
	
	@Autowired
	OrderWriterService orderWriterService;
	
	@Autowired
	OrderWriterFacadeService orderWriterFacadeService;
	
	@PostMapping("/order")
	OrderResponseDTO<OrderDTO> order(@RequestBody OrderDTO orderDTO){
		orderWriterService.order(orderDTO);
		return new OrderResponseDTO<OrderDTO>(HttpStatus.OK, "OK", orderDTO);
	}
	
	@PostMapping("/orderpay")
	OrderResponseDTO<OrderDTO> orderpay(@RequestBody OrderDTO orderDTO) throws Exception{
		orderWriterFacadeService.orderPay(orderDTO);
		return new OrderResponseDTO<OrderDTO>(HttpStatus.OK, "OK", orderDTO);
	}
	
}
