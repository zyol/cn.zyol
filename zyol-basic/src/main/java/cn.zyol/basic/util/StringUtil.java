package cn.zyol.basic.util;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.lang.StringUtils;

/**
 * 类StringUtil.java的实现描述：TODO 类实现描述 String的util
 */
public class StringUtil extends StringUtils {

    public StringUtil() {
    }

    /**
     * 手机号隐藏 例 ：158*****5566
     *
     * @param phone
     * @return
     */
    public static String hidePhone(String phone) {
        if (phone == null || phone.length() < 3) {
            return "";
        }
        return hideStr(3, 4, 4, phone);
    }

    /**
     * 手机号隐藏 例 ：15***666
     *
     * @param phone
     * @return
     */
    public static String hidePhone2(String phone) {
        if (phone == null || phone.length() < 3) {
            return "";
        }
        return hideStr(2, 3, 3, phone);
    }

    /**
     * 隐藏姓名 例：*三,*德华
     *
     * @param name
     * @return
     */
    public static String hideName(String name) {
        if (isBlank(name)) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        if (name.length() > 3) {
            sb.append(creatHideChar(2)).append(name.substring(2));// 复姓
        } else {
            sb.append(creatHideChar(1)).append(name.substring(1));
        }
        return sb.toString();
    }

    /**
     * 隐藏身份证
     *
     * @param cardCode
     * @return
     */
    public static String hideCardCode(String cardCode) {
        if (isBlank(cardCode)) {
            return "";
        }
        return hideStr(6, 1, 11, cardCode);
    }

    /**
     * 隐藏用户名 例：lu***cy,l路***cy
     *
     * @param loginName
     * @return
     */
    public static String hideLoginName(String loginName) {
        if (isBlank(loginName)) {
            return loginName;
        }
        StringBuilder buf = new StringBuilder();
        int len = loginName.length();
        if (len > 5) {
            // return buf.append(loginName.substring(0,
            // 2)).append(creatHideChar(3)).append(loginName.substring(5)).toString();
            return buf.append(loginName.substring(0, 2)).append(creatHideChar(len - 4)).append(loginName.substring(len - 2)).toString();
        }
        if (len <= 2) {
            return loginName;
        }
        if (len > 2 && len <= 5) {
            return buf.append(loginName.substring(0, 2)).append(creatHideChar(len - 2)).toString();
        }
        return "";
    }

    /**
     * 隐藏邮箱
     *
     * @param email
     * @return
     */
    public static String hideEmail(String email) {
        if (isBlank(email)) {
            return "";
        }
        String prefix = email.substring(0, email.indexOf("@"));
        String suffix = email.substring(email.indexOf("@"));
        if (prefix.length() <= 3) {
            return hideStr(1, 0, prefix.length() - 1, prefix) + suffix;
        }
        return hideStr(prefix.length() - 3, 0, 3, prefix) + suffix;
    }

    /**
     * 截取银行卡后4位
     *
     * @param bankCard
     * @return
     */
    public static String hideBankCard(String bankCard) {
        if (isBlank(bankCard)) {
            return "";
        }
        if (bankCard.length() <= 4) {
            return bankCard;
        }
        return bankCard.substring(bankCard.length() - 4);
    }

    /**
     * 生成*字符窜
     *
     * @param num
     * @return
     */
    public static String creatHideChar(int num) {
        StringBuilder sb = new StringBuilder();
        while (num > 0) {
            sb.append("*");
            num--;
        }
        return sb.toString();
    }

    /**
     * 隐藏字符的显示
     *
     * @param preNum 字符前端要显示的字符数
     * @param sufNum 字符后端要显示的字符数
     * @param str
     * @return
     */
    public static String hideStr(int preNum, int sufNum, int hideNum, String str) {
        if (str == null) {
            return str;
        }
        int len = str.length();
        if (preNum + sufNum > len) {
            return str;
        }
        StringBuilder sb = new StringBuilder();
        if (sufNum > 0) {
            sb.append(str.substring(0, preNum)).append(creatHideChar(hideNum)).append(str.substring(len - sufNum));
        } else {
            sb.append(str.substring(0, preNum)).append(creatHideChar(hideNum));
        }
        return sb.toString();
    }

    /**
     * 将byte数组转化为16进制
     *
     * @param buf
     * @return
     */
    public static String parseByte2HexStr(byte[] buf) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < buf.length; i++) {
            String hex = Integer.toHexString(buf[i] & 0xFF);
            if (hex.length() == 1) {
                hex = '0' + hex;
            }
            sb.append(hex);
        }
        return sb.toString();
    }

    public static byte[] parseHexStr2Byte(String hexStr) {
        byte[] result = new byte[hexStr.length() / 2];
        for (int i = 0; i < hexStr.length() / 2; i++) {
            int strIndex = i << 1;
            int high = Integer.parseInt(hexStr.substring(strIndex, strIndex + 1), 16);
            int low = Integer.parseInt(hexStr.substring(strIndex + 1, strIndex + 2), 16);
            result[i] = (byte) ((high << 4) + low);
        }
        return result;
    }

    /**
     * 判断一个字符是Ascill字符还是其它字符（如汉，日，韩文字符）
     *
     * @param char c, 需要判断的字符
     * @return boolean, 返回true,Ascill字符
     */
    public static boolean isLetter(char c) {
        int k = 0x80;
        return c / k == 0 ? true : false;
    }

    /**
     * 得到一个字符串的长度,显示的长度,一个汉字或日韩文长度为2,英文字符长度为1
     *
     * @param String s ,需要得到长度的字符串
     * @return int, 得到的字符串长度
     */
    public static int lengths(String s) {
        if (s == null) {
            return 0;
        }
        char[] c = s.toCharArray();
        int len = 0;
        for (int i = 0; i < c.length; i++) {
            len++;
            if (!isLetter(c[i])) {
                len++;
            }
        }
        return len;
    }

    /**
     * 截取一段字符的长度,不区分中英文,如果数字不正好，则少取一个字符位
     *
     * @author patriotlml
     * @param String origin, 原始字符串
     * @param int len, 截取长度(一个汉字长度按2算的)
     * @return String, 返回的字符串
     */
    public static String substring(String origin, int len) {
        if (origin == null || origin.equals("") || len < 1) {
            return "";
        }
        byte[] strByte = new byte[len];
        if (len > lengths(origin)) {
            return origin;
        }
        System.arraycopy(origin.getBytes(), 0, strByte, 0, len);
        int count = 0;
        for (int i = 0; i < len; i++) {
            int value = (int) strByte[i];
            if (value < 0) {
                count++;
            }
        }
        if (count % 2 != 0) {
            // len = (len == 1) ? ++len : --len;
            --len;
        }
        return new String(strByte, 0, len);
    }

    /**
     * 用于转换字符类的list为字符串
     *
     * @param list
     * @param delimt
     * @return
     */
    public static String list2Str(List<Object> list, String delimt) {
        if (list == null || list.isEmpty()) {
            return null;
        }
        StringBuffer ids = new StringBuffer();
        for (Object value : list) {
            ids.append(value).append(delimt);
        }
        return ids.substring(0, ids.length() - 1);

    }

    public static boolean sql_Injection(String str) {
        if (str == null || "".equals(str)) {
            return true;
        }
        String inj_str = "' and exec insert select delete update" + " count * % chr mid master truncate char declare ; or - + ,";
        String arr[] = inj_str.split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (str.indexOf(arr[i]) != -1) {
                return true;
            }
        }
        return false;
    }

    public static boolean notNumber(String str) {
        try {
            if (str == null || "".equals(str)) {
                return true;
            }
            String arr[] = str.split(",");
            for (int i = 0; i < arr.length; i++) {
                Integer.parseInt(arr[i]);
            }
        } catch (Exception e) {
            return true;
        }
        return false;
    }

    /**
     * 截取字符传 字符，汉字均视为一个字符
     *
     * @param str
     * @param length
     * @return
     */
    public static String cutString(String str, int length) {
        if (str == null || str.equals("") || length < 1) {
            return "";
        }
        if (str.length() < length) {
            return str;
        }
        // 把字符串转换成char类型的数组
        char[] b = str.toCharArray();
        // 取数组里的等长度字符
        String s = new String(b, 0, length);

        return s;
    }

    /**
     * 字符串替换
     *
     * @param str
     * @return
     */
    public static String replaceStr(String str) {
        String res = str.replaceAll("delete", "");
        res = res.replaceAll("truncate", "");
        res = res.replaceAll("drop", "");
        res = res.replaceAll("update", "");
        res = res.replaceAll("insert", "");
        res = res.replaceAll("select", "");
        res = res.replaceAll("value", "");
        res = res.replaceAll("<!", "");
        res = res.replaceAll(">", "");
        res = res.replaceAll("--", "");
        return res;
    }

    /**
     * 根据日期格式如20160930 替换为季度名称
     *
     * @param str
     * @return
     */
    public static String replaceqQuarter(String str) {
        if (str == null || "".equals(str) || str.length() != 8) {
            return null;
        }
        String year = str.substring(0, 4);
        String quarter = str.substring(4);
        String quarterName = "";
        switch (quarter) {
            case "0331":
                quarterName = "一季报";
                break;
            case "0630":
                quarterName = "二季报";
                break;
            case "0930":
                quarterName = "三季报";
                break;
            case "1231":
                quarterName = "年报";
                break;
            default:
                return null;
        }
        return year + quarterName;
    }

    /**
     * 判断字符串是否含有特殊字符
     *
     * @param string
     * @return
     */
    public static boolean isSpecialChar(String string) {
        if (string.replaceAll("[\u4e00-\u9fa5]*[a-z]*[A-Z]*\\d*-*_*\\s*", "").length() == 0) {
            // 不包含特殊字符
            return false;
        }
        return true;
    }

    /**
     * 判断一个字符串是否含有数字
     *
     * @param content
     * @return
     */
    public static boolean hasDigit(String content) {
        boolean flag = false;
        Pattern p = Pattern.compile(".*\\d+.*");
        Matcher m = p.matcher(content);
        if (m.matches()) {
            flag = true;
        }
        return flag;
    }

    public static void main(String[] args) {
        System.out.println(isSpecialChar("十多个发达@"));
        System.out.println(hasDigit("测试ddd88"));
    }
}
