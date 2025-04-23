package kr.hhplus.be.server.config.filter;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

/*
 * URL 체크 후 요청에 대한 인증 필터
 * */
@Slf4j
public class AuthenticationChecker implements Filter{
	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		StringBuffer requestURL = httpServletRequest.getRequestURL();
		
		log.info("Authentication Checker start :  " + requestURL);
		
		/*
		 * session이 없으면 null 반환
		 * */
		HttpSession session = httpServletRequest.getSession(false);
		
		if(session == null) {
			/*
			 * 미인증 사용자 접근 시 강제 리다이렉트
			 * ServletResponse를 조작하여 응답을 바꾼다.
			 * */
			log.info("사용자 인가 불가 : 인증되지 않은 사용자입니다.");
			httpServletResponse.sendRedirect("/login");
			
			/*
			 * redirect 후 WAS에 예외를 전달
			 * */
			throw new ServletException();
		}
		
		/*
		 * HTTP 요청이 오면 기본적으로 실행하는 구문
		 * */
		chain.doFilter(request, response);
	}
}	
