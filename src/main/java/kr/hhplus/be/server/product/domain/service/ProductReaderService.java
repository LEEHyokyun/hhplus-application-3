package kr.hhplus.be.server.product.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.product.domain.model.ProductDTO;
import kr.hhplus.be.server.product.infra.jpa.ProductReaderRepository;
import kr.hhplus.be.server.product.infra.mybatis.ProductReaderMapper;

@Service
@Transactional(readOnly = true)
public class ProductReaderService {
	@Autowired
	ProductReaderRepository productReaderRepository;
	
	@Autowired
	ProductReaderMapper productReaderMapper;
	
	public ProductDTO search(Long productId) {
		return productReaderRepository.findByProductId(productId);
	}
	
	public List<ProductDTO> searchHotSaled(){
		return productReaderMapper.searchHotSaled();
	}
	
}
