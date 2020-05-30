package thread;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/3/29
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 **/
public class VolitailSimple {

    private static volatile boolean initFlag = true;

    private static void refresh(){
        initFlag = false;
    }

    private static void loadData(){
        while(initFlag){

        }
        System.out.println("变量从主内存中加载");
    }

    public static void main(String[] args) {

       /* Thread thread1 = new Thread(() -> {
            loadData();
        }, "thread1");
        thread1.start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Thread thread2 = new Thread(()->{
            refresh();
        },"thread2");
        thread2.start();*/

    }
}
