package com.util.otherutil;

import java.util.Map;
import java.util.Properties;

public class SystemUtil {
	/**
	 * 获取各种系统属性的方法
	 */
	public static void getSystemEnv(){
		Map<String,String> map=System.getenv();
		for (Map.Entry<String, String> entry:map.entrySet()) {
			 System.out.println( entry.getKey()   
                     + "-->" + entry.getValue() );
		}
	}
	
	/**
	 * 获取各种环境变量的方法
	 */
	public static void getSystemPro(){
		Properties properties=System.getProperties();
		for(Map.Entry<?, ?> entry : properties.entrySet()){  
            System.out.println( entry.getKey()   
                     + "-->" + entry.getValue() );   
        } 
	}
	
	public static void main(String[] args) {
		//SystemUtil.getSystemEnv();
		SystemUtil.getSystemPro();
	}
}
