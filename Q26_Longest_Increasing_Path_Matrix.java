/**
 * Question 26: Longest Increasing Path in a Matrix
 * 
 * Russian: "Найти самую длинную возрастающую последовательность в матрице"
 * English: "Find the longest increasing path in a matrix"
 * 
 * Problem Statement:
 * You are given a matrix of n rows and m columns, filled with natural numbers.
 * You can move within the matrix from a cell only to an adjacent cell 
 * (up, down, left, or right). Diagonal movements and exiting the matrix 
 * boundaries are forbidden.
 * 
 * Your task is to find the longest increasing path in the matrix. A path is 
 * considered increasing if the values in the visited cells strictly increase 
 * from the beginning of the path to its end.
 * 
 * Constraints:
 * - 1 ≤ n, m ≤ 10^3 (matrix dimensions)
 * - Matrix elements are natural numbers ≤ 10^9
 * 
 * Example 1:
 * Input:
 *   2 3
 *   10 8 5
 *   10 5 4
 * Output: 4
 * Explanation: Path 4 -> 5 -> 8 -> 10 (starting from bottom-right)
 * 
 * Example 2:
 * Input:
 *   2 2
 *   1 1
 *   1 1
 * Output: 1
 * Explanation: All values are 1, so longest path is just one cell
 * 
 * Example 3:
 * Input:
 *   2 2
 *   10 9
 *   9 11
 * Output: 2
 * Explanation: Path 9 -> 10 or 9 -> 11 (length 2)
 * 
 * ANSWER: DFS with memoization (Dynamic Programming)
 * 
 * ALGORITHM EXPLANATION:
 * 
 * Key Insight:
 * - For each cell, find the longest increasing path starting from that cell
 * - Use memoization to avoid recomputing paths from the same cell
 * - The answer is the maximum path length across all starting cells
 * 
 * Optimal Approach (O(n*m) time, O(n*m) space):
 * 1. Create memoization table dp[i][j] = longest path from (i,j)
 * 2. For each cell (i, j):
 *    - If dp[i][j] already computed, return cached value
 *    - Otherwise, try all 4 directions (up, down, left, right)
 *    - For each valid neighbor with greater value, recursively compute path
 *    - Take maximum: 1 + longest path from neighbor
 * 3. Cache result in dp[i][j]
 * 4. Return maximum across all cells
 * 
 * Why this works:
 * - Memoization ensures each cell is computed only once
 * - DFS explores all possible paths from each starting cell
 * - Strictly increasing constraint ensures no cycles
 * - Time complexity: O(n*m) - each cell visited once
 * 
 * Alternative Approaches:
 * 
 * 1. Brute Force DFS (O(4^(n*m))):
 *    - Try all paths without memoization
 *    - Exponential time - too slow!
 * 
 * 2. Topological Sort + DP:
 *    - Build DAG where edge (i,j) -> (x,y) if matrix[x][y] > matrix[i][j]
 *    - Use topological sort to process in correct order
 *    - More complex, same time complexity
 * 
 * 3. Sorting + DP:
 *    - Sort all cells by value
 *    - Process in increasing order, update neighbors
 *    - O(n*m*log(n*m)) - slower than memoized DFS
 */

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.io.IOException;

public class Q26_Longest_Increasing_Path_Matrix {
    
    /**
     * Optimal Solution: DFS with Memoization
     * Time Complexity: O(n*m) - each cell visited once
     * Space Complexity: O(n*m) - memoization table
     */
    public static int solveOptimal(List<List<Integer>> matrix) {
        int n = matrix.size();
        int m = matrix.get(0).size();
        
        // Memoization: dp[i][j] = longest increasing path starting from (i, j)
        int[][] dp = new int[n][m];
        
        int maxPath = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxPath = Math.max(maxPath, dfs(matrix, i, j, dp));
            }
        }
        
        return maxPath;
    }
    
    /**
     * DFS with memoization to find longest increasing path from (i, j)
     */
    private static int dfs(List<List<Integer>> matrix, int i, int j, int[][] dp) {
        // If already computed, return cached result
        if (dp[i][j] != 0) {
            return dp[i][j];
        }
        
        int n = matrix.size();
        int m = matrix.get(0).size();
        int currentValue = matrix.get(i).get(j);
        
        // Base case: path of length 1 (the cell itself)
        int maxPath = 1;
        
        // Directions: up, down, left, right
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        // Try all 4 directions
        for (int d = 0; d < 4; d++) {
            int ni = i + dx[d];
            int nj = j + dy[d];
            
            // Check if valid and strictly increasing
            if (ni >= 0 && ni < n && nj >= 0 && nj < m && 
                matrix.get(ni).get(nj) > currentValue) {
                maxPath = Math.max(maxPath, 1 + dfs(matrix, ni, nj, dp));
            }
        }
        
        // Cache and return result
        dp[i][j] = maxPath;
        return maxPath;
    }
    
    /**
     * Brute Force DFS (for comparison - too slow!)
     * Time Complexity: O(4^(n*m)) - exponential
     */
    public static int solveBruteForce(List<List<Integer>> matrix) {
        int n = matrix.size();
        int m = matrix.get(0).size();
        
        int maxPath = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                maxPath = Math.max(maxPath, dfsBruteForce(matrix, i, j, -1));
            }
        }
        
        return maxPath;
    }
    
    private static int dfsBruteForce(List<List<Integer>> matrix, int i, int j, int prevValue) {
        int n = matrix.size();
        int m = matrix.get(0).size();
        
        // Check if valid and increasing
        if (i < 0 || i >= n || j < 0 || j >= m || matrix.get(i).get(j) <= prevValue) {
            return 0;
        }
        
        int currentValue = matrix.get(i).get(j);
        int maxPath = 1;
        
        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        
        for (int d = 0; d < 4; d++) {
            maxPath = Math.max(maxPath, 1 + dfsBruteForce(matrix, i + dx[d], j + dy[d], currentValue));
        }
        
        return maxPath;
    }
    
    public static void main(String[] args) throws IOException {
        System.out.println("=== Longest Increasing Path in a Matrix ===\n");
        
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
        System.out.println("Input:");
        System.out.println("  2 3");
        System.out.println("  10 8 5");
        System.out.println("  10 5 4");
        List<List<Integer>> matrix = Arrays.asList(
            Arrays.asList(10, 8, 5),
            Arrays.asList(10, 5, 4)
        );
        int result = solveOptimal(matrix);
        System.out.println("Output: " + result);
        System.out.println("Explanation: Path 4 -> 5 -> 8 -> 10 (length 4)");
        System.out.println();
    }
    
    private static void testExample2() {
        System.out.println("Example 2:");
        System.out.println("Input:");
        System.out.println("  2 2");
        System.out.println("  1 1");
        System.out.println("  1 1");
        List<List<Integer>> matrix = Arrays.asList(
            Arrays.asList(1, 1),
            Arrays.asList(1, 1)
        );
        int result = solveOptimal(matrix);
        System.out.println("Output: " + result);
        System.out.println("Explanation: All values are 1, longest path is 1 cell");
        System.out.println();
    }
    
    private static void testExample3() {
        System.out.println("Example 3:");
        System.out.println("Input:");
        System.out.println("  2 2");
        System.out.println("  10 9");
        System.out.println("  9 11");
        List<List<Integer>> matrix = Arrays.asList(
            Arrays.asList(10, 9),
            Arrays.asList(9, 11)
        );
        int result = solveOptimal(matrix);
        System.out.println("Output: " + result);
        System.out.println("Explanation: Path 9 -> 10 or 9 -> 11 (length 2)");
        System.out.println();
    }
    
    private static void compareApproaches() {
        List<List<Integer>> matrix = Arrays.asList(
            Arrays.asList(10, 8, 5),
            Arrays.asList(10, 5, 4)
        );
        
        System.out.println("Testing with Example 1 matrix:");
        System.out.println();
        
        // Optimal approach
        long start = System.nanoTime();
        int result1 = solveOptimal(matrix);
        long time1 = System.nanoTime() - start;
        System.out.println("1. Memoized DFS: " + result1 + " (Time: " + time1 + " ns)");
        
        // Brute force (only for small matrices)
        if (matrix.size() * matrix.get(0).size() <= 16) {
            start = System.nanoTime();
            int result2 = solveBruteForce(matrix);
            long time2 = System.nanoTime() - start;
            System.out.println("2. Brute Force DFS: " + result2 + " (Time: " + time2 + " ns)");
            System.out.println("   ⚠️  Only works for very small matrices (exponential time!)");
            System.out.println("   ✅ Results match: " + (result1 == result2));
        } else {
            System.out.println("2. Brute Force DFS: Skipped (too slow for larger matrices)");
        }
        
        System.out.println("\nFor n=m=1000 (1,000,000 cells):");
        System.out.println("  - Memoized DFS: O(n*m) = ~1,000,000 operations ✅");
        System.out.println("  - Brute Force: O(4^(n*m)) = exponential ❌");
    }
    
    private static void printInsights() {
        System.out.println("1. Key Insight:");
        System.out.println("   - For each cell, find longest increasing path starting there");
        System.out.println("   - Use memoization to avoid recomputation");
        System.out.println("   - Answer is maximum across all starting cells");
        System.out.println();
        
        System.out.println("2. Optimal Solution:");
        System.out.println("   - DFS with memoization (Dynamic Programming)");
        System.out.println("   - dp[i][j] = longest path from (i,j)");
        System.out.println("   - For each cell, try all 4 directions");
        System.out.println("   - Time: O(n*m), Space: O(n*m)");
        System.out.println();
        
        System.out.println("3. Why Memoization Works:");
        System.out.println("   - Each cell's longest path is independent of path taken");
        System.out.println("   - Once computed, can be reused");
        System.out.println("   - Reduces exponential time to polynomial");
        System.out.println();
        
        System.out.println("4. Why No Cycles:");
        System.out.println("   - Path must be strictly increasing");
        System.out.println("   - Can't return to a cell with same or smaller value");
        System.out.println("   - Ensures DAG structure (no cycles)");
    }
    
    /**
     * Template solution for competitive programming.
     * This is the solution that would be submitted.
     *
     * Java version: Requires Java 11+. Not compatible with Java below 11.
     * - .strip() requires Java 11+ (use .trim() for Java 8)
     */
    public static void mainTemplate(String[] args) throws IOException {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            String[] sizes = br.readLine().strip().split(" ");
            int n = Integer.parseInt(sizes[0]);
            int m = Integer.parseInt(sizes[1]);
            
            List<List<Integer>> matrix = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                List<Integer> row = Arrays.stream(br.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());
                matrix.add(row);
            }
            
            // Memoization table
            int[][] dp = new int[n][m];
            
            int maxPath = 0;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < m; j++) {
                    maxPath = Math.max(maxPath, dfs(matrix, i, j, dp));
                }
            }
            
            System.out.println(maxPath);
        }
    }
    
    // Note: The dfs method is already defined above in solveOptimal method
    // and can be reused. For template, you can inline it or use the same method.
}
