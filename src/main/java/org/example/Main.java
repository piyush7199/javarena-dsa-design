package org.example;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        /**
         *
         */
       int[] arr = {1,2,3,4,5,6,7,8,9};

       int targetValue = 8;

        Set<Integer> set = new HashSet<>();
        int i = 0;
        int j = arr.length -1;

        while(i<j) {
            int sum = arr[i]+arr[j];
            if(sum == targetValue) {
                System.out.println("Pair exists");
            } else if(sum > targetValue) {
                j--;
            } else {
                i++;
            }
        }
    }
}
