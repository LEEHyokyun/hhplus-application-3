package kr.hhplus.be.server.common.key;
/*
 * 각 도메인에서 공통적으로 활용할 수 있는 상수로
 * 전역적인 최상위 도메인으로 구성
 * */
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
