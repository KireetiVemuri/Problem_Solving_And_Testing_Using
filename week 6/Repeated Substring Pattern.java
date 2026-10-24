import java.util.*;

public class Solution {
    public boolean repeatedSubstringPattern(String s) {
        int n = s.length();
        int[] lps = new int[n];
        
        // Build LPS (Longest Prefix Suffix) array
        int len = 0;
        int i = 1;
        while (i < n) {
            if (s.charAt(i) == s.charAt(len)) {
                len++;
                lps[i] = len;
                i++;
            } else {
                if (len != 0) {
                    len = lps[len - 1];
                } else {
                    lps[i] = 0;
                    i++;
                }
            }
        }
        
        int longestPatternLen = lps[n - 1];
        
        // Check if the longest proper prefix-suffix leaves a pattern that cleanly divides s
        return longestPatternLen > 0 && n % (n - longestPatternLen) == 0;
    }

    public static void main(String[] args) {
        Solution solver = new Solution();

        // Test cases
        String test1 = "abab";
        String test2 = "aba";
        String test3 = "abcabcabcabc";

        System.out.println("Pattern 'abab': " + solver.repeatedSubstringPattern(test1)); // Expected: true
        System.out.println("Pattern 'aba': " + solver.repeatedSubstringPattern(test2));  // Expected: false
        System.out.println("Pattern 'abcabcabcabc': " + solver.repeatedSubstringPattern(test3)); // Expected: true
    }
}