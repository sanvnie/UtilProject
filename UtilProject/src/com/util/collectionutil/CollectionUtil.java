package com.util.collectionutil;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import com.util.stringutil.StringUtil;

/**
 * 各种常用集合的工具：Map/List/Set
 * 
 */
public class CollectionUtil {

	/**
	 * 从映射中取出一个字符串类型的值，如果isRequired=true而map中又找不到则抛出异常
	 */
	public static String getMapString(Map map, String key, boolean isRequired)
			throws ClassCastException, Exception {

		String value = (String) map.get(key);

		if (isRequired && value == null) {
			throw new Exception();
		}
		return value;

	}

	/**
	 * Map转换成字符串，主要用于打印调试信息
	 * 
	 * @param map
	 * @return
	 */
	public static String map2String(Map map) {
		return map2String(map, "", "", "", true, "=");
	}

	/**
	 * Map转换成字符串，主要用于打印调试信息
	 * 
	 * @return
	 */
	public static String map2String(Map map, String head, String entryPrefix,
			String foot, boolean isOneItemPl, String kvSep) {

		if (map == null) {
			return null;
		}
		String lineSeparator = (String) System.getProperty("line.separator");
		StringBuffer buff = new StringBuffer();
		if (head != null && !head.equals("")) {
			buff.append(head);

			if (isOneItemPl) {
				buff.append(lineSeparator);
			}
		}
		if (entryPrefix == null) {
			entryPrefix = "";
		}
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			Entry entry = (Entry) iterator.next();
			buff.append(entryPrefix).append(entry.getKey()).append(kvSep)
					.append(entry.getValue());

			if (isOneItemPl) {
				buff.append(lineSeparator);
			}
		}

		if (foot != null && !foot.equals("")) {
			buff.append(foot);
			if (isOneItemPl) {
				buff.append(lineSeparator);
			}
		}
		return buff.toString();
	}

	/**
	 * 将一个Map转成名值对形式的字符串，如：key1=value1&key2=value2
	 */
	public static String map2String(Map map, String fieldSep, String kvSep) {
		if (map == null) {
			return "";
		}
		StringBuffer buff = new StringBuffer();

		boolean first = true;
		for (Iterator iterator = map.entrySet().iterator(); iterator.hasNext();) {
			if (first) {
				first = false;
			} else {
				buff.append(fieldSep);
			}

			Entry entry = (Entry) iterator.next();
			buff.append(entry.getKey()).append(kvSep).append(entry.getValue());

		}

		return buff.toString();

	}
	
	/**
	 * 将一个形如key1=value1&key2=value2...的字符串转换成Map映射。
	 * 
	 */
	public static Map<String, String> string2Map(String src) {
		return string2Map(src, String.class, String.class, "&", "=");
	}

	/**
	 * 将一个形如key1=value1&key2=value2...的字符串转换成Map映射。
	 * 注意：key和value只支持类型为String,Long,Integer,Float,Double！
	 * 
	 */
	public static <K extends Object, V extends Object> Map<K, V> string2Map(
			String src, Class<K> keyClass, Class<V> valueClass,
			String fieldDelimiter, String keyValueDelimiter) {
		Map<K, V> result = new HashMap<K, V>();

		if (src == null || src.trim().equals("")) {
			return result;
		}

		String[] fields = StringUtil.split(src, fieldDelimiter);
		for (int i = 0; i < fields.length; i++) {
			String[] keyValue = StringUtil.split(fields[i], keyValueDelimiter);
			String key = keyValue[0];
			String value = keyValue[1];

			K objKey = null;
			V objValue = null;

			if (keyClass == String.class) {
				objKey = (K) key;
			} else if (keyClass == Integer.class) {
				objKey = (K) Integer.valueOf(key);
			} else if (keyClass == Long.class) {
				objKey = (K) Long.valueOf(key);
			} else if (keyClass == Float.class) {
				objKey = (K) Float.valueOf(key);
			} else if (keyClass == Double.class) {
				objKey = (K) Double.valueOf(key);
			} else {
				return null;
			}

			if (valueClass == String.class) {
				objValue = (V) value;
			} else if (valueClass == Integer.class) {
				objValue = (V) Integer.valueOf(value);
			} else if (valueClass == Long.class) {
				objValue = (V) Long.valueOf(value);
			} else if (valueClass == Float.class) {
				objValue = (V) Float.valueOf(value);
			} else if (valueClass == Double.class) {
				objValue = (V) Double.valueOf(value);
			} else {
				return null;
			}

			result.put(objKey, objValue);

		}

		return result;
	}

}
