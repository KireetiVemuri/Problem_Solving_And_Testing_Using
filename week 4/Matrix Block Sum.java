import java.util.*;

public class Solution {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length;
        int n = mat[0].length;
        
        // 1. Build a (m+1) x (n+1) prefix sum matrix to handle boundary conditions easily
        int[][] pref = new int[m + 1][n + 1];
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                pref[r + 1][c + 1] = mat[r][c] + pref[r][c + 1] + pref[r + 1][c] - pref[r][c];
            }
        }
        
        int[][] ans = new int[m][n];
        
        // 2. Query the sum for each cell block (r1, c1) to (r2, c2)
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // Determine bounding box indices (clamped to matrix dimensions)
                int r1 = Math.max(0, i - k);
                int c1 = Math.max(0, j - k);
                int r2 = Math.min(m - 1, i + k);
                int c2 = Math.min(n - 1, j + k);
                
                // Inclusion-Exclusion Principle using 1-indexed prefix sum array
                ans[i][j] = pref[r2 + 1][c2 + 1] - pref[r1][c2 + 1] - pref[r2 + 1][c1] + pref[r1][c1];
            }
        }
        
        return ans;
    }
}