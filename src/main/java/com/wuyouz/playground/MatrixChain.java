package com.wuyouz.playground;

import java.util.Arrays;

public class MatrixChain {

    public static int minMultiply(int[] chain) {
        int N = chain.length - 1;
        int[][] dp = new int[N][N];
        for (int[] row : dp) {
            Arrays.fill(row, 0);
        }
        for (int n = 2; n <= N; n++) {
            for (int i = 0; i < N - n + 1 ; i++) {
                int j = i + n - 1;
                int min = Integer.MAX_VALUE;
                for (int k = i; k < j; k++) {
                    min = Math.min(dp[i][k] + dp[k + 1][j] + chain[i] * chain[k+1] * chain[j+1], min);
                }
                dp[i][j] = min;
            }
            System.out.println("----------");
            for (int[] row : dp) {
                System.out.println(Arrays.toString(row));
            }
            System.out.println("----------");
        }

        return dp[0][N - 1];
    }

    public static void main(String[] args) {
        int[] chain = new int[]{30, 35, 15, 5, 10, 20, 25};

        System.out.println("the minimum cost of chain is " + minMultiply(chain));

        int[] chain2 = new int[]{5, 4, 6, 2, 7};

        System.out.println("the minimum cost of chain is " + minMultiply(chain2));

    }

}
