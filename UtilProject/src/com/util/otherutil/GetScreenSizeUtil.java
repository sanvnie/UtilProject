package com.util.otherutil;

import java.awt.Toolkit;
/**
 * 获取电脑屏幕宽高的工具类
 *
 */
public class GetScreenSizeUtil {
	public static int getScreenWidth(){
		int screenwidth=0;
		screenwidth=Toolkit.getDefaultToolkit().getScreenSize().width;
		return screenwidth;
	}
	
	public static int getScreenHeight(){
		int screenheight=0;
		screenheight=Toolkit.getDefaultToolkit().getScreenSize().height;
		return screenheight;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		int w=GetScreenSizeUtil.getScreenWidth();
		int h=GetScreenSizeUtil.getScreenHeight();
		
		System.out.println(w+"*"+h);

	}

}
