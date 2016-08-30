package com.webLean.weixin;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.webLean.domain.WechatCustomer;
import com.webLean.service.IWeChatCustomerService;
import com.webLean.utils.IdGenerator;
import com.webLean.utils.WeChatUtil;
import com.webLean.utils.WxBaseController;
import com.webLean.weixin.common.po.SNSUserInfo;
import com.webLean.weixin.common.po.WeixinOauth2Token;
import com.webLean.weixin.common.po.WeixinUserInfo;
import com.webLean.weixin.common.util.AdvancedUtil;


/**
 * @ClassName: OAuthServlet.java
 * @Description: TODO(微信公众号相关)
 * @author wangqin
 *
 */
@Controller
@RequestMapping("/oAuthServlet")
public class OAuthServlet extends WxBaseController {
//	private @Autowired IAccountService accountService;
	private @Autowired IWeChatCustomerService weChatCustomerService;
	
	private static Logger log = LoggerFactory.getLogger(OAuthServlet.class);
	
	@RequestMapping("")
	public @ResponseBody void getAuth(){
		log.debug("============into_OAuth=============");
		HttpServletRequest request = sessionContextUtils.getContextRequest();
		HttpServletResponse response = sessionContextUtils.getContextResponse();
		try {
			request.setCharacterEncoding("UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		response.setCharacterEncoding("UTF-8");
		
		// 用户同意授权后，能获取到code
		String code = request.getParameter("code");
		String state = request.getParameter("state");
		
		log.info("code:"+code);
		log.info("state:"+state);
		// 用户同意授权
		if (!"authdeny".equals(code) && code !=null) {
			
			//获取properties文件的appId和appSecret
			String appId = Configure.getAppID();
			String appSecret = Configure.getAppsecret();
			
			// 获取网页授权信息，通过scope获取用户相关信息
			WeixinOauth2Token weixinOauth2Token = AdvancedUtil.getOauth2AccessToken(appId, appSecret, code);
			String openId = weixinOauth2Token.getOpenId();
			WechatCustomer customer = new WechatCustomer();
			customer.setOpenid(openId);
			WeixinUserInfo user = AdvancedUtil.getUserInfo(WeChatUtil.getWeChatProperties().getAccesstoken(), openId);
			
			if(weixinOauth2Token.getScope().equals("snsapi_userinfo")){
				SNSUserInfo snsuser = AdvancedUtil.getSNSUserInfo(weixinOauth2Token.getAccessToken(), openId);
				customer.setNickname(snsuser.getNickname().replaceAll("[^a-zA-Z_\u4e00-\u9fa5]", ""));
				customer.setHeadimgurl(snsuser.getHeadImgUrl());
				customer.setSex(snsuser.getSex());
				customer.setCountry(snsuser.getCountry());
				customer.setProvince(snsuser.getProvince());
				customer.setCity(snsuser.getCity());
				customer.setSubscribe(user.getSubscribe());
			}else{
				//准备启用是否关注字段
				customer.setSubscribe(user.getSubscribe());
				if(customer.getSubscribe()==1){
					customer.setNickname(user.getNickname().replaceAll("[^a-zA-Z_\u4e00-\u9fa5]", ""));
					customer.setHeadimgurl(user.getHeadImgUrl());
					customer.setSex(user.getSex());
					customer.setCountry(user.getCountry());
					customer.setProvince(user.getProvince());
					customer.setCity(user.getCity());
				}
			}
			
			// 用户标识
			String customerid = "";
			WechatCustomer isExist = weChatCustomerService.getCustomerByOpenId(openId);
			
			if(isExist==null){
				customerid = IdGenerator.createId(9);
				customer.setId(customerid);
				weChatCustomerService.insertCustomer(customer);
			}else{
				customer.setId(isExist.getId());
			    if(customer.getSubscribe()==1){
			    	weChatCustomerService.updateCustomerNameAndImgUrl(customer);
			    }
			}
			
			//RedisObj数据缓存方式
			/*WeChatRedisDto redisDto = new WeChatRedisDto();
			redisDto.setCustomer(customer);
			redisDto.setOpenid(openId);
			redisDto.setState(state);
			RedisObject ro=new RedisObject();
			ro.setExtObject(redisDto);
			setWeChatRedisSession(request.getSession().getId(), ro);
			
			RedisObject ros = (RedisObject)
					getWeChatRedisSession(request.getSession().getId(),RedisObject.class);
			log.debug("========SessionId:=========="+request.getSession().getId());
			Map<Object,Object> map = (HashMap) ros.getExtObject();
			log.debug("========RedisDate-Customer:======="+JsonUtils.toJson(map.get("customer")));*/
			//Session数据保存方式，可能会存在Session无法共享的问题
//			request.getSession().setAttribute("customer", customer);
//			request.getSession().setAttribute("openid", openId);
//			request.getSession().setAttribute("state", state);
			
			String realURL = request.getParameter("realURL");
		    if(realURL.contains("param")){
		    	realURL = realURL.replace("param=","").replace(",", "&").replace("|", "=");
		    }
			log.debug("========realURL:======="+realURL);
			
			try {
				if(!realURL.contains("?")){
					response.sendRedirect(request.getContextPath()+"/" + realURL +"?code="+code);
				}else{
					response.sendRedirect(request.getContextPath()+"/" + realURL +"&code="+code);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}else{
			String url = request.getRequestURL().toString();
			String path = request.getContextPath();
			log.debug("========WrongLink_url:======="+url);
			log.debug("========WrongLink_path:======="+path);
			try {
				response.sendRedirect(request.getContextPath()+"/error/wronglink.html");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
	}
	
}