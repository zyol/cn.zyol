package cn.zyol.basic.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.util.Random;
import java.util.UUID;

/**
 * 格式化数值
 */
public class Numbers {

    /**
     * 天数字符串的转换 大于90天用月，小于用天：55天，3个月+
     * 
     * @param dayDiff
     * @return
     */
    public static String getPeriodBetweenDates(Integer dayDiff) {
        if ((dayDiff / 30) >= 3) {
            StringBuilder contentBuilder = new StringBuilder();
            contentBuilder.append(String.format("%d个月", dayDiff / 30));
            contentBuilder.append(dayDiff % 30 == 0 ? "" : "+");
            return String.valueOf(contentBuilder);
        }
        return String.format("%d天", dayDiff);
    }

    /**
     * 万元 格式化
     * 
     * @param val
     * @return
     */
    public static String formatWan(BigDecimal val) {
        String unit = "元";
        if (val == null) {
            return "0.00" + unit;
        }
        if (val.compareTo(new BigDecimal(10000)) >= 0) {
            unit = "万";
            val = val.divide(new BigDecimal(10000));
        }
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(6); // 设置数值的小数部分允许的最大位数。
        format.setMinimumFractionDigits(2); // 设置数值的小数部分允许的最小位数。
        format.setMaximumIntegerDigits(10); // 设置数值的整数部分允许的最大位数。
        format.setMinimumIntegerDigits(1); // 设置数值的整数部分允许的最小位数.
        return format.format(val.doubleValue()) + unit;
    }

    public static String formatWanForApp(BigDecimal val) {
        String unit = "元";
        if (val == null) {
            return "0.00" + unit;
        }
        // if(val.compareTo(new BigDecimal(1000))>=0){
        //
        // }
        unit = "万";
        val = val.divide(new BigDecimal(10000));
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(6); // 设置数值的小数部分允许的最大位数。
        format.setMinimumFractionDigits(2); // 设置数值的小数部分允许的最小位数。
        format.setMaximumIntegerDigits(10); // 设置数值的整数部分允许的最大位数。
        format.setMinimumIntegerDigits(1); // 设置数值的整数部分允许的最小位数.
        if (unit.equals("万")) {
            return format.format(val.setScale(2, RoundingMode.HALF_UP).doubleValue()) + unit;
        } else {
            return format.format(val.doubleValue()) + unit;
        }

    }

    /**
     * 百分比(0-100 内的整数)
     * 
     * @param dividend
     * @param divisor
     * @return
     */
    public static int getPercent(BigDecimal dividend, BigDecimal divisor) {
        int percent = 0;
        if (dividend == null || dividend.compareTo(BigDecimal.ZERO) < 0) {
            return percent;
        }
        if (divisor == null || divisor.compareTo(BigDecimal.ZERO) <= 0) {
            return percent;
        }
        BigDecimal result = dividend.multiply(new BigDecimal(100)).divide(divisor, RoundingMode.DOWN);
        percent = result.intValue();
        if (percent > 100) {
            percent = 100;
        }
        if (percent <= 0) {
            percent = 0;
        }
        return percent;
    }

    /**
     * 百分比(0-100 内的整数)
     * 
     * @param dividend
     * @param divisor
     * @return
     */
    public static int getPercent(Integer dividend, Integer divisor) {
        if (dividend == null) {
            dividend = 0;
        }
        if (divisor == null) {
            divisor = 1;
        }
        int result = Math.round((dividend * 100) / divisor);

        if (result > 100) {
            result = 100;
        }
        if (result <= 0) {
            result = 0;
        }
        return result;
    }

    /**
     * 货币，每三位加逗号
     *
     * @param val
     * @return
     */
    public static String toCurrency(BigDecimal val) {
        if (val == null) {
            return "0.00";
        }

        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2); // 设置数值的小数部分允许的最大位数。
        format.setMinimumFractionDigits(2); // 设置数值的小数部分允许的最小位数。
        format.setMaximumIntegerDigits(16); // 设置数值的整数部分允许的最大位数。
        format.setMinimumIntegerDigits(1); // 设置数值的整数部分允许的最小位数.
        return format.format(val.doubleValue());
    }

    public static int intValue(BigDecimal b) {
        if (b == null) {
            return 0;
        }
        return b.intValue();
    }

    /**
     * 相加
     *
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal add(BigDecimal b1, BigDecimal b2) {
        if (b1 == null) {
            b1 = BigDecimal.ZERO;
        }
        if (b2 == null) {
            b2 = BigDecimal.ZERO;
        }
        return b1.add(b2);
    }

    /**
     * 相减
     * 
     * @param b1
     * @param b2
     * @return
     */
    public static BigDecimal subtract(BigDecimal b1, BigDecimal b2) {
        if (b1 == null) {
            b1 = BigDecimal.ZERO;
        }
        if (b2 == null) {
            b2 = BigDecimal.ZERO;
        }
        return b1.subtract(b2);
    }

    /**
     * 相乘
     *
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal mul(BigDecimal b1, BigDecimal b2) {
        if (b1 == null) {
            b1 = BigDecimal.ZERO;
        }
        if (b2 == null) {
            b2 = BigDecimal.ZERO;
        }
        return b1.multiply(b2);
    }

    /**
     * 相除(不四舍五入)
     *
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal div(BigDecimal b1, BigDecimal b2, int len) {
        if (b1 == null) {
            b1 = BigDecimal.ZERO;
        }
        if (b2 == null || b2.compareTo(BigDecimal.ZERO) == 0) {
            b2 = BigDecimal.ONE;
        }
        return b1.divide(b2, len, RoundingMode.DOWN);
    }

    /**
     * 相除(四舍五入)
     *
     * @param d1
     * @param d2
     * @return
     */
    public static BigDecimal round(BigDecimal b1, BigDecimal b2, int len) {
        if (b1 == null) {
            b1 = BigDecimal.ZERO;
        }
        if (b2 == null) {
            b2 = BigDecimal.ONE;
        }
        return b1.divide(b2, len, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 相加
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Double addDouble(Double b1, Double b2) {
        BigDecimal dVal = add(new BigDecimal(b1), new BigDecimal(b2));
        return dVal.doubleValue();
    }

    /**
     * 相减
     * 
     * @param b1
     * @param b2
     * @return
     */
    public static Double subDouble(Double b1, Double b2) {
        BigDecimal dVal = subtract(new BigDecimal(b1), new BigDecimal(b2));
        return dVal.doubleValue();
    }

    /**
     * 相乘
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Double mulDouble(Double b1, Double b2) {
        BigDecimal dVal = mul(new BigDecimal(b1), new BigDecimal(b2));
        return dVal.doubleValue();
    }

    /**
     * 相除(不四舍五入)
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Double divDouble(Double b1, Double b2, int len) {
        BigDecimal dVal = div(new BigDecimal(b1), new BigDecimal(b2), len);
        return dVal.doubleValue();
    }

    /**
     * 相除(四舍五入)
     *
     * @param d1
     * @param d2
     * @return
     */
    public static Double roundDouble(Double b1, Double b2, int len) {
        BigDecimal dVal = round(new BigDecimal(b1), new BigDecimal(b2), len);
        return dVal.doubleValue();
    }

    /**
     * 生产流水号
     * 
     * @param type
     * @param id
     * @return
     */
    public static synchronized String getSerialNumber(Integer itradeType, Integer id) {
        int machineId = 9;
        int hashCodeV = UUID.randomUUID().toString().hashCode();
        if (hashCodeV < 0) {
            hashCodeV = -hashCodeV;
        }
        StringBuffer sb = new StringBuffer();
        sb.append(String.format("%02d", itradeType));
        sb.append(DateUtil.getDate("yyyyMMdd"));
        sb.append(machineId + String.format("%010d", hashCodeV));
        sb.append(String.format("%0" + 5 + "d", id));
        return sb.toString();
    }

    /**
     * 格式化 固定长度字符
     * 
     * @param str
     * @param len
     * @return
     */
    public static String formatStrToNo(String str, Integer len) {
        if (str.length() > len) {
            str = str.substring(str.length() - len);
        } else {
            str = String.format("%11s", str);
            str = str.replaceAll("\\s", "0");
        }
        return str;
    }

    /**
     * 万元 格式化(整数)
     * 
     * @param val
     * @return
     */
    public static String formatWanToInt(BigDecimal val) {
        String unit = "元";
        if (val == null) {
            return "0" + unit;
        }
        if (val.compareTo(new BigDecimal(10000)) >= 0) {
            unit = "万";
            val = val.divide(new BigDecimal(10000));
        }
        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(6); // 设置数值的小数部分允许的最大位数。
        format.setMinimumFractionDigits(2); // 设置数值的小数部分允许的最小位数。
        format.setMaximumIntegerDigits(10); // 设置数值的整数部分允许的最大位数。
        format.setMinimumIntegerDigits(1); // 设置数值的整数部分允许的最小位数.
        return format.format(val.doubleValue()).substring(0, format.format(val.doubleValue()).length() - 3) + unit;
    }

    /**
     * 获取随机字符串
     * 
     * @param length 字符串长度
     * @return
     */
    public static String getRandomString(int length) {
        StringBuffer buffer = new StringBuffer("0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
        StringBuffer sb = new StringBuffer();
        Random r = new Random();
        int range = buffer.length();
        for (int i = 0; i < length; i++) {
            sb.append(buffer.charAt(r.nextInt(range)));
        }
        return sb.toString();
    }

    /**
     * 验证字符串是否为数字
     * 
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        java.util.regex.Pattern pattern = java.util.regex.Pattern.compile("[0-9]*");
        return pattern.matcher(str).matches();
    }

    /**
     * 保留两位小数，四舍五入
     * 
     * @param value
     * @return
     */
    public static BigDecimal round(BigDecimal value) {
        if (null == value) {
            value = new BigDecimal(0);
        }
        return value.setScale(2, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 保留4位小数，四舍五入
     * 
     * @param value
     * @return
     */
    public static BigDecimal roundFour(BigDecimal value) {
        if (null == value) {
            value = new BigDecimal(0);
        }
        return value.setScale(4, BigDecimal.ROUND_HALF_UP);
    }
    
    /**
     * 保留两位小数，舍去小数
     * 
     * @param value
     * @return
     */
    public static BigDecimal roundNumber(BigDecimal value) {
        if (null == value) {
            value = new BigDecimal(0);
        }
        return value.setScale(2, BigDecimal.ROUND_DOWN);
    }

    /**
     * hash
     * 
     * @param value
     * @param divs
     * @return
     */
    public static int hash(long value, int divs) {
        int hashCode = (int) (value ^ (value >>> 32));
        int res = hashCode % divs;
        if(res < 0) {
            res = Math.abs(res);
        }
        return res;
    }
    
    public static void main(String[] args) throws Exception {
       
    }
}
