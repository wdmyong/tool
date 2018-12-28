package com.wdm.tool.service;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private static String str = "123";
    @PostConstruct
    private void init() {
        System.out.println("PostConstruct" + getClass());
        str = "456";
    }

    public String getStr() {
        return str;
    }
}
