package com.anjubao.fileupload.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;



@Slf4j
public final class DateTools {



    private static final String LONG_DATETIME_FORMAT = "yyyy-MM-dd HH:mm:ss";

    private static final String Y_M_D_DATETIME_FORMAT = "yyyy-MM-dd";

    private DateTools( ) {

    }

    public static void validDate(String sStartDate, String sEndDate) {

        Date startDate = DateTools.toDateymd(sStartDate);
        Date endDate = DateTools.toDateymd(sEndDate);
        if ((startDate == null && StringUtils.isNotBlank(sStartDate))
                || (endDate == null && StringUtils.isNotBlank(sEndDate))) {
//            throw new CustomException(ErrorNum.PARAMS_ERROR.getIndex(),
//                    "时间格式有误，正确格式：yyyy-MM-dd，请检查！");
        }
        if (StringUtils.isNotBlank(sStartDate) && StringUtils.isNotBlank(sEndDate)
                && startDate.after(endDate)) {
//            throw new CustomException(ErrorNum.PARAMS_ERROR.getIndex(), "开始时间不能大于结束时间！");
        }
    }


    /**
     * 装换成yyyy-MM-dd HH:mm:ss时间
     * 
     * @param date
     * @return
     */
    public static Date toDateymdhms(String date) {
        if (StringUtils.isNotBlank(date)) {
            try {
                return DateUtils.parseDate(date, LONG_DATETIME_FORMAT);
            } catch (ParseException e) {
                log.error("parse exception : ", e);
            }
        }
        return null;
    }

    /**
     * 装换成yyyy-MM-dd HH:mm:ss时间
     * 
     * @param date
     * @return
     */
    public static Date toDateymd(String date) {
        if (StringUtils.isNotBlank(date)) {
            try {
                return DateUtils.parseDate(date, Y_M_D_DATETIME_FORMAT);
            } catch (ParseException e) {
                log.error("parse exception : ", e);
            }
        }
        return null;
    }

    public static String getDateString(Date date) {
        return getDateString(date, "yyyy-MM-dd HH:mm:ss");
    }

    /**
     * 获取当前年月
     * 
     * @return
     */
    public static String getYearAndMonthDay() {
        return getDateString(new Date(), "yyyyMMdd");
    }

    // //毫秒级别
    public static String getMsDateString(Date date) {
        return getDateString(date, "yyyyMMddHHmmssSSS");
    }

    public static String getMsDateString3(Date date) {
        return getDateString(date, "yyMMddHHmmssSSS");
    }

    public static String getMsDateString2(Date date) {
        return getDateString(date, "yyyy-MM-dd");
    }

    public static String getShortDateString(Date date) {
        return getDateString(date, "yyMMdd");
    }

    public static String getYmdhmsDateString(Date date) {
        return getDateString(date, LONG_DATETIME_FORMAT);
    }

    /**
     * 广告开始时间格式化
     * 
     * @param
     * @param
     * @return
     */
    public static String resetStartDateStrFormat(String startDateYYYYMMdd) {
        if (StringUtils.isNotBlank(startDateYYYYMMdd) && startDateYYYYMMdd.contains("-")) {
            return startDateYYYYMMdd + " 00:00:00";
        }
        return startDateYYYYMMdd;
    }

    /**
     * 广告结束时间格式化
     * 
     * @param
     * @return
     */
    public static String resetEndDateStrFormat(String endDateYYYYMMdd) {
        if (StringUtils.isNotBlank(endDateYYYYMMdd) && endDateYYYYMMdd.contains("-")) {
            return endDateYYYYMMdd + " 23:59:59";
        }
        return endDateYYYYMMdd;
    }

    /**
     * 格式化时间
     * 
     * @param date
     * @param sFormat
     * @return
     */
    private static String getDateString(Date date, String sFormat) {
        SimpleDateFormat format = new SimpleDateFormat(sFormat);
        if (date != null) {
            return format.format(date);
        }
        return "";
    }


    /**
     * 求两个时间相差几天
     * 
     * @param beginDate
     * @param endDate
     * @return
     */
    public static int getTimeDifferenceInDays(Date beginDate, Date endDate) {
        if (beginDate != null && endDate != null) {
            return (int) ((endDate.getTime() - beginDate.getTime()) / DateUtils.MILLIS_PER_DAY);
        }
        return 0;
    }

    /**
     * 两个string类型的时间比较。
     * 
     * @return
     */
    public static boolean isCorrectIntervalTime(String startTime, String endTime) {
        Date end = toDateymdhms(endTime);
        Date start = toDateymdhms(startTime);
        return end != null && start != null && end.compareTo(start) >= 0;
    }

    /**
     * 校验开始日期是否早于当前日期
     * 
     * @param startDate
     */
    public static boolean isBeforeToday(Date startDate) {
        // 发布的开始时间不能早于当天
        Calendar nowTime = Calendar.getInstance();
        nowTime.set(Calendar.HOUR_OF_DAY, 0);
        nowTime.set(Calendar.MINUTE, 0);
        nowTime.set(Calendar.SECOND, 0);
        nowTime.set(Calendar.MILLISECOND, 0);
        if (startDate.before(nowTime.getTime())) {
            return true;
        }
        return false;
    }

}