package com.util.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


/**
 * The Class TestLog.
 */
public class TestLog {
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		//Date lastweekDate=getLastWeekString(new Date());
		String thisMondayDate=getMondayOfThisWeek();
//		DateFormat format=new SimpleDateFormat("yyyy-MM-dd");
//		String resString=format.format(thisMondayDate);
		System.out.println(thisMondayDate);
	}
	 public static final Date getLastWeekString(Date date){
	    	Calendar lastWeek = Calendar.getInstance();
	        lastWeek.setTime(date);
	        lastWeek.add(Calendar.DATE, -7);
	        return lastWeek.getTime();
	}
	
	//获取本周一时间
	    public static String getMondayOfThisWeek() {
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	  Calendar c = Calendar.getInstance();
	    	  c.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    	  c.set(Calendar.HOUR_OF_DAY, 0);
	    	  c.set(Calendar.MINUTE, 0);
	    	  c.set(Calendar.SECOND, 0);
	    	  return sdf.format(c.getTime());
	    }
}
