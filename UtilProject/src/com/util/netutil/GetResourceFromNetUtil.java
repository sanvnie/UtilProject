package com.util.netutil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * 获取网上资源工具类
 *
 */
public class GetResourceFromNetUtil {
	/**
	 * 从互联网上获得一张图片，并复制到本地
	 * @param url 图片地址
	 * @param filename 要保存的文件名
	 */
	public static void getPicFromNet(String url,String filename){
		 try {
	            URL picUrl;
	            HttpURLConnection conn = null;
	            InputStream is = null;
	                picUrl = new URL(url);
	                conn = (HttpURLConnection) picUrl.openConnection();
	                conn.setConnectTimeout(20000);
	                conn.setReadTimeout(20000);
	                conn.connect();
	                // 获取图片大小
	                int picSize = conn.getContentLength();
	                is = conn.getInputStream();
	                    File folder = new File("c:/image/");
	                    if(!folder.exists()){
	                        folder.mkdir();
	                    }
	                    File file = new File("c:/image/"+filename);
	                    OutputStream os = new FileOutputStream(file);
	 
	                    final int buffer_size = 1024;
	                    byte[] bytes = new byte[buffer_size];
	                    for (;;) {
	                        int count = is.read(bytes, 0, buffer_size);
	                        if (count == -1)
	                            break;
	                        os.write(bytes, 0, count);
	                    }
	                    os.close();
	        } catch (MalformedURLException e) {
	            e.printStackTrace();
	        } catch (FileNotFoundException e) {
	            e.printStackTrace();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	     
	}
	
	/***
	 * 获取网页内容
	 * @param url
	 * @param needBR
	 * @return
	 * @throws IOException
	 */
	public static String getContentByURL(String url, boolean needBR) throws IOException {
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		conn.setConnectTimeout(1000*30);
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			is =conn.getInputStream();
			isr = new InputStreamReader(is, "GBK");
			br = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
				if(needBR)
					sb.append("\n");
			}
			sb.deleteCharAt(sb.lastIndexOf("*"));
			return sb.toString();
		}catch(Exception e){
			return null;
		} finally {
			if(br != null) br.close();
			if(isr != null) isr.close();
			if(is != null) is.close();
			conn.disconnect();
		}
	}
	
	public static String getContentByURL(String url, boolean needBR,String encode) throws IOException {
		URL u = new URL(url);
		HttpURLConnection conn = (HttpURLConnection) u.openConnection();
		conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 7.0; Windows NT 5.1)");
		conn.setConnectTimeout(1000*30);
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		try {
			is =conn.getInputStream();
			isr = new InputStreamReader(is, encode);
			br = new BufferedReader(isr);
			StringBuilder sb = new StringBuilder();
			String line = null;
			while((line = br.readLine()) != null) {
				sb.append(line);
				if(needBR)
					sb.append("\n");
			}
			sb.deleteCharAt(sb.lastIndexOf("*"));
			return sb.toString();
		}catch(Exception e){
			return null;
		} finally {
			if(br != null) br.close();
			if(isr != null) isr.close();
			if(is != null) is.close();
			conn.disconnect();
		}
	}
	public static void main(String[] args) {
		GetResourceFromNetUtil.getPicFromNet("http://static9.pplive.cn/pub/login/v_20141215173208/images/sprite_log.png", "cornerpoint.png");
	}
}
