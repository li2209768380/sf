package sf4;

import java.util.PriorityQueue;

import java.util.*;

public class KnapsackBranchAndBound {
    static int maxValue = 0; // 记录最大价值
    static int[] bestSolution; // 记录最优解
    static int[] values; // 物品价值
    static int[] weights; // 物品重量
    static int capacity; // 背包容量

    public static void main(String[] args) {
        weights = new int[]{5, 15, 25, 27, 30};
        values = new int[]{12, 30, 44, 46, 50};
        capacity = 50;
        bestSolution = new int[weights.length];
        Arrays.fill(bestSolution, -1);
        knapsackProblem();
        System.out.println("最大价值为：" + maxValue);
        System.out.println("最优解为：" + Arrays.toString(bestSolution));
    }

    // 分支限界法求解0/1背包问题
    public static void knapsackProblem() {
        Queue<Node> queue = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node n1, Node n2) {
                return n2.upperBound - n1.upperBound;
            }
        }); // 使用优先队列，根据上界从大到小排序
        queue.offer(new Node(0, 0, 0, new int[weights.length])); // 将根节点加入队列
        while (!queue.isEmpty()) {
            Node node = queue.poll(); // 取出队头节点
            if (node.level == weights.length) { // 叶子节点，更新最优解和最大价值
                if (node.value > maxValue) {
                    maxValue = node.value;
                    bestSolution = node.solution;
                }
                continue;
            }
            if (node.weight + weights[node.level] <= capacity) { // 加入左子节点
                int[] newSolution = Arrays.copyOf(node.solution, node.solution.length);
                newSolution[node.level] = 1;
                queue.offer(new Node(node.level + 1, node.weight + weights[node.level], node.value + values[node.level], newSolution));
            }
            // 加入右子节点
            queue.offer(new Node(node.level + 1, node.weight, node.value, node.solution));
        }
    }

    // 节点类
    static class Node {
        int level; // 节点所处的层数
        int weight; // 当前节点的背包重量
        int value; // 当前节点的背包价值
        int[] solution; // 当前节点的解向量
        int upperBound; // 当前节点的上界

        public Node(int level, int weight, int value, int[] solution) {
            this.level = level;
            this.weight = weight;
            this.value = value;
            this.solution = solution;
            this.upperBound = calculateUpperBound();
        }

        // 计算当前节点的上界
        public int calculateUpperBound() {
            int remainingCapacity = capacity - weight;
            int upperBound = value;
            int i = level;
            while (i < weights.length && remainingCapacity > 0) {
                if (weights[i] <= remainingCapacity) {
                    upperBound += values[i];
                    remainingCapacity -= weights[i];
                } else {
                    upperBound += (int) (1.0 * remainingCapacity / weights[i] * values[i]); // 用部分物品
                    break;
                }
                i++;
            }
            return upperBound;
        }
    }
}

