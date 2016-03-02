package com.util.qqschool;
import static com.util.qqschool.Utils.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.RandomAccessFile;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
 
/**
 * 读取省市和小学数据
 * <p>
 * 根据省市获取区县
 * @author   yangjian1004
 * @Date     Jun 17, 2014    
 */
public class SchoolFromPengYou {
 
    public static String fileName = "D:/xuexiao.sql";
    public static String areaFileName = "D:/area.sql";
    static Map<String, List<String>> locationMap = map();
 
    public static void main(String[] args) {
                //读取省市区 存入locationMap 
        URL is = SchoolFromPengYou.class.getResource("location.js");
        readFileByLines(is.getPath());
 
                //对省市区进行排序，可以省略
        List<String> keyList = new ArrayList<String>();
        Set<String> keySet = locationMap.keySet();
        keyList.addAll(keySet);
        Collections.sort(keyList);//按编号顺序获取
        Iterator<String> iterator = keyList.iterator();
        List<String> cityList = new ArrayList<String>();
        System.out.println(keyList);
        String pid = null;
        while (iterator.hasNext()) {
            pid = iterator.next();
            if (pid == null || "".equals(pid)) {
                continue;
            }
            cityList = locationMap.get(pid);
            if (cityList == null || cityList.size() == 0) {
                continue;
            }
            System.err.println("开始抓取数据...");
            //*****循环一次获取所有学校（特别是小学） 时间非常漫长，建议单独获取*****
            for (String cityid : cityList) {
                getPengYou(pid, cityid);
            }
        }
    }
 
    private static void getLocation(String str1) {
 
        //区域编码省市
        Pattern pp = Pattern.compile("(\\d{2})");
        Matcher pm = pp.matcher(str1);
        boolean pResult = pm.find();
 
        //区域编码区县
        Pattern p = Pattern.compile("(\\d{4})");
        Matcher m = p.matcher(str1);
        boolean cResult = m.find();
        String pno = null;
        String no = null;
        if (cResult && pResult) {
            pno = pm.group(1);
            no = m.group(1);
            addMapList(locationMap, pno, no);
        } else if (!cResult && pResult) {
            pno = pm.group(1);
        }
        //区域名称
        String name = null;
        String regex = "([\u4e00-\u9fa5]+)";
        Matcher matcher = Pattern.compile(regex).matcher(str1);
        if (matcher.find()) {
            name = matcher.group(0);
            if (no == null) {
                no = pno;
                pno = "0";
            }
            //createLocation(name, no, pno);//可以一并保存区县市
        }
        System.out.println(pno + " " + no + " " + name);
    }
 
    /**
     * 省市区域 
     *
     * @param sName
     * @param no
     * @param pno
     * @Date     Aug 1, 2014 6:43:57 PM
     */
    public static void createLocation(String sName, String no, String pno) {
        StringBuffer sb = new StringBuffer();
        sb.append("insert into area (name,no,parentNo) values ('").append(decodeUnicode(sName));
        sb.append("','").append(no);
        sb.append("','").append(pno);
        sb.append("');\n");
        appendMethodA(areaFileName, sb.toString());
    }
 
    public static void readFileByLines(String fileName1) {
        File file = new File(fileName1);
        BufferedReader reader = null;
        try {
            System.out.println("以行为单位读取文件内容，一次读一行");
            reader = new BufferedReader(new FileReader(file));
            String tempString = null;
            //一次读一行，读入null时文件结束
            while ((tempString = reader.readLine()) != null) {
                if (tempString.indexOf("请选择") > -1 || "".equals(tempString)) {
                    continue;
                }
                getLocation(tempString);
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e1) {
                }
            }
        }
    }
 
    //区县市
    public static void getPengYou(String pid, String cityid) {
 
        String pageURL = "http://api.pengyou.com/json.php?cb=__i_28&mod=getdistrict&cityid=" + cityid
                + "&district_obj_name=_distinct&g_tk=2053360324";
        String html = getHTML(pageURL, "utf-8");
        int start = html.indexOf("district_arr");
        int end = html.indexOf("err");
        if (start > -1 && end > -1) {
            String cString = html.substring(start + 15, end - 4);
            String[] citys = cString.split(",");
            for (String city : citys) {
                String[] c = city.split(":");
                if (c != null && c.length == 2) {
                    String cityName = decodeUnicode(c[1]);
                    String areaId = c[0].substring(1, c[0].length() - 1);
                    String cName = cityName.substring(1, cityName.length() - 1);
                    //createLocation(cName, areaId, cityid);//保存区县市
//*****循环一次获取所有学校（特别是小学） 时间非常漫长，建议一个一个的获取*****
                    getXiaoXue(pid, cityid, areaId, cName, "__i_4", 6, 1);//6-1小学
                    getXiaoXue(pid, cityid, areaId, cName, "__i_3", 5, 2);//5-2初中
                    getXiaoXue(pid, cityid, areaId, cName, "__i_3", 4, 3);//4-3高中，中专或技校，
                    getXiaoXue(pid, cityid, areaId, cName, "__i_3", 3, 4);//3-4大学，专科或高职
                    getXiaoXue(pid, cityid, areaId, cName, "__i_3", 2, 5);//2-5硕士，
                    getXiaoXue(pid, cityid, areaId, cName, "__i_3", 1, 6);//1-6博士，
                }
            }
 
        }
    }
 
    //根据区县市获取小学数据
    @SuppressWarnings("unused")
    public static void getXiaoXue(String pid, String cityId, String areaId, String cityName, String cb, int schoolType,
            int type) {
        String district = "";
        if (type < 4) {
            district = "&district=" + areaId;
        }
        String url = "http://api.pengyou.com/json.php?cb=" + cb + "&mod=school&act=selector&schooltype=" + schoolType
                + "&country=0&province=" + pid + district + "&g_tk=2053360324";
        System.out.println(url);
        String school = getHTML(url, "UTF-8");
        Map<String, List<String>> schoolMap = map();
        Pattern schoolPattern = Pattern.compile("title=\\\\\"(.*?)\\\\\"");
        Matcher schoolMatcher = schoolPattern.matcher(school);
        while (schoolMatcher.find()) {
            String schName = schoolMatcher.group(1).trim();
            if (isEmpty(schName)) {
                continue;
            }
            addMapList(schoolMap, areaId, decodeUnicode(schName));
        }
        int entryCount = 0;
        StringBuffer sb = new StringBuffer();
        for (Entry<String, List<String>> entry : schoolMap.entrySet()) {
            String orgNo = entry.getKey();
            List<String> sNames = entry.getValue();
            for (String sName : sNames) {
                sb = new StringBuffer();
                sb.append("insert into school (name,orgNo,status,type) values ('").append(decodeUnicode(sName));
                sb.append("','").append(orgNo);
                sb.append("','").append(0);
                sb.append("','").append(type);
                sb.append("');\n");
                appendMethodA(fileName, sb.toString());
            }
            System.out.println(cityName + "地区学校：" + schoolMap.size() + "/" + (++entryCount));
        }
    }
 
    public static String decodeUnicode(String str) {
        Charset set = Charset.forName("UTF-16");
        Pattern p = Pattern.compile("\\\\u([0-9a-fA-F]{4})");
        Matcher m = p.matcher(str);
        int start = 0;
        int start2 = 0;
        StringBuffer sb = new StringBuffer();
        while (m.find(start)) {
            start2 = m.start();
            if (start2 > start) {
                String seg = str.substring(start, start2);
                sb.append(seg);
            }
            String code = m.group(1);
            int i = Integer.valueOf(code, 16);
            byte[] bb = new byte[4];
            bb[0] = (byte) ((i >> 8) & 0xFF);
            bb[1] = (byte) (i & 0xFF);
            ByteBuffer b = ByteBuffer.wrap(bb);
            sb.append(String.valueOf(set.decode(b)).trim());
            start = m.end();
        }
        start2 = str.length();
        if (start2 > start) {
            String seg = str.substring(start, start2);
            sb.append(seg);
        }
        return sb.toString();
    }
 
    public static String getHTML(String pageURL, String encoding) {
        StringBuilder pageHTML = new StringBuilder();
        try {
            URL url = new URL(pageURL);
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
    * 追加文件：使用RandomAccessFile
    */
    @SuppressWarnings({ "null", "unused" })
    public static void appendMethodA(String fileName1, String content) {
        if (content == null || "".equals(content)) {
            return;
        }
        try {
            // 打开一个随机访问文件流，按读写方式
            RandomAccessFile randomFile = new RandomAccessFile(fileName1, "rw");
            if (randomFile == null) {
                return;
            }
            // 文件长度，字节数
            long fileLength = randomFile.length();
            //将写文件指针移到文件尾。
            randomFile.seek(fileLength);
            randomFile.write(content.getBytes("utf-8"));
            randomFile.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

