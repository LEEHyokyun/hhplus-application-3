package kr.hhplus.be.server.common.topic;
/*
 * 인기상품 조회를 위해 필요한 
 * Platform Key값을 Enum으로 정의합니다.
 * */
public enum PlatformEnum {
	PLATFORM("PLATFORM"),
	DLQ("DLQ")
	;
	
	private final String topic;
	
	PlatformEnum(String topic){
		this.topic = topic;
	}
	
	public String topic() {
		return topic;
	}
}
