/**   
* @Title: LoggerFactory.java 
* @Package com.appvworks.framework.Logger 
* @Description: TODO
* @author duanwei (awei0916@qq.com) 
* @date 2015��7��2�� ����10:41:19  
*/
package com.webLean.utils;

import org.apache.log4j.Logger;

/** 
* @ClassName: : LoggerFactory 
* @Description: ��־������
* @author duanwei (awei0916@qq.com)
* @date 2015��7��2�� ����10:41:19 
*  
*/
public class LoggerFactory {
	protected static Logger log = null;
	/**
	 * ��־���
	* @Title: WriteLog 
	* @Description: TODO
	* @param type
	* @param clazz
	* @param msg
	* @return void    
	* @throws
	 */
	public static void WriteLog(LoggerType type, Class<?> clazz, String msg){
		WriteLog(type,clazz,msg,null);
		
	}
	
	/**
	 * ��־���
	* @Title: WriteLog 
	* @Description: TODO
	* @param type
	* @param clazz
	* @param msg
	* @param ex
	* @return void    
	* @throws
	 */
	public static void WriteLog(LoggerType type, Class<?> clazz, String msg,Exception ex) { 
		log =  Logger.getLogger(clazz);
		switch (type) {
		case DEBUG:
			log.debug(msg, ex);
			break;
		case INFO:
			log.info(msg, ex);
			break;
		case ERROR:
			log.error(msg, ex);
			break;
		case WARN:
			log.warn(msg, ex);
			break;
		case FATAL:
			log.error(msg, ex);
			break;
		default:
			log.trace(msg, ex);
			break;
		}
	}
	/**
	 * ��ȡ��־����
	* @Title: getLogger 
	* @Description: TODO
	* @param clazz
	* @return
	* @return Logger    
	* @throws
	 */
	public static Logger getLogger(Class<?> clazz){
		log =  Logger.getLogger(clazz); 
		return log;
	}
}
