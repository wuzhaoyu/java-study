package sort;

import java.util.Arrays;

/**
 * 类功能说明:
 * 类修改者	创建日期2021/1/28
 * 修改说明
 *
 * @author wzy
 * @version V1.0
 * @description 说明：
 **/
public class HeapSort1 {
    public static void main(String[] args) {
        int[] arr = new int[]{9, 6, 8, 7, 0, 1, 10, 4, 2};

        // 最大非叶子节点 开始
        int start = (arr.length - 1) / 2;

        buildMaxHeap(start,arr);
        // [10, 7, 9, 6, 0, 1, 8, 4, 2]
        // [10, 7, 9, 6, 0, 1, 8, 4, 2]
        System.out.println(Arrays.toString(arr));
        // 排序
        for (int i = arr.length-1; i > 0; i--) {
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
            heap(arr,i,0);
        }
        System.out.println(Arrays.toString(arr));
    }

    /**
     * 构建最大顶堆
     *
     * @param start
     * @param arr
     */
    public static void buildMaxHeap(int start, int[] arr) {
        for (int i = start; i >=0; i--) {
            heap(arr, arr.length, i);
        }

    }

    /**
     * 堆排序
     *
     * @param arr
     * @param size
     * @param index
     */
    public static void heap(int[] arr, int size, int index) {

        // 假设当前的节点为最大
        int max = index;
        int leftIndex = 2 * index + 1;
        int rightIndex = 2 * index + 2;

        // 考虑可能存在 父节点的子节点不存在的情况
        // 左节点大于 最大节点
        if (size > leftIndex && arr[max] < arr[leftIndex]) {
            max = leftIndex;
        }
        // 右节点大于 最大节点
        if (size > rightIndex && arr[max] < arr[rightIndex]) {
            max = rightIndex;
        }

        // 节点置换条件
        if (max != index){
            swap(arr, max, index);
            heap(arr, size, max);
        }

    }

    /**
     * 置换节点
     *
     * @param arr
     * @param max
     * @param index
     */
    public static void swap(int[] arr, int max, int index) {
        int temp = arr[index];
        arr[index] = arr[max];
        arr[max] = temp;
    }
}
