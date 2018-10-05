package com.wdm.reference;


import java.util.ArrayList;
import java.util.List;

import org.apache.commons.collections.ListUtils;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

/**
 * Created by wdmyong on 2018/10/05.
 */
public class ListTest {

    @Test
    public void testList() {
        List<User> list = new ArrayList<>();
        User wdm = new User("wdm", 18, true);
        User bai = new User("bai", 18, false);
        list.add(wdm);
        list.add(bai);
        list.add(new User("wc", 19, true));
        list.add(new User("bv", 19, false));
        List<User> users = new ArrayList<>();
        users.addAll(list);
        printList(list);
        printList(users);
        assertTrue(isSameList(list, users));
        wdm.setAge(20);
        printList(list);
        printList(users);
        assertTrue(isSameList(list, users));
        list.get(0).setName("newName");
        printList(list);
        printList(users);
        assertTrue(isSameList(list, users));
        users.get(0).setAge(100);
        printList(list);
        printList(users);
        assertTrue(isSameList(list, users));
    }

    private static void printList(List<?> list) {
        list.forEach(System.out::println);
        System.out.println("------------");
    }

    private static boolean isSameList(List<?> list1, List<?> list2) {
        return ListUtils.isEqualList(list1, list2);
    }

    private static class User {
        private String name;
        private int age;
        private boolean isMan;

        public User(String name, int age, boolean isMan) {
            this.name = name;
            this.age = age;
            this.isMan = isMan;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public boolean isMan() {
            return isMan;
        }

        public void setMan(boolean man) {
            isMan = man;
        }

        @Override
        public String toString() {
            return "User{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    ", isMan=" + isMan +
                    '}';
        }
    }
}
