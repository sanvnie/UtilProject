package com.util.netutil;

import java.net.InetAddress;
import java.net.InterfaceAddress;
import java.net.NetworkInterface;  
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;  
import java.util.List;
  
/** 
 * 获取MAC地址工具类 
 */  
public class MacUtil {  
    private MacUtil() {  
    }  
  
    /** 
     * 按照"XX-XX-XX-XX-XX-XX"格式，获取本机MAC地址 
     * @return 
     * @throws Exception 
     */  
    public static String getMacAddress() throws Exception{  
        Enumeration<NetworkInterface> ni = NetworkInterface.getNetworkInterfaces();  
          
        while(ni.hasMoreElements()){  
            NetworkInterface netI = ni.nextElement();  
              
            byte[] bytes = netI.getHardwareAddress(); 
           
            if(netI.isUp() && netI != null && bytes != null && bytes.length == 6){  
                StringBuffer sb = new StringBuffer();  
                for(byte b:bytes){  
                     //与11110000作按位与运算以便读取当前字节高4位  
                     sb.append(Integer.toHexString((b&240)>>4));  
                     //与00001111作按位与运算以便读取当前字节低4位  
                     sb.append(Integer.toHexString(b&15));  
                     sb.append("-");  
                 }  
                 sb.deleteCharAt(sb.length()-1);  
                 return sb.toString().toUpperCase();   
            }  
        }  
        return null;  
    }  
    
    /**
     * 获取本地网卡信息
     * @param netint
     * @throws SocketException
     */
    public static void displayInterfaceInformation(NetworkInterface netint)
            throws SocketException {
        System.out.printf("Display name: %s\n", netint.getDisplayName());
        System.out.printf("Name: %s\n", netint.getName());
        Enumeration<InetAddress> inetAddresses = netint.getInetAddresses();
        for (InetAddress inetAddress : Collections.list(inetAddresses)) {
        	System.out.printf("InetAddress: %s\n", inetAddress);
        }
        System.out.printf("\n");
    }
     
    public static void getLocalAllIp(){
    	try {
            for (Enumeration<NetworkInterface> interfaces = NetworkInterface.getNetworkInterfaces(); interfaces.hasMoreElements();) {
                NetworkInterface networkInterface = interfaces.nextElement();
                if (networkInterface.isLoopback() || networkInterface.isVirtual() || !networkInterface.isUp()) {
                    continue;
                }
                List<InterfaceAddress> addresses = networkInterface.getInterfaceAddresses();
                for (InterfaceAddress interfaceAddress : addresses) {
                    String ip = interfaceAddress.getAddress().getHostAddress();
                    if (ip.length() < 20) {
                        System.out.println("IP:" + ip);
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
    }
    
    public static void main(String args[]) throws SocketException {
//        Enumeration<NetworkInterface> nets = NetworkInterface
//                .getNetworkInterfaces();
//        for (NetworkInterface netint : Collections.list(nets))
//            displayInterfaceInformation(netint);
//    	MacUtil.getLocalAllIp();
//    	try {
//			System.out.println(MacUtil.getMacAddress());
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
    	InetAddress netAddress =IPUtil.catchLocalIP();
    	NetworkInterface netint=NetworkInterface.getByInetAddress(netAddress);
    	MacUtil.displayInterfaceInformation(netint);
    	
    }
  
}  
