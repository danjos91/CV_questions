/**
 * Question 57: Range geometric mean (prefix of logarithms)
 *
 * Russian: "Геометрическое среднее на отрезке массива. Много запросов."
 * English: "Answer Q range queries: geometric mean of a[i..j] (inclusive)."
 *
 * GM(a_i … a_j) = (a_i · … · a_j)^{1/(j-i+1)}
 *
 * Direct product overflows / loses precision. Hint: pick f with
 * f(xy)=f(x)+f(y), i.e. ln. Then
 *   GM = exp( (ln a_i + … + ln a_j) / len )
 * and the sum of logs is a prefix-sum range query in O(1).
 *
 * Constraints:
 * - 1 ≤ N ≤ 3·10^5, 1 ≤ Q ≤ 10^5
 * - 0.01 ≤ a_k ≤ 100
 * - 0 ≤ i ≤ j ≤ N-1
 * Print ≥ 6 decimal places (samples use 10).
 *
 * ALGORITHM NAME:
 * Prefix sums of logarithms (range product in log-space).
 *
 * WHERE TO READ IN SKIENA, 3rd edition:
 *
 *   Ch. 3  Data Structures — prefix arrays for range sums.
 *   Ch. 2  Algorithm Analysis — O(N+Q) vs O(N·Q) naively.
 *   Catalog 16 Numerical Problems — logs/exp; this is not knapsack.
 *
 * Java version: stdin template uses BufferedReader (Java 8+).
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Locale;
import java.util.StringTokenizer;

public class Q57_Range_Geometric_Mean {

    public static double[] prefixLn(double[] a) {
        double[] prefix = new double[a.length + 1];
        for (int k = 0; k < a.length; k++) {
            prefix[k + 1] = prefix[k] + Math.log(a[k]);
        }
        return prefix;
    }

    public static double rangeGM(double[] prefix, int i, int j) {
        double sumLn = prefix[j + 1] - prefix[i];
        return Math.exp(sumLn / (j - i + 1));
    }

    private static int check(String name, double got, double expected) {
        boolean pass = Math.abs(got - expected) <= 1e-8 * Math.max(1.0, Math.abs(expected));
        System.out.println((pass ? "PASS" : "FAIL") + "  " + name
                + "  got=" + String.format(Locale.US, "%.10f", got)
                + "  expected=" + String.format(Locale.US, "%.10f", expected));
        return pass ? 0 : 1;
    }

    public static void main(String[] args) {
        System.out.println("=== Range geometric mean ===\n");
        int fails = 0;

        double[] p1 = prefixLn(new double[]{1.00});
        fails += check("ex1 [0,0]", rangeGM(p1, 0, 0), 1.0000000000);

        double[] a2 = {1.34, 1.37, 1.40, 1.44, 1.91, 1.95, 1.96, 1.97};
        double[] p2 = prefixLn(a2);
        fails += check("ex2 [1,4]", rangeGM(p2, 1, 4), 1.5155180581);
        fails += check("ex2 [2,7]", rangeGM(p2, 2, 7), 1.7527237330);
        fails += check("ex2 [4,6]", rangeGM(p2, 4, 6), 1.9398791862);
        fails += check("ex2 [0,3]", rangeGM(p2, 0, 3), 1.3870080234);
        fails += check("ex2 [2,6]", rangeGM(p2, 2, 6), 1.7122332072);

        double[] a3 = {79.02, 36.68, 79.83, 76.00, 95.48, 48.84, 49.95, 91.91};
        double[] p3 = prefixLn(a3);
        double[] exp3 = {
                79.0200000000,
                53.8372881932,
                61.3918650506,
                64.7569698060,
                69.9860845089,
                65.9131939292,
                63.3529861609,
                66.3691949553,
                64.7354537009,
                71.1641075963
        };
        int[][] q3 = {{0, 0}, {0, 1}, {0, 2}, {0, 3}, {0, 4}, {0, 5}, {0, 6}, {0, 7}, {1, 7}, {2, 7}};
        for (int t = 0; t < q3.length; t++) {
            fails += check("ex3 [" + q3[t][0] + "," + q3[t][1] + "]",
                    rangeGM(p3, q3[t][0], q3[t][1]), exp3[t]);
        }

        fails += check("T1 min value 0.01", rangeGM(prefixLn(new double[]{0.01}), 0, 0), 0.01);
        fails += check("T2 max value 100", rangeGM(prefixLn(new double[]{100.0}), 0, 0), 100.0);

        System.out.println(fails == 0 ? "\nAll extra tests passed." : "\nFailed: " + fails);
    }

    /**
     * Solution as submitted for competitive programming (stdin / stdout).
     */
    public static void mainTemplate(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        double[] a = new double[n];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int k = 0; k < n; k++) {
            a[k] = Double.parseDouble(st.nextToken());
        }
        double[] prefix = prefixLn(a);

        int q = Integer.parseInt(br.readLine().trim());
        for (int t = 0; t < q; t++) {
            st = new StringTokenizer(br.readLine());
            int i = Integer.parseInt(st.nextToken());
            int j = Integer.parseInt(st.nextToken());
            bw.write(String.format(Locale.US, "%.10f", rangeGM(prefix, i, j)));
            bw.newLine();
        }
        bw.flush();
        br.close();
        bw.close();
    }
}
