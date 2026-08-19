/**
 * Question 51: Mirror-symmetric binary tree (Twin-Tree)
 *
 * Russian: "Является ли бинарное дерево зеркально-симметричным относительно
 *          центра (левое поддерево — зеркальное отражение правого)?"
 * English: "Is a binary tree a mirror of itself (left subtree mirrors the
 *          right subtree)?"
 *
 * Problem Statement:
 * The tree has n vertices, root = 1. Vertex i is given as (value, left, right)
 * with 1-based child indices, or -1 if a child is missing. Print YES if the
 * tree is mirror-symmetric about the root, otherwise NO.
 *
 * Two nodes a and b are mirrors iff:
 * - both are missing, or
 * - both exist, val[a] == val[b], left(a) mirrors right(b), right(a) mirrors left(b).
 *
 * Constraints:
 * - 1 ≤ n ≤ 2·10^5
 * - 1 ≤ x_i ≤ 10^9
 *
 * Example 1:
 *   5
 *   1 2 3
 *   2 4 -1
 *   2 -1 5
 *   3 -1 -1
 *   3 -1 -1
 * Output: YES
 *
 * Example 2:
 *   5
 *   1 2 3
 *   2 -1 4
 *   2 -1 5
 *   3 -1 -1
 *   3 -1 -1
 * Output: NO  (both inner nodes have a right child — not mirrored)
 *
 * Example 3:
 *   7
 *   1 2 3
 *   2 4 5
 *   2 6 7
 *   1 -1 -1
 *   3 -1 -1
 *   3 -1 -1
 *   1 -1 -1
 * Output: YES
 *
 * ALGORITHM:
 * Iterative pair-check from (left[root], right[root]). Time O(n), extra
 * space O(n). Iterative because n is large enough to blow the call stack.
 *
 * Java version: stdin template uses BufferedReader (Java 8+).
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

public class Q51_Mirror_Symmetric_Tree {

    /**
     * Returns true iff the tree rooted at 1 is a mirror of itself.
     * Arrays are 1-based; missing child = -1.
     */
    public static boolean isSymmetric(int[] val, int[] left, int[] right) {
        Deque<int[]> stack = new ArrayDeque<>();
        stack.push(new int[]{left[1], right[1]});

        while (!stack.isEmpty()) {
            int[] pair = stack.pop();
            int a = pair[0];
            int b = pair[1];

            if (a == -1 && b == -1) {
                continue;
            }
            if (a == -1 || b == -1 || val[a] != val[b]) {
                return false;
            }
            stack.push(new int[]{left[a], right[b]});
            stack.push(new int[]{right[a], left[b]});
        }
        return true;
    }

    private static int check(String name, boolean expected, int[] val, int[] left, int[] right) {
        boolean got = isSymmetric(val, left, right);
        boolean pass = got == expected;
        System.out.println((pass ? "PASS" : "FAIL") + "  " + name
                + "  got=" + (got ? "YES" : "NO")
                + "  expected=" + (expected ? "YES" : "NO"));
        return pass ? 0 : 1;
    }

    /** Complete 1-based heap of n=2^k-1 nodes; val[i]=depth so the tree is a mirror. */
    private static int[][] completeDepthTree(int n) {
        int[] val = new int[n + 1];
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            val[i] = 31 - Integer.numberOfLeadingZeros(i);
            left[i] = (2 * i <= n) ? 2 * i : -1;
            right[i] = (2 * i + 1 <= n) ? 2 * i + 1 : -1;
        }
        return new int[][]{val, left, right};
    }

    public static void main(String[] args) {
        System.out.println("=== Mirror-symmetric binary tree ===\n");
        int fails = 0;

        fails += check("ex1 sample YES", true,
                new int[]{0, 1, 2, 2, 3, 3},
                new int[]{0, 2, 4, -1, -1, -1},
                new int[]{0, 3, -1, 5, -1, -1});
        fails += check("ex2 sample NO", false,
                new int[]{0, 1, 2, 2, 3, 3},
                new int[]{0, 2, -1, -1, -1, -1},
                new int[]{0, 3, 4, 5, -1, -1});
        fails += check("ex3 sample YES", true,
                new int[]{0, 1, 2, 2, 1, 3, 3, 1},
                new int[]{0, 2, 4, 6, -1, -1, -1, -1},
                new int[]{0, 3, 5, 7, -1, -1, -1, -1});

        fails += check("T1 single node (n=1)", true,
                new int[]{0, 42},
                new int[]{0, -1},
                new int[]{0, -1});
        fails += check("T2 only left child", false,
                new int[]{0, 1, 2},
                new int[]{0, 2, -1},
                new int[]{0, -1, -1});
        fails += check("T3 only right child", false,
                new int[]{0, 1, 2},
                new int[]{0, -1, -1},
                new int[]{0, 2, -1});
        fails += check("T4 two children, different values", false,
                new int[]{0, 1, 2, 3},
                new int[]{0, 2, -1, -1},
                new int[]{0, 3, -1, -1});
        fails += check("T5 two children, same value, both leaves", true,
                new int[]{0, 1, 9, 9},
                new int[]{0, 2, -1, -1},
                new int[]{0, 3, -1, -1});
        fails += check("T6 values at 1e9", true,
                new int[]{0, 1, 1_000_000_000, 1_000_000_000},
                new int[]{0, 2, -1, -1},
                new int[]{0, 3, -1, -1});
        fails += check("T7 mirror shape, leaf values differ", false,
                new int[]{0, 1, 2, 2, 3, 4},
                new int[]{0, 2, 4, -1, -1, -1},
                new int[]{0, 3, -1, 5, -1, -1});
        fails += check("T8 both inner nodes have a LEFT child (not mirrored)", false,
                new int[]{0, 1, 2, 2, 3, 3},
                new int[]{0, 2, 4, 5, -1, -1},
                new int[]{0, 3, -1, -1, -1, -1});
        fails += check("T9 missing child vs present on the opposite side", false,
                new int[]{0, 1, 2, 2, 3},
                new int[]{0, 2, 4, -1, -1},
                new int[]{0, 3, -1, -1, -1});

        int nLarge = (1 << 13) - 1; // 8191 nodes, complete tree
        int[][] big = completeDepthTree(nLarge);
        fails += check("T10 complete tree n=" + nLarge + " (depth values, memory/size)", true,
                big[0], big[1], big[2]);
        big[0][2] = 1;
        big[0][3] = 2;
        fails += check("T10b same shape, root children differ", false,
                big[0], big[1], big[2]);

        System.out.println(fails == 0 ? "\nAll extra tests passed." : "\nFailed: " + fails);
    }

    /**
     * Solution as submitted for competitive programming (stdin / stdout).
     */
    public static void mainTemplate(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(br.readLine().trim());
        int[] val = new int[n + 1];
        int[] left = new int[n + 1];
        int[] right = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            val[i] = Integer.parseInt(st.nextToken());
            left[i] = Integer.parseInt(st.nextToken());
            right[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(isSymmetric(val, left, right) ? "YES" : "NO");
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
    }
}
