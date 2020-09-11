package threadPractice.myLock;

/**
 * @author: YeJunyu
 * @description:
 * @email: yyyejunyu@gmail.com
 * @date: 2020/8/31
 */
public class LockTest {

    public static void main(String[] args) {
        final BooleanLock booleanLock = new BooleanLock();
        for (int i = 0; i < 4; i++) {
            String name = "t" + i;
            new Thread(() -> {
                try {
                    booleanLock.lock(10L);
                    work();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IMyLock.TimeoutException e) {
                    e.printStackTrace();
                } finally {
                    booleanLock.unlock();
                }
            }, name).start();
        }
    }

    private static void work() throws InterruptedException {
        System.out.println(Thread.currentThread().getName() + " is working...");
        Thread.sleep(10_000L);
    }
}
