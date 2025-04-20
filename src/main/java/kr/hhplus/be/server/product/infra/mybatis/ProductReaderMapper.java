package kr.hhplus.be.server.product.infra.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import kr.hhplus.be.server.product.domain.model.ProductDTO;

@Mapper
public interface ProductReaderMapper {
	List<ProductDTO> searchHotSaled();
}
