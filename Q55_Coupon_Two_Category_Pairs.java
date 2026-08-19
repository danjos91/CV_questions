/**
 * Question 55: Coupon pairs summing to S (two categories)
 *
 * Russian: "Купон на S рублей: сколько пар товаров из двух разных категорий
 *          имеют сумму цен ровно S? Списки отсортированы. HashMap/set нельзя."
 * English: "A coupon of value S covers exactly two items from different
 *          categories. Count pairs whose prices sum to S. Both price lists
 *          are sorted ascending. Associative collections are forbidden."
 *
 * Example:
 *   Input:
 *     5
 *     1 2 3 4
 *     1 2 3
 *   Output: 3
 *   Pairs: (2,3), (3,2), (4,1)
 *
 * ALGORITHM NAME:
 * Two pointers on two sorted arrays (sorted two-sum / meet in the middle
 * of the price axes). Not the HashMap two-sum from Q33 — that is banned.
 *
 * Because each list has unique prices, each match is exactly one pair, so
 * both pointers move after a hit. Time O(n + m), extra space O(1) besides
 * the input arrays.
 *
 * WHERE TO READ IN SKIENA, 3rd edition:
 *
 *   Ch. 4  Sorting — why sorted input unlocks a linear scan; we do not
 *          sort here, the lists are already ordered.
 *   Catalog 17.2 Searching — two-pointer “search for a pair with given
 *          sum” in sorted sequences. Contrast with hashing (Ch. 6), which
 *          this task forbids.
 *   Same family as Q33 Two Sum, different tool because of the constraint.
 *
 * Java version: stdin template uses BufferedReader (Java 8+).
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q55_Coupon_Two_Category_Pairs {

    public static int solve(int s, int[] a, int[] b) {
        int i = 0;
        int j = b.length - 1;
        int count = 0;
        while (i < a.length && j >= 0) {
            long sum = (long) a[i] + b[j];
            if (sum == s) {
                count++;
                i++;
                j--;
            } else if (sum < s) {
                i++;
            } else {
                j--;
            }
        }
        return count;
    }

    private static int check(String name, int expected, int s, int[] a, int[] b) {
        int got = solve(s, a, b);
        boolean pass = got == expected;
        System.out.println((pass ? "PASS" : "FAIL") + "  " + name
                + "  got=" + got + "  expected=" + expected);
        return pass ? 0 : 1;
    }

    public static void main(String[] args) {
        System.out.println("=== Coupon pairs summing to S ===\n");
        int fails = 0;

        fails += check("ex sample", 3, 5, new int[]{1, 2, 3, 4}, new int[]{1, 2, 3});

        fails += check("T1 no pairs", 0, 5, new int[]{1, 2}, new int[]{1, 2});
        fails += check("T2 empty A", 0, 5, new int[]{}, new int[]{1, 2, 3});
        fails += check("T3 empty B", 0, 5, new int[]{1, 2, 3}, new int[]{});
        fails += check("T4 both empty", 0, 5, new int[]{}, new int[]{});
        fails += check("T5 single matching pair", 1, 10, new int[]{3}, new int[]{7});
        fails += check("T6 single pair too small", 0, 10, new int[]{3}, new int[]{6});
        fails += check("T7 all sums too large", 0, 3, new int[]{10, 20}, new int[]{10, 20});
        fails += check("T8 MAX+MAX must not overflow to a false match", 0,
                0, new int[]{Integer.MAX_VALUE}, new int[]{Integer.MAX_VALUE});
        fails += check("T9 MAX + 0", 1,
                Integer.MAX_VALUE, new int[]{Integer.MAX_VALUE}, new int[]{0});

        int n = 20_000;
        int[] a = new int[n];
        int[] b = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = i + 1;
            b[i] = i + 1;
        }
        fails += check("T10 large n=20000, S=n+1 → n pairs", n, n + 1, a, b);

        System.out.println(fails == 0 ? "\nAll extra tests passed." : "\nFailed: " + fails);
    }

    /**
     * Solution as submitted for competitive programming (stdin / stdout).
     */
    public static void mainTemplate(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int s = Integer.parseInt(br.readLine().trim());
        int[] a = readInts(br.readLine());
        int[] b = readInts(br.readLine());

        bw.write(String.valueOf(solve(s, a, b)));
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
    }

    private static int[] readInts(String line) {
        StringTokenizer st = new StringTokenizer(line);
        int n = st.countTokens();
        int[] arr = new int[n];
        for (int k = 0; k < n; k++) {
            arr[k] = Integer.parseInt(st.nextToken());
        }
        return arr;
    }
}
