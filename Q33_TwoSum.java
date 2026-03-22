/**
 * Question 33: Two Sum (Google-style)
 *
 * Classical problem: "Find two numbers in an array that sum to target."
 * Variation:        "Find two indices i, j such that nums[i] + nums[j] = target."
 *
 * Both use the same idea (complement / hash), but the variation must store
 * value -> index (HashMap) instead of just seen complements (HashSet).
 *
 * Problem:
 * - Input: int[] nums, int target
 * - Exactly one valid pair exists (no duplicates that would create ambiguity)
 *
 * Variation 1 - Return the two NUMBERS:
 *   - Use HashSet of complements (target - nums[i])
 *   - When nums[i] is in the set, the pair is (nums[i], target - nums[i])
 *
 * Variation 2 - Return the two INDICES (LeetCode / Google):
 *   - Use HashMap: value -> index
 *   - When (target - nums[i]) is in the map, return (map.get(complement), i)
 *   - Store nums[i] -> i only after checking (to avoid using same index twice)
 *
 * Time:  O(n)
 * Space: O(n) for hash structure
 *
 * Java version: Java 8+ compatible.
 */
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Q33_TwoSum {

    /**
     * Variation 1: Return the two NUMBERS that sum to target.
     * Uses HashSet of complements. We only need to know "have we seen the complement?"
     */
    public static int[] findTwoNumbers(int[] nums, int target) {
        Set<Integer> complements = new HashSet<>();
        for (int num : nums) {
            int complement = target - num;
            if (complements.contains(num)) {
                return new int[] { num, complement };
            }
            complements.add(complement);
        }
        return new int[0];
    }

    /**
     * Variation 2: Return the two INDICES such that nums[i] + nums[j] = target.
     * Uses HashMap: value -> index. We need the index of the complement.
     */
    public static int[] findTwoIndices(int[] nums, int target) {
        Map<Integer, Integer> valueToIndex = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int complement = target - nums[i];
            if (valueToIndex.containsKey(complement)) {
                return new int[] { valueToIndex.get(complement), i };
            }
            valueToIndex.put(nums[i], i);
        }
        return new int[0];
    }

    public static void main(String[] args) {
        int[] nums = { 2, 7, 11, 15 };
        int target = 9;

        System.out.println("Example: nums = [2, 7, 11, 15], target = 9");
        System.out.println();

        int[] numbers = findTwoNumbers(nums, target);
        System.out.println("Variation 1 - Return numbers:");
        System.out.println("  Output:   [" + numbers[0] + ", " + numbers[1] + "]");
        System.out.println("  Expected: [2, 7] or [7, 2]");
        System.out.println();

        int[] indices = findTwoIndices(nums, target);
        System.out.println("Variation 2 - Return indices:");
        System.out.println("  Output:   [" + indices[0] + ", " + indices[1] + "]");
        System.out.println("  Expected: [0, 1] (nums[0]+nums[1] = 2+7 = 9)");
        System.out.println();

        int[] nums2 = { 3, 2, 4 };
        int target2 = 6;
        int[] indices2 = findTwoIndices(nums2, target2);
        System.out.println("Example 2: nums = [3, 2, 4], target = 6");
        System.out.println("  Indices: [" + indices2[0] + ", " + indices2[1] + "]");
        System.out.println("  Expected: [1, 2]");
    }
}
