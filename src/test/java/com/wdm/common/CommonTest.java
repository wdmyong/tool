package com.wdm.common;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.wdm.tool.PrimitiveObjectValueEqualsUtils;

import static java.time.format.DateTimeFormatter.BASIC_ISO_DATE;

/**
 * @author wdmyong
 */
public class CommonTest {

    @Test
    public void testLowerCase() {

        System.out.println("90b3e091-5b73-4091-9a15-3a8dfe299a60".length());
        System.out.println("ABC".toLowerCase());
        System.out.println("BDF".toLowerCase() + "_test");
        LocalDate birthDate;
        try {
            birthDate = LocalDate.parse("20001128", BASIC_ISO_DATE);
        } catch (Exception e) {
            throw new IllegalArgumentException("idCard parse birthday error");
        }
        Period period = Period.between(birthDate, LocalDate.now());
        System.out.println(period.getYears());

        List<Integer> list = IntStream.range(1, 20).boxed().collect(Collectors.toList());
        list.forEach(System.out::println);

        Map<String, Object> map = Maps.newHashMap();
        map.put("a", 1);
        map.put("b", 2);
        Object o = map.get("a");
        int i = 1;
        Object a = i;
        System.out.println(Objects.equals(a, o));
    }

    @Test
    public void testPrimitiveObjectEquals() {
        HashMap<String, Object> map = Maps.newHashMap();
        map.put("a", 1);
        map.put("b", "True");
        map.put("c", "true");
        System.out.println(PrimitiveObjectValueEqualsUtils.equals(map.get("a"), map.get("c")));
        System.out.println(PrimitiveObjectValueEqualsUtils.equalsIgnoreCase(map.get("a"), map.get("b")));
    }
}
