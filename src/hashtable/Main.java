package hashtable;

import java.lang.reflect.Array;
import java.util.*;

public class Main {
    public static boolean itemInCommon (int[] array1, int[] array2) {
        HashMap<Integer, Boolean> hashmap = new HashMap<>();
        for (int num1 : array1) hashmap.put(num1, true);
        for (int num2 : array2) {
            if (hashmap.get(num2) != null) return true;
        }
        return false;
    }

    public static List<Integer> findDuplicates (int[] nums) {
        Map<Integer, Integer> numCounts = new HashMap<>();
        for (int num : nums) {
            numCounts.put(num, numCounts.getOrDefault(num, 0) + 1);
        }
        List<Integer> duplicates = new ArrayList<>();
        for (Map.Entry<Integer, Integer> entry : numCounts.entrySet()) {
            if (entry.getValue() > 1) duplicates.add(entry.getKey());
        }
        return duplicates;
    }

    public static Character firstNonRepeatingChar (String s) {
        Map<Character, Integer> myMap = new HashMap<>();
        for (char c : s.toCharArray()) {
            myMap.put(c, myMap.getOrDefault(c, 0) + 1);
        }
        for (char c : s.toCharArray()) {
            if (myMap.get(c) == 1) return c;
        }
        return null;
    }

    public static List<List<String>> groupAnagrams (String[] strings) {
        Map<String, List<String>> result = new HashMap<>();
        for (String s : strings) {
            char[] characters = s.toCharArray();
            Arrays.sort(characters);
            String canonical = new String(characters);
            if (result.containsKey(canonical)) {
                result.get(canonical).add(s);
            } else {
                result.put(canonical, new ArrayList<>());
                result.get(canonical).add(s);
            }
        }
        return new ArrayList<>(result.values());
    }

    public static int[] twoSum (int[] nums, int target) {
        Map<Integer, Integer> myMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            // int num = nums[i];
            int compliment = target - nums[i];
            if (myMap.containsKey(compliment)) {
                return new int[]{i, myMap.get(compliment)};
            }
            myMap.put(nums[i], i);
        }
        return new int[]{};
    }

    public static int[] subarraySum (int[] nums, int target) {
        Map<Integer, Integer> result = new HashMap<>();
        int currentSum = 0;
        result.put(0, -1);
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (result.containsKey(currentSum - target)) {
                return new int[]{result.get(currentSum - target) + 1, i};
            } else {
                result.put(currentSum, i);
            }
        }
        return new int[0];
    }

    public static List<Integer> removeDuplicates (List<Integer> myList) {
        Set<Integer> result = new HashSet<>(myList);
        return new ArrayList<>(result);
    }

    public static boolean hasUniqueChars (String string) {
        char[] characters = string.toCharArray();
        List<Character> result = new ArrayList<>();
        for (char c : characters) {
            if (result.contains(c)) return false;
            result.add(c);
        }
        return true;
    }

    public static List<int[]> findPairs (int[] arr1, int[] arr2, int target) {
        List<int[]> result = new ArrayList<>();
        Set<Integer> mySet = new HashSet<>();
        for (int num1 : arr1) {
            mySet.add(num1);
        }
        for (int num2 : arr2) {
            if (mySet.contains(target - num2)) {
                result.add(new int[]{target - num2, num2});
            }
        }
        return result;
    }

    public static int longestConsecutiveSequence (int[] nums) {
        Set<Integer> mySet = new HashSet<>();
        for (int num : nums) {
            mySet.add(num);
        }
        int longestStreak = 0;
        for (int num : mySet) {
            if (!mySet.contains(num - 1)) {
                int currentStreak = 1;
                while (mySet.contains(num + 1)) {
                    currentStreak++;
                    num = num + 1;
                }
                longestStreak = Math.max(longestStreak, currentStreak);
            }
        }
        return longestStreak;
    }

    static void main(String[] args) {
        int[] arr1 = {5, 4, 3, 2, 1, 55};
        System.out.println(longestConsecutiveSequence(arr1));
    }
}
