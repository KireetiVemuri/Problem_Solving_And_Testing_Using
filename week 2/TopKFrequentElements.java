import java.util.*;

public class TopKFrequentElements {

    public static class Solution {
        public int[] topKFrequent(int[] nums, int k) {
            // Step 1: Count frequency of each element
            Map<Integer, Integer> frequencyMap = new HashMap<>();
            for (int num : nums) {
                frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
            }

            // Step 2: Create buckets where index = frequency
            List<Integer>[] bucket = new List[nums.length + 1];
            for (int key : frequencyMap.keySet()) {
                int frequency = frequencyMap.get(key);
                if (bucket[frequency] == null) {
                    bucket[frequency] = new ArrayList<>();
                }
                bucket[frequency].add(key);
            }

            // Step 3: Gather the top k frequent elements starting from the highest frequency
            int[] result = new int[k];
            int counter = 0;

            for (int pos = bucket.length - 1; pos >= 0 && counter < k; pos--) {
                if (bucket[pos] != null) {
                    for (int num : bucket[pos]) {
                        result[counter++] = num;
                        if (counter == k) {
                            break;
                        }
                    }
                }
            }

            return result;
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

        System.out.print("Enter value of k: ");
        int k = scanner.nextInt();

        Solution solution = new Solution();
        int[] result = solution.topKFrequent(nums, k);

        // Output results
        System.out.println("\nTop " + k + " Frequent Elements: " + Arrays.toString(result));

        scanner.close();
    }
}