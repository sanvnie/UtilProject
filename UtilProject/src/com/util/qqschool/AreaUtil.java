package com.util.qqschool;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.util.stringutil.JsonUtil;

public class AreaUtil {  
	  
    private final static Map<Integer,String> provinces=new HashMap<Integer,String>();  
      
    static{  
        provinces.put(1, "北京");  
        provinces.put(2, "上海");  
        provinces.put(3, "天津");  
        provinces.put(4, "重庆");  
        provinces.put(5, "河北");  
        provinces.put(6, "山西");  
        provinces.put(7, "河南");  
        provinces.put(8, "辽宁");  
        provinces.put(9, "吉林");  
        provinces.put(10, "黑龙江");  
        provinces.put(11, "内蒙古");  
        provinces.put(12, "江苏");  
        provinces.put(13, "山东");  
        provinces.put(14, "安徽");  
        provinces.put(15, "浙江");  
        provinces.put(16, "福建");  
        provinces.put(17, "湖北");  
        provinces.put(18, "湖南");  
        provinces.put(19, "广东");  
        provinces.put(20, "广西");  
        provinces.put(21, "江西");  
        provinces.put(22, "四川");  
        provinces.put(23, "海南");  
        provinces.put(24, "贵州");  
        provinces.put(25, "云南");  
        provinces.put(26, "西藏");  
        provinces.put(27, "陕西");  
        provinces.put(28, "甘肃");  
        provinces.put(29, "青海");  
        provinces.put(30, "宁夏");  
        provinces.put(31, "新疆");  
        provinces.put(32, "台湾");  
        provinces.put(42, "香港");  
        provinces.put(43, "澳门");  
        provinces.put(84, "钓鱼岛");  
    }  
    private static final String area_pattern="\\[.+?\\]";  
    public static String areaUrl="http://passport.jd.com/emReg/AjaxService.aspx?action=GetAreas&level=[level]&parentId=[parentId]";  
    
    private static String getTextContent(String toUrl,String encoding){
    	StringBuilder pageHTML = new StringBuilder();
        try {
            URL url = new URL(toUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "MSIE 7.0");
            BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), encoding));
            String line = null;
            while ((line = br.readLine()) != null) {
                pageHTML.append(line);
                pageHTML.append("\r\n");
            }
            connection.disconnect();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pageHTML.toString();
    }
    /** 
     *  
     * @author YLPan 
     * @date 2013-5-15 
     * @param level 1 获取市 2获取区县 
     * @param parentId 
     * @return 
     * @throws Exception 
     */  
    public static List<Map<String,Object>> getAreas(Integer level,Integer parentId) throws Exception{  
        String cityUrl=areaUrl.replaceAll("\\[level\\]",String.valueOf(level)).replaceAll("\\[parentId\\]", String.valueOf(parentId));  
        System.out.println("cityUrl:"+cityUrl);  
        String cityJson=getTextContent(cityUrl, "gbk");  
        Pattern pattern = Pattern.compile(area_pattern);  
        Matcher matcher = pattern.matcher(cityJson);  
        if(matcher.find()){  
            cityJson=matcher.group();  
            List<Map<String,Object>> cityList=JsonUtil.getList4Json(cityJson, null);  
            return cityList;  
        }  
        return null;  
    }  
    public static void areaInit() throws Exception{  
        for(Entry<Integer,String> entry : provinces.entrySet()){  
            System.out.println("province:"+entry.getValue());  
                List<Map<String,Object>> cityList=getAreas(1,entry.getKey());  
                if(cityList==null)continue;  
                for(Map<String,Object> citymap : cityList){  
                    Integer cityId=(Integer)citymap.get("Id");  
                    String cityName=(String)citymap.get("Name");  
                    System.out.println("--cityName:"+cityName);  
                    List<Map<String,Object>> countyList=getAreas(2,cityId);  
                    if(countyList==null)continue;  
                        for(Map<String,Object> countyMap : countyList){  
                            Integer countyId=(Integer)countyMap.get("Id");  
                            String countyName=(String)countyMap.get("Name");  
                            System.out.println("----countyName:"+countyName);  
                    }  
            }  
        }  
    }  
    public static void main(String[] args) {  
        try {  
            areaInit();  
        } catch (Exception e) {  
            e.printStackTrace();  
        }  
    }  
}  