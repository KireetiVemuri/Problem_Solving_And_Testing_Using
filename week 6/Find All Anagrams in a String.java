import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        
        if (s == null || p == null || s.length() < p.length()) {
            return result;
        }

        int[] pCount = new int[26];
        int[] sCount = new int[26];

        // Fill initial frequencies for string p and the first window in s
        for (int i = 0; i < p.length(); i++) {
            pCount[p.charAt(i) - 'a']++;
            sCount[s.charAt(i) - 'a']++;
        }

        // Check the first window
        if (Arrays.equals(pCount, sCount)) {
            result.add(0);
        }

        // Slide the window across string s
        for (int i = p.length(); i < s.length(); i++) {
            // Add new character entering the window
            sCount[s.charAt(i) - 'a']++;
            // Remove old character leaving the window
            sCount[s.charAt(i - p.length()) - 'a']--;

            // Compare frequency counts
            if (Arrays.equals(pCount, sCount)) {
                result.add(i - p.length() + 1);
            }
        }

        return result;
    }
}