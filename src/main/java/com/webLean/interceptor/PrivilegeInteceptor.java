package com.webLean.interceptor;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.webLean.domain.userT;
import com.webLean.utils.URLController;

public class PrivilegeInteceptor implements HandlerInterceptor{
	private final static Logger log = LoggerFactory.getLogger(PrivilegeInteceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		response.setHeader("Access-Control-Allow-Origin", "*");
		response.setContentType("application/json;charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		String url = request.getRequestURL().toString();
		String path = request.getContextPath();
		log.debug("收到请求Url", url);
		
		if(URLController.isContainsUrl(url)){
			String   see = (String) request.getSession().getAttribute("user");
			log.debug("seeId:", see);
			userT user = (userT) request.getSession().getAttribute("user");  
	        if(user != null) {    
	            return true;  //返回true，则这个方面调用后会接着调用postHandle(),  afterCompletion()  
	        }else{  
	            // 未登录  跳转到登录页面  
	        	response.sendRedirect(path+"/login/login.html");
	        }  
			
		}
		return false;
		
	        
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
