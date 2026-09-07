import java.util.Scanner;

public class RemoveDuplicatesFromSortedArray {

    public static class Solution {
        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) return 0;

            int k = 1; // Index for placing unique elements
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] != nums[i - 1]) {
                    nums[k] = nums[i];
                    k++;
                }
            }
            return k;
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
        System.out.print("Enter " + n + " sorted space-separated integers: ");
        for (int i = 0; i < n; i++) {
            nums[i] = scanner.nextInt();
        }

        Solution solution = new Solution();
        int k = solution.removeDuplicates(nums);

        // Output results
        System.out.println("\nOutput k = " + k);
        System.out.print("Modified array (first " + k + " elements): [");
        for (int i = 0; i < k; i++) {
            System.out.print(nums[i] + (i < k - 1 ? ", " : ""));
        }
        System.out.println("]");

        scanner.close();
    }
}