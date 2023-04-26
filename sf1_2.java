import java.util.Arrays;

public class sf1_2 {

    public static void main(String[] args) {

        int n = 5;

        int[] x = {1,2,1,3,3};
        int[] y = {2,2,3,-2,3};

        sort(n, x, y);

    }

    private static void sort(int n, int[] x, int[] y) {
        //先把x坐标和y坐标按升序排列
        Arrays.parallelSort(x);
        Arrays.parallelSort(y);

        //取y坐标的中位数
        int y0= y[n /2];
        int sum=0;

        //y方向上士兵需要移动的步数
        for(int i = 0; i< n; i++) {
            sum+=Math.abs(y[i]-y0);
            x[i]-=i;//为了获取x的中位数
        }

        //再对处理过的x坐标排序，并取排序后的中位数
        Arrays.parallelSort(x);
        int x0= x[n /2];

        //x方向上士兵需要移动的步数
        for(int i = 0; i< n; i++)
            sum+=Math.abs(x[i]-x0);

        System.out.println(sum);
    }
}

