package org.example.coding.algorithms.twoPointerAndSlidingWindow;

import java.util.HashMap;

public class SubarrayWithKDifferentInteger {
    public int subarraysWithKDistinct(int[] nums, int k) {
        return freqLessThanEquals(nums, k) - freqLessThanEquals(nums, k - 1);
    }

    private int freqLessThanEquals(int[] nums, int goal) {
        if (goal < 0) return 0;
        int cnt = 0;
        int left = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        int right = 0;
        while (right < nums.length) {
            map.put(nums[right], map.getOrDefault(nums[right], 0) + 1);
            while (map.size() > goal) {
                map.put(nums[left], map.get(nums[left]) - 1);
                if (map.get(nums[left]) == 0) {
                    map.remove(nums[left]);
                }
                left++;
            }
            cnt += (right - left + 1);
            right++;
        }
        return cnt;
    }
}
