package kr.hhplus.be.server.product.domain.redis;

import java.util.ArrayList;
import java.util.Set;

public class ProductRedisTemplateConvertor {
	
	/*
	 * id set을 list로 변환하여
	 * 순서가 보장된 상태에서 productDTO List를 확보할 수 있도록 하기위한 형변환기(컨버터)
	 * */
	public ArrayList<String> getHotSaledProductIdList(Set<String> productIds){
		return new ArrayList<>(productIds);
	}
}
