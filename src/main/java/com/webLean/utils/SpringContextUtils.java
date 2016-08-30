package com.webLean.utils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;



/**
 * Spring�����Ĺ�����
 * @ClassName: : SpringContextUtils 
 * @author wangqin
 *
 */
public class SpringContextUtils implements BeanFactoryAware {
	private BeanFactory factory=null;
	private static SpringContextUtils instance=null;
	
	@Override
	public void setBeanFactory(BeanFactory factory) throws BeansException {
		instance=this;
		this.factory=factory;
		
	}
	public static BeanFactory getBeanFacotory(){
		if(instance==null)
			throw new RuntimeException("ϵͳ��δ��ʼ������ע������룡");
		
		return instance.factory;
	}
	 /**
	  * �������ƻ�ȡBean
	  * @param name
	  * @return
	  */
	@SuppressWarnings("unchecked")
	public static <T> T getBean(String name){
		return (T) getBeanFacotory().getBean(name);
	}
	
	/**
	 * �������ȡBean
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return getBeanFacotory().getBean(clazz);
	}
	/**
	 * ��ȡ����ʵ�ִ˽ӿڵ���(Ĭ�ϰ���Ϊ��ǰ�ӿ����ڵİ�)
	* @Title: getAllImplClassByInterface 
	* @Description: TODO
	* @param clazz 
	* @return
	* @return List<T>    
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	public static  List<Class> getAllImplClassByInterface(Class<?> clazz) {
		 return getAllImplClassByInterface(clazz,clazz.getPackage().getName());
	}
	
	/**
	 * ��ȡ����ʵ�ִ˽ӿڵ���
	* @Title: getAllImplClassByInterface 
	* @Description: TODO
	* @param clazz
	* @param packagePath
	* @return
	* @return List<T>    
	* @throws
	 */
	@SuppressWarnings("rawtypes")
	public static  List<Class> getAllImplClassByInterface(Class<?> clazz,String packagePath) {
		List<Class> returnClassList = null;
		try {
			if (clazz.isInterface()) {
				// ��ȡ��ǰ�����Լ��Ӱ������Ե���
				List<Class> allClass = (List<Class>) getClasses(packagePath);
				if (allClass != null) {
					returnClassList = new ArrayList<Class>();
					for (Class classes : allClass) {
						// �ж��Ƿ���ͬһ���ӿ�
						if (clazz.isAssignableFrom((Class<?>) classes)) {
							// ���������ȥ
							if (!clazz.equals(classes)) {  
								returnClassList.add(classes);
		                          
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LoggerFactory.WriteLog(LoggerType.ERROR, SpringContextUtils.class, "getAllInstanceClassByInterface�����쳣��ԭ��"+e.getMessage());
		}
		return returnClassList;
	}
	
    /**
     * ��ȡָ��������ʵ�ִ˽ӿڵ�������ʵ��
    * @Title: getAllImplClassInstanceByInterface 
    * @Description: TODO
    * @param clazz
    * @param packagePath
    * @return
    * @return List<T>    
    * @throws
     */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getAllImplClassInstanceByInterface(Class<T> clazz,String packagePath) {
		List<T> returnClassList = null;
		try {
			if (clazz.isInterface()) {
				// ��ȡ��ǰ�����Լ��Ӱ������Ե���
				List<T> allClass = (List<T>) getClasses(packagePath);
				if (allClass != null) {
					returnClassList = new ArrayList<T>();
					for (T classes : allClass) {
						// �ж��Ƿ���ͬһ���ӿ�
						if (clazz.isAssignableFrom((Class<?>) classes)) {
							// ���������ȥ
							if (!clazz.equals(classes)) { 
								/**
								 * �����ʵ�� ���Ǽ���
								 */
								String cls=classes.toString().split(" ")[1];
							  	T t=(T) Class.forName(cls).newInstance(); 
								returnClassList.add(t);
		                          
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LoggerFactory.WriteLog(LoggerType.ERROR, SpringContextUtils.class, "getAllInstanceClassByInterface�����쳣��ԭ��"+e.getMessage());
		}
		return returnClassList;
	}
	
	/**
	 *  ��ȡ�ýӿ����ڰ�����ʵ�ִ˽ӿڵ�������ʵ��
	 * @param clazz
	 * @return
	 */
	public static <T> List<T> getAllImplClassInstanceByInterface(Class<T> clazz){
		 return getAllImplClassInstanceByInterface(clazz,clazz.getPackage().getName());
	}
	
	/**
	 * ��һ�����в��ҳ����е��࣬��jar���в��ܲ���
	 * @param packageName
	 * @return
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	@SuppressWarnings("rawtypes")
	private static List<Class> getClasses(String packageName)
			throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		// ��'/'����'.'·��
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
		}
		ArrayList<Class> classes = new ArrayList<Class>();
		for (File directory : dirs) {
			classes.addAll(findClasses(directory, packageName));
		}
		return classes;
	}
	/**
	 * ����ָ�����µ���
	 * @param directory
	 * @param packageName
	 * @return
	 * @throws ClassNotFoundException
	 */
	@SuppressWarnings({ "rawtypes" })
	private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
		List<Class> classes = new ArrayList<Class>();
		if (!directory.exists()) {
			return classes;
		}
		File[] files = directory.listFiles();
		for (File file : files) {
			if (file.isDirectory()) {
				//assert !file.getName().contains(".");
				//System.out.println("---package name:"+packageName+"---name:"+file.getName());
				classes.addAll(findClasses(file,
						packageName + "." + file.getName()));
			} else if (file.getName().endsWith(".class")) { 
				if(packageName.contains("com.appvworks.framework.OSS")||packageName.contains("com.appvworks.framework.pay")||packageName.contains("com.appvworks.framework.Utils")||packageName.contains("com.appvworks.framework.MyBatisCache")||packageName.contains("com.appvworks.framework.Redis")){
					continue;
				}
				// ȥ��'.class'
				String classStr=packageName+ '.'+ file.getName().substring(0,file.getName().length() - 6);
 				//classStr=classStr.replace("/", ".");
				classes.add(Class.forName(classStr));
				//System.out.println("package name:"+packageName+"---name:"+file.getName());

			}
		}
		return classes;
	}
	
	/**
	 * ��ȡjar����ʵ����
	* @Title: getAllClassByInterfaceFromJar 
	* @Description: TODO
	* @param clazz
	* @param packagePath
	* @return
	* @return List<T>    
	* @throws
	 */
	@SuppressWarnings({ "rawtypes" })
	public static List<Class> getAllImplClassByInterfaceFromJar(Class<?> clazz,String packagePath) {
		List<Class> returnClassList = null;
		try {
			if (clazz.isInterface()) {
				// ��ȡ��ǰ�����Լ��Ӱ������Ե���
				List<Class> allClass = (List<Class>) getClassesFromJar(packagePath);
				if (allClass != null) {
					returnClassList = new ArrayList<Class>();
					for (Class classes : allClass) {
						// �ж��Ƿ���ͬһ���ӿ�
						if (clazz.isAssignableFrom((Class<?>) classes)) {
							// ���������ȥ
							if (!clazz.equals(classes)) { 
								returnClassList.add(classes);
		                          
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LoggerFactory.WriteLog(LoggerType.ERROR, SpringContextUtils.class, "getAllInstanceClassByInterface�����쳣��ԭ��"+e.getMessage());
		}
		return returnClassList;
	}
	
	/**
	 * ��ȡjar����ʵ�� �෵��ʵ��
	* @Title: getAllClassInstanceByInterfaceFromJar 
	* @Description: TODO
	* @param clazz
	* @param packagePath
	* @return
	* @return List<T>    
	* @throws
	 */
	@SuppressWarnings("unchecked")
	public static <T> List<T> getAllImplClassInstanceByInterfaceFromJar(Class<T> clazz,String packagePath) {
		List<T> returnClassList = null;
		try {
			if (clazz.isInterface()) {
				// ��ȡ��ǰ�����Լ��Ӱ������Ե���
				List<T> allClass = (List<T>) getClassesFromJar(packagePath);
				if (allClass != null) {
					returnClassList = new ArrayList<T>();
					for (T classes : allClass) {
						// �ж��Ƿ���ͬһ���ӿ�
						if (clazz.isAssignableFrom((Class<?>) classes)) {
							// ���������ȥ
							if (!clazz.equals(classes)) { 
								String cls=classes.toString().split(" ")[1];
								T t=(T) Class.forName(cls).newInstance(); 
								returnClassList.add(t);
		                          
							}
						}
					}
				}
			}
		} catch (Exception e) {
			LoggerFactory.WriteLog(LoggerType.ERROR, SpringContextUtils.class, "getAllInstanceClassByInterface�����쳣��ԭ��"+e.getMessage());
		}
		return returnClassList;
	}
	/**
	 * ��ȡjar����ָ���İ��µ�������
	* @Title: getClassesFromJar 
	* @Description: TODO
	* @param packageName
	* @return
	* @throws ClassNotFoundException
	* @throws IOException
	* @return List<Class>    
	* @throws
	 */
	@SuppressWarnings({ "resource", "rawtypes" })
	private static List<Class> getClassesFromJar(String packageName)
			throws ClassNotFoundException, IOException {
		ClassLoader classLoader = Thread.currentThread()
				.getContextClassLoader();
		// ��'/'����'.'·��
		String path = packageName.replace('.', '/');
		Enumeration<URL> resources = classLoader.getResources(path);
		List<File> dirs = new ArrayList<File>();
		List<Class> classes = new ArrayList<Class>();
		while (resources.hasMoreElements()) {
			URL resource = resources.nextElement();
			dirs.add(new File(resource.getFile()));
			
			String name=resource.getFile().split("!")[0];
			name=name.substring(name.indexOf('/')+1);
			if(OSUtils.OsIsLinux()){
				name="/"+name;
			}
			//System.out.println("jar name="+name);
			JarFile jarFile=new JarFile(name);
			Enumeration<JarEntry> ens=jarFile.entries();
			
		 while(ens.hasMoreElements()){
			JarEntry je=ens.nextElement();
			if(je.isDirectory())continue;
			//System.out.println(je.getName());
		 	if(je.getName().endsWith(".class"))
			{
		 		name=je.getName().substring(0,je.getName().length() - 6); 
		 		String classStr=name.replace("/", ".");
		 		//System.out.println("classStr:"+classStr);
				classes.add(Class.forName(classStr));
			} 
			 
			}
		}
		//for(Class cz :classes){
		//	System.out.println(cz);
		//}
		return classes;
	}
}
