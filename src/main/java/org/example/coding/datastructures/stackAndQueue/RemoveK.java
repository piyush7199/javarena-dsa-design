package org.example.coding.datastructures.stackAndQueue;

import java.util.HashMap;
import java.util.PriorityQueue;

public class RemoveK {
    public int findLeastNumOfUniqueInts(int[] arr, int k) {

        if (k >= arr.length) return 0;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int ele : arr) {
            map.put(ele, map.getOrDefault(ele, 0) + 1);
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>(map.values());


        while (!pq.isEmpty() && k > 0) {
            if (pq.peek() > k) break;
            k -= pq.poll();
        }
        return pq.size();

    }
}
