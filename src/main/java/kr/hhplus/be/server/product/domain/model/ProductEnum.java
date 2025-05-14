package kr.hhplus.be.server.product.domain.model;

/*
 * 인기상품 조회를 위해 필요한 
 * Redis Key값을 Enum으로 정의합니다.
 * */
public enum ProductEnum {
	HOT_SALE_PRODUCT("HOT_SALE_PRODUCT")
    ;

    private final String key;

    ProductEnum(String key) {
        this.key = key;
    }

    public String key() {
        return key;
    }
}
