package thread;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/12/17
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class ThreadLocalTest {

    private static  ThreadLocal<Integer> LOCAL = new ThreadLocal<>();
    private static final ThreadLocal<Integer> INIT_LOCAL = ThreadLocal.withInitial(()-> 2 );

    public static void main(String[] args) {
        // threadLocals
        // 当前线程中的 ThreadLocalMap threadLocals 变量
        // threadLocals.set(this,value)
        // 对ThreadLocal的弱引用
        // static class Entry extends WeakReference<ThreadLocal<?>> {
        //            /** The value associated with this ThreadLocal. */
        //            Object value;
        //
        //            Entry(ThreadLocal<?> k, Object v) {
        //                super(k);
        //                value = v;
        //            }
        //        }
        LOCAL.set(1);
        LOCAL = null;
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.gc();
        LOCAL.remove();
    }
}
