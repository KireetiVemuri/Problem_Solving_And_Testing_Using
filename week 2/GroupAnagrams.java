import java.util.*;

public class GroupAnagrams {

    public static class Solution {
        public List<List<String>> groupAnagrams(String[] strs) {
            if (strs == null || strs.length == 0) return new ArrayList<>();

            Map<String, List<String>> map = new HashMap<>();

            for (String str : strs) {
                // Sort the characters of the string to create a key
                char[] charArray = str.toCharArray();
                Arrays.sort(charArray);
                String key = new String(charArray);

                // Add original string to the corresponding list in map
                map.putIfAbsent(key, new ArrayList<>());
                map.get(key).add(str);
            }

            return new ArrayList<>(map.values());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of strings: ");
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();

        String[] strs = new String[n];
        System.out.println("Enter " + n + " space-separated strings:");
        for (int i = 0; i < n; i++) {
            strs[i] = scanner.next();
        }

        Solution solution = new Solution();
        List<List<String>> result = solution.groupAnagrams(strs);

        // Output results
        System.out.println("\nGrouped Anagrams:");
        System.out.println(result);

        scanner.close();
    }
}