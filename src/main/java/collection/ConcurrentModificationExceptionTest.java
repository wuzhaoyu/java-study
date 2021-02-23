package collection;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * 类功能说明:
 * 类修改者	创建日期2021/1/29
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class ConcurrentModificationExceptionTest {

    private static HashMap<Integer, String> map = new HashMap<Integer, String>();;
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            map.put(i, "value" + i);
        }
        Iterator<Map.Entry<Integer, String>> iterator = map.entrySet().iterator();
        while(iterator.hasNext()){
            Map.Entry<Integer, String> next = iterator.next();
            Integer key  =  next.getKey();
            if (key % 2 == 0) {
                System.out.println("To delete key " + key);
                iterator.remove();
                System.out.println("The key " + +key + " was deleted");
            }
        }
       /* for (Map.Entry<Integer, String> entry : map.entrySet()) {
            Integer key = entry.getKey();
            if (key % 2 == 0) {
                System.out.println("To delete key " + key);
                map.remove(key);
                System.out.println("The key " + +key + " was deleted");
            }
        }*/
    }
}
