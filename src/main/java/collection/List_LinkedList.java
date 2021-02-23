package collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/10/9
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class List_LinkedList {

    public static void main(String[] args) {
        LinkedList<Object> linkedList = new LinkedList<>();
        linkedList.addFirst(1);
        linkedList.add(2);
        linkedList.add(3);
        linkedList.addLast(4);
        // 输出有序
        System.out.println("LinkedList（直接输出的）: " + linkedList);
        // 返回此列表的第一个元素
        System.out.println("getFirst()获得第一个元素: " + linkedList.getFirst());
        // 返回此列表的第一个元素
        System.out.println("getLast()获得第一个元素: " + linkedList.getLast());
        // 遍历
        Iterator<Object> iterator = linkedList.iterator();
        while(iterator.hasNext()){
            System.out.println(iterator.next());
        }
        // 向队列的尾部插数据
        linkedList.offer(5);
        System.out.println(linkedList);
        // 向队列的尾部插数据
        linkedList.offerLast(6);
        System.out.println("输出链表第一个数"+ linkedList.peek());
        System.out.println("输出链表第一个数"+ linkedList.peekFirst());
        System.out.println("输出链表最后一个数"+ linkedList.peekLast());
        System.out.println("输出链表第一个数后，移除一个节点"+ linkedList.poll());
        System.out.println(linkedList);
        System.out.println("输出链表最后数后，移除一个节点"+ linkedList.pollLast());
        System.out.println(linkedList);
        System.out.println(linkedList);

        System.out.println(linkedList.remove(null));
        System.out.println(linkedList.removeFirstOccurrence(null));
        linkedList.remove(1);


    }
}
