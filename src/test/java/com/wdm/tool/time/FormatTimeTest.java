package com.wdm.tool.time;

import java.text.DecimalFormat;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import static java.util.concurrent.TimeUnit.MILLISECONDS;

/**
 * Created by wdmyong on 2018/10/30.
 */
public class FormatTimeTest {

    private static final long ONE_MINUTE_IN_MILLIS = TimeUnit.MINUTES.toMillis(1);
    private static final DecimalFormat DECIMAL_FORMATTER = new DecimalFormat("00000");

    private enum A {
        a("a"),
        b("a");

        String s;

        public String getS() {
            return s;
        }

        A(String s) {
            this.s = s;
        }
    }

    @Test
    public void test() {
        System.out.println(A.a.getS());
        System.out.println(A.b.getS());
        long id = 4620884L;
        System.out.println(calcTimePartition(id));
        System.out.println(calculateOffset(id));
    }

    private String calcTimePartition(long offsetTime) {
        return String.format("%05d", MILLISECONDS.toMinutes(offsetTime));
    }

    private String calculateOffset(long timestamp) {
        long partition = timestamp / ONE_MINUTE_IN_MILLIS;
        String timePartition = DECIMAL_FORMATTER.format(partition);
        return timePartition;
    }
}
