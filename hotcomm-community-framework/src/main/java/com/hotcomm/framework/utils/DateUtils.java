package com.hotcomm.framework.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * @Description: 
 * 
 * @author  wanpeng http://www.hotcomm.net/
 * @date 2018年3月26日 下午3:57:02
 */
public class DateUtils {
	
	public static final byte YEAR = 0x01;
	public static final byte MONTH = 0x02;
	public static final byte DAY = 0x03;
	public static final byte HOUR = 0x04;
	public static final byte MINUTE = 0x05;
	public static final byte SECOND = 0x06;
	
	public static final byte ADD = 0X01; 
	public static final byte DEL = 0X02;
	
	/**
	 * 单个日期加减- 年|月|日|时|分|秒 
	 * @param date
	 * @param timeUnit
	 * @param methodUnit
	 * @param amount
	 * @return
	 */
	public static Date converTime(Date date,byte timeUnit,byte methodUnit,int amount) {
		Calendar cal=Calendar.getInstance();
		cal.setTime(date);
		amount = methodUnit==DateUtils.ADD ?amount:-amount;
		switch (timeUnit) {
		case 0x1:
			cal.add(Calendar.YEAR, amount);
			break;
		case 0x02:
			cal.add(Calendar.MONTH, amount);
			break;
		case 0x03:
			cal.add(Calendar.DAY_OF_MONTH, amount);
			break;
		case 0x04:
			cal.add(Calendar.HOUR, amount);
			break;
		case 0x05:
			cal.add(Calendar.MINUTE, amount);
			break;
		default:
			cal.add(Calendar.SECOND, amount);
			break;
		}
		date = cal.getTime();
		return date;
	}
	
	/**
	 * 获取当前时间的字符串
	 * @return
	 */
	public static String getNowTime(){
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		return tempDate.format(new java.util.Date());  
	}
	
	/**
	 * 获取当前时间的字符串
	 * @return
	 */
	public static String getTime(){
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd");  
		return tempDate.format(new java.util.Date());  
	}
	
	/**
	 * 获取当前时间的字符串
	 * @return
	 */
	public static String getTimes(){
		SimpleDateFormat tempDate = new SimpleDateFormat("yyyy-MM-dd HH:mm");  
		return tempDate.format(new java.util.Date());  
	}
	
	/**
	 *格式化指定时间（YYYY-MM）
	 * @param dateTime
	 * @return
	 */
	public static String getYearMon(Date dateTime){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
		return sdf.format(dateTime);	
	}
	
	/**
	 * 获取上个月的时间（YYYY-MM）
	 * @return
	 */
	public static  String getLastYearMon() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
		return sdf.format(calendar.getTime());
	}
	
	
	/**
	 * 获取指定时间的年、月、日
	 * @param dateTime
	 * @return
	 */
	public static Map<String, Integer>  getYmd(Date dateTime) {
	        Calendar now = Calendar.getInstance();
	        now.setTime(dateTime);
	        int year = now.get(Calendar.YEAR);
	        int month = now.get(Calendar.MONTH) + 1; 
	        int day = now.get(Calendar.DAY_OF_MONTH);
	        Map<String , Integer> map = new HashMap<>();
	        map.put("year", year);
	        map.put("month",month);
	        map.put("day", day);
			return map;
	}
	
	/**
	 * 获取上个月的月份、天数及本月的天数
	 * @return
	 */
	public static Map<String, Integer> getLastMonDay() {
		Map<String, Integer> map  = new HashMap<>();
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DATE, 1);//把日期设置为当月第一天  
		cal.roll(Calendar.DATE, -1);//日期回滚一天，也就是最后一天  
	    int maxDate = cal.get(Calendar.DATE);  
		map.put("curMonDay", maxDate);
		int lastMon = cal.get(Calendar.MONTH);
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		int lastMonDay= cal.get(Calendar.DAY_OF_MONTH);
		map.put("lastMon", lastMon);
		map.put("lastMonDay", lastMonDay);
		return map;
	}
	

    /** 
     * 描述:获取上个月的最后一天
     * @return
     */
    public static String getLastMaxMonthDate() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        calendar.add(Calendar.MONTH, -1);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        return dft.format(calendar.getTime());
    }
        
    /** 
     * 描述:获取本月第一天零点
     * @return
     */
    public  static String getMonthFristDate() {
        SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        //将小时至0
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        //将分钟至0
        calendar.set(Calendar.MINUTE, 0);
        //将秒至0
        calendar.set(Calendar.SECOND,0);
        //将毫秒至0
        calendar.set(Calendar.MILLISECOND, 0);
        //获得当前月第一天
        Date startDate = calendar.getTime();
        return dft.format(startDate);
    }
    
	
    /** 
     * 描述:获取昨天日期
     * @return
     */
    public static String  getYesterDay() {
    	  Calendar   cal   =   Calendar.getInstance();
    	  cal.add(Calendar.DATE,   -1);
    	  return new SimpleDateFormat( "yyyy-MM-dd ").format(cal.getTime());
    }
    
	/**
	 * 获得当天零时零分零秒
	 * @return
	 */
	public static Map<String, String> initDateByDay(){
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Map<String, String> map = new HashMap<>();
		// 获取当天凌晨0点0分0秒Date
		Calendar calendar1 = Calendar.getInstance();
		calendar1.set(calendar1.get(Calendar.YEAR), calendar1.get(Calendar.MONTH), calendar1.get(Calendar.DAY_OF_MONTH),
						0, 0, 0);
		Date beginOfDate = calendar1.getTime();

		// 获取当天23点59分59秒Date
		Calendar calendar2 = Calendar.getInstance();
		calendar1.set(calendar2.get(Calendar.YEAR), calendar2.get(Calendar.MONTH), calendar2.get(Calendar.DAY_OF_MONTH),
						23, 59, 59);
		Date endOfDate = calendar1.getTime();
		
		map.put("beginOfDate", formatter.format(beginOfDate));
		map.put("endOfDate", formatter.format(endOfDate));
		return map;
	}
	
	/**
	 * 获取两个时间之间的小时差
	 * @param endDate
	 * @param nowDate
	 * @return
	 */
	public static long getDatePoor(Date endDate, Date nowDate) {
		Map<String , Object> map  = new HashMap<>();
	    long nd = 1000 * 24 * 60 * 60;
	    long nh = 1000 * 60 * 60;
	    long nm = 1000 * 60;
	    // 获得两个时间的毫秒时间差异
	    long diff = endDate.getTime() - nowDate.getTime();
	    // 计算差多少小时
	    long hour = diff % nd / nh;
	    return hour;
	}
	
	/**
	 * 取到 n个小时 以前时间
	 * @param hours
	 * @return
	 * @throws ParseException 
	 */
	public static String getBeforeDate(int n){
		Calendar expireDate = Calendar.getInstance(); 
		expireDate.set(Calendar.HOUR_OF_DAY, expireDate.get(Calendar.HOUR_OF_DAY)-n);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(expireDate.getTime());  
	}
	
	/**
	 * 取到 n分钟 以前时间
	 * @param hours
	 * @return
	 * @throws ParseException 
	 */
	public static String getBeforeMinute(int n){
		Calendar expireDate = Calendar.getInstance(); 
		expireDate.set(Calendar.MINUTE, expireDate.get(Calendar.MINUTE)-n);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		return df.format(expireDate.getTime());  
	}
	
	/**
	 * 取到 n分钟 以前时间,长度不同
	 * @param hours
	 * @return
	 * @throws ParseException 
	 */
	public static String getBeforeMinutes(int n){
		Calendar expireDate = Calendar.getInstance(); 
		expireDate.set(Calendar.MINUTE, expireDate.get(Calendar.MINUTE)-n);
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		return df.format(expireDate.getTime());  
	}
	
	/**
	 * 获取当前时间之前一年的时间
	 * @return
	 */
	public static String getBeforeYearDate() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		//过去一年
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
		return format.format(c.getTime());
		
	}
	
	/**
	 * 获得本周一0点时间
	 * @return
	 */
	public static String getTimesWeekmorning() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONDAY), cal.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
		cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
		return format.format(cal.getTime());
	}
	
	/**
	 * 获得当前日期的前N天的时间
	 * @return
	 */
	public static String getBeforeNdayTime(int n) {
		//获取当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Date today = new Date();
		Calendar theCa = Calendar.getInstance();
		theCa.setTime(today);
		theCa.add(theCa.DATE, -n);
		Date start = theCa.getTime();
		return sdf.format(start);
	}
	
	/**
	 * 获得当前日期的前N天的时间
	 * @return
	 */
	public static String getBeforeNdayTime1(int n) {
		//获取当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date today = new Date();
		Calendar theCa = Calendar.getInstance();
		theCa.setTime(today);
		theCa.add(theCa.DATE, -n);
		Date start = theCa.getTime();
		return sdf.format(start);
	}
	
	/**
	 * 获得当前日期的前N天的时间
	 * @return
	 */
	public static String getBeforeNdayTime2(int n) {
		//获取当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date today = new Date();
		Calendar theCa = Calendar.getInstance();
		theCa.setTime(today);
		theCa.add(theCa.DATE, -n);
		Date start = theCa.getTime();
		return sdf.format(start);
	}
	
	/**
	 * 获得当前日期的N天后的时间
	 * @return
	 */
	public static String getAfterNdayTime(int n) {
		//获取当前日期
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		Date today = new Date();
		Calendar theCa = Calendar.getInstance();
		theCa.setTime(today);
		theCa.add(theCa.DATE, n);
		Date start = theCa.getTime();
		return sdf.format(start);
	}
	
	/**
	 * 获得当前时间前一年的时间
	 * @return
	 */
	public static String getLastYearTime() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Calendar c = Calendar.getInstance();
		c.add(Calendar.YEAR, -1);
		return format.format(c.getTime());
	}
	

    /**
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     */
    public static int getDaysBetween(String  smdate,Date bdate) throws ParseException    
    {    
    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    	Date date =  sdf.parse(smdate);
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();      
        long between_days=(time2-time1)/(1000*3600*24);  
        return Integer.parseInt(String.valueOf(between_days));           
    }
    
    /* 
     * 将时间转换为时间戳
     */    
    public static String dateToStamp(String s){
        String res = null;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			Date date = simpleDateFormat.parse(s);
			long ts = date.getTime();
			res = String.valueOf(ts);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return res;
    }
	
}
