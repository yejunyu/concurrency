package org.example.service;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

/**
 * author: YeJunyu
 * description:
 * email: yyyejunyu@gmail.com
 * date: 2020/8/16
 */
@Service
public class DemoService {

    public String hello(String name) {
        System.out.println("hello " + name);
        return name;
    }
}
