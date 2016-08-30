package com.webLean.weixin;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.webLean.weixin.common.menu.Button;
import com.webLean.weixin.common.menu.ClickButton;
import com.webLean.weixin.common.menu.ComplexButton;
import com.webLean.weixin.common.menu.Menu;
import com.webLean.weixin.common.menu.ViewButton;
import com.webLean.weixin.common.util.CommonUtil;
import com.webLean.weixin.common.util.MenuUtil;

public class MenuManager {
	private static Logger log = LoggerFactory.getLogger(MenuManager.class);
	   //appID
		private static String appID = "wx322330a266a36785";
		//appsecret
		private static String appsecret = "bbd2ea7859c2dfadf82f162a53d01516";
		private static String Domain = "1bfets4ymc.proxy.qqbrowser.cc";

	public static void main(String[] args) throws UnsupportedEncodingException {
		boolean  i = false;
		String jsonString = "";
		String accessToken = CommonUtil.getToken(Configure.getAppID(), Configure.getAppsecret()).getAccessToken();
		
		i = MenuUtil.deleteMenu(accessToken);
		if(i)
			log.debug("ɾ���˵��ɹ�");
		else
			log.debug("ɾ���˵�ʧ��");
		
		  i =  MenuUtil.createMenu(getMenu(), accessToken);
		if(i)
			log.debug("�����˵��ɹ�");
		else
			log.debug("�����˵�ʧ��");
		
		
		
		jsonString = MenuUtil.getMenu(accessToken);
		System.out.println("�˵�Ϊ��"+jsonString);
		
		
			
	}
	
	/**
	 * ��װ�˵�����
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	private static Menu getMenu() throws UnsupportedEncodingException {
		
		ClickButton click1 = new ClickButton();
		click1.setName("���ո���");
		click1.setKey("V1001_TODAY_MUSIC");
		click1.setType("click");
		
		
		
		ViewButton btn1 = new ViewButton();
		btn1.setType("view");
		btn1.setName("����");
		btn1.setUrl("https://open.weixin.qq.com/connect/oauth2/authorize?appid="+appID+"&redirect_uri=http%3A%2F%2F"+Domain+"%2FoAuthServlet%3FrealURL%3Dhealth/index.html&response_type=code&scope=SCOPE&state=STATE#wechat_redirect");
		
		ViewButton btn2 = new ViewButton();
		btn2.setType("view");
		btn2.setName("��Ƶ");
		btn2.setUrl("http://v.qq.com/");
		
		
		ComplexButton cbtn1 = new ComplexButton();
		cbtn1.setName("�˵�");
		cbtn1.setSub_button(new Button[]{btn1,btn2});
		
		ClickButton click2 = new ClickButton();
		click2.setName("��һ������");
		click2.setKey("V1001_GOOD");
		click2.setType("click");
		
		Menu menu = new Menu();
		menu.setButton(new Button[]{click1,cbtn1,click2});
		return menu;
			
		
	}
	

}
