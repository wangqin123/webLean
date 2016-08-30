package com.webLean.utils;

import java.util.Iterator;
import java.util.Map;
import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.webLean.domain.WechatProperties;
import com.webLean.service.IWeChatPropertiesService;
import com.webLean.weixin.Configure;
import com.webLean.weixin.common.util.AdvancedUtil;
import com.webLean.weixin.common.util.CommonUtil;


/**
 * 
 * @ClassName: WeChatUtil.java
 * @Description: TODO(微信公众号相关)
 * @author seven_zhaokaifeng(502946172@qq.com)
 * @date 2016年3月24日
 * @version V1.0
 */
public class WeChatUtil{
	
	private static Logger logger = Logger.getLogger(WeChatUtil.class);
	
	private static String API_KEY = Configure.getKey();
	
	private static IWeChatPropertiesService weChatPropertiesService = (IWeChatPropertiesService) SpringContextUtils.getBean("weChatPropertiesService");

	/**
	 * 通过request获取IP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		 // 获取请求主机IP地址,如果通过代理进来，则透过防火墙获取真实IP地址  
		
        String ip = request.getHeader("X-Forwarded-For");  
        if (logger.isInfoEnabled()) {  
            logger.info("getIpAddress(HttpServletRequest) - X-Forwarded-For - String ip=" + ip);  
        }  
  
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("Proxy-Client-IP");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - Proxy-Client-IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("WL-Proxy-Client-IP");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - WL-Proxy-Client-IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_CLIENT_IP");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_CLIENT_IP - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - HTTP_X_FORWARDED_FOR - String ip=" + ip);  
                }  
            }  
            if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {  
                ip = request.getRemoteAddr();  
                if (logger.isInfoEnabled()) {  
                    logger.info("getIpAddress(HttpServletRequest) - getRemoteAddr - String ip=" + ip);  
                }  
            }  
        } else if (ip.length() > 15) {  
            String[] ips = ip.split(",");  
            for (int index = 0; index < ips.length; index++) {  
                String strIp = (String) ips[index];  
                if (!("unknown".equalsIgnoreCase(strIp))) {  
                    ip = strIp;  
                    break;  
                }  
            }  
        }  
        return ip;  
	}
	
	/**
	 * 随机字符串
	 * @param length 随机串长度
	 * @return
	 */
    public static String generateRandCode(int length){
            Random random = new Random();
            StringBuffer randomCode = new StringBuffer();
            char[] codeSequence = { 'a','b','c','d','e','f','g','h','i','g','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z',
            		'1','2','3','4','5','6','7','8','9','0' };
            for(int i=0;i<length;i++){
                String strRand = String.valueOf(
                        codeSequence[random.nextInt(codeSequence.length)]);
                randomCode.append(strRand);
            }
            return randomCode.toString();
    }
    
    
    
    
    /**
	 * @Description：sign签名
	 * @param characterEncoding 编码格式
	 * @param parameters 请求参数
	 * @return
	 */
    /**
	 * @Description：sign签名
	 * @param params 请求参数
	 * @param signType 签名类型 
	 * @return
	 */
	public static String sign(SortedMap<String, String> params,String signType) {
		StringBuffer sb = new StringBuffer();
		for(Map.Entry<String, String> entry : params.entrySet()){
			String key 	 = entry.getKey();
			String value = entry.getValue();
			if (null != value && !"".equals(value) && !"sign".equals(key) && !"key".equals(key)) {
				sb.append(key + "=" + value + "&");
			}
		}
		sb.append("key=" + API_KEY);
		
		String sign = "";
		System.out.println("签名前:" + params);
		if(signType.equalsIgnoreCase("MD5")){
			sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		}else if(signType.equalsIgnoreCase("SHA1")){
			sign = Sha1Util.getSha1(sb.toString());
		}
		System.out.println("签名后：" + sign);
		return sign;
	}
	
	/**
	 * 验证消息是否是微信发出的合法消息,防止数据泄漏导致出现“假通知”，造成资金损失
	 * @param params  通知返回来的参数数组
	 * @return 验证结果
	 */
	public static boolean verify(Map<String, String> params,String signType) {
		String sign = params.get("sign");
		// 1、去除空和sign
		Map<String, String> paraFilterMap 		 = paraFilter(params);
		// 2、排序
		SortedMap<String, String> paraStortedMap = new TreeMap<String, String>();
		paraStortedMap.putAll(paraFilterMap);
		// 3、生成新的签名字符串
		String newSign = sign(paraStortedMap, signType);
		// 4、签名字符串 比对
		if(newSign.equals(sign)) {
    		return true;
    	}
		return false;
	}
	
	/**
	 * 除去数组中的空值和签名参数
	 * @param params 签名参数组
	 * @return 去掉空值与签名参数后的新签名参数组
	 */
	public static Map<String, String> paraFilter(Map<String, String> params) {
		Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			String key 	 = entry.getKey();
			String value = entry.getValue();
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
				it.remove();//删除value为空的和key=sign的
			}
		}
		return params;
	}
    
	/**
	 * 获取微信接口参数
	 * @return
	 */
	public static WechatProperties getWeChatProperties() {
		WechatProperties properties = new WechatProperties();
		properties = weChatPropertiesService.selectProperties();
		Long now  = Long.parseLong(String.valueOf(System.currentTimeMillis()).substring(0, 10))-Long.parseLong(properties.getRefreshtime());
		if(now > 100 ){
			String accessToken = CommonUtil.getToken(Configure.getAppID(), Configure.getAppsecret()).getAccessToken();
			String jsapi_ticket = AdvancedUtil.getTickect(accessToken);
			String api_ticket = AdvancedUtil.getCardTickect(accessToken);
			properties.setAccesstoken(accessToken);
			properties.setJsapiTicket(jsapi_ticket);
			properties.setApiTicket(api_ticket);
			properties.setRefreshtime(String.valueOf(System.currentTimeMillis()).substring(0, 10));
			weChatPropertiesService.updateProperties(properties);
		}
		return properties; 
	}
    
    
    public static void main(String[] args) {
    	System.out.println(generateRandCode(32));
	}
    
}
