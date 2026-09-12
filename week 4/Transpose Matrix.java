import java.util.Arrays;

public class Main {
    
    public static class Solution {
        public int[][] transpose(int[][] matrix) {
            int m = matrix.length;
            int n = matrix[0].length;
            
            int[][] result = new int[n][m];
            
            for (int i = 0; i < m; i++) {
                for (int j = 0; j < n; j++) {
                    result[j][i] = matrix[i][j];
                }
            }
            
            return result;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int[][] matrix = {
            {1, 2, 3},
            {4, 5, 6}
        };

        int[][] transposed = solution.transpose(matrix);

        System.out.println("Original Matrix:");
        for (int[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }

        System.out.println("\nTransposed Matrix:");
        for (int[] row : transposed) {
            System.out.println(Arrays.toString(row));
        }
    }
}