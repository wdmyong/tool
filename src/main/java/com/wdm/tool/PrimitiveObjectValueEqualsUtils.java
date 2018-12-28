package com.wdm.tool;

import org.apache.commons.lang3.StringUtils;

/**
 * @author wdmyong
 */
public class PrimitiveObjectValueEqualsUtils {

    private PrimitiveObjectValueEqualsUtils() {
    }

    public static boolean equals(Object o1, Object o2) {
        return o1 == o2 || o1 != null && o2 != null && StringUtils.equals(String.valueOf(o1), String.valueOf(o2));
    }

    public static boolean equalsIgnoreCase(Object o1, Object o2) {
        return o1 == o2 || o1 != null && o2 != null && StringUtils.equalsIgnoreCase(String.valueOf(o1), String.valueOf(o2));
    }
}
