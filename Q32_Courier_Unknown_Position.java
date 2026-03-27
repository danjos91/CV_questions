/**
 * Question 32: Courier Unknown Position (Неизвестная позиция курьера)
 *
 * Russian: "По измерениям базовых станций x_i, d_i найти максимально возможную
 *          координату X курьера на прямой."
 * English: "Given base-station observations x_i, d_i, find the maximum possible
 *          courier coordinate X on a line."
 *
 * Problem Statement:
 * At some moment GPS stopped, so the exact courier position X is unknown.
 * For each observation i:
 *   - base station is at coordinate x_i
 *   - courier is at distance at most d_i from x_i
 * So each pair gives:
 *   |X - x_i| <= d_i
 *
 * Need to output the largest X satisfying all observations, or -1 if no such X.
 *
 * Key transformation:
 *   |X - x_i| <= d_i  <=>  X in [x_i - d_i, x_i + d_i]
 * We need the intersection of all intervals.
 *
 * If intersection is [L, R], where:
 *   L = max(x_i - d_i)
 *   R = min(x_i + d_i)
 * then:
 *   - if L > R => no valid X => answer -1
 *   - else maximum valid X is R
 *
 * Complexity:
 * Time:  O(n)
 * Space: O(1)
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q32_Courier_Unknown_Position {

    /**
     * Solves from in-memory observations.
     * Each observation is int[]{x, d}.
     */
    public static long solveMaxX(List<int[]> observations) {
        long left = Long.MIN_VALUE;
        long right = Long.MAX_VALUE;

        for (int[] obs : observations) {
            int x = obs[0];
            int d = obs[1];
            left = Math.max(left, (long) x - d);
            right = Math.min(right, (long) x + d);
        }

        return (left > right) ? -1 : right;
    }

    public static void main(String[] args) {
        System.out.println("=== Courier Unknown Position ===\n");

        List<int[]> ex1 = Arrays.asList(
                new int[]{1, 2},
                new int[]{3, 2}
        );
        System.out.println("Example 1:");
        System.out.println("Input: (1,2), (3,2)");
        System.out.println("Output:   " + solveMaxX(ex1));
        System.out.println("Expected: 3");
        System.out.println();

        List<int[]> ex2 = Arrays.asList(
                new int[]{2, 2},
                new int[]{6, 2},
                new int[]{8, 1}
        );
        System.out.println("Example 2:");
        System.out.println("Input: (2,2), (6,2), (8,1)");
        System.out.println("Output:   " + solveMaxX(ex2));
        System.out.println("Expected: -1");
        System.out.println();

        List<int[]> ex3 = new ArrayList<>();
        ex3.add(new int[]{100, 97});
        ex3.add(new int[]{115, 104});
        ex3.add(new int[]{97, 115});
        ex3.add(new int[]{111, 115});
        ex3.add(new int[]{107, 97});
        System.out.println("Example 3:");
        System.out.println("Input: (100,97), (115,104), (97,115), (111,115), (107,97)");
        System.out.println("Output:   " + solveMaxX(ex3));
        System.out.println("Expected: 197");
    }

    /**
     * Competitive-programming stdin/stdout template.
     */
    public static void mainTemplate(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        long left = Long.MIN_VALUE;
        long right = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {
            String[] parts = reader.readLine().split(" ");
            int x = Integer.parseInt(parts[0]);
            int d = Integer.parseInt(parts[1]);

            left = Math.max(left, (long) x - d);
            right = Math.min(right, (long) x + d);
        }

        writer.write(left > right ? "-1" : String.valueOf(right));

        reader.close();
        writer.close();
    }
}
