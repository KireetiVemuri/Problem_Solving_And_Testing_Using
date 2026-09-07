import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        Deque<Integer> deque = new ArrayDeque<>();
        Set<Integer> set = new HashSet<>();

        int n = in.nextInt(); // Total number of elements
        int m = in.nextInt(); // Subarray size

        int maxUnique = 0;

        for (int i = 0; i < n; i++) {
            int num = in.nextInt();

            // Add new element to deque and set
            deque.addLast(num);
            set.add(num);

            // Once window reaches size m
            if (deque.size() == m) {
                // Update max unique count
                if (set.size() > maxUnique) {
                    maxUnique = set.size();
                }

                // Optimization: If we found m unique elements, we reached the maximum possible
                if (maxUnique == m) {
                    break;
                }

                // Slide the window: remove the first element
                int first = deque.removeFirst();
                if (!deque.contains(first)) {
                    set.remove(first);
                }
            }
        }

        System.out.println(maxUnique);
        in.close();
    }
}