package com.webLean.weixin.common.po;


/**
 * ƾ֤
 * @author wangqin
 *
 */
public class Token {
	// �ӿڷ���ƾ֤
	private String accessToken;
	// ƾ֤��Ч�ڣ���λ����
	private int expiresIn;

	public String getAccessToken() {
		return accessToken;
	}

	public void setAccessToken(String accessToken) {
		this.accessToken = accessToken;
	}

	public int getExpiresIn() {
		return expiresIn;
	}

	public void setExpiresIn(int expiresIn) {
		this.expiresIn = expiresIn;
	}
}