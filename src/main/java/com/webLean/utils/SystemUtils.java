package com.webLean.utils;

/**
 * 系统帮助类
* @ClassName: SystemUtils 
* @Description: TODO
* @author duanwei (awei0916@qq.com)
* @date 2016年3月22日 下午2:00:00 
*
 */
public class SystemUtils {
	/**
	 * 是否进行Url签名验证
	 */
	public static boolean IS_URL_DIGITALSIGNATURE=false;
	/**
	 * 密钥类
	* @TypeName: SecretKey 
	* @Description: TODO
	* @author：duanwei
	* @date 2015年7月30日 下午1:48:00 
	*
	 */
	public static class  SecretKey{
		/**
		 * 签名密钥key
		 */
		public static String key="8f3206d0b73ea8fe";
		
		/**
		 * 签名密钥secret
		 */
		public static String secret="68de6fd28f3206d0b73ea8feb7f4b7ec";
	}
}
