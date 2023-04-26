package sf4;

import java.util.Arrays;

public class KnapsackBacktracking {
    private int maxVal;
    private int[] bestSolution;

    public int knapsack(int[] weights, int[] values, int capacity) {
        int n = weights.length;
        int[] solution = new int[n];
        backtrack(0, weights, values, capacity, solution, 0, 0);
        return maxVal;
    }

    private void backtrack(int i, int[] weights, int[] values, int capacity, int[] solution, int currVal, int currWeight) {
        if (i == weights.length) {
            if (currVal > maxVal) {
                maxVal = currVal;
                bestSolution = Arrays.copyOf(solution, solution.length);
            }
            return;
        }
        if (currWeight + weights[i] <= capacity) {
            solution[i] = 1;
            backtrack(i + 1, weights, values, capacity, solution, currVal + values[i], currWeight + weights[i]);
        }
        solution[i] = 0;
        backtrack(i + 1, weights, values, capacity, solution, currVal, currWeight);
    }

    public int[] getBestSolution() {
        return bestSolution;
    }

    public static void main(String[] args) {
        int[] weights = {5, 15, 25, 27, 30};
        int[] values = {12, 30, 44, 46, 50};
        int capacity = 50;

        KnapsackBacktracking knapsack = new KnapsackBacktracking();
        int maxVal = knapsack.knapsack(weights, values, capacity);
        int[] bestSolution = knapsack.getBestSolution();

        System.out.println("最大价值为：" + maxVal);
        System.out.println("最优解为：" + Arrays.toString(bestSolution));
    }

}

