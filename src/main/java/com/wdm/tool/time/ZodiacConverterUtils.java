package com.wdm.tool.time;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;

/**
 * Created by wdmyong on 2018/10/03.
 */
public final class ZodiacConverterUtils {

    private ZodiacConverterUtils() {
    }

    public static String calculateZodiac(String dateStr) {
        LocalDate date = LocalDate.parse(dateStr, BASIC_ISO_DATE);
        return calculateZodiac(date);
    }

    public static String calculateZodiacWithSecond(long timestamp) {
        LocalDate date = new Date(timestamp * 1000).toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        return calculateZodiac(date);
    }

    public static String calculateZodiac(LocalDate date) {

        String zodiac = "";
        int dayOfMonth = date.getDayOfMonth();
        switch (date.getMonthValue()) {
            case 1:
                if (dayOfMonth <= 19) zodiac = "摩羯座";
                else if (dayOfMonth >= 20) zodiac = "水瓶座";
                break;
            case 2:
                if (dayOfMonth <= 18) zodiac = "水瓶座";
                else if (dayOfMonth >= 19) zodiac = "双鱼座";
                break;
            case 3:
                if (dayOfMonth <= 20) zodiac = "双鱼座";
                else if (dayOfMonth >= 21) zodiac = "白羊座";
                break;
            case 4:
                if (dayOfMonth <= 19) zodiac = "白羊座";
                else if (dayOfMonth >= 20) zodiac = "金牛座";
                break;
            case 5:
                if (dayOfMonth <= 20) zodiac = "金牛座";
                else if (dayOfMonth >= 21) zodiac = "双子座";
                break;
            case 6:
                if (dayOfMonth <= 20) zodiac = "双子座";
                else if (dayOfMonth >= 21) zodiac = "巨蟹座";
                break;
            case 7:
                if (dayOfMonth <= 22) zodiac = "巨蟹座";
                else if (dayOfMonth >= 23) zodiac = "狮子座";
                break;
            case 8:
                if (dayOfMonth <= 22) zodiac = "狮子座";
                else if (dayOfMonth >= 23) zodiac = "处女座";
                break;
            case 9:
                if (dayOfMonth <= 22) zodiac = "处女座";
                else if (dayOfMonth >= 23) zodiac = "天秤座";
                break;
            case 10:
                if (dayOfMonth <= 22) zodiac = "天秤座";
                else if (dayOfMonth >= 23) zodiac = "天蝎座";
                break;
            case 11:
                if (dayOfMonth <= 21) zodiac = "天蝎座";
                else if (dayOfMonth >= 22) zodiac = "射手座";
                break;
            case 12:
                if (dayOfMonth <= 21) zodiac = "射手座";
                else if (dayOfMonth >= 22) zodiac = "摩羯座";
                break;
            default:
                break;
        }
        return zodiac;
    }
}
