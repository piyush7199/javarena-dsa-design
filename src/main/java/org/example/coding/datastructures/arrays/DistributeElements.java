package org.example.coding.datastructures.arrays;

public class DistributeElements {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        ans[0] = nums[0];
        ans[n - 1] = nums[1];
        int i = 0;
        int j = n - 1;
        for (int k = 2; k < n; k++) {
            if (ans[i] > ans[j]) {
                ans[++i] = nums[k];
            } else {
                ans[--j] = nums[k];
            }
        }

        int k = n - 1;
        while (j <= k) {
            int temp = ans[j];
            ans[j] = ans[k];
            ans[k] = temp;
            j++;
            k--;
        }
        return ans;
    }
}
