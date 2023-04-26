public class sf2_1 {

    public static int minDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        //dp[i][j]表示将字符串A的前i个字符转换成字符串B的前j个字符所需要的最小操作次数。
        int[][] dp = new int[m + 1][n + 1];
        //初始化dp数组。当i=0时，dp[0][j]=j。当j=0时，dp[i][0]=i。
        for (int i = 0; i <= m; i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= n; j++) {
            dp[0][j] = j;
        }
        for (int i = 1; i <= m; i++) {
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    //当A[i-1]=B[j-1]时，不需要操作，所以dp[i][j]=dp[i-1][j-1]。
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    /*
                        当A[i-1]!=B[j-1]时，需要进行插入、删除和替换操作，取三者中最小的操作数，
                        即dp[i][j]=min(dp[i-1][j]+1, dp[i][j-1]+1, dp[i-1][j-1]+1)。
                     */
                    dp[i][j] = Math.min(dp[i - 1][j], Math.min(dp[i][j - 1], dp[i - 1][j - 1])) + 1;
                }
            }
        }
        return dp[m][n];
    }

    public static void main(String[] args) {
        String word1 = "kitten";
        String word2 = "sitting";
        int distance = minDistance(word1, word2);
        System.out.println(distance);
    }
}
