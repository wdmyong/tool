package com.wdm.tool.json;

import java.util.List;

import javax.annotation.PostConstruct;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.wdm.tool.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
public class JsonUtilsTest {

    @Autowired
    private UserService userService;

    @PostConstruct
    private void init() {
        System.out.println("PostConstruct");
    }

    @Test
    public void testObject() {
        String json = "{\"id\":123, \"str\":\"123456789\"}";
        System.out.println(JsonUtils.jsonStr2Object(json, Model.class));
        System.out.println(userService.getStr());
    }

    @Test
    public void testList() {
        String json = "[{\"id\":1, \"str\":\"asdf\"}, {\"id\":2, \"str\":\"lkj\"}]";
        List<Model> list = JsonUtils.jsonStr2Collection(json, List.class, Model.class);
        list.forEach(System.out::println);
    }

    private static class Model {
        private int id;
        private String str;

        public Model() {
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        @Override
        public String toString() {
            return "Model{" +
                    "id=" + id +
                    ", str='" + str + '\'' +
                    '}';
        }
    }
}
