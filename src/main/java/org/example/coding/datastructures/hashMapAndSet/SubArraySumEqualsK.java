package org.example.coding.datastructures.hashMapAndSet;

import java.util.HashMap;

public class SubArraySumEqualsK {

    /**
     * In brute force approach we will try every subarray,
     * TC - O n^2;
     * SC - O 1
     */
    public int subarraySumBrute(int[] Arr, int K) {
        int ans = 0;
        int n = Arr.length;
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                sum += Arr[j];
                if (sum == K) ans++;
            }
        }
        return ans;
    }
    /**
     * To optimize the above approach we can use prefix sum and a hashMap to store old
     * TC - O(n)
     * SC - O(n)
     */
    public int subarraySum(int[] Arr, int K) {
        // code here
        int N = Arr.length;
        long prefSum = 0;
        int c = 0;
        HashMap<Long,Integer> map = new HashMap<>();
        map.put(0L,1);
        for (int j : Arr) {
            prefSum += j;
            long rem = prefSum - K;
            c += map.getOrDefault(rem, 0);
            map.put(prefSum, map.getOrDefault(prefSum, 0) + 1);
        }
        return c;
    }
}
