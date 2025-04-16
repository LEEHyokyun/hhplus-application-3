package kr.hhplus.be.server.product.infra.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import kr.hhplus.be.server.product.domain.entity.Product;
import kr.hhplus.be.server.product.domain.model.ProductDTO;

@Repository
public interface ProductReaderRepository extends JpaRepository<Product, Long>, ProductCustomReaderRepository{
	ProductDTO findByProductId(Long productId);
}
