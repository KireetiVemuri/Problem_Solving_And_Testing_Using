import java.util.*;
import java.lang.*;
import java.io.*;

class Codechef
{
	public static void main (String[] args) throws java.lang.Exception
	{
		        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        
        while (T-- > 0) {
            String s = sc.next();
            int n = s.length();
            
            // Split into two halves
            String left, right;
            if (n % 2 == 0) {
                left = s.substring(0, n/2);
                right = s.substring(n/2);
            } else {
                left = s.substring(0, n/2);
                right = s.substring(n/2 + 1);
            }
            
            // Count character frequencies
            int[] freqLeft = new int[26];
            int[] freqRight = new int[26];
            
            for (char c : left.toCharArray()) {
                freqLeft[c - 'a']++;
            }
            for (char c : right.toCharArray()) {
                freqRight[c - 'a']++;
            }
            
            // Compare frequencies
            if (Arrays.equals(freqLeft, freqRight)) {
                System.out.println("YES");
            } else {
                System.out.println("NO");
            }
        

	}
}
}
