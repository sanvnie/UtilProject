package com.util.stringutil;

import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 各种操作字符串的工具类
 *
 */
public class StringUtil {
	/**
	 * 过滤各种特殊字符的方法
	 * @param str 需要过滤的字符串
	 * @return 过滤后剩下的合法字符串（只有汉字，大小写字母，0-9数字及“_”）
	 */
	public static String filterSpecialString(String str) {
		char[] arr=str.toCharArray();
		StringBuffer buffer=new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			if((arr[i]>=19968&&arr[i]<=40869)||(arr[i]>=97&&arr[i]<=122)||(arr[i]>=65&&arr[i]<=90)||(arr[i]>=48&&arr[i]<=57)||(i!=0&&arr[i]==95)){
				if(buffer.length()<9){
					buffer.append(arr[i]);
				}
			}
		}
		return buffer.toString();
	}
	
	/**
	 * 填充数字0
	 * 
	 * @param src
	 * @param targetLength
	 * @return
	 */
	public static String fill(long src, int targetLength) {
		return fill(String.valueOf(src), "0", targetLength, true);
	}

	/**
	 * 填充字符串。如果原字符串比目标长度长，则截去多出的部分。如果onTheLeft等于true 截去左边部分，否则截去右边部分。
	 * 注意填充物一般为单个字符，如果是多个字符，可能导致最后的结果不可用。
	 * 
	 * @param src
	 *            原字符串
	 * @param padding
	 *            填充物，一般是0、空格等
	 * @param targetLength
	 *            目标长度
	 * @param onTheLeft
	 *            是否左填充。
	 * @return
	 */
	public static String fill(String src, String padding, int targetLength,
			boolean onTheLeft) {

		if (src == null) {
			src = "";
		}

		while (src.length() < targetLength) {
			if (onTheLeft) {
				src = padding + src;
			} else {
				src = src + padding;
			}
		}

		if (src.length() > targetLength) {
			if (onTheLeft) {
				src = src.substring(src.length() - targetLength);
			} else {
				src = src.substring(0, targetLength);
			}
		}

		return src;
	}




	/**
	 * 删除字符串中非数字部分。
	 * @param removeDot
	 *            是否删除小数点
	 */
	public static String removeNotDigit(String src, boolean removeDot) {

		if (removeDot) {
			try {
				Integer.parseInt(src);
				return src;
			} catch (Exception e) {
			}
		} else {
			try {
				Double.parseDouble(src);
				return src;
			} catch (Exception e) {
			}
		}

		char[] chars = src.toCharArray();
		String result = "";
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] >= 48 && chars[i] <= 57 || !removeDot
					&& chars[i] == '.') {
				result += chars[i];
			}
		}
		return result;

	}

	/**
	 * 删除空白字符，包括空格、制表符、回车、换行、Ascci码0
	 * 
	 */
	public static String removeWhiteSpace(String src) {
		if (src == null) {
			return src;
		}
		char[] chars = src.toCharArray();
		String result = "";
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] != 32 && chars[i] != 9 && chars[i] != 10
					&& chars[i] != 13 && chars[i] != 0) {
				result += chars[i];
			}
		}
		return result;
	}

	/**
	 * 
	 */
	public static String fill(String delimiter, String padding,
			boolean onTheLeft, Object... items) {
		StringBuffer buff = new StringBuffer();
		boolean firstItem = true;
		for (Object item : items) {
			if (firstItem) {
				firstItem = false;
			} else {
				buff.append(delimiter);
			}

			if (onTheLeft) {
				buff.append(padding);
			}

			if (item == null) {
				buff.append("");
			} else {
				buff.append(item.toString());
			}

			if (!onTheLeft) {
				buff.append(padding);
			}
		}
		return buff.toString();
	}
	/**
	 * 
	 * 将一个字符串按分隔符分成多个子串。 此方法用于代替Jdk的String.split()方法，不同的地方：
	 * (1)在此方法中，空字符串不管在哪个位置都视为一个有效字串。 (2)在此方法中，分隔符参数不是一个正则表达式。
	 * 
	 * @param src
	 * @param delimiter
	 * @return
	 */
	public static String[] split(String src, String delimiter) {
		if (src == null || delimiter == null || src.trim().equals("")
				|| delimiter.trim().equals("")) {
			return new String[] { src };
		}

		ArrayList<String> list = new ArrayList<String>();

		int lengthOfDelimiter = delimiter.length();
		int pos = 0;
		while (true) {
			if (pos < src.length()) {

				int indexOfDelimiter = src.indexOf(delimiter, pos);
				if (indexOfDelimiter < 0) {
					list.add(src.substring(pos));
					break;
				} else {
					list.add(src.substring(pos, indexOfDelimiter));
					pos = indexOfDelimiter + lengthOfDelimiter;
				}
			} else if (pos == src.length()) {
				list.add("");
				break;
			} else {
				break;
			}
		}

		String[] result = new String[list.size()];
		list.toArray(result);
		return result;

	}
	
	/**
	 * 数字的金额表达式(人民币)
	 * @param num
	 * @return
	 */
	public static String convertNumToMoney(int num){
		NumberFormat formatc = NumberFormat.getCurrencyInstance(Locale.CHINA);
		String strcurr = formatc.format(num);
		return strcurr;
	}
	
	/**
	 * 功能描述：人民币转成大写
	 * @param str 数字字符串
	 * @return String 人民币转换成大写后的字符串
	 */
	public static String hangeToBig(String str) {
		double value;
		try {
			value = Double.parseDouble(str.trim());
		} catch (Exception e) {
			return null;
		}
		char[] hunit = { '拾', '佰', '仟' }; // 段内位置表示
		char[] vunit = { '万', '亿' }; // 段名表示
		char[] digit = { '零', '壹', '贰', '叁', '肆', '伍', '陆', '柒', '捌', '玖' }; // 数字表示
		long midVal = (long) (value * 100); // 转化成整形
		String valStr = String.valueOf(midVal); // 转化成字符串

		String head = valStr.substring(0, valStr.length() - 2); // 取整数部分
		String rail = valStr.substring(valStr.length() - 2); // 取小数部分

		String prefix = ""; // 整数部分转化的结果
		String suffix = ""; // 小数部分转化的结果
		// 处理小数点后面的数
		if (rail.equals("00")) { // 如果小数部分为0
			suffix = "整";
		} else {
			suffix = digit[rail.charAt(0) - '0'] + "角"
					+ digit[rail.charAt(1) - '0'] + "分"; // 否则把角分转化出来
		}
		// 处理小数点前面的数
		char[] chDig = head.toCharArray(); // 把整数部分转化成字符数组
		char zero = '0'; // 标志'0'表示出现过0
		byte zeroSerNum = 0; // 连续出现0的次数
		for (int i = 0; i < chDig.length; i++) { // 循环处理每个数字
			int idx = (chDig.length - i - 1) % 4; // 取段内位置
			int vidx = (chDig.length - i - 1) / 4; // 取段位置
			if (chDig[i] == '0') { // 如果当前字符是0
				zeroSerNum++; // 连续0次数递增
				if (zero == '0') { // 标志
					zero = digit[0];
				} else if (idx == 0 && vidx > 0 && zeroSerNum < 4) {
					prefix += vunit[vidx - 1];
					zero = '0';
				}
				continue;
			}
			zeroSerNum = 0; // 连续0次数清零
			if (zero != '0') { // 如果标志不为0,则加上,例如万,亿什么的
				prefix += zero;
				zero = '0';
			}
			prefix += digit[chDig[i] - '0']; // 转化该数字表示
			if (idx > 0)
				prefix += hunit[idx - 1];
			if (idx == 0 && vidx > 0) {
				prefix += vunit[vidx - 1]; // 段结束位置应该加上段名如万,亿
			}
		}

		if (prefix.length() > 0)
			prefix += '圆'; // 如果整数部分存在,则有圆的字样
		return prefix + suffix; // 返回正确表示
	}

	public static void main(String[] args) {
		System.out.println(StringUtil.hangeToBig("0.54"));
	}

}
