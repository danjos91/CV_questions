/**
 * Question 58: Three subjects closest to load X (3Sum Closest)
 *
 * Russian: "Выбрать три предмета, сумма нагрузок которых ближе всего к X."
 * English: "Pick three values whose sum is as close as possible to target X."
 *
 * Constraints:
 * - 3 ≤ N ≤ 10_000
 * - |a_i| ≤ 10^9, |X| ≤ 10^9  → sums need long (3e9 > Integer.MAX_VALUE)
 *
 * Example:
 *   Input:  10 0
 *           5 4 3 2 1 1 2 3 4 -10
 *   Output: -10 4 5     (sum = -1, closest to 0)
 *
 * ALGORITHM NAME:
 * 3Sum Closest — sort + two pointers, O(N^2).
 *
 * Fix the smallest index i after sorting. Then search a pair (l, r) in
 * a[i+1..n) with two pointers: if the triple sum is below X, move l right;
 * if above, move r left. Track the triple with smallest |sum − X|.
 *
 * WHERE TO READ IN SKIENA, 3rd edition:
 *
 *   Ch. 4  Sorting — sorted order lets two pointers replace an extra loop.
 *   Catalog 17.2 Searching — pair-with-given-sum, lifted to three numbers.
 *   Same family as Q55 (two-list two-sum) and Q33 (hash two-sum).
 *
 * Java version: stdin template uses BufferedReader (Java 8+).
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Q58_Three_Sum_Closest {

    public static long[] solve(long[] a, long x) {
        int n = a.length;
        long[] b = a.clone();
        Arrays.sort(b);

        long bestDiff = Long.MAX_VALUE;
        long[] best = new long[3];

        for (int i = 0; i < n - 2; i++) {
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                long sum = b[i] + b[l] + b[r];
                long diff = Math.abs(sum - x);
                if (diff < bestDiff) {
                    bestDiff = diff;
                    best[0] = b[i];
                    best[1] = b[l];
                    best[2] = b[r];
                    if (diff == 0) {
                        return best;
                    }
                }
                if (sum < x) {
                    l++;
                } else {
                    r--;
                }
            }
        }
        return best;
    }

    private static int check(String name, long[] a, long x, long expectedSumDiff) {
        long[] t = solve(a, x);
        long sum = t[0] + t[1] + t[2];
        long diff = Math.abs(sum - x);
        boolean pass = diff == expectedSumDiff;
        System.out.println((pass ? "PASS" : "FAIL") + "  " + name
                + "  triple=" + t[0] + " " + t[1] + " " + t[2]
                + "  |sum-X|=" + diff + "  expected=" + expectedSumDiff);
        return pass ? 0 : 1;
    }

    public static void main(String[] args) {
        System.out.println("=== 3Sum closest to X ===\n");
        int fails = 0;

        fails += check("ex sample",
                new long[]{5, 4, 3, 2, 1, 1, 2, 3, 4, -10}, 0, 1);

        fails += check("T1 exact hit", new long[]{1, 2, 3, 100}, 6, 0);
        fails += check("T2 n=3 only one triple", new long[]{-5, 0, 4}, 100, 101);
        fails += check("T3 all negative", new long[]{-10, -8, -3, -1}, -12, 0);
        fails += check("T4 large values (need long)",
                new long[]{1_000_000_000L, 1_000_000_000L, 1_000_000_000L},
                3_000_000_000L, 0);
        fails += check("T5 overshoot vs undershoot, pick closer",
                new long[]{1, 2, 3, 10}, 7, 1); // 1+3+10=14 or 1+2+3=6 → |6-7|=1

        System.out.println(fails == 0 ? "\nAll extra tests passed." : "\nFailed: " + fails);
    }

    /**
     * Solution as submitted for competitive programming (stdin / stdout).
     */
    public static void mainTemplate(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());
        st = new StringTokenizer(br.readLine());
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }

        long[] t = solve(a, x);
        bw.write(t[0] + " " + t[1] + " " + t[2]);
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
    }
}
