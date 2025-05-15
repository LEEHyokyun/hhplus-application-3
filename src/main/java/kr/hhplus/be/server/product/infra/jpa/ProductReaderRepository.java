package kr.hhplus.be.server.product.infra.jpa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.product.domain.entity.Product;
import kr.hhplus.be.server.product.domain.model.ProductDTO;

@Repository
public interface ProductReaderRepository extends JpaRepository<Product, Long>, ProductCustomReaderRepository{
	ProductDTO findByProductId(Long productId);
}
