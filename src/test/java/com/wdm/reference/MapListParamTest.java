package com.wdm.reference;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.LongStream;

import org.junit.Test;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.wdm.tool.pb.User;

/**
 * Created by wdmyong on 2018/10/29.
 */
public class MapListParamTest {

    private static Map<Long, List<User>> MAP = Maps.newHashMap();

    @Test
    public void test() {
        LongStream.rangeClosed(1, 10).forEach(id -> {
            put(id % 3, Collections.singletonList(new User(id, String.valueOf(id), (int) id % 3)));
        });
        deal(MAP);
    }

    private void put(long id, List<User> users) {
        if (MAP == null) {
            System.out.println("null");
        }
        System.out.println(MAP.size());
        MAP.computeIfAbsent(id, k -> Lists.newArrayList()).addAll(users);
    }

    private void deal(Map<Long, List<User>> id2User) {
        int count = id2User.values().stream().map(Collection::size).mapToInt(Integer::valueOf).sum();
        System.out.println("size:\t" + id2User.size());
        System.out.println("count:\t" + count);
    }
}
