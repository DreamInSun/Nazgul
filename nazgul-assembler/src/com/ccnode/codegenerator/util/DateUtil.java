// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   DateUtil.java

package com.ccnode.codegenerator.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import org.apache.commons.lang.time.DateUtils;

public final class DateUtil
{

	private static final int INSTANCE_COUNT = 20;
	public static final SimpleDateFormat formatYYYYMMDD[] = createDateFormat("yyyy-MM-dd");
	private static final SimpleDateFormat formatLong[] = createDateFormat("yyyy-MM-dd HH:mm:ss");
	private static final SimpleDateFormat insureFormat[] = createDateFormat("yyyy-MM-dd|HH:mm:ss");
	private static final SimpleDateFormat formatShort[] = createDateFormat("yyyyMMdd");
	private static final SimpleDateFormat formatTime[] = createDateFormat("HH:mm:ss");
	private static final SimpleDateFormat formatYYYYMMDDSSS[] = createDateFormat("yyMMddHHmmssSSS");
	private static final SimpleDateFormat formatYYYYMMDDHHMMSS[] = createDateFormat("yyyyMMddHHmmss");
	private static final SimpleDateFormat formatYYYYMMDDHHMMSSS[] = createDateFormat("yyyyMMddHHmmsss");
	private static final SimpleDateFormat formatMMDD[] = createDateFormat("MMdd");
	private static final SimpleDateFormat formatYYYYMMDDHHMM[] = createDateFormat("yyyy-MM-dd HH:mm");
	private static final String parseYYYYMMDD = "yyyy-MM-dd HH:mm:ss";
	private static int currentIndex = 0;

	private DateUtil()
	{
	}

	private static SimpleDateFormat[] createDateFormat(String fmt)
	{
		SimpleDateFormat ret[] = new SimpleDateFormat[20];
		for (int i = 0; i < ret.length; i++)
		{
			ret[i] = new SimpleDateFormat(fmt);
			ret[i].setLenient(false);
		}

		return ret;
	}

	private static final int getIndex()
	{
		int n = currentIndex++;
		if (n < 0)
		{
			currentIndex = 0;
			n = 0;
		}
		return n % 20;
	}

	public static String formatYYYYMMDDHHMM()
	{
		SimpleDateFormat fmt = formatYYYYMMDDHHMM[getIndex()];
		SimpleDateFormat simpledateformat = fmt;
		JVM INSTR monitorenter ;
		return fmt.format(new Date());
		Exception exception;
		exception;
		throw exception;
	}

	public static Date paseYYYYMMDDHHMM(String strDate)
	{
		if (strDate == null || strDate.indexOf("null") >= 0)
			return null;
		Date date = null;
		try
		{
			SimpleDateFormat fmt = formatYYYYMMDDHHMM[getIndex()];
			synchronized (fmt)
			{
				date = fmt.parse(strDate);
			}
		}
		catch (Exception e)
		{
			return null;
		}
		return date;
	}

	public static String formatMMDD()
	{
		SimpleDateFormat fmt = formatMMDD[getIndex()];
		SimpleDateFormat simpledateformat = fmt;
		JVM INSTR monitorenter ;
		return fmt.format(new Date());
		Exception exception;
		exception;
		throw exception;
	}

	public static String formatCurTime()
	{
		SimpleDateFormat fmt = formatYYYYMMDDSSS[getIndex()];
		SimpleDateFormat simpledateformat = fmt;
		JVM INSTR monitorenter ;
		return fmt.format(new Date());
		Exception exception;
		exception;
		throw exception;
	}

	public static String formatCurTimeLong()
	{
		SimpleDateFormat fmt = formatYYYYMMDDHHMMSS[getIndex()];
		SimpleDateFormat simpledateformat = fmt;
		JVM INSTR monitorenter ;
		return fmt.format(new Date());
		Exception exception;
		exception;
		throw exception;
	}

	public static String formatYYYYMMDDHHMMSS(Date date)
	{
		SimpleDateFormat fmt = formatYYYYMMDDHHMMSS[getIndex()];
		SimpleDateFormat simpledateformat = fmt;
		JVM INSTR monitorenter ;
		return fmt.format(date);
		Exception exception;
		exception;
		throw exception;
	}

	public static String formatYYYYMMDDHHMMSSS(Date date)
	{
		SimpleDateFormat fmt = formatYYYYMMDDHHMMSSS[getIndex()];
		SimpleDateFormat simpledateformat = fmt;
		JVM INSTR monitorenter ;
		return fmt.format(date);
		Exception exception;
		exception;
		throw exception;
	}

	public static Date convertYYYYMMDDHHMMSS(String strDate)
	{
		if (strDate == null || strDate.indexOf("null") >= 0)
			return null;
		Date date = null;
		try
		{
			SimpleDateFormat fmt = formatYYYYMMDDHHMMSS[getIndex()];
			synchronized (fmt)
			{
				date = fmt.parse(strDate);
			}
		}
		catch (Exception e)
		{
			return null;
		}
		return date;
	}

	public static Date convertYYYYMMDD(String strDate)
	{
		if (strDate == null || strDate.indexOf("null") >= 0)
			return null;
		Date date = null;
		try
		{
			SimpleDateFormat fmt = formatYYYYMMDD[getIndex()];
			synchronized (fmt)
			{
				date = fmt.parse(strDate);
			}
		}
		catch (Exception e)
		{
			return null;
		}
		return date;
	}

	public static Date convertShort(String strDate)
	{
		if (strDate == null || strDate.indexOf("null") >= 0)
			return null;
		Date date = null;
		try
		{
			SimpleDateFormat fmt = formatShort[getIndex()];
			synchronized (fmt)
			{
				date = fmt.parse(strDate);
			}
		}
		catch (Exception e)
		{
			return null;
		}
		return date;
	}

	public static Date convertLong(String strDate)
	{
		if (strDate == null || strDate.indexOf("null") >= 0)
			return null;
		Date date = null;
		try
		{
			SimpleDateFormat fmt = formatLong[getIndex()];
			synchronized (fmt)
			{
				date = fmt.parse(strDate);
			}
		}
		catch (Exception e)
		{
			return null;
		}
		return date;
	}

	public static String formatYYYYMMDD(Date date)
	{
		SimpleDateFormat fmt = formatYYYYMMDD[getIndex()];
		SimpleDateFormat simpledateformat = fmt;
		JVM INSTR monitorenter ;
		return fmt.format(date);
		Exception exception;
		exception;
		throw exception;
	}

	public static String insureFormat(Date date)
	{
		SimpleDateFormat fmt = insureFormat[getIndex()];
		SimpleDateFormat simpledateformat = fmt;
		JVM INSTR monitorenter ;
		return fmt.format(date).replaceAll("\\|", "T");
		Exception exception;
		exception;
		throw exception;
	}

	public static String formatTime(Date date)
	{
		SimpleDateFormat fmt = formatTime[getIndex()];
		SimpleDateFormat simpledateformat = fmt;
		JVM INSTR monitorenter ;
		return fmt.format(date);
		Exception exception;
		exception;
		throw exception;
	}

	public static String formatShort(Date date)
	{
		SimpleDateFormat fmt = formatShort[getIndex()];
		SimpleDateFormat simpledateformat = fmt;
		JVM INSTR monitorenter ;
		return fmt.format(date);
		Exception exception;
		exception;
		throw exception;
	}

	public static String formatLong(Date date)
	{
		SimpleDateFormat fmt = formatLong[getIndex()];
		SimpleDateFormat simpledateformat = fmt;
		JVM INSTR monitorenter ;
		return fmt.format(date);
		Exception exception;
		exception;
		throw exception;
	}

	public static String formatDDMMMYY(Date date)
	{
		return String.format(Locale.US, "%1$td%1$tb%1$ty", new Object[] {
			date
		});
	}

	public static String convertYYYYMMDDToDDMMMYY(String date)
	{
		Date d = convertYYYYMMDD(date);
		return null != d ? formatDDMMMYY(d) : "";
	}

	public static String formatTodyDate(String pattern)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(new Date());
	}

	public static String formatDate(Date date, String pattern)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.format(date);
	}

	public static Date parse(String date, String pattern)
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat(pattern);
		return dateFormat.parse(date);
		ParseException e;
		e;
		return null;
	}

	public static int getDayBetween(Date d1, Date d2)
	{
		Calendar before = Calendar.getInstance();
		Calendar after = Calendar.getInstance();
		if (d1.before(d2))
		{
			before.setTime(d1);
			after.setTime(d2);
		} else
		{
			before.setTime(d2);
			after.setTime(d1);
		}
		int days = 0;
		int startDay = before.get(6);
		int endDay = after.get(6);
		int startYear = before.get(1);
		int endYear = after.get(1);
		before.clear();
		before.set(startYear, 0, 1);
		while (startYear != endYear) 
		{
			before.set(startYear++, 11, 31);
			days += before.get(6);
		}
		return (days + endDay) - startDay;
	}

	public static int getDayBetweenD(Date d1, Date d2)
	{
		if (d1.before(d2))
			return getDayBetween(d1, d2);
		else
			return -getDayBetween(d1, d2);
	}

	public static String getTimeBetween(String time1, String time2)
	{
		String t1[] = time1.split(":");
		String t2[] = time2.split(":");
		int minute = Integer.parseInt(t2[1]) - Integer.parseInt(t1[1]);
		int hour = Integer.parseInt(t2[0]) - Integer.parseInt(t1[0]);
		if (minute < 0)
		{
			minute += 60;
			hour--;
		}
		if (hour < 0)
			hour += 24;
		if (hour == 0)
			return (new StringBuilder()).append(minute).append("分钟").toString();
		if (minute == 0)
			return (new StringBuilder()).append(hour).append("小时").toString();
		else
			return (new StringBuilder()).append(hour).append("小时").append(minute).append("分钟").toString();
	}

	public static Date addDay(Date myDate, int amount)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(5, amount);
		return cal.getTime();
	}

	public static Date addMinute(Date myDate, int amount)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(12, amount);
		return cal.getTime();
	}

	public static Date addYear(Date myDate, int amount)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(1, amount);
		return cal.getTime();
	}

	public static String dateFormatStr(String dateByyyyyMMddStr)
	{
		if (dateByyyyyMMddStr != null && dateByyyyyMMddStr.length() == 8)
		{
			String year = dateByyyyyMMddStr.substring(0, 4);
			String month = dateByyyyyMMddStr.substring(4, 6);
			String day = dateByyyyyMMddStr.substring(6, 8);
			return (new StringBuilder()).append(year).append("-").append(month).append("-").append(day).toString();
		} else
		{
			return "";
		}
	}

	public static String long2DateStr(long msec, String pattern)
	{
		Date date = new Date();
		date.setTime(msec);
		SimpleDateFormat sdf = new SimpleDateFormat(pattern);
		return sdf.format(date);
	}

	public static boolean beforeCurrentTime(String param)
	{
		Calendar nowcal = Calendar.getInstance();
		Calendar paramcal = Calendar.getInstance();
		nowcal.setTime(new Date());
		paramcal.setTime(parse(param, "yyyy-MM-dd HH:mm:ss"));
		return nowcal.after(paramcal);
	}

	public static Date getBeforeNowDays(int days)
	{
		return DateUtils.add(new Date(), 6, days);
	}

	public static boolean isAddDay(Date date1, Date date2)
	{
		String time1;
		String time2;
		if (date1 == null || date2 == null)
			return false;
		time1 = formatTime(date1);
		time2 = formatTime(date2);
		Exception e;
		return time1.compareToIgnoreCase(time2) >= 0;
		e;
		return false;
	}

	public static Date addHour(Date myDate, int hour)
	{
		Calendar cal = Calendar.getInstance();
		cal.setTime(myDate);
		cal.add(11, hour);
		return cal.getTime();
	}

	public static Date getAfterNowDaysWithoutHHmmss(int days)
	{
		return addDay(getNowWithoutHHmmss(), days);
	}

	public static Date getBeforeNowDaysWithoutHHmmss(int days)
	{
		return addDay(getNowWithoutHHmmss(), -days);
	}

	public static Date getDateWithoutHHmmss(Date date)
	{
		if (date == null)
		{
			return null;
		} else
		{
			Calendar c = Calendar.getInstance();
			c.setTime(date);
			c.set(11, 0);
			c.set(12, 0);
			c.set(13, 0);
			c.set(14, 0);
			return c.getTime();
		}
	}

	public static Date getNowWithoutHHmmss()
	{
		return getDateWithoutHHmmss(new Date());
	}

}
