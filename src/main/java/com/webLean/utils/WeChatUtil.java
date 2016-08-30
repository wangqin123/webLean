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
 * @Description: TODO(΢�Ź��ں����)
 * @author seven_zhaokaifeng(502946172@qq.com)
 * @date 2016��3��24��
 * @version V1.0
 */
public class WeChatUtil{
	
	private static Logger logger = Logger.getLogger(WeChatUtil.class);
	
	private static String API_KEY = Configure.getKey();
	
	private static IWeChatPropertiesService weChatPropertiesService = (IWeChatPropertiesService) SpringContextUtils.getBean("weChatPropertiesService");

	/**
	 * ͨ��request��ȡIP
	 * @param request
	 * @return
	 */
	public static String getIpAddr(HttpServletRequest request) {
		 // ��ȡ��������IP��ַ,���ͨ�������������͸������ǽ��ȡ��ʵIP��ַ  
		
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
	 * ����ַ���
	 * @param length ���������
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
	 * @Description��signǩ��
	 * @param characterEncoding �����ʽ
	 * @param parameters �������
	 * @return
	 */
    /**
	 * @Description��signǩ��
	 * @param params �������
	 * @param signType ǩ������ 
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
		System.out.println("ǩ��ǰ:" + params);
		if(signType.equalsIgnoreCase("MD5")){
			sign = MD5Util.MD5Encode(sb.toString(), "UTF-8").toUpperCase();
		}else if(signType.equalsIgnoreCase("SHA1")){
			sign = Sha1Util.getSha1(sb.toString());
		}
		System.out.println("ǩ����" + sign);
		return sign;
	}
	
	/**
	 * ��֤��Ϣ�Ƿ���΢�ŷ����ĺϷ���Ϣ,��ֹ����й©���³��֡���֪ͨ��������ʽ���ʧ
	 * @param params  ֪ͨ�������Ĳ�������
	 * @return ��֤���
	 */
	public static boolean verify(Map<String, String> params,String signType) {
		String sign = params.get("sign");
		// 1��ȥ���պ�sign
		Map<String, String> paraFilterMap 		 = paraFilter(params);
		// 2������
		SortedMap<String, String> paraStortedMap = new TreeMap<String, String>();
		paraStortedMap.putAll(paraFilterMap);
		// 3�������µ�ǩ���ַ���
		String newSign = sign(paraStortedMap, signType);
		// 4��ǩ���ַ��� �ȶ�
		if(newSign.equals(sign)) {
    		return true;
    	}
		return false;
	}
	
	/**
	 * ��ȥ�����еĿ�ֵ��ǩ������
	 * @param params ǩ��������
	 * @return ȥ����ֵ��ǩ�����������ǩ��������
	 */
	public static Map<String, String> paraFilter(Map<String, String> params) {
		Iterator<Map.Entry<String, String>> it = params.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<String, String> entry = it.next();
			String key 	 = entry.getKey();
			String value = entry.getValue();
			if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
				it.remove();//ɾ��valueΪ�յĺ�key=sign��
			}
		}
		return params;
	}
    
	/**
	 * ��ȡ΢�Žӿڲ���
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
