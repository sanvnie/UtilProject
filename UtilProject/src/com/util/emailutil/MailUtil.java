package com.util.emailutil;

import org.apache.commons.mail.EmailAttachment;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.MultiPartEmail;
import org.apache.commons.mail.SimpleEmail;

/**
 * 邮件发送工具类（使用的commons-email.jar）
 * 特别提示：在测试发送前必须要先开启发送邮箱中的POP3/SMTP服务（可登陆邮箱设置）
 */
public class MailUtil {
		//SMTP服务器常见地址有：新浪：smtp.sina.com.cn 搜狐：smtp.sohu.com  126邮箱：smtp.126.com
		// 139邮箱：SMTP.139.com  163邮箱：smtp.163.com  QQ邮箱：smtp.qq.com  QQ企业邮箱：smtp.exmail.qq.com
		//雅虎邮箱：smtp.mail.yahoo.com  HotMail邮箱：smtp.live.com  GoogleMail邮箱：smtp.gmail.com
		//FoxMail邮箱：smtp.foxmail.com  Tom邮箱:smtp.tom.com  China邮箱：smtp.china.com  ETang邮箱：smtp.etang.com
		//更多的参见smpt.properties
		private static boolean TLS = false;//设置是否需要TLS登录
		
		/**
		 * 发送普通文本文件
		 * @param smtp 发送邮件方smtp服务器地址
		 * @param sendMailAddress 发送方账号（邮箱地址）
		 * @param sendMailPasswd 发送方邮箱密码
		 * @param getMailAddress 接收方邮箱地址
		 * @param mailTitle 邮件标题
		 * @param mailContent 邮件内容
		 */
		public  static void  sendTextMail(String smtp,String sendMailAddress,String sendMailPasswd,String[] getMailAddress,String mailTitle,String mailContent){
			SimpleEmail email=new SimpleEmail();
			email.setTLS(TLS); //是否TLS校验，，某些邮箱需要TLS安全校验，同理有SSL校验  
			email.setHostName(smtp);
			try {
				email.setFrom(sendMailAddress);
				email.setAuthentication(sendMailAddress, sendMailPasswd);
				email.setCharset("utf-8");//解决中文乱码问题
				email.setSubject(mailTitle); //标题       
				email.setMsg(mailContent);//内容  
				for(int i = 0; i < getMailAddress.length; ++i){
					email.addTo(getMailAddress[i]); //接收方
					email.send();
				}
				    	
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}
	
		/**
		 * 发送含附件的邮件
		 * @param filename 要上传的文件名称（含本地路径）
		 * @param filedescription 附件描述
		 * @param attachname 附件名
		 */
		public static void sendAttachMail(String smtp,String sendMailAddress,String sendMailPasswd,String[] getMailAddress,String mailTitle,String mailContent,String filename,String filedescription,String attachname){
			 MultiPartEmail  email = new MultiPartEmail();
			 email.setHostName(smtp);
			 email.setAuthentication(sendMailAddress, sendMailPasswd);
			 EmailAttachment attachment=new EmailAttachment();
			 attachment.setPath(filename);
			 attachment.setDisposition(EmailAttachment.ATTACHMENT);
			 attachment.setDescription(filedescription);
			 attachment.setName(attachname);
			 email.setCharset("utf-8");
			 email.setSubject(mailTitle);
			 try {
				email.attach(attachment);
				email.setMsg(mailContent);
				email.setFrom(sendMailAddress);
				for(int i = 0; i < getMailAddress.length; ++i){
					email.addTo(getMailAddress[i]); //接收方
					email.send();
				}
			} catch (EmailException e) {
				e.printStackTrace();
			}
		}
}
