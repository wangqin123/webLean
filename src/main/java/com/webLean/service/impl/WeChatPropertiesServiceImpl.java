package com.webLean.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webLean.dao.WechatPropertiesMapper;
import com.webLean.domain.WechatProperties;
import com.webLean.service.IWeChatPropertiesService;


/**
 * @ClassName: WeChatPropertiesServiceImpl.java
 * @Description: TODO(微信公众号相关)
 * @author wangqin
 *
 */
@Service("weChatPropertiesService")
public class WeChatPropertiesServiceImpl implements IWeChatPropertiesService {

	private @Autowired WechatPropertiesMapper weChatPropertiesMapper;

	@Override
	public WechatProperties selectProperties() {
		return null;
		//return weChatPropertiesMapper.selectProperties();
	}

	@Override
	public boolean updateProperties(WechatProperties dto) {
		return false;
		//return weChatPropertiesMapper.updateProperties(dto) == 1;
	}
	

}
