package com.util.stringutil;

import java.io.UnsupportedEncodingException;
import java.util.List;

import com.hankcs.hanlp.HanLP;
import com.hankcs.hanlp.dictionary.py.Pinyin;

/**
 * 中文汉字工具类
 * @author Administrator
 *
 */
public class ChineseUtil {
	
	/**
	 * 将中文繁体字转化为简体
	 * @param traditional
	 * @return
	 */
	public static String converToSimple(String traditional){
		if(traditional!=null&&traditional.length()>0){
			return HanLP.convertToSimplifiedChinese(traditional);
		}
		return null;
	}
	
	/**
	 * 将中文简体字转化为繁体
	 * @param simple
	 * @return
	 */
	public static String converToTraditional(String simple){
		if(simple!=null&&simple.length()>0){
			return HanLP.convertToTraditionalChinese(simple);
		}
		return null;
	}
	
	/**
	 * 获取中文的拼音（含声调）
	 * @param text
	 * @return
	 */
	public static String getPinYinWithTone(String text){
		if(text!=null&&text.length()>0){
			StringBuffer sbf=new StringBuffer();
			List<Pinyin> list=HanLP.convertToPinyinList(text);
			for (Pinyin pinyin : list) {
				sbf.append(pinyin.getPinyinWithToneMark()).append(" ");
			}
			return sbf.toString();
		}
		return null;
	}
	
	/**
	 * 获取中文的拼音（不含声调）
	 * @param text
	 * @return
	 */
	public static String getPinYinWithoutTone(String text){
		if(text!=null&&text.length()>0){
			StringBuffer sbf=new StringBuffer();
			List<Pinyin> list = HanLP.convertToPinyinList(text);
			for(Pinyin pinyin : list){
				sbf.append(pinyin.getPinyinWithoutTone()).append(" ");
			}
			return sbf.toString();
		}
		return null;
	}
	
	/**
	 * 获取中文的声调
	 * @param text
	 * @return
	 */
	public static String getPinyinTone(String text){
		if(text!=null&&text.length()>0){
			StringBuffer sbf=new StringBuffer();
			List<Pinyin> list = HanLP.convertToPinyinList(text);
			for(Pinyin pinyin : list){
				sbf.append(pinyin.getTone()).append(" ");
			}
			return sbf.toString();
		}
		return null;
	}
	/**
	 * 获取中文的声母
	 * @param text
	 * @return
	 */
	public static String getPinyinShengmu(String text){
		if(text!=null&&text.length()>0){
			StringBuffer sbf=new StringBuffer();
			List<Pinyin> list = HanLP.convertToPinyinList(text);
			for(Pinyin pinyin : list){
				sbf.append(pinyin.getShengmu()).append(" ");
			}
			return sbf.toString();
		}
		return null;
	}
	
	/**
	 * 获取中文的韵母
	 * @param text
	 * @return
	 */
	public static String getPinyinYunmu(String text){
		if(text!=null&&text.length()>0){
			StringBuffer sbf=new StringBuffer();
			List<Pinyin> list = HanLP.convertToPinyinList(text);
			for(Pinyin pinyin : list){
				sbf.append(pinyin.getYunmu()).append(" ");
			}
			return sbf.toString();
		}
		return null;
	}
	public static void main(String[] args) {
		
	}
}
