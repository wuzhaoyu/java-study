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
    private static final InheritableThreadLocal<Integer> INIT_INHERITABLETHREADLOCAL = new InheritableThreadLocal();

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
//        LOCAL.set(1);
//        LOCAL = null;
//        try {
//            Thread.sleep(1000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        System.gc();
//        LOCAL.remove();
        INIT_INHERITABLETHREADLOCAL.set(11);
        use();
    }

    /**
     *  set(a)
     *  1.ThreadLocal对象为key ,传入值a
     *  2.创建的LocalThreadMap对象赋值给threadLocals局部对象
     *
     *  get()
     *  1.Thread t = Thread.currentThread(); 作为 key
     *  2.获取到LocalThreadMap
     *  3.用ThreadLocal 对象为key 获取存储的值
     */
    public static void use(){
        Thread t1 = new Thread(()->{
            LOCAL.set(2);
            System.out.println(LOCAL.get());
        });
        Thread t2 = new Thread(()->{
            LOCAL.set(3);
            System.out.println(LOCAL.get());
            System.out.println(INIT_INHERITABLETHREADLOCAL.get()+"-------------");
        });
        t2.setName("wzy");
        t1.start();
        t2.start();
        System.out.println(LOCAL.get());
        LOCAL.remove();
    }
}
