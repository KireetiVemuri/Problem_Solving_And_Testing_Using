import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'stringSimilarity' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts STRING s as parameter.
     */

    public static int stringSimilarity(String s) {
    // Write your code here
    if (s == null || s.length() == 0) {
            return 0;
        }

        int n = s.length();
        int[] z = new int[n];
        z[0] = n; // Similarity of the string with itself is its full length

        int l = 0, r = 0;
        for (int i = 1; i < n; i++) {
            if (i <= r) {
                // Use previously computed Z-values within the window [l, r]
                z[i] = Math.min(r - i + 1, z[i - l]);
            }
            
            // Try to extend the match beyond the current window
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }
            
            // Update the match window [l, r] if extended past r
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }

        // Sum up all similarity values
        int totalSimilarity = 0;
        for (int score : z) {
            totalSimilarity += score;
        }

        return totalSimilarity;
    }

    }

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        for (int tItr = 0; tItr < t; tItr++) {
            String s = bufferedReader.readLine();

            int result = Result.stringSimilarity(s);

            bufferedWriter.write(String.valueOf(result));
            bufferedWriter.newLine();
        }

        bufferedReader.close();
        bufferedWriter.close();
    }
}
