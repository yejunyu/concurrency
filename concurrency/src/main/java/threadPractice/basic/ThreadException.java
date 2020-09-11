package threadPractice.basic;

/**
 * @author: YeJunyu
 * @description: 线程异常捕获
 * @email: yyyejunyu@gmail.com
 * @date: 2020/9/3
 */
public class ThreadException {

    static {
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            graceShutdown();
        }));
    }

    public static void main(String[] args) {
        int a = 0;
        Thread t = new Thread(() -> {
            int a1 = 1 / a;
        });
        /**
         * 线程内打印异常
         */
        t.setUncaughtExceptionHandler((thread, e) -> {
            System.out.println(e);
            System.out.println(thread);
        });
        t.start();
    }

    /**
     * 系统关闭时执行钩子函数
     * 但是 kill -9 这种强杀不会执行
     */
    private static void graceShutdown() {
        System.out.println("钩子函数");
    }
}
