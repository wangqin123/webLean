package com.webLean.service;

import com.webLean.domain.WechatCustomer;

/**
 * 微信独立用户信息
 * @author wangqin
 *
 */
public interface IWeChatCustomerService {

	/**
	 * 增加用户
	 * @param customer
	 * @return
	 */
	public boolean insertCustomer(WechatCustomer customer);

	/**
	 * 根据openid查询用户
	 * @param openid
	 * @return
	 */
	public WechatCustomer getCustomerByOpenId(String openid);

	/**
	 * 根据id查询用户
	 * @param id
	 * @return
	 */
	public WechatCustomer getCustomerById(String id);

	/**
	 * 更新用户昵称和头像
	 * @param customer
	 * @return
	 */
	public boolean updateCustomerNameAndImgUrl(WechatCustomer customer);

	/**
	 * 解绑用户
	 * @param moblie
	 * @return
	 */
	public boolean releaseUser(String moblie);

}