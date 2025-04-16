package kr.hhplus.be.server.product.application;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kr.hhplus.be.server.product.domain.model.ProductDTO;
import kr.hhplus.be.server.product.domain.service.ProductReaderService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	ProductReaderService productReaderService;
	
	@GetMapping("/search/{productId}")
	ProductDTO search(@PathVariable Long productId) {
		return productReaderService.search(productId);
	}
	
	@GetMapping("/search/hot")
	List<ProductDTO> searchHotSaled(){
		return productReaderService.searchHotSaled();
	}
}
