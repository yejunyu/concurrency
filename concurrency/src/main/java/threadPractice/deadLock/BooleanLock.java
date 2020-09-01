package threadPractice.deadLock;

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

    private boolean value;

    private Collection<Thread> blockedThreadCollection = new ArrayList<>();

    public BooleanLock() {
        this.value = false;
    }

    @Override
    public synchronized void lock() throws InterruptedException {
        while (value) {
            this.wait();
            System.out.println(Thread.currentThread().getName() + " have the locked~~~");
            blockedThreadCollection.add(Thread.currentThread());
        }
        blockedThreadCollection.remove(Thread.currentThread());
        this.value = true;
    }

    @Override
    public synchronized void lock(long mills) throws InterruptedException, TimeoutException {

    }

    @Override
    public synchronized void unlock() {
        this.value = false;
        System.out.println(Thread.currentThread() + " release the lock monitor.");
        this.notifyAll();
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
