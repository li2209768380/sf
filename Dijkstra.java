import java.util.*;

public class Dijkstra {
    /*
    首先，我们使用Java中的优先队列来实现最小堆。然后我们初始化源节点到其他所有节点的距离为无穷大，将源节点的距离设置为0，
    并将其放入优先队列中。然后，我们重复以下步骤，直到所有节点都被访问：从未访问的节点中选择最小距离的节点，并将其标记为已访问，
    然后对于与该节点相邻的未访问节点，更新它们的距离，如果新的距离比原来的距离更小，就将其添加到优先队列中。
     */
    private final int[][] graph;
    private final int source;
    private final int[] dist;
    private final Set<Integer> visited;
    private final PriorityQueue<Node> pq;
    private final int n;

    public Dijkstra(int[][] graph, int source, int n) {
        this.graph = graph;
        this.source = source;
        this.n = n;
        this.dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[source] = 0;
        this.visited = new HashSet<>();
        this.pq = new PriorityQueue<>(n, new Node());
        pq.offer(new Node(source, 0));
    }

    public void shortestPath() {
        while (visited.size() != n) {
            int u = pq.poll().node;
            visited.add(u);
            for (int v = 0; v < n; v++) {
                if (graph[u][v] != 0 && !visited.contains(v)) {
                    int alt = dist[u] + graph[u][v];
                    if (alt < dist[v]) {
                        dist[v] = alt;
                        pq.offer(new Node(v, dist[v]));
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        int n = 5;
        int[][] graph = new int[n][n];
        graph[0] = new int[]{0, 10, 0, 30, 100};
        graph[1] = new int[]{10, 0, 50, 0, 0};
        graph[2] = new int[]{0, 50, 0, 20, 10};
        graph[3] = new int[]{30, 0, 20, 0, 60};
        graph[4] = new int[]{100, 0, 10, 60, 0};
        int source = 0;
        Dijkstra dijkstra = new Dijkstra(graph, source, n);
        dijkstra.shortestPath();
        System.out.println(Arrays.toString(dijkstra.dist));
    }

    static class Node implements Comparator<Node> {
        public int node;
        public int cost;

        public Node() {
        }

        public Node(int node, int cost) {
            this.node = node;
            this.cost = cost;
        }

        @Override
        public int compare(Node node1, Node node2) {
            if (node1.cost < node2.cost)
                return -1;
            if (node1.cost > node2.cost)
                return 1;
            return 0;
        }
    }
}
