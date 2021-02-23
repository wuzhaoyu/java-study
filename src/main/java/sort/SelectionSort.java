package sort;

import java.util.Arrays;

/**
 * 类功能说明:
 * 类修改者	创建日期2021/1/29
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class SelectionSort {

    /**
     * 查询数组中最大值的下标
     *
     * @param arr
     * @param n
     * @return
     */
    public static int findMaxPos(int[] arr, int n) {
        int max = arr[0];
        int pos = 0;
        for (int i = 0; i < n ; i++) {
            if (arr[i] > max) {
                max = arr[i];
                pos = i;
            }
        }
        return pos;
    }

    /**
     * 最大数据与遍历数据的最后为互换
     * @param arr
     * @param n
     */
    public static void selectionSort(int[] arr, int n) {
        while (n > 1) {
            int maxPos = findMaxPos(arr, n);
            System.out.println(maxPos);
            int temp;
            temp = arr[n - 1];
            arr[n - 1] = arr[maxPos];
            arr[maxPos] = temp;
            n--;
        }
        System.out.println(Arrays.toString(arr));
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 2, 6, 1, 3, 9, 15};
        selectionSort(arr, arr.length);
    }

}
