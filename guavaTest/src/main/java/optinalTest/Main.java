package optinalTest;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

/**
 * author: YeJunyu
 * description:
 * email: yyyejunyu@gmail.com
 * date: 2020/8/17
 */
public class Main {
    public static void main(String[] args) {
        A a = null;
        Map<String, String> stringStringMap = Optional.ofNullable(a.map).orElse(new HashMap<>());
        stringStringMap.put("1","1");
        System.out.println(stringStringMap);
    }

    static class A {
        String name;
        Map<String, String> map;

        public A(String name) {
            this.name = name;
        }
    }
}
