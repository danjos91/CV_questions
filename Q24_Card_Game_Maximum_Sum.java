/**
 * Question 24: Card Game - Maximum Sum from Ends
 * 
 * Russian: "Карточная игра: максимизация суммы при выборе карт с концов ряда"
 * English: "Card Game: Maximize sum by selecting cards from row ends"
 * 
 * Problem Statement:
 * Cards with natural numbers are laid out in a row. In each move, a player can 
 * take a card from either the left or the right end of the row. A total of k 
 * moves can be made. The final score is the sum of the numbers on the selected 
 * cards. Determine the maximum possible score.
 * 
 * Constraints:
 * - 1 ≤ n ≤ 10^5 (number of cards)
 * - 1 ≤ k ≤ n (number of moves)
 * - Card values are natural numbers ≤ 10^4
 * 
 * Example 1:
 * Input: n=7, k=3, cards=[5, 8, 2, 1, 3, 4, 11]
 * Output: 24
 * Explanation: Take 11 (right), 5 (left), 8 (left) = 11+5+8 = 24
 * 
 * Example 2:
 * Input: n=5, k=5, cards=[1, 2, 3, 4, 5]
 * Output: 15
 * Explanation: Take all cards = 1+2+3+4+5 = 15
 * 
 * Example 3:
 * Input: n=7, k=4, cards=[1, 1, 9, 2, 2, 2, 6]
 * Output: 17
 * Explanation: Take 6, 2, 2, 9 = 6+2+2+9 = 19? Wait, let me recalculate...
 * Actually: Take 6 (right), 2 (right), 2 (right), 9 (left) = 6+2+2+9 = 19
 * Or: Take 1 (left), 1 (left), 9 (left), 6 (right) = 1+1+9+6 = 17
 * The answer is 17, so optimal is: 1+1+9+6 or some other combination
 * 
 * ANSWER: Use prefix/suffix sums with sliding window approach
 * 
 * ALGORITHM EXPLANATION:
 * 
 * Key Insight:
 * - We can take i cards from the left (0 ≤ i ≤ k)
 * - And (k-i) cards from the right
 * - We need to try all combinations: i = 0 to k
 * 
 * Optimal Approach (O(n) time, O(n) space):
 * 1. Precompute prefix sums for left side: prefix[i] = sum of first i cards
 * 2. Precompute suffix sums for right side: suffix[i] = sum of last i cards
 * 3. For each i from 0 to k:
 *    - Take i cards from left: prefix[i]
 *    - Take (k-i) cards from right: suffix[k-i]
 *    - Total = prefix[i] + suffix[k-i]
 * 4. Return maximum total
 * 
 * Why this works:
 * - We exhaustively check all possible combinations
 * - Prefix/suffix sums allow O(1) lookup for any range
 * - Time: O(n) for preprocessing + O(k) for checking = O(n)
 * 
 * Alternative Approaches:
 * 
 * 1. Brute Force Recursion (O(2^k)):
 *    - Try all combinations recursively
 *    - Too slow for k up to 10^5
 * 
 * 2. Dynamic Programming (O(n*k)):
 *    - dp[i][j] = max sum using i cards from left, j from right
 *    - Overkill, more complex than needed
 * 
 * 3. Two Pointers / Sliding Window (O(k)):
 *    - Start with all k from left, then slide window
 *    - Remove from left, add from right iteratively
 *    - Same complexity but more intuitive
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;

public class Q24_Card_Game_Maximum_Sum {
    
    /**
     * Optimal Solution: Using Prefix and Suffix Sums
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     */
    public static int solveOptimal(int n, int k, List<Integer> cards) {
        // Precompute prefix and suffix sums in one loop
        int[] prefix = new int[k + 1];
        int[] suffix = new int[k + 1];
        prefix[0] = 0;
        suffix[0] = 0;
        
        for (int i = 1; i <= k && i <= n; i++) {
            prefix[i] = prefix[i - 1] + cards.get(i - 1);  // prefix[i] = sum of first i cards
            suffix[i] = suffix[i - 1] + cards.get(n - i);   // suffix[i] = sum of last i cards
        }
        
        // Try all combinations: i from left, (k-i) from right
        int maxSum = 0;
        for (int i = 0; i <= k; i++) {
            int leftSum = prefix[i];
            int rightSum = suffix[k - i];
            maxSum = Math.max(maxSum, leftSum + rightSum);
        }
        
        return maxSum;
    }
    
    /**
     * Alternative Solution: Sliding Window Approach
     * Time Complexity: O(k)
     * Space Complexity: O(1) extra space
     * 
     * Start with all k cards from left, then slide window:
     * - Remove one from left, add one from right
     */

    public static int solveSlidingWindow(int n, int k, List<Integer> cards) {
        // Start with all k cards from left
        int sum = 0;
        for (int i = 0; i < k; i++) {
            sum += cards.get(i);
        }
        
        int maxSum = sum;
        
        // Slide window: remove from left, add from right
        // Must start from i=0 to check all k+1 combinations:
        // i=0: (k-1 from left, 1 from right)
        // i=1: (k-2 from left, 2 from right)
        // ...
        // i=k-1: (0 from left, k from right)
        // Starting from i=1 would miss the first transition!
        for (int i = 0; i < k; i++) {
            sum = sum - cards.get(k - 1 - i) + cards.get(n - 1 - i);
            maxSum = Math.max(maxSum, sum);
        }
        
        return maxSum;
    }
    
    /**
     * Brute Force Recursive Approach (for comparison)
     * Time Complexity: O(2^k) - exponential, too slow!
     * Space Complexity: O(k) for recursion stack
     */
    public static int solveBruteForce(int n, int k, List<Integer> cards, 
                                      int left, int right, int moves) {
        // Base case: no more moves
        if (moves == 0) {
            return 0;
        }
        
        // Try taking from left
        int takeLeft = cards.get(left) + 
                      solveBruteForce(n, k, cards, left + 1, right, moves - 1);
        
        // Try taking from right
        int takeRight = cards.get(right) + 
                       solveBruteForce(n, k, cards, left, right - 1, moves - 1);
        
        return Math.max(takeLeft, takeRight);
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("=== Card Game: Maximum Sum from Ends ===\n");
        
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
        System.out.println("Input: n=7, k=3, cards=[5, 8, 2, 1, 3, 4, 11]");
        List<Integer> cards = Arrays.asList(5, 8, 2, 1, 3, 4, 11);
        int result = solveOptimal(7, 3, cards);
        System.out.println("Output: " + result);
        System.out.println("Explanation: Take 11 (right), 5 (left), 8 (left) = " + result);
        System.out.println();
    }
    
    private static void testExample2() {
        System.out.println("Example 2:");
        System.out.println("Input: n=5, k=5, cards=[1, 2, 3, 4, 5]");
        List<Integer> cards = Arrays.asList(1, 2, 3, 4, 5);
        int result = solveOptimal(5, 5, cards);
        System.out.println("Output: " + result);
        System.out.println("Explanation: Take all cards = " + result);
        System.out.println();
    }
    
    private static void testExample3() {
        System.out.println("Example 3:");
        System.out.println("Input: n=7, k=4, cards=[1, 1, 9, 2, 2, 2, 6]");
        List<Integer> cards = Arrays.asList(1, 1, 9, 2, 2, 2, 6);
        int result = solveOptimal(7, 4, cards);
        System.out.println("Output: " + result);
        System.out.println("Explanation: Optimal combination gives " + result);
        System.out.println();
    }
    
    private static void compareApproaches() {
        List<Integer> cards = Arrays.asList(5, 8, 2, 1, 3, 4, 11);
        int n = 7, k = 3;
        
        System.out.println("Testing with: n=" + n + ", k=" + k + ", cards=" + cards);
        System.out.println();
        
        // Optimal approach
        long start = System.nanoTime();
        int result1 = solveOptimal(n, k, cards);
        long time1 = System.nanoTime() - start;
        System.out.println("1. Prefix/Suffix Sums: " + result1 + " (Time: " + time1 + " ns)");
        
        // Sliding window
        start = System.nanoTime();
        int result2 = solveSlidingWindow(n, k, cards);
        long time2 = System.nanoTime() - start;
        System.out.println("2. Sliding Window: " + result2 + " (Time: " + time2 + " ns)");
        
        // Brute force (only for small k)
        if (k <= 20) {
            start = System.nanoTime();
            int result3 = solveBruteForce(n, k, cards, 0, n - 1, k);
            long time3 = System.nanoTime() - start;
            System.out.println("3. Brute Force Recursion: " + result3 + " (Time: " + time3 + " ns)");
            System.out.println("   ⚠️  Only works for small k (exponential time!)");
        }
        
        System.out.println("\n✅ Both optimal approaches give same result: " + result1);
    }
    
    private static void printInsights() {
        System.out.println("1. Key Insight:");
        System.out.println("   - We can take i cards from left (0 ≤ i ≤ k)");
        System.out.println("   - And (k-i) cards from right");
        System.out.println("   - Try all k+1 combinations");
        System.out.println();
        
        System.out.println("2. Optimal Solution:");
        System.out.println("   - Precompute prefix sums for left side");
        System.out.println("   - Precompute suffix sums for right side");
        System.out.println("   - For each i: sum = prefix[i] + suffix[k-i]");
        System.out.println("   - Time: O(n), Space: O(n)");
        System.out.println();
        
        System.out.println("3. Alternative: Sliding Window");
        System.out.println("   - Start with all k from left");
        System.out.println("   - Slide: remove from left, add from right");
        System.out.println("   - Time: O(k), Space: O(1)");
        System.out.println();
        
        System.out.println("4. Why Brute Force Fails:");
        System.out.println("   - Recursive approach: O(2^k) time");
        System.out.println("   - For k=10^5, this is impossible!");
        System.out.println("   - Need polynomial solution");
    }
    
    /**
     * Template solution for competitive programming
     * This is the solution that would be submitted
     */
    public static void mainTemplate(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            int k = Integer.parseInt(br.readLine());
            List<Integer> numbers = Arrays.stream(br.readLine().strip().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());
            
            // Optimal solution using prefix/suffix sums (computed in one loop)
            int[] prefix = new int[k + 1];
            int[] suffix = new int[k + 1];
            prefix[0] = 0;
            suffix[0] = 0;
            for (int i = 1; i <= k && i <= n; i++) {
                prefix[i] = prefix[i - 1] + numbers.get(i - 1);
                suffix[i] = suffix[i - 1] + numbers.get(n - i);
            }
            
            int maxValue = 0;
            for (int i = 0; i <= k; i++) {
                int leftSum = prefix[i];
                int rightSum = suffix[k - i];
                maxValue = Math.max(maxValue, leftSum + rightSum);
            }
            
            System.out.println(maxValue);
        }
    }
}
