import java.util.Scanner;

public class MaximumSubarray {

    public static class Solution {
        public int maxSubArray(int[] nums) {
            int maxSum = nums[0];
            int currentSum = nums[0];

            for (int i = 1; i < nums.length; i++) {
                // Decide whether to add current element to existing subarray sum
                // or start a new subarray from current element
                currentSum = Math.max(nums[i], currentSum + nums[i]);
                maxSum = Math.max(maxSum, currentSum);
            }

            return maxSum;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter number of elements in array: ");
        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int n = scanner.nextInt();

        int[] nums = new int[n];
        System.out.print("Enter " + n + " space-separated integers: ");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        Solution solution = new Solution();
        int result = solution.maxSubArray(nums);

        // Output result
        System.out.println("\nMaximum Subarray Sum = " + result);

        scanner.close();
    }
}