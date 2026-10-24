import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'circularPalindromes' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts STRING s as parameter.
     */

    public static List<Integer> circularPalindromes(String s) {
    // Write your code here
        int n = s.length();
        List<Integer> result = new ArrayList<Integer>();
        if (n == 0) return result;
        if (n == 1) {
            result.add(1);
            return result;
        }

        String doubleS = s + s;
        int totalLen = 2 * n;

        char[] t = new char[2 * totalLen + 3];
        t[0] = '^';
        int idx = 1;
        for (int i = 0; i < totalLen; i++) {
            t[idx++] = '#';
            t[idx++] = doubleS.charAt(i);
        }
        t[idx++] = '#';
        t[idx++] = '$';

        int m = t.length;
        int[] p = new int[m];
        int center = 0, right = 0;

        for (int i = 1; i < m - 1; i++) {
            int iMirror = 2 * center - i;
            if (right > i) {
                p[i] = Math.min(right - i, p[iMirror]);
            }
            while (t[i + 1 + p[i]] == t[i - 1 - p[i]]) {
                p[i]++;
            }
            if (i + p[i] > right) {
                center = i;
                right = i + p[i];
            }
        }

        SegmentTree st = new SegmentTree(n);

        for (int i = 1; i < m - 1; i++) {
            int radius = p[i];
            if (radius == 0) continue;

            int origStart = (i - 1 - radius) / 2;
            int origEnd = (i - 1 + radius) / 2 - 1;

            int len = origEnd - origStart + 1;
            if (len > n) {
                int diff = len - n;
                origStart += diff / 2;
                origEnd -= (diff + 1) / 2;
                len = n;
            }

            int minK = Math.max(0, origEnd - n + 1);
            int maxK = Math.min(n - 1, origStart);

            if (minK <= maxK) {
                st.updateConstant(1, 0, n - 1, minK, maxK, len);
            }

            if (origStart < n && origEnd >= origStart) {
                int lStart = Math.max(0, origStart + 1);
                int lEnd = Math.min(n - 1, origEnd);
                if (lStart <= lEnd) {
                    boolean isOdd = (i % 2 == 0);
                    int b = isOdd ? (2 * origEnd + 1) : (2 * origEnd + 2);
                    st.updateLinear(1, 0, n - 1, lStart, lEnd, -2, b);
                }
            }

            int rStart = Math.max(0, origStart);
            int rEnd = Math.min(n - 1, origEnd - n);
            if (rStart <= rEnd) {
                boolean isOdd = (i % 2 == 0);
                int b = isOdd ? (2 * (n - 1 - origStart) + 1) : (2 * (n - 1 - origStart) + 2);
                st.updateLinear(1, 0, n - 1, rStart, rEnd, 2, b);
            }
        }

        for (int k = 0; k < n; k++) {
            result.add(st.query(1, 0, n - 1, k));
        }

        return result;
    }

    static class SegmentTree {
        int[] constTree;
        int[] slopeTree;
        int[] interceptTree;

        public SegmentTree(int size) {
            constTree = new int[4 * size];
            slopeTree = new int[4 * size];
            interceptTree = new int[4 * size];
        }

        public void updateConstant(int node, int start, int end, int l, int r, int val) {
            if (r < start || end < l) return;
            if (l <= start && end <= r) {
                constTree[node] = Math.max(constTree[node], val);
                return;
            }
            int mid = (start + end) / 2;
            updateConstant(2 * node, start, mid, l, r, val);
            updateConstant(2 * node + 1, mid + 1, end, l, r, val);
        }

        public void updateLinear(int node, int start, int end, int l, int r, int m, int b) {
            if (r < start || end < l) return;
            if (l <= start && end <= r) {
                int currentValAtStart = slopeTree[node] * start + interceptTree[node];
                int newValAtStart = m * start + b;
                if (newValAtStart > currentValAtStart) {
                    slopeTree[node] = m;
                    interceptTree[node] = b;
                }
                return;
            }
            int mid = (start + end) / 2;
            updateLinear(2 * node, start, mid, l, r, m, b);
            updateLinear(2 * node + 1, mid + 1, end, l, r, m, b);
        }

        public int query(int node, int start, int end, int idx) {
            int currentConst = constTree[node];
            int currentLinear = slopeTree[node] * idx + interceptTree[node];
            int maxVal = Math.max(currentConst, currentLinear);

            if (start == end) return maxVal;

            int mid = (start + end) / 2;
            if (idx <= mid) {
                maxVal = Math.max(maxVal, query(2 * node, start, mid, idx));
            } else {
                maxVal = Math.max(maxVal, query(2 * node + 1, mid + 1, end, idx));
            }
            return maxVal;
        }
    }
}



public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int n = Integer.parseInt(bufferedReader.readLine().trim());

        String s = bufferedReader.readLine();

        List<Integer> result = Result.circularPalindromes(s);

        for (int i = 0; i < result.size(); i++) {
            bufferedWriter.write(String.valueOf(result.get(i)));

            if (i != result.size() - 1) {
                bufferedWriter.write("\n");
            }
        }

        bufferedWriter.newLine();

        bufferedReader.close();
        bufferedWriter.close();
    }
}
