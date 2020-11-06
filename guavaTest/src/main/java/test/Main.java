package test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.HashMap;
import java.util.Map;

/**
 * @author: YeJunyu
 * @description:
 * @email: yyyejunyu@gmail.com
 * @date: 2020/11/6
 */
public class Main {


    public static void main(String[] args) {
        Request request = new ARequest();
        Map<String, Object> m1 = new HashMap<>();
        m1.put("key",request);
        System.out.println(JSON.toJSONString(m1, SerializerFeature.WriteClassName));
        Map<String,Request> m2 = new HashMap<>();
        m2.put("key",request);
        System.out.println(JSON.toJSONString(m2, SerializerFeature.WriteClassName));
        MyMap m3 = new MyMap();
        m3.put("key",request);
        System.out.println(JSON.toJSONString(m3, SerializerFeature.WriteClassName));
    }

    public static class MyMap extends HashMap {

    }
}
