package kr.hhplus.be.server.order.domain.model;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponseDTO<T> {
	private HttpStatus status;
    private String message;
    private T data;	
}
