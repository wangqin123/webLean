package com.webLean.controller;

public class test {
	
	/*public static void main(String[] args) {
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
         //���������������ֵ��-128��127֮�䣬��ô����new�µ�Integer���󣬶���ֱ�����ó������е�Integer����
        //�����������������f1==f2�Ľ����true����f3==f4�Ľ����false��
        System.out.println(f1 == f2);
        System.out.println(f3 == f4);  
        
    }
*/
	
	public static void main(String[] args) {
        String s1 = "Programming";
        String s2 = new String("Programming");
        String s3 = "Program" + "ming";
        System.out.println(s1 == s2);
        System.out.println(s1 == s3);
        System.out.println(s1 == s1.intern());
    }
	

}
