package com.webLean.weixin.common.message.response;



/**
 * ������Ϣ
 * @author wangqin
 *
 */
public class VoiceMessage extends BaseMessage {
	// ����
	private Voice Voice;

	public Voice getVoice() {
		return Voice;
	}

	public void setVoice(Voice voice) {
		Voice = voice;
	}
}
