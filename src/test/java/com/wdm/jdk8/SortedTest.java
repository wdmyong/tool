package com.wdm.jdk8;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

/**
 * @author wdmyong
 */
public class SortedTest {

    @Test
    public void testSorted() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, 10L));
        list.add(new User(2, 20L));
        list.add(new User(5, 15L));
        list.stream().sorted((u1, u2) -> u1.getCoin() > u2.getCoin() ? -1 : 1).forEach(System.out::println);
    }

    private static class User {
        private long id;
        private long coin;

        public User(long id, long coin) {
            this.id = id;
            this.coin = coin;
        }

        public long getCoin() {
            return coin;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", coin=" + coin +
                    '}';
        }
    }
}
