package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import service.DemoService;

/**
 * author: YeJunyu
 * description:
 * email: yyyejunyu@gmail.com
 * date: 2020/8/16
 */
@RestController
@RequestMapping("/hello")
public class DemoController {

    @Autowired
    private DemoService service;

    public String hello(@RequestParam String name){
        service.hello(name);
        return name;
    }
}
