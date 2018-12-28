package com.wdm.tool.self.programcreek;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author wdmyong
 */
public class Apple {

    private String color;

    public Apple(String color) {
        this.color = color;
    }

    public boolean equals(Object obj) {
        if(obj==null) return false;
        if (!(obj instanceof Apple))
            return false;
        if (obj == this)
            return true;
        return this.color.equals(((Apple) obj).color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(color);
    }

    public static void main(String[] args) {
        Apple a1 = new Apple("green");
        Apple a2 = new Apple("red");
        Map<Apple, Integer> m = new HashMap<>();
        m.put(a1, 10);
        m.put(a2, 20);
        System.out.println(m.get(new Apple("green")));
    }
}
