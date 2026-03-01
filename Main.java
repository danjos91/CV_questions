import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Java version: Requires Java 11+. Not compatible with Java below 11.
 * - .strip() requires Java 11+ (use .trim() for Java 8)
 */
public class Main{

    private static int getLongestIncreasingPath(List<List<Integer>> matrix) {
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

    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            List<List<Integer>> matrix = readMatrix(reader);

            System.out.println(getLongestIncreasingPath(matrix));
        } 
        
    }

    private static List<List<Integer>> readMatrix(BufferedReader reader) throws IOException {
        String[] sizes = reader.readLine().strip().split(" ");
        int n = Integer.parseInt(sizes[0]);
        List<List<Integer>> matrix = new ArrayList<List<Integer>>(n);
        for (int i = 0; i < n; i++) {
            matrix.add(readList(reader));
        }
        return matrix;
    }

    private static List<Integer> readList(BufferedReader reader) throws IOException {
        return Arrays.asList(reader.readLine().strip().split(" "))
            .stream()
            .map(token -> Integer.parseInt(token))
            .collect(Collectors.toList());
    }
}