package com.util.stringutil;
/**
 * 验证工具类
 *
 */
public class CheckUtil {
	/** 
	 * 身份证验证规则： 第十八位数字（校验码）的计算方法为：  
	 * 1.将前面的身份证号码17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2 
	 * 2.将这17位数字和系数相乘的结果相加。  
	 * 3.用加出来和除以11，看余数是多少？ 
	 * 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。 
	 * 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。 
	 *  
	 */  
	    public static boolean checkIdCard(String idCard) { 
	    	boolean flag=false; 
	        // 1.将身份证号码前面的17位数分别乘以不同的系数。从第一位到第十七位的系数分别为：7 9 10 5 8 4 2 1 6 3 7 9 10 5 8 4 2  
	        int[] intArr = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };  
	        int sum = 0;  
	        for (int i = 0; i < intArr.length; i++) {  
	            // 2.将这17位数字和系数相乘的结果相加。  
	            sum += Character.digit(idCard.charAt(i), 10) * intArr[i];
	        }  
	        // 3.用加出来和除以11，看余数是多少？  
	        int mod = sum % 11; 
	        // 4.余数只可能有0 1 2 3 4 5 6 7 8 9 10这11个数字。其分别对应的最后一位身份证的号码为1 0 X 9 8 7 6 5 4 3 2。  
	        int[] intArr2 = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };  
	        int[] intArr3 = { 1, 0, 'X', 9, 8, 7, 6, 5, 4, 3, 2 };  
	        String matchDigit = "";  
	        for (int i = 0; i < intArr2.length; i++) {  
	            int j = intArr2[i];  
	            if (j == mod) {  
	                matchDigit = String.valueOf(intArr3[i]);  
	                if (intArr3[i] > 57) {  
	                    matchDigit = String.valueOf((char) intArr3[i]);  
	                }  
	            }  
	        }  
	  
	        if (matchDigit.equals(idCard.substring(idCard.length() - 1))) {  
	            flag=true;
	        } 
	        // 5.通过上面得知如果余数是2，就会在身份证的第18位数字上出现罗马数字的Ⅹ。如果余数是10，身份证的最后一位号码就是2。  
	        return flag;
	    }
}
