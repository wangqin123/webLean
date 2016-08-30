package com.webLean.utils;

import java.util.Random;

public class IdGenerator
{

  public static String createId(int length)
  {
    Random random = new Random();
    StringBuffer randomCode = new StringBuffer();
    char[] codeSequence = { '1', '2', '3', '4', '5', '6', '7', '8', '9', '0' };
    char[] codeSequenceOne = { '1', '2', '3', '4', '5', '6', '7', '8', '9' };
    for (int i = 0; i < length; i++) {
      String strRand = "";
      if(i==0){
    		  strRand = String.valueOf(codeSequence[random.nextInt(codeSequenceOne.length)]);
      }else{
    	  strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
      }
      randomCode.append(strRand);
    }
    return randomCode.toString();
  }
  
}