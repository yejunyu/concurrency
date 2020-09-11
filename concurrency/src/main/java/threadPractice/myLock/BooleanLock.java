package threadPractice.myLock;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * @author: YeJunyu
 * @description:
 * @email: yyyejunyu@gmail.com
 * @date: 2020/8/31
 */
public class BooleanLock implements IMyLock {

    private volatile boolean value;

    private volatile Collection<Thread> blockedThreadCollection = new ArrayList<>();

    private Thread currentThread;

    public BooleanLock() {
        this.value = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (value) {
            blockedThreadCollection.add(Thread.currentThread());
            this.wait();
        }
        blockedThreadCollection.remove(Thread.currentThread());
        this.value = true;
        currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeoutException {
        if (mills <= 0) {
            lock();
        }
        long remain = mills;
        long endTime = System.currentTimeMillis() + mills;
        while (value) {
            if (remain < 0) {
                throw new TimeoutException("time out");
            }
            blockedThreadCollection.add(Thread.currentThread());
            this.wait(mills);
            remain = endTime - System.currentTimeMillis();
        }
        blockedThreadCollection.remove(Thread.currentThread());
        this.value = true;
        currentThread = Thread.currentThread();
    }

    @Override
    public synchronized void unlock() {
        if (currentThread == Thread.currentThread()) {
            this.value = false;
            System.out.println(Thread.currentThread() + " release the lock monitor.");
            this.notifyAll();
        }
    }

    @Override
    public Collection<Thread> getBlockedThread() {
        return Collections.unmodifiableCollection(blockedThreadCollection);
    }

    @Override
    public int getBlockedSize() {
        return blockedThreadCollection.size();
    }
}
