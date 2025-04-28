package kr.hhplus.be.server.config.filter;

import java.io.IOException;

import io.micrometer.core.instrument.binder.http.HttpServletRequestTagsProvider;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

/*
 * 요청 URL을 확인하기 위한 필터
 * */
@Slf4j
public class URLChecker implements Filter{
	
	@Override
	public void init(FilterConfig filterConfig) throws ServletException{
		/*
		 * filter 실행 시 최초 동작
		 * */
		
		log.info("filter를 실행합니다.");
	}
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request; //사용자의 요청
		StringBuffer requestURL = httpServletRequest.getRequestURL();
		
		log.info("URL Checker start :  " + requestURL);
		/*
		 * HTTP 요청이 오면 기본적으로 실행하는 구문
		 * */
		chain.doFilter(request, response);
	}
}
