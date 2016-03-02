package com.util.otherutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * DOS中的PING工具类
 *
 */
public class PingUtil {
	
	/**
	 * PING一个IP是否可以连接
	 * @param ip IP地址
	 */
	public static boolean isThrough(String ip) throws IOException{
		Process process = Runtime.getRuntime().exec("ping "+ip);   
        InputStreamReader r = new InputStreamReader(process.getInputStream());   
        LineNumberReader returnData = new LineNumberReader(r);   
  
        String returnMsg="";   
        String line = "";   
        while ((line = returnData.readLine()) != null) {
        	System.out.println(line);
        	Pattern pattern=Pattern.compile("(=\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);    
            Matcher matcher=pattern.matcher(line);        
            while(matcher.find()){
            	return true; 
            }
        }  
		return false;
	}
	
	
	/**
	 * Ping一个IP是否可以到达（相当于在cmd中输入类似“ping 192.168.0.1 -n 10 -w 5”这种命令）
	 * @param remoteIpAddress 远程IP
	 * @param pingTimes ping的次数
	 * @param timeOut 间隔时间
	 * @return
	 */
	public static boolean isReachable(String remoteIpAddress,int pingTimes,int timeOut) {
        BufferedReader in = null;
        Runtime r = Runtime.getRuntime();
        String pingCommand = "ping " + remoteIpAddress + " -n "+pingTimes+" -w " + timeOut;
        
        try {
            Process p = r.exec(pingCommand);
            if (p == null) {
                return false;
            }
            in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            
            int connectedCount=0;
            String line = null;
            while ((line = in.readLine()) != null) {
                connectedCount+=getCheckResult(line);
            }
            
            return connectedCount==pingTimes;
        } catch (Exception ex) {
            ex.printStackTrace();
            
            return false;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    private static int getCheckResult(String line){
        Pattern pattern=Pattern.compile("(=\\d+ms)(\\s+)(TTL=\\d+)", Pattern.CASE_INSENSITIVE);    
        Matcher matcher=pattern.matcher(line);        
        while(matcher.find()){
            return 1;
        }
        
        return 0;
    }
    
    /**
     * 判断当前网络连接的状态
     */
    public static boolean isActivty(){
    	StringBuffer buf = new StringBuffer();
        String s = "";
        Process process;
        try{
            process = Runtime.getRuntime().exec( "cmd /c " + "ping www.baidu.com" );
            BufferedReader br = new BufferedReader( new InputStreamReader(
            process.getInputStream() ) );
            while ( ( s = br.readLine() ) != null ){
                buf.append( s + "\r\n" );
            }

            process.waitFor();
            System.out.println( buf );
        } catch (Exception e){
            e.printStackTrace();
        }
        String host = "www.baidu.com";
        	int timeOut = 3000; //超时应该在3钞以上
        	try {
				return InetAddress.getByName(host).isReachable(timeOut);
			} catch (UnknownHostException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
			return false;
    }
  
}
