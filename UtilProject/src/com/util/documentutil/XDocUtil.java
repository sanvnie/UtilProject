package com.util.documentutil;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import com.hg.xdoc.XDocService;

//JAVA生成公文文档
public class XDocUtil {
	public static void main(String[] args) {
		 try {
	            XDocService service = new XDocService();
	            Map<String, Object> param = new HashMap<String, Object>();
	            param.put("份号", "1");
	            param.put("密级", "秘密");
	            param.put("保密期限", "一年");
	            param.put("紧急程度", "特急");
	            param.put("发文机关", "XDOC智能云文档平台");
	            param.put("机关代字", "农");
	            param.put("年份", "2015");
	            param.put("发文顺序号", "1");
	            param.put("标题", "关于XDOC智能云文档支持标准公文的通知");
	            param.put("正文", "　　XDOC公文模板严格遵循GB/9704-2012《党政机关公文格式》。部署在XDOC云服务器是上，免费提供给大家使用。\n"
	                            + "　　http://myxdoc.sohuapps.com");
	            param.put("发文机关署名", "XDOC办公室");
	            param.put("成文日期", "2015-01-01");
	            param.put("印章", "http://www.nhzx.org/2008/NewsFile/daa1cd1be41148eea1c69bb19b71b1a0/35d35feabbf943f087ad130055044de8.002.png");
	            param.put("抄送机关", "XDOC老用户、新用户、爱好者。");
	            param.put("印发机关", "XDOC办公室");
	            param.put("印发日期", "2015-01-01");
	            param.put("附注", "");
	            service.run("./10001.xdoc", param, new File("d:/10001.pdf"));
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	}
}
