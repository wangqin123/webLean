package com.webLean.weixin.common.po;
/**
 * 
 * @author wangqin
 *
 */
public class WeixinArticle {

	//����
	private String title;
	//ͼ����Ϣ�ķ���ͼƬ�ز�id������������mediaID��
	private String thumb_media_id;
	//����
	private String author;
	//ͼ����Ϣ��ժҪ�����е�ͼ����Ϣ����ժҪ
	private String digest;
	//�Ƿ���ʾ���棬0Ϊfalse��������ʾ��1Ϊtrue������ʾ
	private int show_cover_pic;
	//ͼ����Ϣ�ľ������ݣ�֧��HTML��ǩ����������2���ַ���С��1M���Ҵ˴���ȥ��JS
	private String content;
	//ͼ��url
	private String url;
	//�Ķ�ԭ��url
	private String content_source_url;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getThumb_media_id() {
		return thumb_media_id;
	}
	public void setThumb_media_id(String thumb_media_id) {
		this.thumb_media_id = thumb_media_id;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
	}
	public String getDigest() {
		return digest;
	}
	public void setDigest(String digest) {
		this.digest = digest;
	}
	public int getShow_cover_pic() {
		return show_cover_pic;
	}
	public void setShow_cover_pic(int show_cover_pic) {
		this.show_cover_pic = show_cover_pic;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getContent_source_url() {
		return content_source_url;
	}
	public void setContent_source_url(String content_source_url) {
		this.content_source_url = content_source_url;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	@Override
	public String toString() {
		return "WeixinArticle [title=" + title + ", thumb_media_id=" + thumb_media_id + ", author=" + author
				+ ", digest=" + digest + ", show_cover_pic=" + show_cover_pic + ", content=" + content + ", url=" + url
				+ ", content_source_url=" + content_source_url + "]";
	}
	
	
}
