package org.example.coding.datastructures.arrays;

public class RearrangeArrayEleBySign {
    public int[] rearrangeArray(int[] nums) {
        int n = nums.length;
        int[] ans = new int[n];
        int i = 0;
        int j = 1;
        for (int ele : nums) {
            if (ele > 0) {
                ans[i] = ele;
                i += 2;
            } else {
                ans[j] = ele;
                j += 2;
            }
        }
        return ans;
    }
}
