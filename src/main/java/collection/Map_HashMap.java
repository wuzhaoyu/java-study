package collection;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/10/9
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class Map_HashMap {

    public static void main(String[] args) {
        HashMap hashMap = new HashMap();
        // put方法 执行流程
        // 1.判断key的hash是否存在table中 不存在直接插入
        // 2.存在相同hash相同的key,判断该节点是否为tree节点，是树节点直接插入；不为tree节点，从链表尾部插入，插入时判断链表结构是否达到8
        // 并且数组的容量小于64重新扩容
        // 3. JDK 1.7是头插发 1.8使用的是尾插发 解决死链问题
        hashMap.put("test","11");
        hashMap.get("key");

        // HashSet 是只是包装了HashMap

        // 锁住了链表或者红黑树的首节点，所以在不进行扩容时候是存在并发的
        new ConcurrentHashMap();
        //  双向链表(JDK1.6之前为循环链表，JDK1.7取消了循环)
        new LinkedHashMap<>();
    }
}
