/**
 * Question 53: Optimal temperature range (closer / further)
 *
 * Russian: "По последовательности измерений и пометкам «ближе/дальше» найти
 *          интервал возможных значений оптимальной температуры X."
 * English: "From temperature readings and closer/further marks, recover the
 *          feasible interval for the unknown optimal temperature X."
 *
 * Problem Statement:
 * First measurement T0 is given. Each next reading Ti comes with '<'
 * (closer to X than the previous reading) or '>' (further from X).
 * |X − Ti| < |X − T{i-1}|  or  |X − Ti| > |X − T{i-1}|.
 * Output the smallest and largest possible X. A valid X is guaranteed.
 *
 * Constraints:
 * - 2 ≤ n ≤ 1000
 * - Optimal temperature and all readings lie in [0, 1000]
 * Time 1 s / Memory 256 MB
 *
 * Example:
 *   Input:
 *     3
 *     800.0
 *     150.0 <
 *     400.0 >
 *   Output: 0.0 275.0
 *
 *   800 → 150 closer: mid 475, 150 < 800 → X < 475  → [0, 475]
 *   150 → 400 further: mid 275, 400 > 150 → X < 275 → [0, 275]
 *
 * ALGORITHM NAME:
 * 1D interval intersection (linear constraint scan).
 *
 * Geometric fact: points equally far from A and B lie on the perpendicular
 * bisector. On a line that is just the midpoint M = (A+B)/2.
 * “Closer to Ti” ⇔ X is on Ti’s side of M.
 * “Further from Ti” ⇔ X is on T{i-1}’s side of M.
 * Intersect these half-lines with [0, 1000]. Time O(n).
 *
 * This is a typical “maintain a feasible interval” problem, same family as
 * intersecting segments. It is NOT binary search on X (you can check a
 * candidate X in O(n), but the closed form is the midpoints themselves).
 *
 * WHERE TO READ IN SKIENA, 3rd edition:
 *
 *   Ch. 1  §1.5 Modeling — the object is a point X on a line, each
 *          observation is a half-line constraint, not a search problem.
 *   Ch. 2  §2.2 Big-Oh — n ≤ 1000, a single O(n) pass is enough.
 *   Catalog 20.6 Range Search / 20.8 Intersection Detection — the 1D
 *          special case: intersecting n intervals/half-lines.
 *   Do not jump to Ch. 9 combinatorial search or binary search (17.2)
 *          unless you want a slower “check mid of [lo,hi]” rewrite.
 *
 * Java version: stdin template uses BufferedReader (Java 8+).
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q53_Optimal_Temperature_Range {

    /**
     * readings[0] is T0; for i≥1, readings[i] is Ti and closer[i] is true for '<'.
     * closer[0] is unused.
     */
    public static double[] solve(double[] readings, boolean[] closer) {
        double lo = 0.0;
        double hi = 1000.0;
        for (int i = 1; i < readings.length; i++) {
            double prev = readings[i - 1];
            double t = readings[i];
            double mid = (prev + t) / 2.0;
            boolean xGreaterThanMid = (t > prev) == closer[i];
            if (xGreaterThanMid) {
                lo = Math.max(lo, mid);
            } else {
                hi = Math.min(hi, mid);
            }
        }
        return new double[]{lo, hi};
    }

    private static int check(String name, double expLo, double expHi, double[] readings, boolean[] closer) {
        double[] got = solve(readings, closer);
        boolean pass = Math.abs(got[0] - expLo) < 1e-9 && Math.abs(got[1] - expHi) < 1e-9;
        System.out.println((pass ? "PASS" : "FAIL") + "  " + name
                + "  got=" + got[0] + " " + got[1]
                + "  expected=" + expLo + " " + expHi);
        return pass ? 0 : 1;
    }

    public static void main(String[] args) {
        System.out.println("=== Optimal temperature range ===\n");
        int fails = 0;

        fails += check("ex sample", 0.0, 275.0,
                new double[]{800.0, 150.0, 400.0},
                new boolean[]{false, true, false});

        fails += check("T1 n=2, 0 then 1000 closer → X>500", 500.0, 1000.0,
                new double[]{0.0, 1000.0},
                new boolean[]{false, true});
        fails += check("T2 n=2, 0 then 1000 further → X<500", 0.0, 500.0,
                new double[]{0.0, 1000.0},
                new boolean[]{false, false});
        fails += check("T3 n=2, 1000 then 0 closer → X<500", 0.0, 500.0,
                new double[]{1000.0, 0.0},
                new boolean[]{false, true});
        fails += check("T4 n=2, 1000 then 0 further → X>500", 500.0, 1000.0,
                new double[]{1000.0, 0.0},
                new boolean[]{false, false});
        fails += check("T5 two equal readings, closer, mid=0 → hi=0", 0.0, 0.0,
                new double[]{0.0, 0.0},
                new boolean[]{false, true});
        fails += check("T6 tiny step 0.0001 closer from 0", 0.00005, 1000.0,
                new double[]{0.0, 0.0001},
                new boolean[]{false, true});
        fails += check("T7 two cuts: (0,100 <) then (100,200 >) → [50,150]", 50.0, 150.0,
                new double[]{0.0, 100.0, 200.0},
                new boolean[]{false, true, false});
        fails += check("T8 raise lo then lower hi: 0→200 < then 200→150 <", 100.0, 175.0,
                new double[]{0.0, 200.0, 150.0},
                new boolean[]{false, true, true});
        double[] walk = new double[1000];
        boolean[] closerN = new boolean[1000];
        for (int i = 0; i < 1000; i++) {
            walk[i] = i;
            closerN[i] = true;
        }
        fails += check("T9 n=1000, always closer while T increases", 998.5, 1000.0,
                walk, closerN);
        fails += check("T10 midpoint 1.5 (odd integers)", 1.5, 1000.0,
                new double[]{1.0, 2.0},
                new boolean[]{false, true});

        System.out.println(fails == 0 ? "\nAll extra tests passed." : "\nFailed: " + fails);
    }

    /**
     * Solution as submitted for competitive programming (stdin / stdout).
     */
    public static void mainTemplate(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        double[] readings = new double[n];
        boolean[] closer = new boolean[n];
        readings[0] = Double.parseDouble(br.readLine().trim());
        for (int i = 1; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            readings[i] = Double.parseDouble(st.nextToken());
            closer[i] = st.nextToken().equals("<");
        }

        double[] ans = solve(readings, closer);
        bw.write(ans[0] + " " + ans[1]);
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
    }
}
