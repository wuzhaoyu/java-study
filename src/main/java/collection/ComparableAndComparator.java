package collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 * 类功能说明:
 * 类修改者	创建日期2020/10/11
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class ComparableAndComparator {

    public static void main(String[] args) {

        Comparable comparable = new Comparable() {
            @Override
            public int compareTo(Object o) {
                return 0;
            }
        };
        Comparator comparator = new Comparator() {
            @Override
            public int compare(Object o1, Object o2) {
                return 0;
            }
        };
        Collections.sort(new ArrayList<>(),comparator);
    }
}
