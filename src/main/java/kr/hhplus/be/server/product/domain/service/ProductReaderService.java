package kr.hhplus.be.server.product.domain.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.hhplus.be.server.product.domain.entity.Product;
import kr.hhplus.be.server.product.domain.model.ProductDTO;
import kr.hhplus.be.server.product.domain.redis.ProductRedisTemplateConvertor;
import kr.hhplus.be.server.product.domain.redis.ProductRedisTemplateProvider;
import kr.hhplus.be.server.product.infra.jpa.ProductReaderRepository;
import kr.hhplus.be.server.product.infra.mybatis.ProductReaderMapper;

@Service
@Transactional(readOnly = true)
public class ProductReaderService {
	@Autowired
	ProductReaderRepository productReaderRepository;
	
	@Autowired
	ProductReaderMapper productReaderMapper;
	
	@Autowired
	ProductRedisTemplateProvider productRedisTemplateProvider;
	
	@Autowired
	ProductRedisTemplateConvertor productRedisTemplateConvertor;
	
	public ProductDTO search(Long productId) {
		return productReaderRepository.findByProductId(productId);
	}
	
	/*
	 * [ASIS]기존 RDB를 통해 조회하였던 서비스를
	 * */
	public List<ProductDTO> searchHotSaled(){
		return productReaderMapper.searchHotSaled();
	}
	
	/*
	 * [TOBE] Redis를 통해 조회해오는 서비스로 변경
	 * */
	public List<Product> searchHotSaledQuickly(){
		
		/*
		 * 기존 설계방식인 Impl에 모든 의존성을 넣는 것에 위배되지만
		 * 현재 구현할 수 있는 방안이 이것밖에 떠오르지 않아서 일단 이 방법으로 구현
		 * 어떻게 하면 의존성을 최소화할 수 있을지 더 고민해볼 필요 있음
		 * */
		List<Product> resultList = productRedisTemplateConvertor.getHotSaledProductIdList(productRedisTemplateProvider.getHotSaledProductIds())
				   .stream()
				   .map(id -> productReaderRepository.findById(Long.getLong(id)).get())
				   .collect(Collectors.toList());
		
		return resultList;
	}
}
