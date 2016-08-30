package com.webLean.weixin.common.message.response;

import com.webLean.Auth.KfRedis;

/**
 *  指定客服消息
 * @author wangqin
 *
 */
public class TransMessage extends BaseMessage{
	// 客服账户
	private KfRedis TransInfo;
	
	public KfRedis getTransInfo() {
		return TransInfo;
	}

	public void setTransInfo(KfRedis transInfo) {
		TransInfo = transInfo;
	}

}
