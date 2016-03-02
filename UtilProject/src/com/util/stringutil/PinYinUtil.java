package com.util.stringutil;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;

/**
 * 汉语拼音工具类
 *
 */
public class PinYinUtil {
	/**
	 * 将中文转化成拼音
	 * @param chinese 中文
	 * @return
	 */
	public static String getPinyin(String chinese){
		HanyuPinyinOutputFormat pyFormat=new HanyuPinyinOutputFormat();
		pyFormat.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		pyFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		pyFormat.setVCharType(HanyuPinyinVCharType.WITH_V);
		return PinyinHelper.toHanyuPinyinString(chinese, pyFormat, "");
	}
	
	/**
	 * 转化姓名为拼音（大小写规范）
	 * @param name
	 * @return
	 */
	 public static String getUpEname(String name) {
         	char[] strs = name.toCharArray();
         	String newname = null;
        	//名字的长度
        	if (strs.length == 2) {   
	             newname = toUpCase(getPinyin("" + strs[0])) + " "
	                        + toUpCase(getPinyin ("" + strs[1]));
	        } else if (strs. length == 3) {
	             newname = toUpCase(getPinyin("" + strs[0])) + " "
	                        + toUpCase(getPinyin("" + strs[1] + strs[2]));
	        } else if (strs. length == 4) {
	             newname = toUpCase(getPinyin("" + strs[0] + strs[1])) + " "
	                        + toUpCase(getPinyin("" + strs[2] + strs[3]));
	        } else {
	             newname = toUpCase(getPinyin(name));
	        }
	  
	      return newname;
	 }
	
	/**
	 *首字母大写 
	 * @param str
	 * @return
	 */
	private static String toUpCase(String str) {
         StringBuffer newstr = new StringBuffer();
         newstr.append((str.substring(0, 1)).toUpperCase()).append(
                   str.substring(1, str.length()));

          return newstr.toString();
   }
	public static void main(String[] args) {
		System.out.println(PinYinUtil.getPinyin("不"));
	}
}
