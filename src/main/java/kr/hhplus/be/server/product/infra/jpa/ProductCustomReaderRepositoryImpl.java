package kr.hhplus.be.server.product.infra.jpa;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.product.domain.entity.Product;
import kr.hhplus.be.server.product.domain.model.ProductDTO;

@Repository
public class ProductCustomReaderRepositoryImpl implements ProductCustomReaderRepository {
	
	private final ProductReaderRepository productReaderRepository;
	
	/*
	 * 생성자 주입
	 * */
	@Autowired
    public ProductCustomReaderRepositoryImpl(ProductReaderRepository productReaderRepository) {
        this.productReaderRepository = productReaderRepository;
    }
	
	//private final JPAQueryFactory jpaQueryFactory;
	
	/*
	 * QClass 오류 : 조회쿼리에 한해 mybatis로 대체
	 * */
}
