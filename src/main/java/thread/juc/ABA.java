package thread.juc;

import java.util.concurrent.atomic.AtomicMarkableReference;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/10/11
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class ABA {

    static AtomicReference<String> ref = new AtomicReference<>("A");
    static AtomicStampedReference<String> stampedReference = new AtomicStampedReference<>("A",0);
    static AtomicMarkableReference<String> markableReference = new AtomicMarkableReference<>("A",true);


    public static void main(String[] args) {

       /* other();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(String.format("修改为A -> C %b", ref.compareAndSet("A","C"))); other();*/

       otherMark();
       try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


        String reference = markableReference.getReference();
        System.out.println(String.format("修改为A -> C %b", markableReference.compareAndSet(reference,"C",true,false)));
    }

    /**
     *  主线程无法感知
     */
    private static void other(){
        new Thread(()->{
            System.out.println(String.format("修改为A -> B %b", ref.compareAndSet("A","B")));
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            System.out.println(String.format("修改为B -> A %b", ref.compareAndSet("B","C")));
        }).start();
    }

    /**
     *  标记
     */
    private static void otherMark(){
        new Thread(()->{
            System.out.println(String.format("修改为A -> B %b", markableReference.compareAndSet(markableReference.getReference(),"B",true,false)));
        }).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(()->{
            System.out.println(String.format("修改为B -> A %b", markableReference.compareAndSet("B",markableReference.getReference(),false,false)));
        }).start();
    }
}
