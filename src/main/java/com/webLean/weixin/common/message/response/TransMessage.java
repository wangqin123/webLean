package com.webLean.weixin.common.message.response;

import com.webLean.Auth.KfRedis;

/**
 *  ָ���ͷ���Ϣ
 * @author wangqin
 *
 */
public class TransMessage extends BaseMessage{
	// �ͷ��˻�
	private KfRedis TransInfo;
	
	public KfRedis getTransInfo() {
		return TransInfo;
	}

	public void setTransInfo(KfRedis transInfo) {
		TransInfo = transInfo;
	}

}
