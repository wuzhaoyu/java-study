package thread.aqs;

import sun.misc.Unsafe;

import java.lang.reflect.Field;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.LockSupport;

/**
 * 类功能说明: 构建AQS
 * 类修改者	创建日期2020/3/31
 * 修改说明
 * 1. 标识当前lock状态的标识
 * 2. 定义当前变量
 * 3. 锁队列
 *
 * @author wzy
 * @version V1.0
 **/
public class WrLock {

    /**
     * 锁状态
     * 由于CAS操作会通过对象实例中的偏移量直接进行赋值，
     * 因此，它不支持static字段(Unsafe. objectFieldOffset()不支持静态变量)。
     */
    private volatile int state = 0;

    /**
     * 获取锁的当前线程
     */
    private Thread threadHolder;

    private ConcurrentLinkedQueue<Thread> waiters = new ConcurrentLinkedQueue();

    public boolean acquire() { //cas 比较与交换
        Thread currentThread = Thread.currentThread();
        int c = getState();
        //当前的lock还没有被获取
        if (c == 0) {
            if (waiters.size() == 0 && compareAndSwap(getState(), 1)) {
                setThreadHolder(currentThread);
                return true;
            }
        }
        return false;
    }

    public void lock() {
        if (acquire()) {
            return;
        }
        Thread currentThread = Thread.currentThread();
        waiters.add(currentThread);
        //自旋
        for (; ; ) {
            // 让出cpu使用权,不能保证自身不在使用cpu 浪费资源
            //Thread.yield();
            if (currentThread == waiters.peek() && acquire()) {
                waiters.poll();//把队列的头部的线程移除，因为已经无唤醒
                return;
            }
            // 阻塞当前线程，保持对线程的引用 释放了对cpu的使用权
            LockSupport.park(currentThread);

        }

    }

    public void unlock() {
        if (Thread.currentThread() != threadHolder) {
            throw new RuntimeException("Current Thread not threadHolder");
        }
        int c = getState();
        if (compareAndSwap(c, 0)) {
            setThreadHolder(null);
            Thread first = waiters.peek();
            //点对点唤醒阻塞线程
            if (first != null) {
                LockSupport.unpark(first);
            }
        }


    }

    /**
     * 原子的比较与交换
     *
     * @return
     */
    public final boolean compareAndSwap(int expect, int update) {
        return unsafe.compareAndSwapInt(this, fieldOffset, expect, update);
    }

    private final static Unsafe unsafe = UnSafeInstance.reflectGetUnSafe();

    /**
     * 对象实例变量在内存中偏移量
     */
    private static long fieldOffset;

    static {
        try {
            fieldOffset = unsafe.objectFieldOffset(WrLock.class.getDeclaredField("state"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public Thread getThreadHolder() {
        return threadHolder;
    }

    public void setThreadHolder(Thread threadHolder) {
        this.threadHolder = threadHolder;
    }


}
