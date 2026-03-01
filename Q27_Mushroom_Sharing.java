/**
 * Question 27: Mushroom Sharing (Делёж грибов)
 *
 * Russian: "Вася и Маша делят грибы. Вася может сделать не более одного обмена
 *           любых двух грибов. Найти максимальную радость Васи."
 * English: "Vasya and Masha divide mushrooms. Vasya can swap at most one pair
 *           of mushrooms. Find maximum joy (sum Vasya - sum Masha)."
 *
 * Problem:
 * - n mushrooms in a row, weights a_1, a_2, ..., a_n
 * - Vasya gets odd positions (1st, 3rd, 5th...) → indices 0, 2, 4
 * - Masha gets even positions (2nd, 4th...) → indices 1, 3, 5
 * - Joy = sum(Vasya) - sum(Masha) = a_1 - a_2 + a_3 - a_4 + ...
 * - Vasya can do at most ONE swap of ANY two mushrooms to maximize joy
 *
 * Key insight: The only beneficial swap is between one of Vasya's and one of
 * Masha's. Best swap: Vasya's minimum ↔ Masha's maximum (maximizes gain 2*(maxMasha - minVasya)).
 *
 * Time:  O(n) — single pass possible
 * Space: O(1) extra — no need to store separate lists
 *
 * Java version: Java 8+ compatible.
 */

import java.io.*;
import java.util.*;

public class Q27_Mushroom_Sharing {

    /**
     * Your original approach (correct logic, O(n) time, O(n) space):
     * - Split into vasya/masha lists, then min(vasya), max(masha), sums.
     */
    public static int solveOriginal(int n, List<Integer> fungs) {
        List<Integer> vasya = new ArrayList<>();
        List<Integer> masha = new ArrayList<>();
        for (int i = 0; i < fungs.size(); i++) {
            if (i % 2 == 0) vasya.add(fungs.get(i));
            else masha.add(fungs.get(i));
        }
        int minVasya = Collections.min(vasya);
        int maxMasha = Collections.max(masha);
        int sumVasya = vasya.stream().mapToInt(Integer::intValue).sum();
        int sumMasha = masha.stream().mapToInt(Integer::intValue).sum();
        int total1 = sumVasya - minVasya + maxMasha - (sumMasha - maxMasha + minVasya);
        int total2 = sumVasya - sumMasha;
        return Math.max(total1, total2);
    }

    /**
     * Improved: single pass, O(n) time, O(1) extra space (excluding input).
     */
    public static int solveOptimal(int n, int[] a) {
        int sumVasya = 0, sumMasha = 0;
        int minVasya = Integer.MAX_VALUE, maxMasha = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (i % 2 == 0) {
                sumVasya += a[i];
                minVasya = Math.min(minVasya, a[i]);
            } else {
                sumMasha += a[i];
                maxMasha = Math.max(maxMasha, a[i]);
            }
        }

        int joyNoSwap = sumVasya - sumMasha;
        // After swapping minVasya with maxMasha: joy = joyNoSwap + 2*(maxMasha - minVasya)
        int joyWithSwap = joyNoSwap + 2 * (maxMasha - minVasya);

        return Math.max(joyNoSwap, joyWithSwap);
    }

    public static void main(String[] args) throws IOException {
        // Example: n=2, [1, 2] -> swap gives [2,1] -> joy = 2-1 = 1
        int[] a1 = {1, 2};
        System.out.println("Example 1: n=2, [1, 2]");
        System.out.println("  Original: " + solveOriginal(2, Arrays.asList(1, 2)));
        System.out.println("  Optimal:  " + solveOptimal(2, a1));
        System.out.println("  Expected:  1");
        System.out.println();

        // Custom: no swap better
        int[] a2 = {10, 1, 10, 1};
        System.out.println("Example 2: [10, 1, 10, 1]");
        System.out.println("  No swap:  10 - 1 + 10 - 1 = 18");
        System.out.println("  Optimal:  " + solveOptimal(4, a2));
        System.out.println();
    }
}
