package kr.hhplus.be.server.point.infra.mybatis;

import org.apache.ibatis.annotations.Mapper;

import kr.hhplus.be.server.point.domain.model.PointDTO;

@Mapper
public interface PointReaderMapper {
	PointDTO searchPoint(Long userId);
}
