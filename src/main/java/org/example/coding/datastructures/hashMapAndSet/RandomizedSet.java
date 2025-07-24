package org.example.coding.datastructures.hashMapAndSet;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * RandomizedSet is a data structure that allows insertion, deletion, and fetching
 * a random element—all in average constant time O(1).
 * <p>
 * Internally, it uses an ArrayList to store values and a HashMap to store
 * value-to-index mappings. This allows efficient random access and updates.
 */
public class RandomizedSet {
    private ArrayList<Integer> list; // Stores the elements
    private Map<Integer, Integer> map; // Maps value -> index in list

    /**
     * Constructs an empty RandomizedSet.
     */
    public RandomizedSet() {
        list = new ArrayList<>();
        map = new HashMap<>();
    }

    /**
     * Checks whether a value exists in the set.
     *
     * @param val the value to search for
     * @return true if the value exists, false otherwise
     */
    public boolean search(int val) {
        return map.containsKey(val);
    }

    /**
     * Inserts a value into the set if it is not already present.
     *
     * @param val the value to insert
     * @return true if the value was inserted, false if it already exists
     * <p>
     * Time Complexity: O(1)
     */
    public boolean insert(int val) {
        if (search(val)) return false;

        list.add(val);
        map.put(val, list.size() - 1); // Store index of val in list
        return true;
    }

    /**
     * Removes a value from the set if it exists.
     * <p>
     * The element to be removed is swapped with the last element in the list,
     * then the last element is removed to maintain O(1) deletion.
     *
     * @param val the value to remove
     * @return true if the value was removed, false if it does not exist
     * <p>
     * Time Complexity: O(1)
     */
    public boolean remove(int val) {
        if (!search(val)) return false;

        int indexToRemove = map.get(val);
        int lastElement = list.get(list.size() - 1);

        // Move the last element to the index of the element to remove
        list.set(indexToRemove, lastElement);
        map.put(lastElement, indexToRemove);

        // Remove the last element and the mapping of the removed value
        list.remove(list.size() - 1);
        map.remove(val);

        return true;
    }

    /**
     * Retrieves a random element from the set.
     *
     * @return a random element from the set
     * <p>
     * Time Complexity: O(1)
     */
    public int getRandom() {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }
}
