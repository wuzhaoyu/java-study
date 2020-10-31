package collection;

import java.util.HashMap;
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
        System.out.println("LinkedList（直接输出的）: " + linkedList);

        System.out.println("getFirst()获得第一个元素: " + linkedList.getFirst()); // 返回此列表的第一个元素
        System.out.println("getLast()获得第一个元素: " + linkedList.getLast()); // 返回此列表的第一个元素

    }
}
