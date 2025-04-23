package kr.hhplus.be.server.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

import jakarta.activation.DataSource;
import jakarta.servlet.Filter;
import kr.hhplus.be.server.config.filter.URLChecker;
import kr.hhplus.be.server.config.filter.AuthenticationChecker;

@Configuration
@EnableWebSecurity
@ComponentScan(basePackages = {"kr.hhplus.be.server"})
public class WebSecurityConfig {
	
	@Autowired
	private DataSource dataSource;
	
	int FIRST_FILTER = 1;
	int SECOND_FILTER = 2;
	
	/*
	 * 인증
	 * */
	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception{
		httpSecurity
			.csrf().disable()
			.authorizeHttpRequests((request) -> request
					.requestMatchers("/", "/register", "/login").permitAll()
					.anyRequest().authenticated()
			)
			.formLogin((form) -> form
					.loginPage("/login")
					.permitAll()
			)
			.logout((logout) -> logout.permitAll());
		
		return httpSecurity.build();
	}
	
	/*
	 * URL 인증
	 * */
	@Bean
	public FilterRegistrationBean<Filter> URLChecker() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		/*
		 * 필터로직 정의
		 * */
		filterRegistrationBean.setFilter(new URLChecker());
		/*
		 * 첫번쨰로 필터 진행
		 * */
		filterRegistrationBean.setOrder(FIRST_FILTER);
		/*
		 * 모든 요청에 대해 진행
		 * */
		filterRegistrationBean.addUrlPatterns("/");
		
		return filterRegistrationBean;
		
	}
	
	/*
	 * 사용자 인가
	 * */
	@Bean
	public FilterRegistrationBean<Filter> AuthenticationChecker() {
		FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<Filter>();
		/*
		 * 필터로직 정의
		 * */
		filterRegistrationBean.setFilter(new AuthenticationChecker());
		/*
		 * 첫번쨰로 필터 진행
		 * */
		filterRegistrationBean.setOrder(SECOND_FILTER);
		/*
		 * 모든 요청에 대해 진행
		 * */
		filterRegistrationBean.addUrlPatterns("/");
		
		return filterRegistrationBean;
		
	}
}
