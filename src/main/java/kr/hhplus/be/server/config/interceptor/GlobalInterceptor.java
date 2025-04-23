package kr.hhplus.be.server.config.interceptor;

import org.springframework.web.context.request.WebRequestInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.WebRequestHandlerInterceptorAdapter;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GlobalInterceptor extends WebRequestHandlerInterceptorAdapter {

	/*
	 * filter 진행 후
	 * Controller로 요청을 보내기 전에 진행하는 전역 인터셉터
	 * */
	
	public GlobalInterceptor(WebRequestInterceptor requestInterceptor) {
		super(requestInterceptor);
	}
	
	/*
	 * 요청을 받은 후 Controller로 넘기기 전
	 * */
	@Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        return super.preHandle(request, response, handler);
    }
	
	/*
	 * Controller 요청 및 반환 작업을 마무리 한 후 Model/Present 계층으로 응답을 보내기 전
	 * */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }
    
    /*
     * Controller 요청 및 반환 작업을 마무리 한 후 Model/Present 계층으로 응답을 보낸 것까지 모두 마무리 후
     * */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }
	
}
