package com.webLean.weixin;

public class Configure {
	//appID
	private static String appID = "wx322330a266a36785";
	//appsecret
	private static String appsecret = "bbd2ea7859c2dfadf82f162a53d01516";
	
	private static String key = "bbd2ea7859c2dfadf82f162a53d01516";
	
	public static String getAppID() {
		return appID;
	}
	public static void setAppID(String appID) {
		Configure.appID = appID;
	}
	public static String getAppsecret() {
		return appsecret;
	}
	public static void setAppsecret(String appsecret) {
		Configure.appsecret = appsecret;
	}
	public static String getKey() {
		return key;
	}
	public static void setKey(String key) {
		Configure.key = key;
	}
	
	
}