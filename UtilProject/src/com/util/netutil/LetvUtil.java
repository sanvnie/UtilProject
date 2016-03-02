package com.util.netutil;

import java.io.IOException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import com.util.securityutil.MD5;

public class LetvUtil {
	
	public static void main(String[] args) {
		String url="http://yunpian.com/v1/sms/send.json";
		Map params=new HashMap();
		params.put("apikey", "2661d34cad9d1e5da9251120551d60c5");
		params.put("mobile", "13705974461");
		params.put("text", "欢迎您的到来");
		
	
		try {
			//String res=HttpUtil.get(reqUrl);
			String res=HttpUtil.postForm(url,params);
			System.out.println(res);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	 /** 
     * 使用 Map按key进行排序 
     * @param map 
     * @return 
     */  
    public static Map<String, String> sortMapByKey(Map<String, String> map) {  
        if (map == null || map.isEmpty()) {  
            return null;  
        }  
        Map<String, String> sortMap = new TreeMap<String, String>(new MapKeyComparator());  
        sortMap.putAll(map);  
        return sortMap;  
    }  
}

//比较器类  
 class MapKeyComparator implements Comparator<String>{  
    public int compare(String str1, String str2) {  
        return str1.compareTo(str2);  
    }  
}  
