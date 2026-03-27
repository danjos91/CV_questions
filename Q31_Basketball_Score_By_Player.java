/**
 * Question 31: Basketball — who scored the most points
 *
 * Russian: "По записям общего счёта и имени забившего определить, кто набрал
 *          больше всех личных очков"
 * English: "From cumulative score updates and the scorer's name, find the
 *          player with the highest total personal points"
 *
 * Problem Statement:
 * There are two hoops (two numbers in the total score a:b). The game starts
 * at 0:0. You are given n player names, then m records. Each record is
 * "a_i:b_i name" — the total score became (a_i, b_i) after that player scored.
 * Only one side's total increases per record; scores never decrease.
 *
 * For each record, the points scored on that play equal the increase in total:
 *   (a_i - a_prev) + (b_i - b_prev)
 * Add that value to the named player's running total. Print the name of a
 * player with the maximum total and that total (any maximal player if tied).
 *
 * Constraints:
 * - 1 ≤ n ≤ 50 (players)
 * - 1 ≤ |name| ≤ 15 (Latin letters)
 * - 1 ≤ m ≤ 500 (records)
 *
 * Example (from problem statement):
 * Input:
 *   3
 *   Vasya
 *   Petya
 *   Masha
 *   4
 *   3:0 Vasya
 *   3:2 Petya
 *   4:2 Masha
 *   7:2 Masha
 * Output: Masha 4
 * Explanation:
 *   Vasya +3, Petya +2, Masha +1+3 = 4
 *
 * ANSWER: Map player name → sum; track previous (a, b); delta per record.
 *
 * ALGORITHM EXPLANATION:
 *
 * Key insight:
 * The line gives the new global totals, not the basket that changed. The
 * points on this possession are exactly the sum of the two coordinate deltas;
 * one delta is zero, the other is the points scored.
 *
 * Optimal approach (O(m) time, O(n) space):
 * 1. Initialize map with all names → 0.
 * 2. prevA = 0, prevB = 0.
 * 3. For each record: parse a, b, name; delta = (a - prevA) + (b - prevB);
 *    map[name] += delta; prevA = a; prevB = b.
 * 4. Scan map for maximum value; print name and value.
 *
 * Java version: stdin template uses BufferedReader (Java 8+).
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Q31_Basketball_Score_By_Player {

    /**
     * Returns "Name points" for the leader (first maximal if tie).
     */
    public static String solveLeader(List<String> playerNames, List<String> records) {
        Map<String, Integer> scores = new HashMap<>();
        for (String name : playerNames) {
            scores.put(name, 0);
        }
        int prevA = 0;
        int prevB = 0;
        for (String line : records) {
            String[] parts = line.split(" ");
            String[] ab = parts[0].split(":");
            int a = Integer.parseInt(ab[0]);
            int b = Integer.parseInt(ab[1]);
            String player = parts[1];
            int delta = (a - prevA) + (b - prevB);
            scores.put(player, scores.get(player) + delta);
            prevA = a;
            prevB = b;
        }
        String bestName = "";
        int best = -1;
        for (Map.Entry<String, Integer> e : scores.entrySet()) {
            if (e.getValue() > best) {
                best = e.getValue();
                bestName = e.getKey();
            }
        }
        return bestName + " " + best;
    }

    public static void main(String[] args) {
        System.out.println("=== Basketball: top scorer from score records ===\n");

        List<String> names = Arrays.asList("Vasya", "Petya", "Masha");
        List<String> records = Arrays.asList(
                "3:0 Vasya",
                "3:2 Petya",
                "4:2 Masha",
                "7:2 Masha");
        System.out.println("Example:");
        System.out.println("Players: " + names);
        System.out.println("Records: " + records);
        System.out.println("Output: " + solveLeader(names, records));
        System.out.println();
        System.out.println("Expected: Masha 4");
    }

    /**
     * Solution as submitted for competitive programming (stdin / stdout).
     */
    public static void mainTemplate(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        int n = Integer.parseInt(reader.readLine());
        Map<String, Integer> scores = new HashMap<>();

        for (int i = 0; i < n; i++) {
            String name = reader.readLine();
            scores.put(name, 0);
        }

        int m = Integer.parseInt(reader.readLine());
        int prevLeft = 0;
        int prevRight = 0;

        for (int i = 0; i < m; i++) {
            String record = reader.readLine();
            String[] parts = record.split(" ");
            String[] scoreParts = parts[0].split(":");
            String playerName = parts[1];

            int currentLeft = Integer.parseInt(scoreParts[0]);
            int currentRight = Integer.parseInt(scoreParts[1]);

            int scoredPoints = (currentLeft - prevLeft) + (currentRight - prevRight);

            scores.put(playerName, scores.get(playerName) + scoredPoints);

            prevLeft = currentLeft;
            prevRight = currentRight;
        }

        String bestPlayer = "";
        int bestScore = -1;

        for (Map.Entry<String, Integer> entry : scores.entrySet()) {
            if (entry.getValue() > bestScore) {
                bestScore = entry.getValue();
                bestPlayer = entry.getKey();
            }
        }

        writer.write(bestPlayer + " " + bestScore);

        reader.close();
        writer.close();
    }
}
