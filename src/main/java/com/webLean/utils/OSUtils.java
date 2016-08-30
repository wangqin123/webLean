
package com.webLean.utils;


/** ϵͳ����������
* @ClassName: : OSUtils
 * @author wangqin
 *
 */
public class OSUtils {
	/**
	 * �жϵ�ǰ����ϵͳ�Ƿ���Linux
	* @Title: OsIsLinux 
	* @Description: TODO
	* @return
	* @return boolean    
	* @throws
	 */
	public static boolean OsIsLinux(){
		String os=getOsName();
		if(os.toLowerCase().startsWith("win")){
			return false;
		}
		return true;
	}
	
	/**
	 * �õ�����ϵͳ������
	* @Title: getOsName 
	* @Description: TODO
	* @return
	* @return String    
	* @throws
	 */
	public static String getOsName(){
		String os = System.getProperty("os.name"); 
		return os;
	}
	/**
	 * ��ȡ�û����˻�����
	* @Title: getUserName 
	* @Description: TODO
	* @return
	* @return String    
	* @throws
	 */
	public static String getUserName(){
		String userName=System.getProperty("user.name"); 
		return userName;
	}
	/**
	 *��ȡ�û�����Ŀ¼
	* @Title: getUserHome 
	* @Description: TODO
	* @return
	* @return String    
	* @throws
	 */
	public static String getUserHome(){
		String userHome=System.getProperty("user.home"); 
		return userHome;
	}
	/**
	 * ��ȡ�û��ĵ�ǰ����Ŀ¼
	* @Title: getUserDir 
	* @Description: TODO
	* @return
	* @return String    
	* @throws
	 */
	public static String getUserDir(){
		String userDir=System.getProperty("user.dir"); 
		return userDir;
	}
	/**
	 * ��ȡ�ļ��ָ������� UNIX ϵͳ���ǡ�/����
	* @Title: getFileSeparator 
	* @Description: TODO
	* @return
	* @return String    
	* @throws
	 */
	public static String getFileSeparator(){
		String fileSeparator=System.getProperty("file.separator"); 
		return fileSeparator;
	}
	/**
	 * ��ȡ·���ָ������� UNIX ϵͳ���ǡ�:����
	* @Title: getPathSeparator 
	* @Description: TODO
	* @return
	* @return String    
	* @throws
	 */
	public static String getPathSeparator(){
		String pathSeparator=System.getProperty("path.separator"); 
		return pathSeparator;
	}
	/**
	 * ��ȡ�зָ������� UNIX ϵͳ���ǡ�/n����
	* @Title: getLineSeparator 
	* @Description: TODO
	* @return
	* @return String    
	* @throws
	 */
	public static String getLineSeparator(){
		String lineSeparator=System.getProperty("line.separator"); 
		return lineSeparator;
	}
}
