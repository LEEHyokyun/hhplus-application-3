package kr.hhplus.be.server.order.application;

import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;

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
import kr.hhplus.be.server.order.domain.redis.OrderRedisTemplateProvider;
import kr.hhplus.be.server.order.domain.service.OrderWriterFacadeService;
import kr.hhplus.be.server.order.domain.service.OrderWriterService;
import kr.hhplus.be.server.platform.service.PlatformSenderService;

@SpringBootTest
@Transactional
public class OrderWriterIntegrationTest {
		
		/*
		 * 통합 테스트를 위해 mock mvc 주입
		 * */
	 	@Autowired  
	    private MockMvc mockMvc;  

	    @Autowired
	    private OrderWriterFacadeService orderWriterFacadeService;
	    
	    @Autowired
		private OrderRedisTemplateProvider orderRedisTemplateProvider;
	    
	    @Autowired
	    private PlatformSenderService platformSenderService;

	    @DisplayName("[주문결제 통합테스트] 주문결제 파사드 서비스를 호출(실제 컨텍스트 호출)시 이벤트리스너에 따라 Redis와 Kafka 후행 동작을 진행한다.")  
	    @Test  
	    void ifUserCreateRecipeShouldComplete() throws Exception {  

	        // Given  
	        OrderDTO orderDTO = this.orderDTO();
	        //Order orderEntity = Order.standardOrderEntityOf(orderDTO.getOrderId(), orderDTO.getProductId(), orderDTO.getUserId(), orderDTO.getOrderQuantity(), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())), Timestamp.valueOf(String.valueOf(System.currentTimeMillis())));
	         
	        // Then  
	        orderWriterFacadeService.orderPay(orderDTO);

	        // Then  
	        verify(orderRedisTemplateProvider, atLeastOnce());
	        verify(platformSenderService, atLeastOnce());
	        
	    }  
	    
	    //orderDTO stub
	    private OrderDTO orderDTO() {  
	        return OrderDTO.standardOrderDTOOf(1L, 1L, 1L, 1000L);  
	    }  

}
