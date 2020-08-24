package threadPractice;

import java.util.concurrent.TimeUnit;

/**
 * author: YeJunyu
 * description: 通过一个中间的线程service控制线程的执行和终止
 * email: yyyejunyu@gmail.com
 * date: 2020/8/16
 */
public class ThreadService {

    private Thread excuteThread;

    private boolean finished = false;

    public void excute(Runnable runnable) {
        excuteThread = new Thread(() -> {
            Thread runner = new Thread(runnable);
            // 设置成守护线程,那么父线程挂了,子线程也跟着结束
            runner.setDaemon(true);
            runner.start();
            // 防止runnable运行过快,还没变成守护线程就执行完了
            try {
                runner.join();
                finished = true;
                System.out.println("任务执行结束");
            } catch (InterruptedException e) {
                //e.printStackTrace();
                System.out.println("excuteThread interrupt 打断runner join");
            }
        });
        excuteThread.start();
    }

    public void shutdown(long mills) {
        long currentTime = System.currentTimeMillis();
        while (!finished) {
            if (System.currentTimeMillis() - currentTime > mills) {
                System.out.println("任务超时,需要结束");
                excuteThread.interrupt();
                break;
            }
            try {
                TimeUnit.MILLISECONDS.sleep(1L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        // 测试
        ThreadService threadService = new ThreadService();
        long start = System.currentTimeMillis();
//        threadService.excute(() -> {
//            // 长时间任务
//            while (true) {
//
//            }
//        });
        threadService.excute(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        threadService.shutdown(5000L);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
