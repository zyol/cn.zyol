package cn.zyol.basic.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author yangpeiliang
 */
public class WorkDayUtil {

    private static List<Calendar> holidayList = null; // 节假日列表

    public static String getWorkDay(String date, int inum) {
        try {
            DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            Calendar ca = Calendar.getInstance();
            Date d = df.parse(date);
            ca.setTime(d);// 设置当前时间
            Calendar c = addDateByWorkDay(ca, inum);
            return df.format(c.getTime());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取下inum个工作日
     * 
     * @param date
     * @param inum
     * @return
     */
    public static String getWorkDay(Date date, int inum) {
        String t1 = "";
        t1 = getWorkDay(DateUtil.getDate(date), inum);
        return t1;
    }

    public static String getDate(Date date, int inum) {
        String t1 = "";
        if (DateUtil.getHour() < 15) {
            t1 = getWorkDay(DateUtil.getDate(date), inum);
        } else {
            t1 = getWorkDay(DateUtil.getDate(date), inum + 1);
        }
        return t1;
    }

    /**
     * 验证是否工作日
     * 
     * @param date
     * @return 工作日:false;非工作日:true
     * @throws Exception
     */
    public static boolean checkHoliday(Date date) throws Exception {
        Calendar ca = Calendar.getInstance();
        ca.setTime(date);
        return checkHoliday(ca);
    }

    /**
     * <p>
     * Title: addDateByWorkDay
     * </P>
     * <p>
     * Description: TODO 计算相加day天，并且排除节假日和周末后的日期
     * </P>
     * 
     * @param calendar 当前的日期
     * @param day 相加天数
     * @return return Calendar 返回类型 返回相加day天，并且排除节假日和周末后的日期 throws date 2014-11-24 上午10:32:55
     */
    public static Calendar addDateByWorkDay(Calendar calendar, int day) {
        try {
            for (int i = 0; i < day; i++) {
                calendar.add(Calendar.DAY_OF_MONTH, 1);
                if (checkHoliday(calendar)) {
                    i--;
                }
            }
            return calendar;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return calendar;
    }

    /**
     * <p>
     * Title: checkHoliday
     * </P>
     * <p>
     * Description: TODO 验证日期是否是节假日
     * </P>
     * 
     * @param calendar 传入需要验证的日期
     * @return return boolean 返回类型 返回true是节假日，返回false不是节假日 throws date 2014-11-24 上午10:13:07
     */
    public static boolean checkHoliday(Calendar calendar) throws Exception {
        initDateForHolidayList();
        // 判断日期是否是周六周日
        if (calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SUNDAY || calendar.get(Calendar.DAY_OF_WEEK) == Calendar.SATURDAY) {
            return true;
        }
        // 判断日期是否是节假日
        if (null != holidayList) {
            for (int i = 0; i < holidayList.size(); i++) {
                Calendar ca = holidayList.get(i);
                if (null != ca) {
                    if (ca.get(Calendar.MONTH) == calendar.get(Calendar.MONTH) && ca.get(Calendar.DAY_OF_MONTH) == calendar.get(Calendar.DAY_OF_MONTH)
                        && ca.get(Calendar.YEAR) == calendar.get(Calendar.YEAR)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static void initDateForHolidayList() {
        holidayList = new ArrayList<Calendar>();
        WorkDayUtil ct = new WorkDayUtil();
        ct.initHolidayList("2016-12-31");
        ct.initHolidayList("2017-01-01");
        ct.initHolidayList("2017-01-02");
        ct.initHolidayList("2017-01-27");
        ct.initHolidayList("2017-01-28");
        ct.initHolidayList("2017-01-29");
        ct.initHolidayList("2017-01-30");
        ct.initHolidayList("2017-01-31");
        ct.initHolidayList("2017-02-01");
        ct.initHolidayList("2017-02-02");
        ct.initHolidayList("2017-04-02");
        ct.initHolidayList("2017-04-03");
        ct.initHolidayList("2017-04-04");
        ct.initHolidayList("2017-04-29");
        ct.initHolidayList("2017-04-30");
        ct.initHolidayList("2017-05-01");
        ct.initHolidayList("2017-05-28");
        ct.initHolidayList("2017-05-29");
        ct.initHolidayList("2017-05-30");
        ct.initHolidayList("2017-10-01");
        ct.initHolidayList("2017-10-02");
        ct.initHolidayList("2017-10-03");
        ct.initHolidayList("2017-10-04");
        ct.initHolidayList("2017-10-05");
        ct.initHolidayList("2017-10-06");
        ct.initHolidayList("2017-10-07");
        ct.initHolidayList("2017-10-08");
    }

    /**
     * <p>
     * Title: initHolidayList
     * </P>
     * <p>
     * Description: TODO 把所有节假日放入list
     * </P>
     * 
     * @param date 从数据库查 查出来的格式2014-05-09 return void 返回类型 throws date 2014-11-24 上午10:11:35
     */
    public void initHolidayList(String date) {
        String[] da = date.split("-");
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, Integer.valueOf(da[0]));
        calendar.set(Calendar.MONTH, Integer.valueOf(da[1]) - 1);// 月份比正常小1,0代表一月
        calendar.set(Calendar.DAY_OF_MONTH, Integer.valueOf(da[2]));
        holidayList.add(calendar);
    }
    
    public static void main(String[] args) {
        try {
            // System.out.println(WorkDayUtil.checkHoliday(DateUtil.getDate("2017-02-01", "yyyy-MM-dd")));
            System.out.println(WorkDayUtil.getWorkDay("2017-04-08", 1));
            System.out.println(WorkDayUtil.getWorkDay("2017-04-08", 2));
            // System.out.println(WorkDayUtil.getWorkDay("2016-07-01", 1));
            System.out.println(WorkDayUtil.getDate(new Date(), 2));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
