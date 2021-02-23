package juc;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 类功能说明:  自定义线程池
 * 类修改者	创建日期2021/2/2
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class CustomTreadPool<T> {

    public static void main(String[] args) {
        CustomTreadPool<Runnable> workCustomTreadPool = new CustomTreadPool<>(2, 1000, TimeUnit.MILLISECONDS, 10, (queue, task) -> {
            // 死等
            // queue.put(task);
            // 等待超时
           queue.offer(task,500,TimeUnit.MILLISECONDS);
            // 调用者 抛出异常
            // 调用者 放弃调用
            // 调用者 自己调用

        });
        for (int i = 0; i < 15; i++) {
            int j = i;
            workCustomTreadPool.execute(new Thread(() -> {
                System.out.println("创建任务===>" + j);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }));

        }

    }

    // 阻塞队列
    private BlockQueue<Runnable> taskQueue;

    // 生产者线程的集合
    HashSet<Work> works = new HashSet<>();

    // 核心线程数
    private int coreSize;

    // 从线程中获取任务的超时时间
    private long timeout;

    private TimeUnit timeUnit;

    // 任务的操作策略
    private RejectPolicy rejectPolicy;

    public CustomTreadPool(int coreSize, long timeout, TimeUnit timeUnit, int queueCapacity, RejectPolicy rejectPolicy) {
        this.coreSize = coreSize;
        this.timeout = timeout;
        this.timeUnit = timeUnit;
        this.taskQueue = new BlockQueue<>(queueCapacity);
        this.rejectPolicy = rejectPolicy;
    }

    /**
     * 提供执行任务
     *
     * @param task
     */
    public void execute(Runnable task) {
        // （生产者）线程集合总数 小于 线程池的核心线程数 创建线程去完成 使用work去完成
        // 线程池中线程不足 则放入队列中完成
        if (coreSize > works.size()) {
            Work work = new Work(task);
            works.add(work);
            System.out.println("新增任务到work中===>" + task);
            work.start();
        } else {
            //当前任务数量大于核心线程数 需要提供策略;
            // 策略的制定应该下方给用户去完成 提高程序的扩展性
//            taskQueue.put(task);
            taskQueue.tryPut(task, rejectPolicy);


        }
    }

    /**
     * 具体任务的执行者
     * 对任务对象的封装 也是个线程对象
     */
    class Work extends Thread {

        private Runnable task;

        public Work(Runnable runnable) {
            this.task = runnable;
        }

        @Override
        public void run() {
            // 启动线程后
            // 线程执行task任务，task执行完成，再去执行队列的任务
            // 执行队列中任务

            // 线程在空转等待任务
//                while(task!=null || (task = taskQueue.take())!=null){
            // 线程等待超时时间
            while (task != null || (task = taskQueue.poll(timeout, timeUnit)) != null) {
                try {
                    task.run();
                    System.out.println("正在执行任务===>" + task);
                } catch (Exception e) {

                } finally {
                    // 任务执行完成后 去除引用
                    task = null;
                }
            }
            // works为共享变量存在线程不安全的情况
            // 当任务执行完成 从生产者线程组中移除
            synchronized (works) {
                works.remove(this);
                System.out.println("移除任务===>" + task);
            }
        }
    }


    /**
     * 内部接口 提供拒绝测试
     *
     * @param <T>
     */
    @FunctionalInterface
    interface RejectPolicy<T> {
        void reject(BlockQueue<T> queue, T t);
    }


    // 阻塞队列
    static class BlockQueue<T> {

        // 阻塞队列
        private Queue<T> queue = new ArrayDeque<>( this.capacity);

        // 锁
        ReentrantLock lock = new ReentrantLock();

        // 消费者 - 为空的等待条件
        private Condition emptyWaitSet = lock.newCondition();

        // 生产者 - 队列满的等待条件
        private Condition fullWaitSet = lock.newCondition();

        private int capacity;

        public BlockQueue(int capacity) {
            this.capacity = capacity;
        }

        // 带超时的阻塞获取
        public T poll(long time, TimeUnit unit) {
            lock.lock();
            T poll = null;
            try {
                // 统一转化为纳秒
                long nanos = unit.toNanos(time);
                while (queue.isEmpty()) {
                    // 存在 如果上次时间没有等足，下次循环还是会重新计数
                    // awaitNanos 返回剩余时间 等待时间 - 经历时间
                    nanos = emptyWaitSet.awaitNanos(nanos);
                    // 超时后 退出
                    if (nanos < 0) {
                        return null;
                    }

                }
                poll = queue.poll();
                fullWaitSet.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return poll;


        }

        // 线程池 获取 队列任务
        public T take() {
            lock.lock();
            T poll = null;
            try {
                while (queue.size() == 0) {
                    emptyWaitSet.await();
                }
                poll = queue.poll();
                fullWaitSet.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return poll;
        }

        // 线程池 添加 队列任务
        public void put(T task) {
            lock.lock();
            try {
                // 队列满后会阻塞
                while (queue.size() == capacity) {
                    System.out.println("阻塞中的任务到队列===>" + task);
                    fullWaitSet.await();
                }
                System.out.println("新增任务到队列中===>" + task);
                queue.add(task);
                emptyWaitSet.signal();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }

        }

        // 线程池获取队列任务
        public void tryPut(T task, RejectPolicy<T> rejectPolicy) {
            lock.lock();
            try {
                // 队列已满 执行策略
                if (queue.size() == capacity) {
                    rejectPolicy.reject(this, task);
                } else {
                    // 有空闲
                    System.out.println("新增任务到队列中===>" + task);
                    queue.add(task);
                    emptyWaitSet.signal();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }

        // 等待超时的阻塞
        public boolean offer(T task, long timeout, TimeUnit timeUnit) {
            lock.lock();
            try {
                long nanos = timeUnit.toNanos(timeout);
                // 队列满后会阻塞
                while (queue.size() == capacity) {
                    System.out.println("阻塞中的任务到队列===>" + task);
                    nanos = fullWaitSet.awaitNanos(nanos);
                    if (nanos <= 0) {
                        return false;
                    }
                }
                System.out.println("新增任务到队列中===>" + task);
                queue.add(task);
                emptyWaitSet.signal();
                return true;
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
            return false;
        }

        public int size() {
            lock.lock();
            try {
                return queue.size();
            } finally {
                lock.unlock();
            }
        }
    }


}
