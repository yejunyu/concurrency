package threadPractice.pc;

import java.util.stream.Stream;

/**
 * @author: YeJunyu
 * @description:
 * @email: yyyejunyu@gmail.com
 * @date: 2020/8/28
 */
public class ProducerConsumer {

    static int n = 0;

    private Object lock = new Object();

    private boolean isProduced = false;

    public void producer(String name) throws InterruptedException {
        synchronized (lock) {
            if (isProduced) {
                lock.wait(10L);
            } else {
                System.out.println(name + " -> " + ++n);
                lock.notify();
                isProduced = true;
                Thread.sleep(10L);
            }
        }
    }


    public void consumer(String name) throws InterruptedException {
        synchronized (lock) {
            if (isProduced) {
                System.out.println(name + " -> " + n);
                lock.notify();
                isProduced = false;
                Thread.sleep(10L);
            } else {
                lock.wait(10L);
            }
        }
    }

    public static void main(String[] args) {
        ProducerConsumer producerConsumer = new ProducerConsumer();
        Stream.of("p1", "p2").forEach(
                i -> new Thread(() -> {
                    while (true) {
                        try {
                            producerConsumer.producer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start()
        );

        Stream.of("c1", "c2").forEach(
                i -> new Thread(() -> {
                    while (true) {
                        try {
                            producerConsumer.consumer(i);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start()
        );

    }
}
