import java.util.Arrays;

public class sf1_1 {

    //如果输入的数组只有一个元素，则返回该元素。
    public static int select(int[] arr, int k) {
        if (arr.length == 1) {
            return arr[0];
        }

        // 划分成五元组，并求出中位数，放在新数组中
        int[] medians = new int[(arr.length + 4) / 5];
        for (int i = 0; i < medians.length; i++) {
            int[] group = Arrays.copyOfRange(arr, i * 5, Math.min(i * 5 + 5, arr.length));
            Arrays.sort(group);
            medians[i] = group[group.length / 2];
        }

        // 找到中位数的中位数
        int pivot = select(medians, medians.length / 2);

        // 划分数组
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];
        int leftCount = 0;
        int rightCount = 0;
        int pivotCount = 0;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] < pivot) {
                left[leftCount++] = arr[i];
            } else if (arr[i] > pivot) {
                right[rightCount++] = arr[i];
            } else {
                pivotCount++;
            }
        }

        // 递归选择
        if (k < leftCount) {
            return select(Arrays.copyOfRange(left, 0, leftCount), k);
        } else if (k < leftCount + pivotCount) {
            return pivot;
        } else {
            return select(Arrays.copyOfRange(right, 0, rightCount), k - leftCount - pivotCount);
        }
    }

    public static void main(String[] args) {
        int[] arr = {3, 7, 8, 2, 1, 5, 6, 4};
        int k = 3;
        int kthSmallest = select(arr, k-1); // 数组下标从0开始，需要k-1
        System.out.println( "第 " + k + " 小的元素为: " + kthSmallest);
    }
}

