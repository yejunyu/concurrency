package test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: YeJunyu
 * @description:
 * @email: yyyejunyu@gmail.com
 * @date: 2020/11/6
 */
public class Main {


    public static void main(String[] args) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();

        Request request = new ARequest();
//        System.out.println("no map: " + mapper.writeValueAsString(request));
//        Map<String, Object> m1 = new HashMap<>();
//        m1.put("key", request);
//        System.out.println(m1);
//        System.out.println(mapper.writeValueAsString(m1));
//
//        Map<String, Request> m2 = new HashMap<>();
//        m2.put("key", request);
//        System.out.println(m2);
//        System.out.println(mapper.writeValueAsString(m2));

        MyMap m3 = new MyMap();
        m3.put("key", request);
        System.out.println(m3);
        System.out.println(mapper.writeValueAsString(m3));
    }

    public static class MyMap extends HashMap<String, Request> {

    }
}
