package kr.hhplus.be.server.config.redis;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RedisConfig {
	
	static String REDISSION_PREFIX = "redis://";
	
	@Value("${spring.data.redis.host}")
    private String host;

    @Value(("${spring.data.redis.port}"))
    private String port;

    @Value(("${spring.data.redis.password}"))
    private String password;
    
	/*
	 * Redission 분산락을 구현하기 위한 환경설정 Bean 등록
	 * */
    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        
        //Redis 접속정보
        config.useSingleServer()
        	  .setAddress(REDISSION_PREFIX +host+":"+port)
        	  .setPassword(password);
        
        //Redission Client
        return Redisson.create(config);
    }
}
