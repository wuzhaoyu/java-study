package test;

import java.util.HashMap;
import java.util.Map;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/11/9
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class OperatorDemo {

    public static void main(String[] args) {
        // 0011 1001 = > 1011 = 11 二进制或
        // 0011 1001 = > 0001 = 1 二进制或
        System.out.println(3|9);
        System.out.println(3&9);

        Map<String,String> hash = new HashMap<>();


        hash.put("1","2");
        String oldValue = hash.put("1", "3");
        hash.get("key");
        System.out.println(oldValue);
    }
}
