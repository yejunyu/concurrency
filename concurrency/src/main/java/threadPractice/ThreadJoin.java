package threadPractice;

import java.util.stream.IntStream;

/**
 * @author: YeJunyu
 * @description:
 * @email: yyyejunyu@gmail.com
 * @date: 2020/8/19
 */
public class ThreadJoin {

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> IntStream.range(1, 1000).forEach(
                i -> System.out.println(Thread.currentThread().getName() + " -> " + i)
        ));
        t1.start();
        /**
         * join一定要在start之后
         * join针对的是父线程,而不是多个线程之间
         */
        t1.join();
        IntStream.range(1, 1000).forEach(
                i -> System.out.println(Thread.currentThread().getName() + " -> " + i)
        );
    }
}
