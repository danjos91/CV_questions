/**
 * Question 50: Card dealing — maximize aces for player 1
 *
 * Russian: "Колода раздаётся по кругу. С какого игрока начать раздачу, чтобы
 *          первый игрок получил максимум тузов (значение 14)?"
 * English: "A known deck is dealt in a circle. With which player should the
 *          dealer start so that player 1 gets as many aces as possible?"
 *
 * Problem Statement:
 * n players sit in a circle. A deck of k cards is dealt one card at a time
 * until it is empty. Card values are 6..14; 14 is an ace. The order of the
 * deck is known. If dealing starts at player S, cards go to
 * S, S+1, ..., n, 1, 2, ..., S-1, then the cycle repeats.
 *
 * Find the smallest S in 1..n such that player 1 receives the maximum
 * possible number of aces.
 *
 * Constraints:
 * - n, k natural numbers ≤ 100
 * - k is always divisible by n
 *
 * Example:
 * Input:
 *   5 10
 *   9 9 9 14 8 7 11 6 14 6
 * Output: 3
 * Explanation:
 *   Start at 3 → deal order 3,4,5,1,2,3,4,5,1,2
 *   Player 1 gets the 4th card (14) and the 9th card (14) → 2 aces.
 *
 * ALGORITHM:
 * Card i (0-based) goes to player ((S - 1 + i) % n) + 1.
 * Brute-force every S in 1..n, count aces for player 1, keep the smallest
 * S with the maximum count. Time O(n·k) ≤ 10^4.
 *
 * Java version: stdin template uses BufferedReader (Java 8+).
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Q50_Card_Dealing_Max_Aces {

    /**
     * Returns the smallest starting player S so that player 1 gets the most aces.
     */
    public static int solve(int n, int[] cards) {
        int k = cards.length;
        int bestStart = 1;
        int bestAces = -1;

        for (int start = 1; start <= n; start++) {
            int aces = 0;
            for (int i = 0; i < k; i++) {
                int player = ((start - 1 + i) % n) + 1;
                if (player == 1 && cards[i] == 14) {
                    aces++;
                }
            }
            if (aces > bestAces) {
                bestAces = aces;
                bestStart = start;
            }
        }
        return bestStart;
    }

    public static void main(String[] args) {
        System.out.println("=== Card dealing: maximize aces for player 1 ===\n");

        int n = 5;
        int[] cards = {9, 9, 9, 14, 8, 7, 11, 6, 14, 6};
        System.out.println("Example:");
        System.out.println("n = " + n + ", k = " + cards.length);
        System.out.println("deck = [9, 9, 9, 14, 8, 7, 11, 6, 14, 6]");
        System.out.println("Output:   " + solve(n, cards));
        System.out.println("Expected: 3");
    }

    /**
     * Solution as submitted for competitive programming (stdin / stdout).
     */
    public static void mainTemplate(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] cards = new int[k];
        for (int i = 0; i < k; i++) {
            cards[i] = Integer.parseInt(st.nextToken());
        }

        bw.write(String.valueOf(solve(n, cards)));
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
    }
}
