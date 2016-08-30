package com.webLean.weixin.common.message.response;



/**
 * 视频消息
 * @author wangqin
 *
 */
public class VideoMessage extends BaseMessage {
	// 视频
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
