package com.wdm.tool.self.programcreek;

/**
 * @author wdmyong
 */
public class PassByValueTest {

    public static void main(String[] args) {
        testStr();
        testObj();
    }

    private static void testStr() {
        String x = new String("ab");
        change(x);
        System.out.println(x);
    }

    private static void change(String x) {
        x = "cd";
    }

    private static void testObj() {
        Obj obj = new Obj(1, "1");
        change(obj);
        System.out.println(obj);
    }

    private static void change(Obj obj) {
        obj = new Obj(2, "2");
    }

    private static class Obj {
        private int id;
        private String name;

        public Obj(int id, String name) {
            this.id = id;
            this.name = name;
        }

        @Override
        public String toString() {
            return "Obj{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }
}
