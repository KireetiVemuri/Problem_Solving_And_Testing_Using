import java.util.Set;

public class Solution {
    public boolean halvesAreAlike(String s) {
        // Set of all uppercase and lowercase vowels for O(1) lookup
        Set<Character> vowels = Set.of('a', 'e', 'i', 'o', 'u', 'A', 'E', 'I', 'O', 'U');
        
        int n = s.length();
        int mid = n / 2;
        int vowelCount = 0;

        // Count vowels in the first half (a) and subtract for the second half (b)
        for (int i = 0; i < mid; i++) {
            if (vowels.contains(s.charAt(i))) {
                vowelCount++;
            }
            if (vowels.contains(s.charAt(i + mid))) {
                vowelCount--;
            }
        }

        // If count returns to 0, both halves have the same number of vowels
        return vowelCount == 0;
    }

    public static void main(String[] args) {
        Solution sol = new Solution();

        // Example 1
        String s1 = "book";
        System.out.println("Input: " + s1 + " -> Output: " + sol.halvesAreAlike(s1)); // Output: true

        // Example 2
        String s2 = "textbook";
        System.out.println("Input: " + s2 + " -> Output: " + sol.halvesAreAlike(s2)); // Output: false
    }
}