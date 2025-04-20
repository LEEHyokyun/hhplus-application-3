package kr.hhplus.be.server.order.application;

import java.sql.Timestamp;

import javax.sound.midi.Patch;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mysql.cj.x.protobuf.MysqlxCursor.Fetch;

import jakarta.transaction.Transactional;
import kr.hhplus.be.server.order.domain.entity.Order;
import kr.hhplus.be.server.order.domain.model.OrderDTO;
import kr.hhplus.be.server.order.domain.model.OrderResponseDTO;
import kr.hhplus.be.server.order.domain.service.OrderWriterService;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class OrderWriterIntegrationTest {
		
		/*
		 * 통합 테스트를 위해 mock mvc 주입
		 * */
	 	@Autowired  
	    private MockMvc mockMvc;  

	    @MockBean  
	    private OrderWriterService orderWriterService; 


	    @DisplayName("[주문결제 통합테스트] 주문결제를 요청하면 정상적인 트랜잭션 수행 및 성공 응답을 반환한다")  
	    @Test  
	    void ifUserCreateRecipeShouldComplete() throws Exception {  

	        // Given  
	        OrderDTO orderDTO = this.orderDTO();
	        Order orderEntity = Order.standardOrderEntityOf(orderDTO.getOrderId(), orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getOrderQuantity(), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
	         
	        // Then  
	        given(orderWriterService.order(orderDTO)).willReturn(OrderResponseDTO.builder());  

	        // Then  
	        mockMvc.perform(post("/order/orderpay"))  
	                        .contentType(MediaType.APPLICATION_JSON)  
	                        .content(new ObjectMapper().writeValueAsString(orderDTO)))  
	                .andExpect(HttpStatus.OK); // 응답 상태 코드가 200 OK인지 확인한다.  
	        
	    }  
	    
	    //orderDTO stub
	    private OrderDTO orderDTO() {  
	        return OrderDTO.standardOrderDTOOf(1L, 1L, 1L, 1000L);  
	    }  

}
