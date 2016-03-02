package com.util.dateutil;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

/**
 * 常用日期处理工具类
 *
 */
public class DateUtil {

	private static SimpleDateFormat SDF_yyyyMMdd = new SimpleDateFormat(
			"yyyyMMdd");
	private static SimpleDateFormat SDF_HHmmss = new SimpleDateFormat("HHmmss");
	private static SimpleDateFormat SDF_yyyyMMddHHmmss = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	// private static SimpleDateFormat SDF_MMdd = new SimpleDateFormat("MMdd");

	private static SimpleDateFormat SDF_STANDARD = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss");

	private static SimpleDateFormat SDF_STANDARD_DATE = new SimpleDateFormat(
			"yyyy-MM-dd");
	private static SimpleDateFormat SDF_STANDARD_TIME = new SimpleDateFormat(
			"HH:mm:ss");

	private static SimpleDateFormat SDF_LONGTIME = new SimpleDateFormat(
			"yyyy-MM-dd HH:mm:ss.SSS");// 2010-06-24 20:31:00.0
	
	 public static int openDay=5;
	  private String iDate="";
	  private int iYear;
	  private int iMonth;
	  private int iDay;
	//  iDateTime = 2002-01-01 23:23:23
	  public void setDate(String iDateTime){
	    this.iDate=iDateTime.substring(0,10);
	  }
	  public String getiDate(){
	    return this.iDate;
	  }
	  public int getYear(){
	    iYear=Integer.parseInt(iDate.substring(0,4));
	    return iYear;
	  }
	  public int getMonth(){
	    iMonth=Integer.parseInt(iDate.substring(5,7));
	    return iMonth;
	  }
	  public int getDay(){
	    iDay=Integer.parseInt(iDate.substring(8,10));
	    return iDay;
	  }

	  public static String subDate(String date){
	    return date.substring(0,10);
	  }
	/**
	 * 当前日期格式化成字符串，例20090812
	 */
	public static String getDate() {
		return getDate(new Date());
	}
	/**
	 * 获取本地时间
	 */
	public static String getLocalDate(String pattern) {
		java.util.Date dt = new java.util.Date();
		SimpleDateFormat df = new SimpleDateFormat(pattern);
		df.setTimeZone(TimeZone.getDefault());
		return df.format(dt);
	}
	/**
	 * 日期格式化成字符串，例20090812
	 */
	public static String getDate(long millisecond) {
		return getDate(new Date(millisecond));

	}

	/**
	 * 日期格式化成字符串，例20090812
	 */
	public static String getDate(Date date) {
		return SDF_yyyyMMdd.format(date);
	}

	/**
	 * 当前时间格式化成字符串，例235933
	 */
	public static String getTime() {
		return getTime(new Date());
	}

	/**
	 * 时间格式化成字符串，例235933
	 */
	public static String getTime(Date date) {
		return SDF_HHmmss.format(date);
	}

	/**
	 * 当前日期时间格式化成字符串，例20090812235933
	 */
	public static String getDateTime() {
		return getDateTime(new Date());
	}

	/**
	 * 日期时间格式化成字符串，例20090812235933
	 */
	public static String getDateTime(long milliseconde) {
		return getDateTime(new Date(milliseconde));
	}

	/**
	 * 日期时间格式化成字符串，例20090812235933
	 */
	public static String getDateTime(Date date) {
		return SDF_yyyyMMddHHmmss.format(date);
	}

	public static String getDateTime(String longtime) {
		String temp = getStandard(longtime);
		if (temp == null || temp.equals("")) {
			return "";
		} else {
			// 2009-08-12 23:59:33
			return temp.substring(0, 4) + temp.substring(5, 7)
					+ temp.substring(8, 10) + temp.substring(11, 13)
					+ temp.substring(14, 16) + temp.substring(17);
		}
	}

	/**
	 * 日期时间格式化成标准字符串，一般用于显示。例2009-08-12 23:59:33
	 */
	public static String getStandard() {
		return getStandard(new Date());
	}

	/**
	 * 日期时间格式化成标准字符串，一般用于显示。例2009-08-12 23:59:33
	 */
	public static String getStandard(long millisecond) {
		return getStandard(new Date(millisecond));
	}

	/**
	 * 日期时间格式化成标准字符串，一般用于显示。例2009-08-12 23:59:33
	 */
	public static String getStandard(Date date) {
		return SDF_STANDARD.format(date);
	}

	/**
	 * 带毫秒的日期时间格式化成标准字符串，毫秒部分舍去。 一般用于显示。<br>
	 * 例如2010-02-2 20:31:11.888转换成2010-02-2 20:31:11
	 */
	public static String getStandard(String longTime) {
		String result = null;

		if (longTime == null | longTime.trim().equals("")) {
			return "";
		} else {
			longTime = longTime.trim();
		}

		try {
			result = SDF_STANDARD.format(SDF_LONGTIME.parse(longTime));
		} catch (Exception e) {
		}

		if (result == null) {
			try {
				result = SDF_STANDARD.format(SDF_STANDARD.parse(longTime));
			} catch (Exception e) {
			}
		}

		if (result == null) {
			try {
				result = SDF_STANDARD.format(SDF_STANDARD_DATE.parse(longTime));
			} catch (Exception e) {
			}
		}

		if (result == null) {
			try {
				result = SDF_STANDARD.format(SDF_STANDARD_TIME.parse(longTime));
			} catch (Exception e) {
			}
		}

		if (result == null) {
			result = "";
		}

		return result;
	}

	public static Date addDate(Date date, int num) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		calendar.add(Calendar.DATE, num);
		return calendar.getTime();
	}

	/**
	 * 增加日期
	 */
	public static Calendar addDate(Calendar calendar, int num) {
		calendar.add(Calendar.DATE, num);
		return calendar;
	}

	/**
	 * 增加小时
	 */
	public static Calendar addHour(Calendar calendar, int num) {
		calendar.add(Calendar.HOUR_OF_DAY, num);
		return calendar;
	}

	/**
	 * 增加分
	 */
	public static Calendar addMinute(Calendar calendar, int num) {
		calendar.add(Calendar.MINUTE, num);
		return calendar;
	}

	/**
	 * 增加秒
	 */
	public static Calendar addSecond(Calendar calendar, int num) {
		calendar.add(Calendar.SECOND, num);
		return calendar;
	}

	/**
	 * 得到指定年月的日期天数。
	 */
	public static int countDaysOfMonth(int year, int month) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, 1, 0, 0, 0);
		Date firstDayOfThisMonth = calendar.getTime();

		if (month == 12) {
			year++;
			month = 0;
		}
		calendar.set(year, month, 1, 0, 0, 0);

		Date firstDayOfNextMonth = calendar.getTime();
		return (int) ((firstDayOfNextMonth.getTime() - firstDayOfThisMonth
				.getTime()) / (1000L * 60 * 60 * 24));
	}

	/**
	 * 得到指定的日期是星期几。
	 */
	public static int getDayOfWeek(int year, int month, int day) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month - 1, day);
		int result = calendar.get(Calendar.DAY_OF_WEEK);
		return result;
	}

	/**
	 * 得到两个时间的差距。毫秒之差除以时间单位，不足一个单位的舍去。 返回大于0表示第二个时间比第一个大。
	 */
	public static long getDifference(Date first, Date second, int type) {
		long result = 0;
		long lFirst = first.getTime();
		long lSecond = second.getTime();

		long diffMillisecond = lSecond - lFirst;

		switch (type) {
		case Calendar.DATE:
			result = diffMillisecond / (1000 * 3600 * 24);
			break;
		case Calendar.HOUR_OF_DAY:
		case Calendar.HOUR:
			result = diffMillisecond / (1000 * 3600);
			break;
		case Calendar.MINUTE:
			result = diffMillisecond / (1000 * 60);
			break;
		case Calendar.SECOND:
			result = diffMillisecond / 1000;
			break;

		case Calendar.MILLISECOND:
			result = diffMillisecond;
			break;

		default:
			throw new UnsupportedOperationException("不支持此类型：" + type);

		}

		return result;

	}

	/**
	 * 解析字符串为日期类型。格式只支持YYYYMMDD或YYYYMMDDhhmmss 例：20101022 例：20101022220033
	 * 
	 */
	public static Date parseDate(String dateString) {
		if (dateString.length() == 8) {
			try {
				return SDF_yyyyMMdd.parse(dateString);
			} catch (Exception e) {
				return null;
			}
		} else if (dateString.length() == 14) {
			try {
				return SDF_yyyyMMddHHmmss.parse(dateString);
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

	/**
	 * 解析字符串为日期类型。注意格式是格式YYYYMMDDhhmmss，例：20101022220033
	 */
	public static Date parseDateTime(String dateTimeString) {
		try {
			return SDF_yyyyMMddHHmmss.parse(dateTimeString);
		} catch (Exception e) {
			return null;
		}

	}
	
	/**
	 * 比较日期，与现在-N天的日期对比  -1 0 1
	 */
	public int getDateCompare(String limitDate,int afterDay){
	     GregorianCalendar calendar = new GregorianCalendar();
	     Date date = calendar.getTime();
	     calendar.set(Calendar.DATE,calendar.get(Calendar.DATE)+afterDay);
	     date = calendar.getTime();//date是新来的天数，跟今天相比的天数

	     iDate=limitDate;
	     calendar.set(getYear(),getMonth()-1,getDay());
	     Date dateLimit = calendar.getTime();
	     return dateLimit.compareTo(date);
	  }
	
	/**
	 * 比较日期，与现在的日期对比    
	 */
	public int getDateCompare(String limitDate){
	     iDate=limitDate;
	     GregorianCalendar calendar = new GregorianCalendar();
	     calendar.set(getYear(),getMonth()-1,getDay());
	     Date date = calendar.getTime();

	     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	     Date nowDate = new Date();
	     return date.compareTo(nowDate);
	}
	  
	 /**
	  * 比较日期，与现在的日期对比  得到天数 
	  */
	public long getLongCompare(String limitDate){

	     iDate=limitDate;
	     GregorianCalendar calendar = new GregorianCalendar();
	     calendar.set(getYear(),getMonth()-1,getDay());
	     Date date = calendar.getTime();
	     long datePP=date.getTime();
	     Date nowDate = new Date();
	     long dateNow = nowDate.getTime();
	     return ((dateNow-datePP)/(24*60*60*1000));

	 }
	
	/**
	 * 比较日期，与现在的日期对比  得到信息
	 */
	public String getStringCompare(String limitDate,int openDay){
	      iDate=limitDate;
	      GregorianCalendar calendar = new GregorianCalendar();
	      calendar.set(getYear(),getMonth()-1,getDay());
	      Date date = calendar.getTime();
	      long datePP=date.getTime();
	      Date nowDate = new Date();
	      long dateNow = nowDate.getTime();
	      long overDay = ((dateNow-datePP)/(24*60*60*1000));
	      String info="";
	      return info;

	 }
		
	/**
	 * 比较日期，与现在的日期对比  得到信息
	 */
	  public String getPicCompare(String limitDate,int openDay){

	       iDate=limitDate;
	       GregorianCalendar calendar = new GregorianCalendar();
	       calendar.set(getYear(),getMonth()-1,getDay());
	       Date date = calendar.getTime();
	       long datePP=date.getTime();
	       Date nowDate = new Date();
	       long dateNow = nowDate.getTime();
	       long overDay = ((dateNow-datePP)/(24*60*60*1000));
	       String info="";
	       if (overDay>0){
	           info="plaint1.gif";
	       }
	       if (overDay==0){
	           info="plaint2.gif";
	       }
	       if (overDay<0&&overDay>=-openDay){
	           info="plaint2.gif";
	       }
	       if (overDay<-openDay){
	           info="plaint3.gif";
	       }
	       if (overDay<-150){
	           info="plaint3.gif";
	       }
	       return info;

	  }
	  /**
	   * method diffdate 计算两个日期间相隔的日子
	   * @param beforDate 格式:2005-06-20
	   * @param afterDate 格式:2005-06-21
	   */
	  public static int diffDate(String beforeDate,String afterDate){
		  String[] tt = beforeDate.split("-");
		  Date firstDate = new Date(Integer.parseInt(tt[0]),Integer.parseInt(tt[1])-1,Integer.parseInt(tt[2]));
		  
		  tt = afterDate.split("-");
		  Date nextDate = new Date(Integer.parseInt(tt[0]),Integer.parseInt(tt[1])-1,Integer.parseInt(tt[2]));
		  return (int)(nextDate.getTime()-firstDate.getTime())/(24*60*60*1000);  
	  }
	  
	   public static Date getLastWeekSunday(){

	        Calendar date=Calendar.getInstance(Locale.CHINA);

	        date.setFirstDayOfWeek(Calendar.MONDAY);//将每周第一天设为星期一，默认是星期天

	        date.add(Calendar.WEEK_OF_MONTH,-1);//周数减一，即上周

	        date.set(Calendar.DAY_OF_WEEK, Calendar.SUNDAY);//日子设为星期天

	        return date.getTime();

	    }

	public static void main(String[] args) {
		try {
//			Date first = parseDate("20110218000003");
//			Date second = parseDate("20110219000002");
//			System.out.println(getDifference(first, second, Calendar.DATE));
			// Date tempLateFeeBeginDate = DateUtil.parseDate("2011010101");
			// Date dtPayDate = DateUtil.parseDate("20110121");
			// long lateDateCount = DateUtil.getDifference(tempLateFeeBeginDate,
			// dtPayDate, Calendar.DATE);
			// System.out.println(lateDateCount);

			// Date first = new Date(2010, 1, 1);

			// Calendar calendar = Calendar.getInstance();
			// calendar.set(Calendar.YEAR, 2010);
			// calendar.set(Calendar.MONTH, 0);
			// calendar.set(Calendar.DATE, 1);
			//
			// System.out.println(getDifference(calendar.getTime(), new Date(),
			// Calendar.DATE));
			// long basisAmount = 1000;
			// long lateFee = (long) (basisAmount * 0.03 * 40);
			// if (lateFee > basisAmount) {
			// lateFee = basisAmount;
			// }
			//
			// System.out.println(lateFee);
			// Calendar today = Calendar.getInstance();
			// today.set(Calendar.HOUR_OF_DAY, 0);
			// today.set(Calendar.MINUTE, 0);
			// today.set(Calendar.SECOND, 0);
			//
			// Date date = DateUtil.parseDate("20101002220033");
			// Calendar lateFeeBeginDate = Calendar.getInstance();
			// lateFeeBeginDate.setTime(date);
			// lateFeeBeginDate.set(Calendar.HOUR_OF_DAY, 0);
			// lateFeeBeginDate.set(Calendar.MINUTE, 0);
			// lateFeeBeginDate.set(Calendar.SECOND, 0);
			// DateUtil.addDate(lateFeeBeginDate, 15);
			//
			// long diff = DateUtil.getDifference(lateFeeBeginDate.getTime(),
			// today.getTime(), Calendar.DATE);
			//
			// System.out.println(diff);
			// String longTime = "2010-10-06 10:34:13";
			// System.out.println(getDateTime(longTime));
		} catch (Exception e) {
			e.printStackTrace();
		}

		// System.out.println(DateUtil.getDayOfWeek(2010, 8, 25));
		// System.out.println(DateUtil.getDayOfWeek(2010, 8, 26));
		// System.out.println(DateUtil.getDayOfWeek(2010, 8, 27));
		// System.out.println(DateUtil.getDayOfWeek(2010, 8, 28));
		// System.out.println(DateUtil.getDayOfWeek(2010, 8, 29));
		// System.out.println(DateUtil.getDayOfWeek(2010, 8, 30));
		//
		// System.out.println("----------");
		//
		// System.out.println(countDaysOfMonth(2010, 8));
		// System.out.println(countDaysOfMonth(2010, 2));
		// System.out.println(countDaysOfMonth(2010, 4));
		//System.out.println(SDF_yyyyMMddHHmmss.format(new Date()));
		System.out.println(DateUtil.getLastWeekSunday());
	}
}
