package threadPractice.pc;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * @author: YeJunyu
 * @description: 有1000个机器需要采集数据, 每台机器数据不同(采集时间不同)
 * 只有5个线程来采集数据,一台机器采集完再去采集另一台
 * @email: yyyejunyu@gmail.com
 * @date: 2020/8/28
 */
public class CapterService {

    private final static LinkedList<Control> controls = new LinkedList<>();
    private final static int MAX_CAPTURE = 5;

    public static void main(String[] args) {
        List<Thread> works = new ArrayList<>();
        Stream.of("m1", "m2", "m3", "m4", "m5", "m6", "m7", "m8", "m9", "m10")
                .map(CapterService::createCaptureThread)
                .forEach(t -> {
                    t.start();
                    works.add(t);
                });
        works.forEach(
                t -> {
                    try {
                        t.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
        );
        Optional.of("the work finish ...").ifPresent(System.out::println);
    }

    private static Thread createCaptureThread(String name) {
        return new Thread(() -> {
            Optional.of("the worker [" + Thread.currentThread().getName() + "] begin capture data.").ifPresent(System.out::println);
            synchronized (controls) {
                while (controls.size() > MAX_CAPTURE) {
                    try {
                        controls.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                controls.addLast(new Control());
            }
            Optional.of("the work [" + Thread.currentThread().getName() + "] is working...").ifPresent(System.out::println);
            try {
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (controls) {
                Optional.of("the worker [" + Thread.currentThread().getName() + "] end capture data.").ifPresent(System.out::println);
                controls.removeFirst();
                controls.notifyAll();
            }
        }, name);
    }

    private static class Control {
    }
}
