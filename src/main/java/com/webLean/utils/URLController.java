package com.webLean.utils;

public class URLController {
    private static String[] urls={"/center/","/health/"};
    public static boolean isContainsUrl(String url){
    	boolean flag=false;
    	for(String s :urls){
    		System.out.println("s:"+s);
    		System.out.println("url:"+url);
    		if(url.contains(s)){
    			flag=true;
    			break;
    		}
    	}
    	return flag;
    }

}
