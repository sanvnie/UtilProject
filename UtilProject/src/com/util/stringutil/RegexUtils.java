package com.util.stringutil;

/**
 * 常用的正则表达式工具类
 *
 */
public class RegexUtils {
	/** 由汉字组成的字符串 */
	public static final String Chinese = "^[\u4e00-\u9fa5]+$";

	/** 由数字、26个英文字母或中文组成的字符串 */
	public static final String LetterAndNumberAndChinese = "^[A-Za-z0-9\u4e00-\u9fa5]+$";

	/** 由数字、26个英文字母或者下划线组成的字符串 */
	public static final String LetterAndNumberAndUnderline = "^\\w+$";

	/** 由数字和26个英文字母组成的字符串 */
	public static final String LetterAndNumber = "^[A-Za-z0-9]+$";

	/** 由数字和26个小写英文字母组成的字符串 */
	public static final String LowerLetterAndNumber = "^[a-z0-9]+$";

	/** 由数字和26个大写英文字母组成的字符串 */
	public static final String UpperLetterAndNumber = "^[A-Z0-9]+$";

	/** 由26个英文字母组成的字符串 */
	public static final String Letter = "^[A-Za-z]+$";

	/** 由26个英文字母的小写组成的字符串 */
	public static final String LowerLetter = "^[a-z]+$";

	/** 由26个英文字母的大写组成的字符串 */
	public static final String UpperLetter = "^[A-Z]+$";

	/** 由数字的字符串 */
	public static final String Number = "^[0-9]+\\s*$";

	/** email地址 */
	public static final String Email = "^[\\w-]+(\\.[\\w-]+)*@[\\w-]+(\\.[\\w-]+)+$";

	/** url */
	public static final String Url = "^[a-zA-z]+://(\\w+(-\\w+)*)(\\.(\\w+(-\\w+)*))*(\\?\\S*)?$";

	/** 正整数 */
	public static final String Gt0Integer = "^[0-9]*[1-9][0-9]*$";

	/** 非负数（正数 + 0） */
	public static final String Ge0Double = "^\\d+(\\.\\d+)?$";

	/** 非数字 */
	public static final String UnNumber = "[^0-9]+";

	/** 身份证 */
	public static final String IdCard = "^(([0-9]{14}[xX0-9]{1})|([0-9]{17}[xX0-9]{1}))$";
	
	/** 手机号 */
	public static final String MobilPhoneNum="^((13[0-9])|(15[^4,\\D])|(18[0,5-9]))\\d{8}$";
	
	/**是否为图像格式（含路径文件名） */
	public static final String ICON_REGEXP = "^(/{0,1}//w){1,}//.(gif|dmp|png|jpg)$|^//w{1,}//.(gif|dmp|png|jpg)$";
	
	/** 日期（XXXX-XX-XX或XXXX-X-X）*/
	public static final String DATE_BARS_REGEXP = "^((((19){1}|(20){1})\\d{2})|\\d{2})-[0,1]?\\d{1}-[0-3]?\\d{1}$";
	
	/** 日期（XXXX/XX/XX）*/
	public static final String DATE_SLASH_REGEXP = "^[0-9]{4}/(((0[13578]|(10|12))/(0[1-9]|[1-2][0-9]|3[0-1]))|(02-(0[1-9]|[1-2][0-9]))|((0[469]|11)/(0[1-9]|[1-2][0-9]|30)))$";
}
