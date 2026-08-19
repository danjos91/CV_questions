/**
 * Question 52: Fastest kilometer (starting minute)
 *
 * Russian: "За какое минимальное число минут Виталик пробежал хотя бы 1 км,
 *          и с какой минуты начался этот отрезок?"
 * English: "Find the starting minute of the shortest interval in which
 *          Vitalik ran at least 1000 meters."
 *
 * Problem Statement:
 * A sensor records meters run in each minute. Find a contiguous interval
 * whose sum is ≥ 1000, then output its 1-based starting minute.
 *
 * Tie-breakers (in order):
 * 1. Shortest length (fewest minutes).
 * 2. If tied, largest total distance.
 * 3. If tied, earliest start.
 *
 * Constraints:
 * - n ≤ 10^6  (one line of integers)
 * Time 2 s / Memory 256 MB
 *
 * Example:
 *   Input:  100 350 450 300 450
 *   Output: 3
 *   [2..4] = 1100 m in 3 minutes, [3..5] = 1200 m in 3 minutes.
 *   Same length, larger distance → start at minute 3.
 *
 * ALGORITHM NAME:
 * Sliding window (two pointers). Also called the “caterpillar” / “inchworm”
 * method in contest programming.
 *
 * Problem class: shortest contiguous subarray with sum ≥ S
 *   (classic twin: LeetCode 209 “Minimum Size Subarray Sum”).
 * This is a typical linear-scan pattern whenever:
 *   - the answer is a contiguous interval (not an arbitrary subset), and
 *   - all values are non-negative (meters per minute ≥ 0).
 * Then the window’s left end only ever moves forward, so the scan is O(n).
 *
 * Why two pointers work here (monotonicity):
 * If a[left..right] already sums to ≥ 1000, any extension to the left is
 * longer, so it cannot beat the “minimum time” criterion. Because every
 * a[i] ≥ 0, shrinking left never increases the sum, so you never need to
 * move left backwards.
 *
 * If values could be negative, this algorithm is WRONG; you would need
 * prefix sums + a data structure, or Kadane-style DP — not this scan.
 *
 * WHERE TO READ IN SKIENA, 3rd edition
 * (“The Algorithm Design Manual”, Steven S. Skiena):
 *
 * Skiena does not have a chapter titled “Sliding Window”. Treat this as
 * a modeling + linear-scan problem, then use the catalog for near-misses.
 *
 *   Ch. 1  Introduction to Algorithm Design — especially 1.5 Modeling.
 *          The key modeling choice: a *contiguous* interval of minutes,
 *          not “any subset of minutes”. That is why this is O(n) instead
 *          of knapsack / subset-sum.
 *
 *   Ch. 2  Algorithm Analysis — 2.2 Big-Oh, and 2.5.3 String Pattern
 *          Matching. The naive scan of a window over a sequence is the
 *          same shape as this two-index walk (each index moves at most n
 *          times → O(n)).
 *
 *   Ch. 10 Dynamic Programming — 10.5 Unordered Partition / Subset Sum
 *          and 10.7 Ordered Partition. Read these to see the *wrong*
 *          model: subset-sum would apply if the minutes did not have to
 *          be consecutive. Ordered partition is the contiguous cousin,
 *          but full DP is overkill once all weights are ≥ 0.
 *
 * Catalog (Part II, Hitchhiker’s Guide) — related entries, not the
 * solution itself:
 *
 *   16.10 Knapsack Problem  — do NOT use this. Knapsack picks any subset
 *          of items; here the minutes must form one block.
 *   17.2  Searching         — alternative: binary search on the length L
 *          plus prefix sums (“does some window of length L sum to ≥ 1000?”)
 *          in O(n log n). Same answer class, slower, more general.
 *   17.5  Generating Subsets — exponential enumeration; only for tiny n.
 *   21.3  String Matching   — another “window sliding over a sequence”
 *          family. Same pointer movement, different predicate.
 *
 * Exercises to try after this task: any “minimum window whose sum / count
 * meets a threshold” on a non-negative array; then contrast with subset
 * sum (Ch. 10.5) on the same numbers without the contiguity constraint.
 *
 * Java version: stdin template uses BufferedReader (Java 8+).
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q52_Fastest_Kilometer {

    /**
     * Returns the 1-based starting minute of the best ≥1000 m interval.
     */
    public static int solve(int[] a) {
        int n = a.length;
        int left = 0;
        long sum = 0;
        int bestLen = Integer.MAX_VALUE;
        long bestSum = -1;
        int bestStart = 0;

        for (int right = 0; right < n; right++) {
            sum += a[right];
            while (left < right && sum - a[left] >= 1000) {
                sum -= a[left];
                left++;
            }
            if (sum >= 1000) {
                int len = right - left + 1;
                if (len < bestLen
                        || (len == bestLen && sum > bestSum)
                        || (len == bestLen && sum == bestSum && left < bestStart)) {
                    bestLen = len;
                    bestSum = sum;
                    bestStart = left;
                }
            }
        }
        return bestStart + 1;
    }

    private static int check(String name, int expected, int[] a) {
        int got = solve(a);
        boolean pass = got == expected;
        System.out.println((pass ? "PASS" : "FAIL") + "  " + name
                + "  got=" + got + "  expected=" + expected);
        return pass ? 0 : 1;
    }

    public static void main(String[] args) {
        System.out.println("=== Fastest kilometer: starting minute ===\n");
        int fails = 0;

        fails += check("ex sample", 3, new int[]{100, 350, 450, 300, 450});

        fails += check("T1 single minute exactly 1000", 1, new int[]{1000});
        fails += check("T2 single minute > 1000", 1, new int[]{Integer.MAX_VALUE});
        fails += check("T3 two length-1, later is farther → pick later", 2,
                new int[]{1000, 2000});
        fails += check("T4 two length-1, equal distance → earliest start", 1,
                new int[]{1000, 1000});
        fails += check("T5 two length-1, first is farther → pick first", 1,
                new int[]{2000, 1000});
        fails += check("T6 zeros then a 1000 at the end", 4,
                new int[]{0, 0, 0, 1000});
        fails += check("T7 999+1 at the start (length 2, cannot shrink)", 1,
                new int[]{999, 1});
        fails += check("T8 1+999 (same, still start 1)", 1,
                new int[]{1, 999});
        int[] ones = new int[1000];
        java.util.Arrays.fill(ones, 1);
        fails += check("T9 whole array of 1s, n=1000, exactly 1000m", 1, ones);

        int[] late = new int[20_000];
        java.util.Arrays.fill(late, 1);
        late[19_999] = 1000;
        fails += check("T10 large n=20000, 1000 only in last minute", 20_000, late);

        System.out.println(fails == 0 ? "\nAll extra tests passed." : "\nFailed: " + fails);
    }

    /**
     * Solution as submitted for competitive programming (stdin / stdout).
     */
    public static void mainTemplate(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = st.countTokens();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(solve(a)));
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
    }
}
