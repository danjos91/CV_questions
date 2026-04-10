import java.io.*;
import java.util.*;

/**
 * Question 37: Artifact exhibition — minimize sum of adjacent absolute differences
 *
 * Russian (problem summary):
 * Для выставки нужно выбрать по одному артефакту из каждой из n эпох. В каждой эпохе есть m вариантов
 * со значениями a_i. Нужно выбрать n артефактов и расставить их в ряд так, чтобы минимизировать сумму
 * модулей разностей соседних значений: |a_2-a_1| + ... + |a_n-a_{n-1}|.
 * При равной минимальной сумме разностей вывести вариант с минимальной суммой всех выбранных значений.
 * Вывести значения выбранных артефактов в выбранном порядке.
 *
 * English:
 * Choose exactly one artifact from each of n eras (m choices per era). Arrange the n chosen values in a row
 * to minimize the sum of absolute differences between adjacent values. If several choices achieve the same
 * minimum, choose the one whose total sum of values is smallest. Print the chosen values in the order
 * that achieves this (non-decreasing order is optimal for the difference sum).
 *
 * Key insight:
 * - For any multiset of n numbers, the sum |x_2-x_1|+...+|x_n-x_{n-1}| is minimized when the numbers are
 *   sorted in non-decreasing order; then the sum equals (max - min) of those n numbers.
 * - So we must pick one value from each era such that (max - min) among picks is minimized; secondarily
 *   minimize the sum of the n picks.
 * - Sort each era's list. This is the classic "smallest range covering elements from k sorted lists"
 *   problem: maintain the current minimum across eras with a min-heap, track the global maximum among
 *   current picks, advance the era that contributed the minimum (two-pointer / merge pattern).
 * - Do not copy the full answer on every improvement (that can TLE); only store bestLeft = the minimum
 *   value in the best window, then rebuild once: for each era take the first value >= bestLeft (binary search),
 *   then sort those n values for output.
 *
 * Constraints: 1 ≤ n·m ≤ 10^5, values up to 10^9.
 * Time: O(n·m log n) — each element pushed once into the heap.
 * Space: O(n + n·m) for arrays and heap.
 *
 * Example:
 * Input:
 *   3 2
 *   2 2
 *   6 7
 *   99 1
 * After sorting rows: [2,2], [6,7], [1,99]. Best window has min=1, max=6 → diff=5, sum=9.
 * Output: 1 2 6
 */
public class Q37_Artifact_Exhibition_Min_Adjacent_Sum {

    static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(a[i]);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        int currentMax = Integer.MIN_VALUE;
        long sum = 0;
        long bestDiff = Long.MAX_VALUE;
        long bestSum = Long.MAX_VALUE;
        int bestLeft = 0;

        for (int i = 0; i < n; i++) {
            pq.add(new int[] {a[i][0], i, 0});
            sum += a[i][0];
            currentMax = Math.max(currentMax, a[i][0]);
        }

        while (true) {
            int[] min = pq.poll();
            long diff = (long) currentMax - min[0];

            if (diff < bestDiff || diff == bestDiff && sum < bestSum) {
                bestDiff = diff;
                bestSum = sum;
                bestLeft = min[0];
            }

            int era = min[1];
            int index = min[2] + 1;
            if (index == m) {
                break;
            }

            sum -= min[0];
            int nextValue = a[era][index];
            pq.add(new int[] {nextValue, era, index});
            sum += nextValue;
            currentMax = Math.max(currentMax, nextValue);
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = a[i][lowerBound(a[i], bestLeft)];
        }
        Arrays.sort(ans);

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                bw.write(" ");
            }
            bw.write(String.valueOf(ans[i]));
        }
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
    }
}
