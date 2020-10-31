package collection;

import java.util.*;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/10/9
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class List_ArrayList {

    public static void main(String[] args) {
        List_ArrayList list_arrayList = new List_ArrayList();
        ArrayList list = new ArrayList();
        list.add(12);
        System.out.println();
        // 新增在初始添加元素的时候 - 》 扩容为10
        // 新增在初扩容 位运算 >> 右移一位 （int newCapacity = oldCapacity + (oldCapacity >> 1)） 右移除以2

        // 通过运行结果，我们可以看出向 ArrayList 添加大量元素之前最好先使用ensureCapacity 方法，以减少增量重新分配的次数。
        list_arrayList.NoEnsureCapacityTest();
        list_arrayList.YesEnsureCapacityTest();
        // 内部实现 iterator()方法
        Iterator iterator = list.iterator();
        while(iterator.hasNext()){
            System.out.println( iterator.next());
        }
        //
        Vector vector = new Vector();

    }

    /**
     * 确认列表容量
     */
    public void NoEnsureCapacityTest() {
        ArrayList<Object> list = new ArrayList<Object>();
        final int N = 10000000;
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法前：" + (endTime - startTime));
    }

    /**
     * 确认列表容量
     */
    public void YesEnsureCapacityTest() {
        ArrayList<Object> list = new ArrayList<Object>();
        final int N = 10000000;
        list = new ArrayList<Object>();
        long startTime1 = System.currentTimeMillis();
        list.ensureCapacity(N);
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法后：" + (endTime1 - startTime1));
    }
}
