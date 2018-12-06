package cn.zyol.basic.util;

import org.apache.commons.lang.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateUtil {

    private static final SimpleDateFormat fromat = new SimpleDateFormat("yyyyMMddHHmmss");

    public static String getCurrentTimeStamp() {
        return fromat.format(new Date());
    }

    public static String datePattern     = "yyyy-MM-dd";

    public static String timePattern     = "HH:mm:ss";

    public static String dateTimePattern = "yyyy-MM-dd HH:mm:ss";

    /**
     * 根据默认日期格式，返回日期字符串。
     *
     * @param aDate 日期对象
     * @return String '年月日'日期字符串
     */
    public static final String getDateAndMonth(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (aDate != null) {
            df = new SimpleDateFormat("yyyy-MM");
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    /**
     * 根据默认日期格式，返回日期字符串。
     *
     * @param aDate 日期对象
     * @return String '年月日'日期字符串
     */
    public static final String getDate(Date aDate) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (aDate != null) {
            df = new SimpleDateFormat(datePattern);
            returnValue = df.format(aDate);
        }
        return (returnValue);
    }

    /**
     * 根据传入日期返回指定日期的日期字符串
     *
     * @param date 日期对象
     * @return String 格式化后的'年月日时分秒'日期字符串
     */
    public static String getDateTime(Date date) {
        SimpleDateFormat df = new SimpleDateFormat(datePattern);
        if (date == null) {
            return "";
        }
        df.applyPattern("yyyy-MM-dd HH:mm:ss");
        return df.format(date);
    }

    /**
     * 返回当前日期 指定格式化格式的 日期字符串
     *
     * @param pattern 格式化字符串
     * @return String 日期字符串
     */
    public static final String getDate(String pattern) {
        Date date = new Date();
        return getDate(date, pattern);
    }

    /**
     * 把传入的日期对象，转换成指定格式的日期字符串
     *
     * @param date 日期对象
     * @param pattern 指定转换格式
     * @return String 格式化后的日期字符串
     */
    public static final String getDate(Date date, String pattern) {
        SimpleDateFormat df = null;
        String returnValue = "";
        if (date != null) {
            df = new SimpleDateFormat(pattern);
            returnValue = df.format(date);
        }
        return (returnValue);
    }

    /**
     * 把传入的日期字符串，转换成指定格式的日期对象
     *
     * @param dateString 日期字符串
     * @param pattern 指定转换格式
     * @return Date 日期对象
     */
    public static Date getDate(String dateString, String pattern) {
        SimpleDateFormat df = null;
        Date date = null;
        if (dateString != null) {
            try {
                df = new SimpleDateFormat(pattern);
                date = df.parse(dateString);
            } catch (Exception e) {
            }
        }
        return date;
    }

    /**
     * 获取传入日期的对象
     *
     * @param date 传入的日期
     * @return String 日期年月字符串
     */
    public static String getMonth(Date date) {
        SimpleDateFormat df = null;
        if (date != null) {
            df = new SimpleDateFormat();
            df.applyPattern("yyyy-MM");
            return df.format(date);
        }
        return null;
    }

    /**
     * 获取传入日期的时分秒
     *
     * @param date 传入的日期
     * @return String 时分秒字符串
     */
    public static String getTime(Date date) {
        SimpleDateFormat df = null;
        if (date != null) {
            df = new SimpleDateFormat();
            df.applyPattern(timePattern);
            return df.format(date);
        }
        return null;
    }

    public static long getLongTime(Date date) {
        return date.getTime() / 1000;
    }

    /**
     * 获取当前年
     *
     * @return String 日期年字符串
     */
    public static String getYear() {
        Date date = new Date();
        return getDate(date, "yyyy");
    }

    /**
     * 获取当前月
     *
     * @return String 日期月字符串
     */
    public static String getMonth() {
        Date date = new Date();
        return getDate(date, "MM");
    }

    /**
     * 获取当前日
     *
     * @return String 日期日字符串
     */
    public static String getDay() {
        Date date = new Date();
        return getDate(date, "dd");
    }

    /**
     * 获取当前时间的小时
     *
     * @return 返回小时
     */
    public static int getHour() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 获取当前时间的分钟
     *
     * @return 返回分钟
     */
    public static int getMinute() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 获取当前时间的秒钟
     *
     * @return 返回秒钟
     */
    public static int getSecond() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**
     * 获取当前时间的毫秒
     *
     * @return 返回毫秒
     */
    public static long getMillis() {
        Date date = new Date();
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 返回传入日期的小时
     *
     * @param date 日期
     * @return 返回小时
     */
    public static int getHour(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.HOUR_OF_DAY);
    }

    /**
     * 返回分钟
     *
     * @param date 日期
     * @return 返回分钟
     */
    public static int getMinute(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.MINUTE);
    }

    /**
     * 返回秒钟
     *
     * @param date 日期
     * @return 返回秒钟
     */
    public static int getSecond(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.get(Calendar.SECOND);
    }

    /**
     * 返回毫秒
     *
     * @param date 日期
     * @return 返回毫秒
     */
    public static long getMillis(Date date) {
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        return c.getTimeInMillis();
    }

    /**
     * 日期相加
     *
     * @param date 日期
     * @param day 天数，可为负数
     * @return 返回相加后的日期
     */
    public static Date addDate(Date date, int day) {
        Calendar c = Calendar.getInstance();
        c.setTimeInMillis(getMillis(date) + ((long) day) * 24 * 3600 * 1000);
        return c.getTime();
    }

    /**
     * 日期相减
     *
     * @param date 日期
     * @param date1 日期
     * @return 返回相减后的日期
     */
    public static int diffDate(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (24 * 3600 * 1000));
    }

    /**
     * 小时相减
     *
     * @param date 日期
     * @param date1 日期
     * @return int 返回相减后的小时
     */
    public static int diffDateToHour(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (1000 * 60 * 60));
    }

    /**
     * 秒相减
     *
     * @param date 日期
     * @param date1 日期
     * @return int 返回相减后的秒
     */
    public static int diffDateToSecond(Date date, Date date1) {
        return (int) ((getMillis(date) - getMillis(date1)) / (1000 * 60));
    }

    /**
     * 时间相减得到分钟
     *
     * @param dateTime
     * @param dateTime1
     * @return
     */
    public static int diffDateToMinute(Long dateTime, Long dateTime1) {
        return (int) (dateTime - dateTime1) / (1000 * 60);
    }

    /**
     * 毫秒相减
     *
     * @param date 日期
     * @param date1 日期
     * @return long 返回相减后的毫秒
     */
    public static long diffDateToMillis(Date date, Date date1) {
        return (long) ((getMillis(date) - getMillis(date1)));
    }

    /**
     * 判断是否在一个时间段内
     *
     * @param time 要判断的时间
     * @param begin 开始时间
     * @param end 结束时间
     * @return boolean true：在开始和结束范围内，false:不在开始和结束范围内
     */
    public static boolean IsTimeIn(Date time, Date begin, Date end) {
        return time.getTime() >= begin.getTime() && time.getTime() <= end.getTime();
    }

    /**
     * 判断输入的时间是否今年
     *
     * @param date 日期字符串
     * @return boolean true：今年，false：不是今年
     */
    public static boolean isThisYear(String date) {
        int timeYear = Integer.parseInt(date.substring(0, 4));
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int thisYear = calendar.get(Calendar.YEAR);
        if (timeYear == thisYear) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获取当前时间的上个月
     *
     * @return String 日期格式yyyyMM格式的日期字符串
     */
    public static String getPreMonth() {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1); // 得到前一天
        calendar.add(Calendar.MONTH, -1); // 得到前一个月
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;

        String data = "" + year;
        if (month < 10) {
            data += "0" + month;
        } else {
            data += "" + month;
        }

        return data;
    }

    /**
     * 计算两日期之间相差的天数 day1 -day2
     *
     * @param day1 日期字符串
     * @param day2 日期字符串
     * @throws int 相减后的天数
     */
    public static int countDays(String day1, String day2) throws ParseException {
        if (day1 != null && day2 != null && day1.length() > 0 && day2.length() > 0) {
            // 日期相减算出秒的算法
            Date date1 = new SimpleDateFormat("yyyy-MM-dd").parse(day1);
            Date date2 = new SimpleDateFormat("yyyy-MM-dd").parse(day2);
            // 日期相减得到相差的日期
            long day = (date1.getTime() - date2.getTime()) / (24 * 60 * 60 * 1000);
            return (int) day;
        } else {
            return 0;
        }
    }

    /**
     * 判断当前日期是星期几
     *
     * @param dateTimeStr 要判断的时间
     * @return int 当前是星期几
     */
    public static int dayForWeek(String dateTimeStr) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(df.parse(dateTimeStr));
        int dayForWeek = 0;
        if (c.get(Calendar.DAY_OF_WEEK) == 1) {
            dayForWeek = 7;
        } else {
            dayForWeek = c.get(Calendar.DAY_OF_WEEK) - 1;
        }
        return dayForWeek;
    }

    /**
     * 获取某日期所在周的星期天日期(以周一为一周的第一天)
     *
     * @param dateTimeStr 日期字符串
     * @return String 返回日期
     */
    public static String getDataForSunday(String dateTimeStr) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(df.parse(dateTimeStr));
        c.setFirstDayOfWeek(Calendar.MONDAY);
        c.set(Calendar.DAY_OF_WEEK, c.getFirstDayOfWeek() + 6);
        return df.format(c.getTime());
    }

    /**
     * 获取某日期是一年中的第几周
     *
     * @param dateTimeStr 日期字符串
     * @return int 第几周
     */
    public static int getWeekOfYear(String dateTimeStr) throws Exception {
        SimpleDateFormat df = new SimpleDateFormat();
        df.applyPattern("yyyy-MM-dd");
        Calendar c = Calendar.getInstance();
        c.setTime(df.parse(dateTimeStr));
        c.setFirstDayOfWeek(Calendar.MONDAY);
        return c.get(Calendar.WEEK_OF_YEAR);
    }

    /**
     * 时间前推或后推分钟,其中minute表示分钟. +后推 -前推
     *
     * @param date 日期字符串，格式年月日时分秒
     * @param minute 分钟
     * @return String
     */
    public static String getPreTime(String date, Integer minute) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String mydate1 = "";
        try {
            Date date1 = format.parse(date);
            long Time = (date1.getTime() / 1000) + minute * 60;
            date1.setTime(Time * 1000);
            mydate1 = format.format(date1);
        } catch (Exception e) {
        }
        return mydate1;
    }

    /**
     * 判断时间date1是否在时间date2之前
     *
     * @param date1 时间
     * @param date2 时间
     * @return boolean true:是，false：否
     */
    public static boolean isDateBefore(Date date1, Date date2) {
        try {
            return date1.getTime() < date2.getTime();
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 判断是否是月末
     *
     * @param date 日期
     * @return boolean true月末,false不是月末
     */
    public static boolean isYueMo(Date date) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        if (calendar.get(Calendar.DATE) == calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 判断日期是否是本周
     *
     * @param time
     * @return
     */
    public static boolean isThisWeek(long time) {
        Calendar calendar = Calendar.getInstance();
        int currentWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        calendar.setTime(new Date(time));
        int paramWeek = calendar.get(Calendar.WEEK_OF_YEAR);
        if (paramWeek == currentWeek) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否当月
     *
     * @param dateStr 日期
     * @return boolean 是返回true，否返回false
     */
    public static boolean isDangYue(String dateStr) {
        String str = dateStr.substring(5, 7);
        Calendar calendar = Calendar.getInstance();
        String cm = "";
        if (calendar.get(Calendar.MONTH) + 1 <= 9) {
            cm = "0" + (calendar.get(Calendar.MONTH) + 1);
        } else {
            cm = (calendar.get(Calendar.MONTH) + 1) + "";
        }
        if (cm.equals(str)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * 获得当前日期的前N天或后N天
     *
     * @param oper day 整数是前推N天（即过去某一天），负数是向后推N天（即将来某一天）
     * @return String 相加减后的日期字符串
     */
    public static String getSpecifiedDayBeforeOrAfter(int oper) {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - oper);
        String dayBefore = new SimpleDateFormat("yyyy-MM-dd").format(c.getTime());
        return dayBefore;
    }

    public static Calendar getSpecifiedDateBeforeOrAfter(int oper) {
        Calendar c = Calendar.getInstance();
        Date date = new Date();
        c.setTime(date);
        int day = c.get(Calendar.DATE);
        c.set(Calendar.DATE, day - oper);
        return c;
    }

    /**
     * 取得当月天数
     *
     * @return int 当月天数
     */
    public static int getCurrentMonthDays() {
        Calendar cal = Calendar.getInstance();
        cal.set(Calendar.DATE, 1);// 把日期设置为当月第一天
        cal.roll(Calendar.DATE, -1);// 日期回滚一天，也就是最后一天
        int maxDate = cal.get(Calendar.DATE);
        return maxDate;
    }

    /**
     * 取得本日是本月的第几天
     *
     * @return int 当前月的第几天
     */
    public static int getCurrentDayOfMonth() {
        Calendar cal = Calendar.getInstance();
        int num = cal.get(Calendar.DAY_OF_MONTH);
        return num;
    }

    public static int compareDateTime(String t1, String t2) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar c1 = Calendar.getInstance();
        Calendar c2 = Calendar.getInstance();
        c1.setTime(sdf.parse(t1));
        c2.setTime(sdf.parse(t2));
        Integer result = c1.compareTo(c2);
        return result;

    }

    /**
     * 日期相减
     *
     * @param date1
     * @param date2
     * @return
     * @throws ParseException
     */
    public static int getMonthSpace(Date date1, Date date2) throws ParseException {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time1 = cal.getTimeInMillis();
        cal.setTime(date2);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long time2 = cal.getTimeInMillis();
        long between_days = (time1 - time2) / (1000 * 3600 * 24);
        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取当前时间的秒数字符串
     *
     * @return
     */
    public static String getNowTimeStr() {
        String str = Long.toString(System.currentTimeMillis() / 1000);
        return str;
    }

    public static Date rollDay(Date d, int day) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(d);
        cal.add(Calendar.DAY_OF_MONTH, day);
        return cal.getTime();
    }

    /**
     * 将日期格式的字符串转换为长整型
     *
     * @param date
     * @param format
     * @return
     * @throws ParseException
     */
    public static long convert2long(String date, String format) throws ParseException {
        if (StringUtils.isNotBlank(date)) {
            if (StringUtils.isBlank(format)) {
                format = DateUtil.datePattern;
            }
            SimpleDateFormat sf = new SimpleDateFormat(format);
            return sf.parse(date).getTime();
        }
        return 0L;
    }

    /**
     * 将长整型数字转换为日期格式的字符串
     *
     * @param time
     * @param format
     * @return
     */
    public static String convert2String(long time, String format) {
        if (time > 0L) {
            if (StringUtils.isBlank(format)) {
                format = DateUtil.datePattern;
            }
            SimpleDateFormat sf = new SimpleDateFormat(format);
            Date date = new Date(time);
            return sf.format(date);
        }
        return "";
    }

    /**
     * 将长整型数字转换为日期格式的字符串
     *
     * @param time
     * @param format
     * @return
     */
    public static Date convert2Date(long time, String format) {
        if (time > 0L) {
            String strDate = convert2String(time, format);
            return getDate(strDate, format);
        }
        return null;
    }

    /**
     * 前后推月份（整数后推, 负数前推）
     *
     * @param perDates
     * @return
     * @throws ParseException
     */
    public static long perDate(int perDates) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, -1);// 得到前一天
        calendar.add(Calendar.MONTH, perDates);// 得到前一个月
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        String dates = year + "-" + month + "-" + date;
        return DateUtil.convert2long(dates, DateUtil.datePattern);
    }

    /**
     * 前后推天数（整数后推，负数前推）
     *
     * @param perDays
     * @return
     * @throws ParseException
     */
    public static long perDay(int perDays) throws ParseException {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DATE, perDays);// 得到前一天
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH) + 1;
        int date = calendar.get(Calendar.DATE);
        String dates = year + "-" + month + "-" + date;
        return DateUtil.convert2long(dates, DateUtil.datePattern);
    }

    /**
     * 获取当前时间前后小时
     *
     * @param date
     * @param hour
     * @return
     */
    public static Date addHours(Date date, int hour) {
        return add(date, Calendar.HOUR_OF_DAY, hour);
    }

    /**
     * 获取当前时间前后的时间
     *
     * @param date
     * @param calendarField
     * @param amount
     * @return
     */
    public static Date add(Date date, int calendarField, int amount) {
        if (date == null) {
            throw new IllegalArgumentException("The date must not be null");
        }
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(calendarField, amount);
        return c.getTime();
    }

    /**
     * 判断指定日期是周几
     *
     * @param dt
     * @return
     */
    public static String getWeekOfDate(Date dt) {
        String[] weekDays = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int w = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (w < 0) {
            w = 0;
        }
        return weekDays[w];
    }

    /**
     * long转String
     *
     * @param time
     * @param format
     * @return
     */
    public static String longToString(long iTime, String dateFormat) {
        Date date = new Date(Long.valueOf(iTime) * 1000);
        SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);
        return sdf.format(date);
    }

    /**
     * long转Date
     *
     * @param time
     * @param format
     * @return
     */
    public static Date longToDate(long time, String format) {
        if (time > 0L) {
            String strDate = longToString(time, format);
            return getDate(strDate, format);
        }
        return null;
    }

    /**
     * 获取指定月的前一月（年）或后一月（年）
     *
     * @param dateStr
     * @param addYear
     * @param addMonth
     * @param addDate
     * @return 输入的时期格式为yyyy-MM，输出的日期格式为yyyy-MM
     * @throws Exception
     */
    public static String getLastMonth(String dateStr, int addYear, int addMonth, int addDate) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");
            Date sourceDate = sdf.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sourceDate);
            cal.add(Calendar.YEAR, addYear);
            cal.add(Calendar.MONTH, addMonth);
            cal.add(Calendar.DATE, addDate);

            SimpleDateFormat returnSdf = new SimpleDateFormat("yyyy-MM");
            String dateTmp = returnSdf.format(cal.getTime());
            Date returnDate = returnSdf.parse(dateTmp);
            return dateTmp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 获取指定月的前一月（年）或后一月（年）
     *
     * @param dateStr
     * @param addYear
     * @param addMonth
     * @param addDate
     * @return 输入的时期格式为yyyy-MM-dd，输出的日期格式为yyyy-MM-dd
     * @throws Exception
     */
    public static String getLastDay(String dateStr, int addYear, int addMonth, int addDate) throws Exception {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Date sourceDate = sdf.parse(dateStr);
            Calendar cal = Calendar.getInstance();
            cal.setTime(sourceDate);
            cal.add(Calendar.YEAR, addYear);
            cal.add(Calendar.MONTH, addMonth);
            cal.add(Calendar.DATE, addDate);

            SimpleDateFormat returnSdf = new SimpleDateFormat("yyyy-MM-dd");
            String dateTmp = returnSdf.format(cal.getTime());
            // java.util.Date returnDate = returnSdf.parse(dateTmp);
            return dateTmp;
        } catch (Exception e) {
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    /**
     * 时间戳转换成日期格式字符串
     *
     * @param seconds 精确到秒的字符串
     * @param formatStr
     * @return
     */
    public static String timeStamp2Date(String seconds, String format) {
        if (seconds == null || seconds.isEmpty() || "null".equals(seconds)) {
            return "";
        }
        if (format == null || format.isEmpty()) {
            format = "yyyy-MM-dd HH:mm:ss";
        }
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.format(new Date(Long.valueOf(seconds + "000")));
    }

    /**
     * 计算两个时间差
     *
     * @param date1
     * @param date2
     * @param i
     * @return
     * @throws Exception
     */
    public static long timeDifference(String date1, String date2, int i) throws Exception {
        long result = 0;
        SimpleDateFormat d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        if (i == 0) {// 得到秒
            result = (d.parse(date1).getTime() - d.parse(date2).getTime()) / 1000;
        }
        if (i == 1) {// 得到分
            result = (d.parse(date1).getTime() - d.parse(date2).getTime()) / 60000;
        }
        if (i == 2) {// 得到小时
            result = (d.parse(date1).getTime() - d.parse(date2).getTime()) / 3600000;
        }
        return result;
    }

    /**
     * 示例函数
     *
     * @param args
     */
    public static void main(String[] args) {
        System.out.println("ddd====" + getDateTime(new Date()));
    }

    public static String formatDate(Date date, String format) {
        if (date == null) {
            return null;
        }
        SimpleDateFormat dateformat = new SimpleDateFormat(format);
        return dateformat.format(date);
    }

    public static Date getStartDate(Date date) throws ParseException {
        String dateStr = formatDate(date, "yyyy-MM-dd");
        dateStr = dateStr + " 00:00:00:000";
        return getDate(dateStr, "yyyy-MM-dd HH:mm:ss:SSS");
    }

    public static Date getEndDate(Date date) throws ParseException {
        String dateStr = formatDate(date, "yyyy-MM-dd");
        dateStr = dateStr + " 23:59:59:999";
        return getDate(dateStr, "yyyy-MM-dd HH:mm:ss:SSS");
    }

    public static String getStartDateStr(Date date) throws ParseException {
        String dateStr = formatDate(date, "yyyy-MM-dd");
        dateStr = dateStr + " 00:00:00:000";
        return dateStr;
    }

    public static String getEndDateStr(Date date) throws ParseException {
        String dateStr = formatDate(date, "yyyy-MM-dd");
        dateStr = dateStr + " 23:59:59:999";
        return dateStr;
    }
}
