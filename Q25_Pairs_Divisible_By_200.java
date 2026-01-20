/**
 * Question 25: Count Pairs with Difference Divisible by 200
 * 
 * Russian: "Найти количество пар элементов (a_i, a_j) таких, что |a_i - a_j| % 200 == 0 и i < j"
 * English: "Count pairs of elements (a_i, a_j) such that |a_i - a_j| % 200 == 0 and i < j"
 * 
 * Problem Statement:
 * You are given an array of natural numbers a_i. Find the number of pairs of 
 * elements (a_i, a_j) such that |a_i - a_j| % 200 == 0 and i < j.
 * 
 * Constraints:
 * - 1 ≤ n ≤ 200,000 (array size)
 * - 1 ≤ a_i ≤ 10^9 (array elements)
 * 
 * Example 1:
 * Input: n=5, array=[203, 404, 204, 200, 403]
 * Output: 2
 * Explanation:
 *   - (203, 403): |203 - 403| = 200, 200 % 200 == 0 ✓
 *   - (404, 204): |404 - 204| = 200, 200 % 200 == 0 ✓
 *   - Total: 2 pairs
 * 
 * Example 2:
 * Input: n=1, array=[1000000]
 * Output: 0
 * Explanation: Only one element, no pairs possible
 * 
 * Example 3:
 * Input: n=3, array=[2022, 2020, 2021]
 * Output: 0
 * Explanation: No pairs satisfy |a_i - a_j| % 200 == 0
 * 
 * ANSWER: Use frequency counting by remainder modulo 200
 * 
 * ALGORITHM EXPLANATION:
 * 
 * Key Insight:
 * |a_i - a_j| % 200 == 0  ⟺  a_i % 200 == a_j % 200
 * 
 * If two numbers have the same remainder when divided by 200, their difference 
 * is divisible by 200. Conversely, if their difference is divisible by 200, 
 * they must have the same remainder.
 * 
 * Optimal Approach (O(n) time, O(200) = O(1) space):
 * 1. Create frequency array of size 200 to count remainders
 * 2. For each element a_i, calculate r = a_i % 200 and increment count[r]
 * 3. For each remainder r with count C:
 *    - Number of pairs with same remainder = C * (C - 1) / 2
 *    - This is "C choose 2" (combinations formula)
 * 4. Sum all pair counts
 * 
 * Why this works:
 * - Elements with same remainder can form pairs
 * - For C elements with remainder r, we can form C*(C-1)/2 pairs
 * - We only count pairs where i < j (automatically handled by combinations)
 * 
 * Alternative Approaches:
 * 
 * 1. Brute Force (O(n²)):
 *    - Check all pairs (i, j) where i < j
 *    - For each pair, check if |a_i - a_j| % 200 == 0
 *    - Too slow for n = 200,000 (would be ~40 billion operations!)
 * 
 * 2. Sorting + Grouping (O(n log n)):
 *    - Sort by remainder, then count pairs in each group
 *    - Works but slower than optimal O(n) approach
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;

public class Q25_Pairs_Divisible_By_200 {
    
    /**
     * Optimal Solution: Frequency counting by remainder
     * Time Complexity: O(n)
     * Space Complexity: O(200) = O(1)
     */
    public static long solveOptimal(int n, List<Integer> array) {
        // Frequency array for remainders modulo 200
        // remainder can be 0 to 199
        long[] remainderCount = new long[200];
        
        // Count frequency of each remainder
        for (int num : array) {
            int remainder = num % 200;
            remainderCount[remainder]++;
        }
        
        // Count pairs: for each remainder r with count C,
        // number of pairs = C * (C - 1) / 2
        long totalPairs = 0;
        for (int r = 0; r < 200; r++) {
            long count = remainderCount[r];
            if (count > 1) {
                // C choose 2 = C * (C - 1) / 2
                totalPairs += count * (count - 1) / 2;
            }
        }
        
        return totalPairs;
    }
    
    /**
     * Brute Force Approach (for comparison - too slow for large n)
     * Time Complexity: O(n²)
     * Space Complexity: O(1)
     */
    public static long solveBruteForce(int n, List<Integer> array) {
        long count = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                long diff = Math.abs((long)array.get(i) - array.get(j));
                if (diff % 200 == 0) {
                    count++;
                }
            }
        }
        return count;
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("=== Count Pairs with Difference Divisible by 200 ===\n");
        
        // Test with provided examples
        testExample1();
        testExample2();
        testExample3();
        
        System.out.println("\n=== Algorithm Comparison ===\n");
        compareApproaches();
        
        System.out.println("\n=== Key Insights ===\n");
        printInsights();
    }
    
    private static void testExample1() {
        System.out.println("Example 1:");
        System.out.println("Input: n=5, array=[203, 404, 204, 200, 403]");
        List<Integer> array = Arrays.asList(203, 404, 204, 200, 403);
        long result = solveOptimal(5, array);
        System.out.println("Output: " + result);
        System.out.println("Explanation:");
        System.out.println("  - 203 % 200 = 3, 403 % 200 = 3 → pair (203, 403)");
        System.out.println("  - 404 % 200 = 4, 204 % 200 = 4 → pair (404, 204)");
        System.out.println("  - 200 % 200 = 0 → no pair (only one element with remainder 0)");
        System.out.println();
    }
    
    private static void testExample2() {
        System.out.println("Example 2:");
        System.out.println("Input: n=1, array=[1000000]");
        List<Integer> array = Arrays.asList(1000000);
        long result = solveOptimal(1, array);
        System.out.println("Output: " + result);
        System.out.println("Explanation: Only one element, no pairs possible");
        System.out.println();
    }
    
    private static void testExample3() {
        System.out.println("Example 3:");
        System.out.println("Input: n=3, array=[2022, 2020, 2021]");
        List<Integer> array = Arrays.asList(2022, 2020, 2021);
        long result = solveOptimal(3, array);
        System.out.println("Output: " + result);
        System.out.println("Explanation:");
        System.out.println("  - 2022 % 200 = 22");
        System.out.println("  - 2020 % 200 = 20");
        System.out.println("  - 2021 % 200 = 21");
        System.out.println("  - All different remainders → no pairs");
        System.out.println();
    }
    
    private static void compareApproaches() {
        List<Integer> array = Arrays.asList(203, 404, 204, 200, 403);
        int n = 5;
        
        System.out.println("Testing with: n=" + n + ", array=" + array);
        System.out.println();
        
        // Optimal approach
        long start = System.nanoTime();
        long result1 = solveOptimal(n, array);
        long time1 = System.nanoTime() - start;
        System.out.println("1. Optimal (Frequency Counting): " + result1 + " (Time: " + time1 + " ns)");
        
        // Brute force (only for small n)
        if (n <= 1000) {
            start = System.nanoTime();
            long result2 = solveBruteForce(n, array);
            long time2 = System.nanoTime() - start;
            System.out.println("2. Brute Force: " + result2 + " (Time: " + time2 + " ns)");
            System.out.println("   ⚠️  Only works for small n (O(n²) time!)");
            System.out.println("   ✅ Results match: " + (result1 == result2));
        } else {
            System.out.println("2. Brute Force: Skipped (too slow for n=" + n + ")");
        }
        
        System.out.println("\nFor n=200,000:");
        System.out.println("  - Optimal: O(n) = ~200,000 operations");
        System.out.println("  - Brute Force: O(n²) = ~40,000,000,000 operations ❌");
    }
    
    private static void printInsights() {
        System.out.println("1. Key Mathematical Insight:");
        System.out.println("   |a_i - a_j| % 200 == 0  ⟺  a_i % 200 == a_j % 200");
        System.out.println("   Two numbers have difference divisible by 200");
        System.out.println("   if and only if they have the same remainder modulo 200");
        System.out.println();
        
        System.out.println("2. Optimal Solution:");
        System.out.println("   - Count frequency of each remainder (0 to 199)");
        System.out.println("   - For remainder r with count C:");
        System.out.println("     Number of pairs = C * (C - 1) / 2");
        System.out.println("   - Time: O(n), Space: O(200) = O(1)");
        System.out.println();
        
        System.out.println("3. Why Combinations Formula:");
        System.out.println("   - C elements with same remainder");
        System.out.println("   - We need pairs (i, j) where i < j");
        System.out.println("   - This is exactly 'C choose 2' = C*(C-1)/2");
        System.out.println();
        
        System.out.println("4. Why Brute Force Fails:");
        System.out.println("   - For n=200,000, brute force needs ~40 billion operations");
        System.out.println("   - Optimal solution needs only ~200,000 operations");
        System.out.println("   - Speedup: ~200,000x faster!");
        System.out.println();
        
        // Detailed explanation of the formula
        explainCombinationsFormula();
    }
    
    /**
     * Detailed explanation of why C * (C - 1) / 2 counts pairs
     */
    private static void explainCombinationsFormula() {
        System.out.println("=== Detailed Explanation: C * (C - 1) / 2 ===");
        System.out.println();
        
        System.out.println("PROBLEM: If we have C elements with the same remainder,");
        System.out.println("how many pairs (i, j) can we form where i < j?");
        System.out.println();
        
        System.out.println("METHOD 1: Visual Counting");
        System.out.println("-------------------------");
        System.out.println("Example: C = 4 elements [A, B, C, D]");
        System.out.println();
        System.out.println("All possible pairs where first < second:");
        System.out.println("  A-B, A-C, A-D  (3 pairs starting with A)");
        System.out.println("  B-C, B-D       (2 pairs starting with B)");
        System.out.println("  C-D             (1 pair starting with C)");
        System.out.println("  Total: 3 + 2 + 1 = 6 pairs");
        System.out.println();
        System.out.println("Pattern: For C elements:");
        System.out.println("  - First element pairs with (C-1) others");
        System.out.println("  - Second element pairs with (C-2) others");
        System.out.println("  - ...");
        System.out.println("  - Last element pairs with 0 others");
        System.out.println();
        System.out.println("Sum = (C-1) + (C-2) + ... + 1 + 0");
        System.out.println("    = 1 + 2 + ... + (C-1)");
        System.out.println("    = (C-1) * C / 2  (sum of first n-1 natural numbers)");
        System.out.println("    = C * (C - 1) / 2");
        System.out.println();
        
        System.out.println("METHOD 2: Permutations Divided by 2");
        System.out.println("------------------------------------");
        System.out.println("To form a pair, we choose 2 elements from C:");
        System.out.println("  - First choice: C options");
        System.out.println("  - Second choice: (C-1) options");
        System.out.println("  - Total permutations: C * (C-1)");
        System.out.println();
        System.out.println("But pair (A,B) = pair (B,A), so we counted each pair twice!");
        System.out.println("Therefore: pairs = C * (C-1) / 2");
        System.out.println();
        
        System.out.println("METHOD 3: Combinations Formula");
        System.out.println("-------------------------------");
        System.out.println("This is exactly 'C choose 2' (combinations):");
        System.out.println("  C(C,2) = C! / (2! * (C-2)!)");
        System.out.println("         = C * (C-1) * (C-2)! / (2 * (C-2)!)");
        System.out.println("         = C * (C-1) / 2");
        System.out.println();
        
        System.out.println("EXAMPLES:");
        System.out.println("---------");
        System.out.println("C=2: 2*(2-1)/2 = 2/2 = 1 pair     → (A,B)");
        System.out.println("C=3: 3*(3-1)/2 = 6/2 = 3 pairs   → (A,B), (A,C), (B,C)");
        System.out.println("C=4: 4*(4-1)/2 = 12/2 = 6 pairs  → (A,B), (A,C), (A,D), (B,C), (B,D), (C,D)");
        System.out.println("C=5: 5*(5-1)/2 = 20/2 = 10 pairs");
        System.out.println();
        
        System.out.println("IN OUR PROBLEM:");
        System.out.println("---------------");
        System.out.println("Example: remainder 3 appears 2 times (203, 403)");
        System.out.println("  C = 2");
        System.out.println("  Pairs = 2 * (2-1) / 2 = 2 / 2 = 1 pair");
        System.out.println("  This pair: (203, 403) ✓");
        System.out.println();
        System.out.println("Example: remainder 4 appears 2 times (404, 204)");
        System.out.println("  C = 2");
        System.out.println("  Pairs = 2 * (2-1) / 2 = 1 pair");
        System.out.println("  This pair: (404, 204) ✓");
        System.out.println();
        System.out.println("Total pairs = 1 + 1 = 2 ✓");
    }
    
    /**
     * Template solution for competitive programming
     * This is the solution that would be submitted
     */
    public static void mainTemplate(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            Integer.parseInt(br.readLine()); // Read n (array size) - not needed but part of input format
            List<Integer> numbers = Arrays.stream(br.readLine().strip().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
            
            // Frequency array for remainders modulo 200
            long[] remainderCount = new long[200];
            
            // Count frequency of each remainder
            for (int num : numbers) {
                int remainder = num % 200;
                remainderCount[remainder]++;
            }
            
            // Count pairs: for each remainder r with count C,
            // number of pairs = C * (C - 1) / 2
            long totalPairs = 0;
            for (int r = 0; r < 200; r++) {
                long count = remainderCount[r];
                if (count > 1) {
                    totalPairs += count * (count - 1) / 2;
                }
            }
            
            System.out.println(totalPairs);
        }
    }
}
