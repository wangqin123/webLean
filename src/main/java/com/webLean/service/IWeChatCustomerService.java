package com.webLean.service;

import com.webLean.domain.WechatCustomer;

/**
 * ΢�Ŷ����û���Ϣ
 * @author wangqin
 *
 */
public interface IWeChatCustomerService {

	/**
	 * �����û�
	 * @param customer
	 * @return
	 */
	public boolean insertCustomer(WechatCustomer customer);

	/**
	 * ����openid��ѯ�û�
	 * @param openid
	 * @return
	 */
	public WechatCustomer getCustomerByOpenId(String openid);

	/**
	 * ����id��ѯ�û�
	 * @param id
	 * @return
	 */
	public WechatCustomer getCustomerById(String id);

	/**
	 * �����û��ǳƺ�ͷ��
	 * @param customer
	 * @return
	 */
	public boolean updateCustomerNameAndImgUrl(WechatCustomer customer);

	/**
	 * ����û�
	 * @param moblie
	 * @return
	 */
	public boolean releaseUser(String moblie);

}