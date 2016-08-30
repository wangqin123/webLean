package com.webLean.controller;

public class test {
	
	/*public static void main(String[] args) {
        Integer f1 = 100, f2 = 100, f3 = 150, f4 = 150;
         //如果整型字面量的值在-128到127之间，那么不会new新的Integer对象，而是直接引用常量池中的Integer对象，
        //所以上面的面试题中f1==f2的结果是true，而f3==f4的结果是false。
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
