package com.util.qqschool;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
*从国家统计局获取最新的县及县以上行政区划
 */
public class RegionUtil {
	private static final String separator = ",";
    private static List<String> getData() throws Exception {
        List<String> retList = new ArrayList<String>();
 
        Document doc = Jsoup.connect("http://www.stats.gov.cn/tjsj/tjbz/xzqhdm/201504/t20150415_712722.html").get();
        Elements ps = doc.select(".TRS_PreAppend p");
        for (Element e : ps) {
            Elements spans = e.select(">span");
            Element firstE = spans.first();
            Element secondE = spans.get(1);
            String key = firstE.text().trim().replace(" ", "").replace("　", "");
            if (key.endsWith("0000")) {
                key = key.substring(0, 2);
            } else if (key.endsWith("00")) {
                key = key.substring(0, 4);
            }
            retList.add(key + separator + secondE.text().trim().replace(" ", "").replace("　", ""));
        }
        return retList;
    }
    
    public static void main(String[] args) {
		List list;
		try {
			list = RegionUtil.getData();
			System.out.println(list);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
