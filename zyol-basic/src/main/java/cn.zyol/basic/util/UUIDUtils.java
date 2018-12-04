package cn.zyol.basic.util;

import org.apache.commons.lang.time.DateFormatUtils;

import java.util.Date;
import java.util.UUID;

public class UUIDUtils {

    /**
     * 基于UUID的流水号生成器，将UUID的散列值(hash code)表示的无符号整数（unsigned int）作为流水号的后缀
     * 存在极低的概率生成相同的流水号
     *
     * @return 18位纯数字的流水号，前缀：8位，日期字符串；后缀：10位，使用零填充的无符号整数
     */
    public static String serialNoGenerator() {
        UUID uuid = UUID.randomUUID();
        int intHashCode = uuid.hashCode();
        String hexHashCode = Integer.toHexString(intHashCode);
        long longHashCode = Long.parseLong(hexHashCode, 16);
        String suffix = String.format("%010d", longHashCode);
        String prefix = DateFormatUtils.format(new Date(), "yyyyMMdd");
        return prefix + suffix;
    }
}
