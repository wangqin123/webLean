package com.webLean.service;

import com.webLean.domain.WechatProperties;


/**΢�Ų�������
 * 
 * @author wangqin
 *
 */
public interface IWeChatPropertiesService {

	/**��ȡ΢�Ų���*/
	public WechatProperties selectProperties();
	/**����΢�Ų���*/
	public boolean updateProperties(WechatProperties dto);
	
	
}