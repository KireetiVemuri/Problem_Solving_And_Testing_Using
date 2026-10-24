import java.util.*;
class Solution {
    public int strStr(String haystack, String needle) {
        if (needle.isEmpty()) return 0;
        
        int[] lps = computeLPSArray(needle);
        int i = 0; // index for haystack
        int j = 0; // index for needle
        
        while (i < haystack.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                i++;
                j++;
            }
            
            if (j == needle.length()) {
                return i - j; // Match found
            } else if (i < haystack.length() && haystack.charAt(i) != needle.charAt(j)) {
                if (j != 0) {
                    j = lps[j - 1];
                } else {
                    i++;
                }
            }
        }
        
        return -1;
    }

    private int[] computeLPSArray(String needle) {
        int[] lps = new int[needle.length()];
        int len = 0;
        int i = 1;
        
        while (i < needle.length()) {
            if (needle.charAt(i) == needle.charAt(len)) {
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
        
        return lps;
    }
}