import java.io.*;
import java.math.*;
import java.security.*;
import java.text.*;
import java.util.*;
import java.util.concurrent.*;
import java.util.regex.*;

class Result {

    /*
     * Complete the 'matrixRotation' function below.
     *
     * The function accepts following parameters:
     *  1. 2D_INTEGER_ARRAY matrix
     *  2. INTEGER r
     */

    public static void matrixRotation(List<List<Integer>> matrix, int r) {
    // Write your code here
    int m = matrix.size();
        int n = matrix.get(0).size();
        int layers = Math.min(m, n) / 2;

        for (int layer = 0; layer < layers; layer++) {
            List<Integer> ring = new ArrayList<>();

            int top = layer;
            int bottom = m - 1 - layer;
            int left = layer;
            int right = n - 1 - layer;

            // 1. Traverse Anti-Clockwise to collect elements into ring
            // Top row (left to right)
            for (int col = left; col <= right; col++) {
                ring.add(matrix.get(top).get(col));
            }
            // Right column (top + 1 to bottom)
            for (int row = top + 1; row <= bottom; row++) {
                ring.add(matrix.get(row).get(right));
            }
            // Bottom row (right - 1 down to left)
            for (int col = right - 1; col >= left; col--) {
                ring.add(matrix.get(bottom).get(col));
            }
            // Left column (bottom - 1 down to top + 1)
            for (int row = bottom - 1; row > top; row--) {
                ring.add(matrix.get(row).get(left));
            }

            // 2. Calculate actual rotation needed
            int ringSize = ring.size();
            int rot = r % ringSize;

            // 3. Place elements back in rotated anti-clockwise position
            int index = 0;

            // Top row
            for (int col = left; col <= right; col++) {
                matrix.get(top).set(col, ring.get((index + rot) % ringSize));
                index++;
            }
            // Right column
            for (int row = top + 1; row <= bottom; row++) {
                matrix.get(row).set(right, ring.get((index + rot) % ringSize));
                index++;
            }
            // Bottom row
            for (int col = right - 1; col >= left; col--) {
                matrix.get(bottom).set(col, ring.get((index + rot) % ringSize));
                index++;
            }
            // Left column
            for (int row = bottom - 1; row > top; row--) {
                matrix.get(row).set(left, ring.get((index + rot) % ringSize));
                index++;
            }
        }

        // Print the rotated matrix as space-separated integers per line
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sb.append(matrix.get(i).get(j)).append(j == n - 1 ? "" : " ");
            }
            sb.append("\n");
        }
        System.out.print(sb.toString());
    }

    }


public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));

        String[] firstMultipleInput = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

        int m = Integer.parseInt(firstMultipleInput[0]);

        int n = Integer.parseInt(firstMultipleInput[1]);

        int r = Integer.parseInt(firstMultipleInput[2]);

        List<List<Integer>> matrix = new ArrayList<>();

        for (int i = 0; i < m; i++) {
            String[] matrixRowTempItems = bufferedReader.readLine().replaceAll("\\s+$", "").split(" ");

            List<Integer> matrixRowItems = new ArrayList<>();

            for (int j = 0; j < n; j++) {
                int matrixItem = Integer.parseInt(matrixRowTempItems[j]);
                matrixRowItems.add(matrixItem);
            }

            matrix.add(matrixRowItems);
        }

        Result.matrixRotation(matrix, r);

        bufferedReader.close();
    }
}
