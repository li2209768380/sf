import java.util.*;

public class sf3_2 {
    static int N = 110;
    static int n;
    //二维数组 g 存储了每对农场之间的距离，使用一个数组 p 存储每个节点所在的集合。
    static int[][] g = new int[N][N];
    static int[] p = new int[N];

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        n = scan.nextInt();
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                g[i][j] = scan.nextInt();
            }
        }
        System.out.println(kruskal());
    }

    //在 find 函数中，如果某个节点的父节点不是自己，就把它的父节点设置为其真正的父节点。
    static int find(int x) {
        if (p[x] != x) p[x] = find(p[x]);
        return p[x];
    }

    //在 kruskal 函数中，先将所有边按照权值从小到大排序。然后遍历每一条边，
    // 判断它所连接的两个节点是否在同一个集合中，如果不是，就把它们合并到同一个集合中，并把这条边的权值加入到最小生成树的权值中。
    static int kruskal() {
        List<int[]> edges = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                edges.add(new int[]{i, j, g[i][j]});
            }
        }
        Collections.sort(edges, (a, b) -> a[2] - b[2]);

        for (int i = 0; i < n; i++) {
            p[i] = i;
        }

        int res = 0, cnt = 0;
        for (int[] e : edges) {
            int a = e[0], b = e[1], w = e[2];
            a = find(a);
            b = find(b);
            if (a != b) {
                p[a] = b;
                res += w;
                cnt++;
                if (cnt == n - 1) break;
            }
        }

        return res;
    }
}
