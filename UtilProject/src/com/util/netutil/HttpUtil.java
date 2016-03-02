package com.util.netutil;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.IllegalCharsetNameException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;


 
/**
**封装的HTTP请求工具类（直接用的JDK默认包即可，无需导入其他外延包  当然用HttpClient等jar包亦可）
 */
public class HttpUtil {
    /**
     * 发送GET请求
     */
    static public String get(String url) throws IOException {
        return get(url, null);
    }
 
    /**
     *发送GET请求
     * @param url 请求地址
     * @param headers 请求头
     * @return response 响应
     * @throws IOException
     */
    static public String get(String url, Map<String, String> headers) throws IOException {
        return fetch("GET", url, null, headers);
    }
 
    /**
     *发送POST请求
     * @param url 请求地址
     * @param body 请求体
     * @param headers 请求头
     * @return response 响应
     * @throws IOException
     */
    static public String post(String url, String body, Map<String, String> headers) throws IOException {
        return fetch("POST", url, body, headers);
    }
 
    /**
     *发送POST请求
     * @param url
     * @param body
     * @return response
     * @throws IOException
     */
    static public String post(String url, String body) throws IOException {
        return post(url, body, null);
    }
 
    /**
     * 表单提交
     * @param url
     * @param params
     * @return response
     * @throws IOException
     */
    static public String postForm(String url, Map<String, String> params) throws IOException {
        return postForm(url, params, null);
    }
 
    /**
     * @param url
     * @param params
     * @param headers
     * @return response 
     * @throws IOException
     */
    static public String postForm(String url, Map<String, String> params, Map<String, String> headers) throws IOException {
        // set content type
        if (headers == null) {
            headers = new HashMap<String, String>();
        }
        headers.put("Content-Type", "application/x-www-form-urlencoded;charset=utf-8;");
        headers.put("Accept", "application/json;charset=utf-8;");
        // parse parameters
        String body = "";
        if (params != null) {
            boolean first = true;
            for (String param : params.keySet()) {
                if (first) {
                    first = false;
                } else {
                    body += "&";
                }
                String value = params.get(param);
                body += URLEncoder.encode(param, "UTF-8") + "=";
                body += URLEncoder.encode(value, "UTF-8");
            }
        }
 
        return post(url, body, headers);
    }
 
    /**
     * Send a put request
     * @param url
     * @param body
     * @param headers
     * @return response
     * @throws IOException
     */
    static public String put(String url, String body, Map<String, String> headers) throws IOException {
        return fetch("PUT", url, body, headers);
    }
 
    /**
     * @param url
     * @return response
     * @throws IOException
     */
    static public String put(String url, String body) throws IOException {
        return put(url, body, null);
    }
 
    /**
     * Send a delete request
     * @param url
     * @param headers
     * @return response 
     * @throws IOException
     */
    static public String delete(String url, Map<String, String> headers) throws IOException {
        return fetch("DELETE", url, null, headers);
    }
 
    /**
     * Send a delete request
     * @param url
     * @return response 
     * @throws IOException
     */
    static public String delete(String url) throws IOException {
        return delete(url, null);
    }
 
    /**
     * Append query parameters to given url
     * @param url
     * @param params
     * @return url 
     * @throws IOException
     */
    static public String appendQueryParams(String url, Map<String, String> params) throws IOException {
        String fullUrl = new String(url);
 
        if (params != null) {
            boolean first = (fullUrl.indexOf('?') == -1);
            for (String param : params.keySet()) {
                if (first) {
                    fullUrl += '?';
                    first = false;
                } else {
                    fullUrl += '&';
                }
                String value = params.get(param);
                fullUrl += URLEncoder.encode(param, "UTF-8") + '=';
                fullUrl += URLEncoder.encode(value, "UTF-8");
            }
        }
 
        return fullUrl;
    }
 
    /**
     * @param url
     * @return params
     * @throws IOException
     */
    static public Map<String, String> getQueryParams(String url) throws IOException {
        Map<String, String> params = new HashMap<String, String>();
 
        int start = url.indexOf('?');
        while (start != -1) {
            // read parameter name
            int equals = url.indexOf('=', start);
            String param = "";
            if (equals != -1) {
                param = url.substring(start + 1, equals);
            } else {
                param = url.substring(start + 1);
            }
 
            // read parameter value
            String value = "";
            if (equals != -1) {
                start = url.indexOf('&', equals);
                if (start != -1) {
                    value = url.substring(equals + 1, start);
                } else {
                    value = url.substring(equals + 1);
                }
            }
 
            params.put(URLDecoder.decode(param, "UTF-8"), URLDecoder.decode(value, "UTF-8"));
        }
 
        return params;
    }
 
    /**
     * Returns the url without query parameters
     * @param url
     * @return url Url without query parameters
     * @throws IOException
     */
    static public String removeQueryParams(String url) throws IOException {
        int q = url.indexOf('?');
        if (q != -1) {
            return url.substring(0, q);
        } else {
            return url;
        }
    }
 
    /**
     * Send a request
     * @param method
     * @param url
     * @param body
     * @param headers
     * @return response Response as string
     * @throws IOException
     */
    static public String fetch(String method, String url, String body, Map<String, String> headers) throws IOException {
        // connection
        URL u = new URL(url);
        HttpURLConnection conn = (HttpURLConnection) u.openConnection();
        conn.setConnectTimeout(10000);
        conn.setReadTimeout(10000);
 
        // method
        if (method != null) {
            conn.setRequestMethod(method);
        }
 
        // headers
        if (headers != null) {
            for (String key : headers.keySet()) {
                conn.addRequestProperty(key, headers.get(key));
            }
        }
 
        // body
        if (body != null) {
            conn.setDoOutput(true);
            OutputStream os = conn.getOutputStream();
            os.write(body.getBytes());
            os.flush();
            os.close();
        }
        boolean isGzip = false;
        String charset = null;
        // response
        if (conn.getResponseCode() == HttpURLConnection.HTTP_OK) {
 
            // for (int i = 0;; i++) {
            // String headerName = conn.getHeaderFieldKey(i);
            String contentEncoding = conn.getHeaderField("Content-Encoding");
            if (contentEncoding != null && contentEncoding.equalsIgnoreCase("gzip")) {
                isGzip = true;
            }
            String contentType = conn.getHeaderField("Content-Type");
            charset = getCharsetFromContentType(contentType);
        }
        InputStream is = conn.getInputStream();
        String response = streamToString(is, charset, isGzip);
        is.close();
 
        // handle redirects
        if (conn.getResponseCode() == 301) {
            String location = conn.getHeaderField("Location");
            return fetch(method, location, body, headers);
        }
 
        return response;
    }
 
    //
    /**
     * 字符集编码
     */
    private static final Pattern charsetPattern = Pattern.compile("(?i)\\bcharset=\\s*(?:\"|')?([^\\s,;\"']*)");
 
    /**
     * 检测网页编码
     * 
     * @param contentType
     * @return
     */
    static String getCharsetFromContentType(String contentType) {
        if (contentType == null)
            return null;
        Matcher m = charsetPattern.matcher(contentType);
        if (m.find()) {
            String charset = m.group(1).trim();
            charset = charset.replace("charset=", "");
            if (charset.length() == 0)
                return null;
            try {
                if ("gb2312".equalsIgnoreCase(charset) || "gbk".equalsIgnoreCase(charset)) {
                    charset = "gb18030";
                }
                if (Charset.isSupported(charset))
                    return charset;
                charset = charset.toUpperCase(Locale.ENGLISH);
                if (Charset.isSupported(charset))
                    return charset;
            } catch (IllegalCharsetNameException e) {
                // if our advanced charset matching fails.... we just take the default
                return null;
            }
        }
        return null;
    }
 
    /**
     * Read an input stream into a string
     * 
     * @param in
     * @return
     * @throws IOException
     */
    static public String streamToString(InputStream is, String charset, boolean isGzip) throws IOException {
        if (charset == null || "".equals(charset.trim())) {
            charset = "utf-8";
        }
        BufferedReader reader = null;
        if (isGzip) {
            reader = new BufferedReader(new InputStreamReader(new GZIPInputStream(is), charset));// 设置编码,否则中文乱码
        } else {
            reader = new BufferedReader(new InputStreamReader(is, charset));// 设置编码,否则中文乱码
        }
        String line = "";
        StringBuffer result = new StringBuffer();
        while (null != (line = reader.readLine())) {
            if (!"".equals(line)) {
                result.append(line).append("\r\n");
            }
        }
        return result.toString();
    }
    
    /**
     * 判断一个http请求是否为ajax请求
     * @param request
     * @return
     */
//    public static boolean isAjax(HttpServletRequest request) {
//		if (request != null
//				&& "XMLHttpRequest".equalsIgnoreCase(request
//						.getHeader("X-Requested-With")))
//			return true;
//		return false;
//	}
    
    @SuppressWarnings("deprecation")
	public static void main(String[] args) {
		try {
			String redirect_url=null;
			String url="www.007caipiao.com";
			String sk="sk=007cpdf9120c63478e385c";
			String product_id="product_id=0";
			redirect_url=url+"&"+sk+"&"+product_id;
			System.out.println("http://www.xiaoyu.com/auth?redirecturl="+redirect_url);
			System.out.println(HttpUtil.get("http://www.xiaoyu.com/auth?"+"redirecturl="+redirect_url));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}