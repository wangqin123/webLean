package com.webLean.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webLean.dao.WechatCustomerMapper;
import com.webLean.domain.WechatCustomer;
import com.webLean.service.IWeChatCustomerService;

/**
 * @ClassName: WeChatCustomerServiceImpl.java
 * @Description: TODO(微信公众号相关) 
 * @author wangqin
 *
 */
@Service("weChatCustomerService")
public class WeChatCustomerServiceImpl implements IWeChatCustomerService{

	@Autowired
	private WechatCustomerMapper weChatCustomerMapper;

	public boolean insertCustomer(WechatCustomer customer) {
		int row = this.weChatCustomerMapper.insert(customer);
		return row > 0;
	}

	public WechatCustomer getCustomerByOpenId(String openid) {
		return null;
		//return this.weChatCustomerMapper.selectByOpenId(openid);
	}

	public WechatCustomer getCustomerById(String id) {
		return weChatCustomerMapper.selectByPrimaryKey(id);
	}

	public boolean updateCustomerNameAndImgUrl(WechatCustomer customer) {
		int row = weChatCustomerMapper.updateByPrimaryKey(customer);
		return row == 1 ? true : false;
	}

	public boolean releaseUser(String moblie) {
		return false;
		/*int row = weChatCustomerMapper.releaseUser(moblie);
		return row == 1 ? true : false;*/
	}

}