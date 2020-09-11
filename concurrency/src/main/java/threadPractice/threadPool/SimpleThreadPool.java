package threadPractice.threadPool;

import java.util.LinkedList;

/**
 * @author: YeJunyu
 * @description:
 * @email: yyyejunyu@gmail.com
 * @date: 2020/9/11
 */
public class SimpleThreadPool {

    private int size;
    private final static int DEFAULT_SIZE = 10;

    private final static LinkedList<Runnable> TASK_QUEUE = new LinkedList<>();

    public SimpleThreadPool() {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.size = size;
    }

    private static class WorkerTask extends Thread {

    }
}
