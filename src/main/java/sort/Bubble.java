package sort;

import java.util.Arrays;

/**
 * 类功能说明: 冒泡
 * 类修改者	创建日期2021/1/29
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class Bubble {

    /**
     * @param arr 排列的数组
     * @param n   个数
     */
    public static void bubble(int[] arr, int n) {
        int temp;
        for (int i = 0; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                temp = arr[i+1];
                arr[i + 1] = arr[i];
                arr[i] = temp;
            }
        }
    }

    public static void bubbleSort(int[] arr) {
        // bubble(arr,n-1);
        for (int i = arr.length ; i > 0; i--) {
            bubble(arr, i);
        }
    }

    public static void main(String[] args) {
        int[] arr = {5, 4, 2, 6, 1, 3, 9, 15};
        bubbleSort(arr);
        System.out.println(Arrays.toString(arr));
    }
}
