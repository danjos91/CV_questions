/**
 * Question 33: Non-overlapping Substrings (Непересекающиеся подстроки)
 *
 * Russian: "Дана строка s. Нужно найти длину подстроки t, у которой число
 *          непересекающихся вхождений в s максимально. Среди таких t выбрать
 *          подстроку максимальной длины."
 * English: "Given a string s, find a substring t with the maximum number of
 *          non-overlapping occurrences in s. Among those, maximize |t|."
 *
 * Visual idea:
 * Let s = "abacaba"
 * - "aba" occurs at [0..2] and [4..6] => 2 non-overlapping occurrences
 * - "a" occurs at [0], [2], [4], [6] => 4 occurrences
 * We must maximize occurrences first, so answer is 1 (substring "a").
 *
 * Core observations:
 * 1) Let M be the maximum frequency of a single character in s.
 *    No substring can have more than M non-overlapping occurrences.
 * 2) Therefore, the best substring must appear exactly M times.
 * 3) We only need to test characters whose frequency is M.
 * 4) For each such character, use all its positions as required starts.
 *
 * For positions p0 < p1 < ... < p(M-1):
 * - To avoid overlap, length L must satisfy:
 *     L <= p(i+1) - p(i) for all i
 * - Also last occurrence must fit in string:
 *     L <= n - p(M-1)
 * So:
 *     limit = min( all consecutive gaps, n - lastStart )
 *
 * Then we extend offset by offset:
 * - offset 0: all s[p_i + 0] must be equal
 * - offset 1: all s[p_i + 1] must be equal
 * - ...
 * Stop on first mismatch or at limit.
 *
 * Complexity:
 * - Time:  O(26 * n) in practice (lowercase letters only)
 * - Space: O(n)
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Q33_Non_Overlapping_Substrings {

    /**
     * Solves the problem for a given lowercase string.
     */
    public static int solve(String s) {
        int n = s.length();

        // Step 1) Count each letter and compute M (max letter frequency).
        int[] frequency = new int[26];
        int maxCount = 0;
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            frequency[idx]++;
            maxCount = Math.max(maxCount, frequency[idx]);
        }

        // Step 2) Store all positions for each letter.
        List<List<Integer>> positions = new ArrayList<>();
        for (int i = 0; i < 26; i++) {
            positions.add(new ArrayList<>());
        }
        for (int i = 0; i < n; i++) {
            int idx = s.charAt(i) - 'a';
            positions.get(idx).add(i);
        }

        // Step 3) Try only letters with frequency M.
        int answer = 1;
        for (int c = 0; c < 26; c++) {
            if (frequency[c] == maxCount) {
                answer = Math.max(answer, bestLengthForStarts(s, positions.get(c)));
            }
        }
        return answer;
    }

    /**
     * Given the start indices of M occurrences, compute the maximum valid length.
     */
    private static int bestLengthForStarts(String s, List<Integer> starts) {
        int m = starts.size();
        int n = s.length();

        // Non-overlap + in-bounds limit:
        // L <= min(gaps between starts, space after last start).
        int limit = n - starts.get(m - 1);
        for (int i = 1; i < m; i++) {
            limit = Math.min(limit, starts.get(i) - starts.get(i - 1));
        }

        // Extend common substring character-by-character up to limit.
        int len = 0;
        while (len < limit) {
            char expected = s.charAt(starts.get(0) + len);
            boolean allEqual = true;
            for (int i = 1; i < m; i++) {
                if (s.charAt(starts.get(i) + len) != expected) {
                    allEqual = false;
                    break;
                }
            }
            if (!allEqual) {
                break;
            }
            len++;
        }
        return len;
    }

    public static void main(String[] args) {
        System.out.println("=== Non-overlapping Substrings ===\n");

        List<String> tests = Arrays.asList("abacaba", "abab", "aaaaa", "abcabcabc");
        for (String s : tests) {
            System.out.println("s = " + s);
            System.out.println("answer length = " + solve(s));
            System.out.println();
        }

        System.out.println("Expected highlights:");
        System.out.println("abacaba -> 1");
        System.out.println("abab -> 2");
    }

    /**
     * Competitive-programming stdin/stdout template.
     */
    public static void mainTemplate(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = reader.readLine();
        writer.write(String.valueOf(solve(s)));

        reader.close();
        writer.close();
    }
}
