package com.wdm.tool.time;

import java.time.LocalDateTime;
import java.util.Date;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by wdmyong on 2018/10/03.
 */
public class LocalTimeDateConvertUtilsTest {

    @Test
    public void test()
    {
        Date date = new Date();
        LocalDateTime localTime = LocalTimeDateConvertUtils.convertDate2LocalTime(date);
        assertTrue(date.equals(LocalTimeDateConvertUtils.convertLocalTime2Date(localTime)));
    }
}
