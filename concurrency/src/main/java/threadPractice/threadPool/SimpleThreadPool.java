package threadPractice.threadPool;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.IntStream;

/**
 * @author: YeJunyu
 * @description: 线程池
 * @email: yyyejunyu@gmail.com
 * @date: 2020/9/11
 */
public class SimpleThreadPool {

    private int size;

    private final static int DEFAULT_SIZE = 10;

    private final static LinkedList<Runnable> tasks = new LinkedList<>();

    private final static List<Worker> WORKERS = new ArrayList<>(10);

    public SimpleThreadPool() {
        this(DEFAULT_SIZE);
    }

    public SimpleThreadPool(int size) {
        this.size = size;
        init();
    }

    private void init() {
        for (int i = 0; i < size; i++) {
            new Worker("worker " + i).start();
        }
    }

    /**
     * 提交任务
     *
     * @param r
     */
    public void submit(Runnable r) {
        synchronized (tasks) {
            tasks.add(r);
            tasks.notifyAll();
        }
    }

    public enum WorkState {
        RUNNABLE, RUNNING, BLOCKED, HOME
    }

    public static class Worker extends Thread {

        private WorkState state = WorkState.RUNNABLE;

        public Worker(String name) {
            super(name);
        }

        public WorkState getWorkState() {
            return this.state;
        }

        public void goHome() {
            this.state = WorkState.HOME;
        }

        @Override
        public void run() {
            System.out.println(this.getName() + this.state.name());
            Runnable task;
            while (getWorkState() != WorkState.HOME) {
                synchronized (tasks) {
                    while (tasks.isEmpty()) {
                        try {
                            this.state = WorkState.BLOCKED;
                            System.out.println(this.getName() + this.state.name());
                            tasks.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    task = tasks.removeFirst();
                }
                this.state = WorkState.RUNNING;
                System.out.println(this.getName() + this.state.name());
                task.run();
                this.state = WorkState.RUNNABLE;
                System.out.println(this.getName() + this.state.name());
            }
        }

    }

    public static void main(String[] args) {
        SimpleThreadPool simpleThreadPool = new SimpleThreadPool(5);
        IntStream.range(0, 15).forEach(i -> simpleThreadPool.submit(() -> {
            // 具体做业务
            try {
                System.out.println(Thread.currentThread().getName() + " 干活中...");
                Thread.sleep(10_000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }));
    }
}
