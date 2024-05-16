package com.boot.dandelion.health.care.common.util;

import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * @ClassName DateUtils
 * @Description 日期格式转换工具类
 * @Author shr
 * @Date 2022/07/19
 */
public class DateUtils {

    public static final String PATTERN = "yyyy-MM-dd";
    public static final String PATTERN_EXPAND = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_MONTH = "yyyy-MM";
    public static final String PATTERN_EXPAND_US = "EEE MMM dd HH:mm:ss zzz yyyy";

    public static final DateTimeFormatter DFY_MD_HMS = DateTimeFormatter.ofPattern(PATTERN_EXPAND);
    public static final DateTimeFormatter DFY_MD = DateTimeFormatter.ofPattern(PATTERN);
    public static final SimpleDateFormat SDF_MD_HMS = new SimpleDateFormat(PATTERN_EXPAND);
    public static final SimpleDateFormat SDF_MD = new SimpleDateFormat(PATTERN);
    public static final SimpleDateFormat SDF_MD_HMS_US = new SimpleDateFormat(PATTERN_EXPAND_US, Locale.US);

    /**
     * LocalDateTime 转时间戳
     *
     * @param localDateTime /
     * @return /
     */
    public static Long getTimeStamp(LocalDateTime localDateTime) {
        return localDateTime.atZone(ZoneId.systemDefault()).toEpochSecond();
    }

    /**
     * 时间戳转LocalDateTime
     *
     * @param timeStamp /
     * @return /
     */
    public static LocalDateTime fromTimeStamp(Long timeStamp) {
        return LocalDateTime.ofEpochSecond(timeStamp, 0, OffsetDateTime.now().getOffset());
    }

    /**
     * LocalDateTime 转 Date
     * Jdk8 后 不推荐使用 {@link Date} Date
     *
     * @param localDateTime /
     * @return /
     */
    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }

    /**
     * LocalDate 转 Date
     * Jdk8 后 不推荐使用 {@link Date} Date
     *
     * @param localDate /
     * @return /
     */
    public static Date toDate(LocalDate localDate) {
        return toDate(localDate.atTime(LocalTime.now(ZoneId.systemDefault())));
    }


    /**
     * Date转 LocalDateTime
     * Jdk8 后 不推荐使用 {@link Date} Date
     *
     * @param date /
     * @return /
     */
    public static LocalDateTime toLocalDateTime(Date date) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault());
    }

    /**
     * 日期 格式化
     *
     * @param localDateTime /
     * @param patten        /
     * @return /
     */
    public static String localDateTimeFormat(LocalDateTime localDateTime, String patten) {
        DateTimeFormatter df = DateTimeFormatter.ofPattern(patten);
        return df.format(localDateTime);
    }

    /**
     * 日期 格式化
     *
     * @param localDateTime /
     * @param df            /
     * @return /
     */
    public static String localDateTimeFormat(LocalDateTime localDateTime, DateTimeFormatter df) {
        return df.format(localDateTime);
    }

    /**
     * 日期格式化 yyyy-MM-dd HH:mm:ss
     *
     * @param localDateTime /
     * @return /
     */
    public static String localDateTimeFormatyMdHms(LocalDateTime localDateTime) {
        return DFY_MD_HMS.format(localDateTime);
    }

    /**
     * 日期格式化 yyyy-MM-dd
     *
     * @param localDateTime /
     * @return /
     */
    public static String localDateTimeFormatyMd(LocalDateTime localDateTime) {
        return DFY_MD.format(localDateTime);
    }

    /**
     * 字符串转 LocalDateTime ，字符串格式 yyyy-MM-dd
     *
     * @param localDateTime /
     * @return /
     */
    public static LocalDateTime parseLocalDateTimeFormat(String localDateTime, String pattern) {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern(pattern);
        return LocalDateTime.from(dateTimeFormatter.parse(localDateTime));
    }

    /**
     * 字符串转 LocalDateTime ，字符串格式 yyyy-MM-dd
     *
     * @param localDateTime /
     * @return /
     */
    public static LocalDateTime parseLocalDateTimeFormat(String localDateTime, DateTimeFormatter dateTimeFormatter) {
        return LocalDateTime.from(dateTimeFormatter.parse(localDateTime));
    }

    /**
     * 字符串转 LocalDateTime ，字符串格式 yyyy-MM-dd HH:mm:ss
     *
     * @param localDateTime /
     * @return /
     */
    public static LocalDateTime parseLocalDateTimeFormatyMdHms(String localDateTime) {
        return LocalDateTime.from(DFY_MD_HMS.parse(localDateTime));
    }


    /**
     * date_md --> text
     *
     * @param date
     * @return
     */
    public static String sdfTimeFormat(Date date) {
        return SDF_MD.format(date);
    }

    /**
     * date_md_hms --> text
     *
     * @param date
     * @return
     */
    public static String sdfTimeFormatMdHms(Date date) {
        return SDF_MD_HMS.format(date);
    }

    /**
     * text ---> sql_date
     *
     * @param date
     * @return
     */
    public static java.sql.Date parseSqlDateFormat(String date) {
        java.sql.Date sqlDate = null;
        try {
            Date parse = SDF_MD.parse(date);
            sqlDate = new java.sql.Date(parse.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return sqlDate;
    }

    /**
     * @Description: US-Date -> China-Date xxxx-xx-xx
     * @param: [date]
     * @return: java.util.Date
     * @author: shr
     * @date: 2022/07/19
     */
    public static Date usDate2ChinaDate(Date usDate) {
        Date chinaDate = null;
        String chinaDateString = SDF_MD.format(usDate);
        chinaDate = parseSqlDateFormat(chinaDateString);
        return chinaDate;
    }


    /**
     * @Description: 时间格式转换：美国CST时间转为中国上海/北京时区时间格式
     * @param: [date]
     * @return: java.lang.String
     * @author: shr
     * @date: 2022/07/19
     */
    public static String usTime2ChinaTime(String date) throws ParseException {
        if (StringUtils.isBlank(date) || StringUtils.isEmpty(date)) {
            return "";
        }
        Date date1 = SDF_MD_HMS_US.parse(date);
        return SDF_MD_HMS.format(date1);
    }

    /**
     * @Description: 月差
     * @param: [lastDate, nowDate]
     * @return: java.lang.Integer
     * @author: shr
     * @date: 2022/07/19
     */
    public static Integer durationMonth(Date lastDate, Date nowDate) {
        DateTimeFormatter SDF_MD_DURATION = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate lastDateLocalDate = LocalDate.of(lastDate.getYear(), lastDate.getMonth() + 1, lastDate.getDate());
        YearMonth lastYearMonth = YearMonth.parse(SDF_MD_DURATION.format(lastDateLocalDate), SDF_MD_DURATION);

        LocalDate nowDateLocalDate = LocalDate.of(nowDate.getYear(), nowDate.getMonth() + 1, nowDate.getDate());
        YearMonth nowDateYearMonth = YearMonth.parse(SDF_MD_DURATION.format(nowDateLocalDate), SDF_MD_DURATION);

        int monDif = nowDateYearMonth.getMonthValue() - lastYearMonth.getMonthValue();
        int month = (nowDateYearMonth.getYear() - lastYearMonth.getYear()) * 12;
        return monDif + month;
    }

    /**
     * @Description: 如果没有启用日期，则启用日期默认是上一年的启用日期
     * @param: []
     * @return: java.util.Date
     * @author: shr
     * @date: 2022/07/19
     */
    public static Date generateBeginDate() {
        SimpleDateFormat sdf = new SimpleDateFormat(PATTERN_EXPAND);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.YEAR, -1);
        return c.getTime();
    }

    /**
     * @Description: 生成距离现在指定月份的时间
     * @param: []
     * @return: java.lang.String
     * @author: shr
     * @date: 2022/07/19
     */
    public static String generateNumberMonthByNow(Integer monthDuration) {
        SimpleDateFormat format = new SimpleDateFormat(PATTERN_EXPAND);
        Calendar c = Calendar.getInstance();
        c.setTime(new Date());
        c.add(Calendar.MONTH, monthDuration);
        Date m = c.getTime();
        return format.format(m);
    }

    /**
     * @Description: 生成指定年月的下N个月
     * @param: [date, monthDuration]
     * @return: java.lang.String
     * @author: shr
     * @date: 2022/07/19
     */
    public static String generateNumberMonthByDate(Date date, Integer monthDuration) {
        SimpleDateFormat format = new SimpleDateFormat(PATTERN_EXPAND);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, monthDuration);
        Date m = c.getTime();
        return format.format(m);
    }

    /**
     * @Description:
     * @param: [date, monthDuration]
     * @return: java.lang.String
     * @author: shr
     * @date: 2022/07/19
     */
    public static String generateNumberMonthAndYearByDate(Date date, Integer monthDuration) {
        SimpleDateFormat format = new SimpleDateFormat(PATTERN_MONTH);
        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.MONTH, monthDuration);
        Date m = c.getTime();
        return format.format(m);
    }

    /**
     * date 前year时间
     *
     * @param date
     * @param year
     * @return
     */
    public static Date getBeforeYear(Date date, int year) {
        date.setYear(date.getYear() - year);
        return date;
    }

    public static String getNowDate() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_EXPAND);
        return currentTime.format(formatter);
    }

    public static String getPreviousDate() {
        LocalDateTime currentTime = LocalDateTime.now().minusHours(1);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(PATTERN_EXPAND);
        return currentTime.format(formatter);
    }
}

