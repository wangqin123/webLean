/**   
* @Title: SessionContextUtils.java 
* @Package com.appvworks.framework.Auth 
* @Description: TODO
* @author duanwei (awei0916@qq.com)  
* @date 2016骞�鏈�5鏃�涓嬪崍12:13:04  
*/
package com.webLean.Auth;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;



/**
 * 会话帮助类
 * @author wangqin
 *
 */
public class SessionContextUtils { 
	@Autowired private HttpServletRequest request;
	@Autowired private HttpServletResponse response;

	/**
	 * 获取当前上下文请求
	 * 
	 * @Title: getContextRequest
	 * @Description: TODO
	 * @return
	 * @return HttpServletRequest
	 * @throws
	 */
	public   HttpServletRequest getContextRequest() { 
		return request;
	}

	/**
	 * 获取当前上下文响应
	 * 
	 * @Title: getContextResponse
	 * @Description: TODO
	 * @return
	 * @return HttpServletResponse
	 * @throws
	 */
	public   HttpServletResponse getContextResponse() { 
		return response;
	}

	/**
	 * 获取当前上下文SessionId
	* @Title: getContextSessionId 
	* @Description: TODO
	* @return
	* @return String    
	* @throws
	 */
	public   String getContextSessionId() { 
		return this.request.getSession().getId();
	}
	 

}
