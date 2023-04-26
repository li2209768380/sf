public class sf2_2 {

    public static int maxRevenueDP(int[] p, int n) {
        /*
             可以定义一个数组r[]，其中r[i]表示长度为i米的钢条的最大收益，初始值为r[0] = 0。
             其中p[j]表示长度为j米的钢条的单价，r[i-j]表示长度为i-j米的钢条的最大收益
         */
        int[] r = new int[n + 1];
        r[0] = 0;
        for (int i = 1; i <= n; i++) {
            int max = Integer.MIN_VALUE;
            //i从1到n，j从1到i，计算长度为j的钢条的收益p[j-1]和长度为i-j的钢条的最大收益r[i-j]的和
            for (int j = 1; j <= i; j++) {
                //   r[i] = max{ p[j] + r[i-j] } (1<=j<=i)
                max = Math.max(max, p[j - 1] + r[i - j]);
            }
            r[i] = max;
        }
        return r[n];
    }

    public static void main(String[] args) {
        int[] p = {1, 5, 8, 9, 10, 17, 17, 20, 24, 24};
        int n = 10;
        System.out.println(maxRevenueDP(p, n));
    }
}

