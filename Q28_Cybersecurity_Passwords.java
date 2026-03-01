/**
 * Question 28: Cybersecurity - Distinct Passwords (Кибербезопасность)
 *
 * Russian: "Вася меняет пароль еженедельно. Новый пароль получается обменом
 *           ровно двух символов в исходном пароле s. Найти число различных паролей."
 * English: "Count distinct passwords: original s + all strings from swapping exactly two chars."
 *
 * Problem:
 * - String s (lowercase letters), length 1..10^5
 * - Distinct passwords = original + all strings from swapping exactly two characters
 * - Output: number of distinct passwords (weeks)
 *
 * Key insight:
 * - Total pairs of positions = n*(n-1)/2
 * - Swapping two positions with the SAME character yields the same string
 * - For each char c with frequency f, subtract f*(f-1)/2 (same-char pairs)
 * - Result = 1 + (n*(n-1)/2 - sum over c of f*(f-1)/2)
 *
 * Time:  O(n)
 * Space: O(1) if using int[26], O(n) for input string
 *
 * Java version: Java 8+ compatible.
 */

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Q28_Cybersecurity_Passwords {

    /**
     * Your approach: HashMap for frequency. Correct, O(n) time, O(26) space for map.
     */
    public static long solveWithMap(char[] s) {
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : s) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);  // single lookup
        }
        long n = s.length;
        long total = n * (n - 1) / 2;
        for (int f : freq.values()) {
            if (f > 1) {
                total -= (long) f * (f - 1) / 2;
            }
        }
        return total + 1;
    }

    /**
     * Improved: int[26] for frequency (only 'a'..'z'). Same logic, faster and less overhead.
     */
    public static long solveOptimal(char[] s) {
        int[] freq = new int[26];
        for (char c : s) {
            freq[c - 'a']++;
        }
        long n = s.length;
        long total = n * (n - 1) / 2;
        for (int f : freq) {
            if (f > 1) {
                total -= (long) f * (f - 1) / 2;
            }
        }
        return total + 1;
    }

    public static void main(String[] args) {
        // Example 1: abacaba -> 15
        char[] s1 = "abacaba".toCharArray();
        System.out.println("abacaba: " + solveOptimal(s1) + " (expected 15)");
        System.out.println("abacaba (map): " + solveWithMap(s1));

        // Example 2: aaaaaa -> 1
        char[] s2 = "aaaaaa".toCharArray();
        System.out.println("aaaaaa: " + solveOptimal(s2) + " (expected 1)");
    }
}
