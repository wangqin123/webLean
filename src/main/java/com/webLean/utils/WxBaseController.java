package com.webLean.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class WxBaseController  extends BaseController{


	/**
	 * Request
	 */
	protected HttpServletRequest getRequest(){
		return sessionContextUtils.getContextRequest();
	}
	
	/**
	 * Response
	 */
	protected HttpServletResponse getResponse(){
		return sessionContextUtils.getContextResponse();
	}



	
	protected void setAttr(String key,Object e){
		this.sessionContextUtils.getContextRequest().getSession().setAttribute(key,e);
	}
	
}
