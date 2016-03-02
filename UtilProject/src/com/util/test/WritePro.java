package com.util.test;

import java.util.Map;

import com.util.documentutil.ExcelUtil;
import com.util.documentutil.PropertiesUtil;

public class WritePro {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Map<String,Object> map=ExcelUtil.readExcel("c://a.xls");
		System.out.println(map);
		PropertiesUtil.writePropertiesFile("c://kk.properties", map);
	}

}
