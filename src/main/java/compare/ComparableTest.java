package compare;

import java.util.Comparator;

/**
 * 类功能说明:
 * 类修改者	创建日期2021/1/24
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class ComparableTest {

    public static void main(String[] args) {
        Comparator<Integer> comparator = new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return 0;
            }
        };
        Comparable<Integer> comparable = new Comparable<Integer>() {
            @Override
            public int compareTo(Integer o) {
                return 0;
            }
        };
    }
}
