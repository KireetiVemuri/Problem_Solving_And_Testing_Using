import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        Solution solution = new Solution();

        int[] nums = {0, 1, 0, 3, 12};
        System.out.println("Original: " + Arrays.toString(nums));

        solution.moveZeroes(nums);
        System.out.println("Modified: " + Arrays.toString(nums));
    }
}

class Solution {
    public void moveZeroes(int[] nums) {
        int lastNonZeroFoundAt = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[lastNonZeroFoundAt] = nums[i];
                lastNonZeroFoundAt++;
            }
        }

        while (lastNonZeroFoundAt < nums.length) {
            nums[lastNonZeroFoundAt] = 0;
            lastNonZeroFoundAt++;
        }
    }
}