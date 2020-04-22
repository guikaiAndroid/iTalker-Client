package net.guikai.italker.utils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

/**
 * Description: 时间工具类
 * Crete by Anding on 2020-04-22
 */
public class DateTimeUtil {
    private static final SimpleDateFormat FORMAT = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);

    /**
     * 获取一个简单的时间字符串
     *
     * @param date Date
     * @return 时间字符串
     */
    public static String getSampleDate(Date date) {
        return FORMAT.format(date);
    }
}
