package futurePractice;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * author: YeJunyu
 * description:
 * email: yyyejunyu@gmail.com
 * date: 2020/8/16
 */
public class FutureTest {
    /**
     * 有这样一个任务 T:
     * T 由 N 个子任务构成,每个子任务完成的时长不同
     * 若其中有一个子任务失败所有任务结束 fail fast
     */

    enum Result {
        /**
         * 任务状态
         */
        SUCCESS, FAIL, CANCEL
    }


    static List<Task> taskList = new ArrayList<>();

    public static void main(String[] args) throws IOException, InterruptedException {
        taskList.add(new Task("task1", 1, Result.SUCCESS));
        taskList.add(new Task("task2", 5, Result.FAIL));
        taskList.add(new Task("task3", 10, Result.SUCCESS));

        for (FutureTest.Task task : taskList) {
            // 提交任务
            CompletableFuture.supplyAsync(task::call)
                    // 执行任务回调
                    .thenAccept((result) -> callback(result, task));
        }

        TimeUnit.SECONDS.sleep(20);
    }

    public static class Task {
        String name;
        int timeSecond;
        Result result;

        volatile boolean cancelled = false;
        volatile boolean cancelling = false;
        volatile boolean done = false;

        public Task(String name, int timeSecond, Result result) {
            this.name = name;
            this.timeSecond = timeSecond * 1000;
            this.result = result;
        }

        public Result call() {
            int interval = 100;
            int total = 0;
            try {
                for (; ; ) {
                    Thread.sleep(interval);
                    total += interval;
                    if (total > timeSecond) {
                        break;
                    }
                    if (cancelled) {
                        return Result.CANCEL;
                    }
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            done = true;
            System.out.println(name + " end! " + result);
            return result;
        }

        public void cancel() {
            if (!cancelled && !done) {
                synchronized (this) {
                    if (cancelled) {
                        return;
                    }
                    cancelling = true;
                    System.out.println(name + " cancelling");
                    try {
                        TimeUnit.MILLISECONDS.sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println(name + " cancelled");
                cancelling = false;
                cancelled = true;
            }
        }
    }

    public static void callback(Result result, Task task) {
        if (Result.FAIL == result) {
            for (Task t : taskList) {
                if (t != task) {
                    if (!t.cancelling) {
                        t.cancel();
                    }
                }
            }
        }
    }
}
