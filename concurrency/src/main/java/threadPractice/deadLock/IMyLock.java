package threadPractice.deadLock;

import java.util.Collection;

/**
 * @author: YeJunyu
 * @description:
 * @email: yyyejunyu@gmail.com
 * @date: 2020/8/31
 */
public interface IMyLock {

    class TimeoutException extends Exception {
        public TimeoutException(String msg) {
            super(msg);
        }
    }

    void lock() throws InterruptedException;

    void lock(long mills) throws InterruptedException, TimeoutException;

    void unlock();

    Collection<Thread> getBlockedThread();

    int getBlockedSize();
}
