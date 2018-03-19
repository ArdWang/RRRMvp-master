package com.ee.project.utils.util;

import android.annotation.SuppressLint;
import android.util.Log;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class DateUtils {

	private static SimpleDateFormat sf = null;

	public static String getCurrentDate(){
		Date d = new Date();
		sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(d);
	}

	//时间戳转为字符串
	public static String getDateToString(long time){
		Date d = new Date(time);
		sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sf.format(d);
	}

	public static String getDateToStringMM(long time){
		Date d = new Date(time);
		sf = new SimpleDateFormat("mm:ss");
		return sf.format(d);
	}

	public static String getDateToStringHH(long time){
		Date d = new Date(time);
		sf = new SimpleDateFormat("HH:mm:ss");
		return sf.format(d);
	}

	//字符串转为时间
	public static Long getStringToDate(String time){
		sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date d = new Date();
		try{
			d = sf.parse(time);
		}catch (Exception e) {

		}

		return d.getTime();
	}

	public static long getStringToDateMM(String time){
		sf = new SimpleDateFormat("mm:ss");
		Date d = new Date();
		try{
			d = sf.parse(time);
		}catch (Exception e) {

		}

		return d.getTime();
	}


	/**
	 * 将未指定格式的字符串转换成日期类型
	 * @param date - 20151123 或者 2015/11/23 或者2015-11-23
	 * @return Mon Nov 23 00:00:00 GMT+08:00 2015
	 */
	public static Date parseStringToDate(String date) throws ParseException {
		Date result = null;
		String parse = date;
		parse = parse.replaceFirst("^[0-9]{4}([^0-9]?)", "yyyy$1");
		parse = parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
		parse = parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
		parse = parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
		SimpleDateFormat format = new SimpleDateFormat(parse, Locale.CHINA);
		result = format.parse(date);
		return result;
	}

	/**
	 * 将日期以指定格式输出
	 * @param date - new Date()
	 * @param format - "yyyy-MM-dd"
	 * @return 2015-11-23
	 */
	public static String formatToString(Date date, String format) {
		try {
			SimpleDateFormat sdf = new SimpleDateFormat(format,Locale.CHINA);
			return sdf.format(date);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "";
	}


	/**
	 * 通过年份和月份 得到当月的日子
	 *
	 * @param year
	 * @param month
	 * @return
	 */
	public static int getMonthDays(int year, int month) {
		month++;
		switch (month) {
			case 1:
			case 3:
			case 5:
			case 7:
			case 8:
			case 10:
			case 12:
				return 31;
			case 4:
			case 6:
			case 9:
			case 11:
				return 30;
			case 2:
				if (((year % 4 == 0) && (year % 100 != 0)) || (year % 400 == 0)){
					return 29;
				}else{
					return 28;
				}
			default:
				return  -1;
		}
	}
	/**
	 * 返回当前月份1号位于周几
	 * @param year
	 * 		年份
	 * @param month
	 * 		月份，传入系统获取的，不需要正常的
	 * @return
	 * 	日：1		一：2		二：3		三：4		四：5		五：6		六：7
	 */
	@SuppressLint("WrongConstant")
	public static int getFirstDayWeek(int year, int month){
		Calendar calendar = Calendar.getInstance();
		calendar.set(year, month, 1);
		Log.d("DateView", "DateView:First:" + calendar.getFirstDayOfWeek());
		return calendar.get(Calendar.DAY_OF_WEEK);
	}

}
