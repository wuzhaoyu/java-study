package thread.aqs;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/3/31
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public class Test {

    private static int a = 5;

    public static void main(String[] args) {
        WrLock lock = new WrLock();
        for(int i=0;i<30;i++){
            Thread thread2 = new Thread(()->{
                refresh();
            },"T"+ i);
            thread2.start();
        }
//        lock.lock();
    }
    public static void refresh(){
        System.out.println(Thread.currentThread().getName() + "--" + a--);
    }
}
