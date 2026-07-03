package hashtable;

import java.lang.reflect.Array;
import java.util.*;

public class HashTable {
    private int size = 10;
    private Node[] dataMap;

    class Node {
        String key;
        int value;
        Node next;

        Node (String key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashTable () {
        dataMap = new Node[size];
    }

    public Node[] getDataMap() {
        return dataMap;
    }

    public void printTable() {
        for (int i = 0; i < dataMap.length; i++) {
            System.out.println(i + ": ");
            Node temp = dataMap[i];
            while (temp != null) {
                System.out.println("   {" + temp.key + " = " + temp.value + "}");
                temp = temp.next;
            }
        }
    }

    private int hash (String key) {
        int hash = 0;
        char[] keyChar = key.toCharArray();
        for (int asciiChar : keyChar) {
            hash += asciiChar * 23;
        }
        return hash % size;
    }

    public void set (String key, int value) {
        Node newNode = new Node(key, value);
        int index = hash(key);
        Node temp = dataMap[index];
        if (temp == null) {
            dataMap[index] = newNode;
        } else {
            if (temp.key.equals(key)) {
                temp.value += value;
                return;
            }
            while (temp.next != null) {
                temp = temp.next;
                if (temp.key.equals(key)) {
                    temp.value += value;
                    return;
                }
            }
            temp.next = newNode;
        }
    }

    public int get (String key) {
        int index = hash(key);
        Node temp = dataMap[index];
        if (temp == null) return 0;
        while (!temp.key.equals(key)) temp = temp.next;
        return temp.value;
    }

    public ArrayList<String> keys() {
        ArrayList<String> allKeys = new ArrayList<>();
        for (Node node : dataMap) {
            while (node != null) {
                allKeys.add(node.key);
                node = node.next;
            }
        }
        return allKeys;
    }

    public static Character firstNonRepeatingChar3 (String s) {
        char[] characters = s.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            for (int j = 0; j < characters.length; j++) {
                if (characters[i] == characters[j] && i != j) break;
                else {
                    if (j == characters.length - 1) return characters[i];
                }
            }
        }
        return null;
    }

    public static Character firstNonRepeatingChar2 (String s) {
        char[] characters = s.toCharArray();
        Map<Character, Integer> charCounts = new HashMap<>();
        for (char c : characters) {
            charCounts.put(c, charCounts.getOrDefault(c, 0) + 1);
        }
        for (Map.Entry<Character, Integer> entry : charCounts.entrySet()) {
            if (entry.getValue() == 1) return entry.getKey();
        }
        return null;
    }

    public Character firstNonRepeatingChar (String s) {
        Map<Character, Character> charCounts = new HashMap<>();
        char[] characters = s.toCharArray();
        for (int i = 0; i < characters.length; i++) {
            for (int j = i + 1; j < characters.length; j++) {
                charCounts.put(characters[i], characters[j]);
                if (charCounts.get(characters[i]) == characters[j]) break;
                if (j == characters.length - 1) return characters[i];
            }
        }
        return null;
    }

    public static List<List<String>> groupAnagrams2 (String[] strings) {
        List<List<String>> result = new ArrayList<>();
        List<Integer> indexes = new ArrayList<>();
        int listIndex = 0;
        for (int i = 0; i < strings.length; i++) {
            if (indexes.contains(i)) continue;
            result.add(new ArrayList<>());
            result.get(listIndex).add(strings[i]);
            indexes.add(i);
            Map<Character, Integer> chars1 = new HashMap<>();
            for (char c : strings[i].toCharArray()) {
                chars1.put(c, chars1.getOrDefault(c, 0) + 1);
            }
            for (int j = i + 1; j < strings.length; j++) {
                if (indexes.contains(j)) continue;
                Map<Character, Integer> chars2 = new HashMap<>();
                for (char c : strings[j].toCharArray()) {
                    chars2.put(c, chars2.getOrDefault(c, 0) + 1);
                }
                if (chars1.equals(chars2)) {
                    result.get(listIndex).add(strings[j]);
                    indexes.add(j);
                }
            }
            listIndex++;
        }
        return result;
    }

    public static List<List<String>> groupAnagrams3 (String[] strings) {
        List<List<String>> result = new ArrayList<>();
        List<Integer> skipIndex = new ArrayList<>();
        for (int i = 0; i < strings.length; i++) {
            if (skipIndex.contains(i)) continue;
            result.add(new ArrayList<>());
            result.getLast().add(strings[i]);
            skipIndex.add(i);
            Map<Character, Integer> chars1 = new HashMap<>();
            for (char c : strings[i].toCharArray()) {
                chars1.put(c, chars1.getOrDefault(c, 0) + 1);
            }
            for (int j = i + 1; j < strings.length; j++) {
                if (skipIndex.contains(j)) continue;
                Map<Character, Integer> chars2 = new HashMap<>();
                for (char c : strings[j].toCharArray()) {
                    chars2.put(c, chars2.getOrDefault(c, 0) + 1);
                }
                if (chars1.equals(chars2)) {
                    result.getLast().add(strings[j]);
                    skipIndex.add(j);
                }
            }
        }
        return result;
    }

    public static List<List<String>> groupAnagrams4 (String[] strings) {
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

    public static int[] twoSum2 (int[] nums, int target) {
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] + nums[j] == target) {
                    result[0] = i;
                    result[1] = j;
                    return result;
                }
            }
        }
        return new int[0];
    }

    public static int[] subarraySum2 (int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int sum = nums[i];
            if (sum == target) {
                return new int[]{i, i};
            }
            for (int j = i + 1; j < nums.length; j++) {
                sum += nums[j];
                if (sum == target) {
                    return new int[]{i, j};
                }
            }
        }
        return new int[0];
    }

    public static int[] subarraySum3 (int[] nums, int target) {
        for (int i = 0; i < nums.length; i++) {
            int compliment = target;
            compliment -= nums[i];
            if (compliment == 0) return new int[]{i, i};
            for (int j = i + 1; j < nums.length; j++) {
                compliment -= nums[j];
                if (compliment == 0) return new int[]{i, j};
            }
        }
        return new int[0];
    }

    public static int[] subarraySum4 (int[] nums, int target) {
        Map<Integer, Integer> sumIndex = new HashMap<>();
        sumIndex.put(0, -1);
        int currentSum = 0;
        for (int i = 0; i < nums.length; i++) {
            currentSum += nums[i];
            if (sumIndex.containsKey(currentSum - target)) {
                return new int[]{sumIndex.get(currentSum - target) + 1, i};
            } else {
                sumIndex.put(currentSum, i);
            }
        }
        return new int[0];
    }

    public static List<Integer> removeDuplicates(List<Integer> myList) {
        List<Integer> result = new ArrayList<>();
        for (Integer integer : myList) {
            if (!result.contains(integer)) result.add(integer);
        }
        return result;
    }

    public static boolean hasUniqueChars (String string) {
        char[] characters = string.toCharArray();
        Set<Character> mySet = new HashSet<>();
        for (char c : characters) {
            mySet.add(c);
        }
        return characters.length == mySet.size();
    }

    public static List<int[]> findPairs (int[] arr1, int[] arr2, int target) {
        List<int[]> result = new ArrayList<>();
        for (int num1 : arr1) {
            for (int num2 : arr2) {
                if (num1 + num2 == target) {
                    result.add(new int[]{num1, num2});
                }
            }
        }
        return result;
    }
}
