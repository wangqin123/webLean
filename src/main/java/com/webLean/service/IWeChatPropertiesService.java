package com.webLean.service;

import com.webLean.domain.WechatProperties;


/**微信参数缓存
 * 
 * @author wangqin
 *
 */
public interface IWeChatPropertiesService {

	/**获取微信参数*/
	public WechatProperties selectProperties();
	/**更新微信参数*/
	public boolean updateProperties(WechatProperties dto);
	
	
}