package com.wuyouz.playground;

import java.util.Arrays;

import static java.util.Arrays.sort;

/**
 * Find k-th smallest element in the array
 * <p>
 * Created by dqliu on 4/21/16.
 */
public class OderStatistic {

    public static int kthOrder(int[] nums, int i) {

        return randomizedSelected(nums, 0, nums.length - 1, i);

    }

    private static int randomizedSelected(int[] nums, int p, int r, int i) {
        if (p == r) {
            return nums[p];
        }
        int q = randomizedPartition(nums, p, r);
        int k = q - p + 1;
        if (i == k) {
            return nums[q];
        } else if (i < k) {
            return randomizedSelected(nums, p, q - 1, i);
        } else {
            return randomizedSelected(nums, q + 1, r, i - k);
        }
    }

    private static int randomizedPartition(int[] nums, int start, int end) {
        int pivotIndex = start + (end - start) / 2;
        int pivot = nums[pivotIndex];
        int i = start;
        int j = end;

        while (i <= j) {
            while (nums[i] < pivot) {
                i++;
            }
            while (nums[j] > pivot) {
                j--;
            }

            if (i <= j) {
                if (i == pivotIndex) {
                    pivotIndex = j;
                } else if (j == pivotIndex) {
                    pivotIndex = i;
                }
                int temp = nums[i];
                nums[i] = nums[j];
                nums[j] = temp;
                i++;
                j--;

            }
        }
        return pivotIndex;

    }

    public static void main(String[] args) {
        int[] nums = {3, 283, 49, 39, 92, 33, 20, 2, 13, 9, 322, 0};
        int k = 9;

        System.out.println(kthOrder(nums, k));
        sort(nums);
        System.out.println(Arrays.toString(nums));

    }

}
