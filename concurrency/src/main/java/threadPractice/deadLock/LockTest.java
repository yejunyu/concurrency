package threadPractice.deadLock;

import java.util.stream.Stream;

/**
 * @author: YeJunyu
 * @description:
 * @email: yyyejunyu@gmail.com
 * @date: 2020/8/31
 */
public class LockTest {

    public static void main(String[] args) {
        final BooleanLock booleanLock = new BooleanLock();
        Stream.of("t1", "t2", "t3", "t4").forEach(
                name -> new Thread(() -> {
                    try {
                        booleanLock.lock();
                        System.out.println(Thread.currentThread().getName() + " have the locked");
                        System.out.println(booleanLock.getBlockedThread());
                        work();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        booleanLock.unlock();
                    }
                }, name).start()
        );
    }

    private static void work() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is working...");
        Thread.sleep(10_000L);
    }
}
