package com.util.documentutil;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
/**
 * 读取Properties文件
 *
 */
public class PropertiesUtil {
	
	/**
	 * 读取properties文件
	 * @param fileName
	 * @return
	 */
	public static HashMap readProperties(String fileName){
		Properties pro = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream(fileName.trim());
		try {
			pro.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return conver2Map(pro);
	}
	
	public static HashMap conver2Map(Properties pro){
		HashMap map= new HashMap();
		Iterator iter = pro.keySet().iterator();
		for(;iter.hasNext();){
			String key = (String) iter.next();
			String value = pro.getProperty(key);
			map.put(key, value);
		}
		return map;
	}
	
	
	 //写资源文件，含中文  
    public static void writePropertiesFile(String filename,Map<String,Object> contentmap)  
    {  
        Properties properties = new Properties();  
        try  
        {  
            OutputStream outputStream = new FileOutputStream(filename);
            for (Map.Entry<String, Object> entry:contentmap.entrySet()) {
				properties.setProperty(entry.getKey(), (String) entry.getValue());
			};  
            properties.store(outputStream, "author: shixing_11@sina.com");  
            outputStream.close();  
        }  
        catch (IOException e)  
        {  
            e.printStackTrace();  
        }  
    }  
}
