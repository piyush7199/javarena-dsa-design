package com.javarena.dsa.algorithms.greedy;

import java.util.*;

class Item {
    int value, weight;

    Item(int val, int wt) {
        this.value = val;
        this.weight = wt;
    }
}

class Pair {
    int first, second;

    public Pair(int start, int finish) {
        this.first = start;
        this.second = finish;
    }
}

/**
 * Greedy Algorithms Collection
 *
 * <p><b>Problem Statement:</b><br>
 * Collection of classic greedy algorithm problems:
 * 1. Assign Cookies - maximize content children
 * 2. Lemonade Change - validate bill change transactions
 * 3. Minimum Coins - partition amount with minimum coins
 * 4. Fractional Knapsack - maximize value with fractional items
 * 5. N Meetings in One Room - schedule maximum meetings
 * 6. Jump Game - check if can reach end
 * 7. Largest Merge - create lexicographically largest merge
 * 8. Distant Barcodes - rearrange to avoid adjacent duplicates
 * 9. Minimum Platforms - find minimum railway platforms needed
 * 10. Job Sequencing - maximize profit with deadlines
 *
 * <p><b>Intuition & Approach:</b><br>
 * Core greedy principles:
 * - Make locally optimal choice at each step
 * - Local optimum leads to global optimum
 * - Often involves sorting for optimal ordering
 * - Use priority queues for maximum/minimum selection
 * - No backtracking needed (unlike DP)
 * 
 * Common patterns:
 * - Activity selection: Sort by end time
 * - Fractional knapsack: Sort by value/weight ratio
 * - Two-pointer after sorting: Matching problems
 * - Heap for dynamic selection: Resource allocation
 *
 * <p><b>Time Complexity:</b> O(N log N) typical for sorting-based greedy
 * <br><b>Space Complexity:</b> O(1) to O(N) depending on problem
 */
public class GreedyAlgorithms {

    /**
     * Assign Cookies: Maximize content children with greedy matching.
     */
    public static int findContentChildren(int[] g, int[] s) {
        Arrays.sort(g);
        Arrays.sort(s);
        
        int i = 0, j = 0;
        while (i < g.length && j < s.length) {
            if (s[j] >= g[i]) {
                i++;
            }
            j++;
        }
        return i;
    }

    /**
     * Lemonade Change: Validate if can provide correct change for all customers.
     */
    public static boolean lemonadeChange(int[] bills) {
        int five = 0, ten = 0;
        
        for (int bill : bills) {
            if (bill == 5) {
                five++;
            } else if (bill == 10) {
                if (five == 0) return false;
                five--;
                ten++;
            } else {  // bill == 20
                if (ten > 0 && five > 0) {
                    ten--;
                    five--;
                } else if (five >= 3) {
                    five -= 3;
                } else {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Minimum Coins: Partition amount using minimum coins (greedy works for standard denominations).
     */
    public static List<Integer> minPartition(int amount) {
        int[] denominations = {2000, 500, 200, 100, 50, 20, 10, 5, 2, 1};
        List<Integer> result = new ArrayList<>();
        
        for (int coin : denominations) {
            while (amount >= coin) {
                result.add(coin);
                amount -= coin;
            }
        }
        
        return result;
    }

    /**
     * Fractional Knapsack: Maximize value with fractional items allowed.
     */
    public static double fractionalKnapsack(int[] values, int[] weights, int W) {
        int n = values.length;
        Item[] items = new Item[n];
        
        for (int i = 0; i < n; i++) {
            items[i] = new Item(values[i], weights[i]);
        }
        
        Arrays.sort(items, (a, b) -> 
            Double.compare((double) b.value / b.weight, (double) a.value / a.weight));
        
        double totalValue = 0;
        int currentWeight = 0;
        
        for (Item item : items) {
            if (currentWeight + item.weight <= W) {
                currentWeight += item.weight;
                totalValue += item.value;
            } else {
                int remaining = W - currentWeight;
                totalValue += ((double) item.value / item.weight) * remaining;
                break;
            }
        }
        
        return totalValue;
    }

    /**
     * N Meetings in One Room: Schedule maximum non-overlapping meetings.
     */
    public static int maxMeetings(int start[], int end[]) {
        int n = start.length;
        Pair[] meetings = new Pair[n];
        
        for (int i = 0; i < n; i++) {
            meetings[i] = new Pair(start[i], end[i]);
        }
        
        Arrays.sort(meetings, (a, b) -> a.second - b.second);
        
        int count = 1;
        int lastEnd = meetings[0].second;
        
        for (int i = 1; i < n; i++) {
            if (meetings[i].first > lastEnd) {
                count++;
                lastEnd = meetings[i].second;
            }
        }
        
        return count;
    }

    /**
     * Jump Game: Check if can reach last index.
     */
    public boolean canJump(int[] nums) {
        int maxReach = 0;
        
        for (int i = 0; i < nums.length; i++) {
            if (i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        
        return true;
    }

    /**
     * Largest Merge: Create lexicographically largest merge of two strings.
     */
    public String largestMerge(String word1, String word2) {
        StringBuilder merge = new StringBuilder();
        int i = 0, j = 0;
        
        while (i < word1.length() && j < word2.length()) {
            if (word1.substring(i).compareTo(word2.substring(j)) > 0) {
                merge.append(word1.charAt(i++));
            } else {
                merge.append(word2.charAt(j++));
            }
        }
        
        while (i < word1.length()) merge.append(word1.charAt(i++));
        while (j < word2.length()) merge.append(word2.charAt(j++));
        
        return merge.toString();
    }

    /**
     * Rearrange Barcodes: Rearrange to avoid adjacent duplicates.
     */
    public int[] rearrangeBarcodes(int[] barcodes) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int code : barcodes) {
            freq.put(code, freq.getOrDefault(code, 0) + 1);
        }
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
        for (Map.Entry<Integer, Integer> entry : freq.entrySet()) {
            pq.offer(new int[]{entry.getKey(), entry.getValue()});
        }
        
        int[] result = new int[barcodes.length];
        int index = 0;
        
        while (!pq.isEmpty()) {
            int[] first = pq.poll();
            if (index == 0 || result[index - 1] != first[0]) {
                result[index++] = first[0];
                first[1]--;
                if (first[1] > 0) pq.offer(first);
            } else {
                if (pq.isEmpty()) break;
                int[] second = pq.poll();
                result[index++] = second[0];
                second[1]--;
                if (second[1] > 0) pq.offer(second);
                pq.offer(first);
            }
        }
        
        return result;
    }

    /**
     * Minimum Platforms: Find minimum railway platforms needed.
     */
    public static int findPlatform(int[] arr, int[] dep) {
        Arrays.sort(arr);
        Arrays.sort(dep);
        
        int platformsNeeded = 0;
        int maxPlatforms = 0;
        int i = 0, j = 0;
        
        while (i < arr.length) {
            if (arr[i] <= dep[j]) {
                platformsNeeded++;
                maxPlatforms = Math.max(maxPlatforms, platformsNeeded);
                i++;
            } else {
                platformsNeeded--;
                j++;
            }
        }
        
        return maxPlatforms;
    }

    /**
     * Job Sequencing: Maximize profit with job deadlines.
     */
    public ArrayList<Integer> jobSequencing(int[] deadline, int[] profit) {
        int n = deadline.length;
        int[][] jobs = new int[n][2];
        
        for (int i = 0; i < n; i++) {
            jobs[i][0] = deadline[i];
            jobs[i][1] = profit[i];
        }
        
        Arrays.sort(jobs, (a, b) -> b[1] - a[1]);
        
        int maxDeadline = 0;
        for (int d : deadline) {
            maxDeadline = Math.max(maxDeadline, d);
        }
        
        int[] slots = new int[maxDeadline + 1];
        Arrays.fill(slots, -1);
        
        int jobCount = 0;
        int totalProfit = 0;
        
        for (int[] job : jobs) {
            int d = job[0];
            int p = job[1];
            
            for (int j = d; j > 0; j--) {
                if (slots[j] == -1) {
                    slots[j] = p;
                    jobCount++;
                    totalProfit += p;
                    break;
                }
            }
        }
        
        ArrayList<Integer> result = new ArrayList<>();
        result.add(jobCount);
        result.add(totalProfit);
        return result;
    }
}
