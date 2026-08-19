/**
 * Question 56: Run-Length Encoding (RLE) string compression
 *
 * Russian: "Сжать строку: aaabbccc → a3b2c3. Вывести длину сжатой строки
 *          и саму строку. O(|S|) время."
 * English: "Compress S with RLE. Output the compressed length, then the
 *          compressed string. Runs of length 1 are written as the letter
 *          only (LeetCode 443 String Compression)."
 *
 * Input:  one line, lowercase English letters, 1 ≤ |S| ≤ 10^6
 * Output: length, then the compressed string.
 *
 * Example:
 *   Input:  aaabbccc
 *   Output:
 *     6
 *     a3b2c3
 *
 * ALGORITHM NAME:
 * Run-Length Encoding (linear scan of consecutive equal characters).
 *
 * For each maximal run of char c with length k:
 *   append c, and if k > 1 append the decimal digits of k.
 * Time O(|S|). Extra memory O(1) besides the output buffer.
 *
 * WHERE TO READ IN SKIENA, 3rd edition:
 *
 *   Ch. 2  §2.5.3 String Pattern Matching — scanning a string left to right.
 *   Catalog 21.3 String Matching / 21.5 Text Compression — RLE is the
 *          simplest text-compression method (store run lengths).
 *
 * Java version: stdin template uses BufferedReader (Java 8+).
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Q56_Run_Length_Encoding {

    public static String compress(String s) {
        StringBuilder sb = new StringBuilder();
        int n = s.length();
        int i = 0;
        while (i < n) {
            char c = s.charAt(i);
            int j = i + 1;
            while (j < n && s.charAt(j) == c) {
                j++;
            }
            int cnt = j - i;
            sb.append(c);
            if (cnt > 1) {
                sb.append(cnt);
            }
            i = j;
        }
        return sb.toString();
    }

    private static int check(String name, String input, String expectedCompressed) {
        String got = compress(input);
        boolean pass = got.equals(expectedCompressed)
                && got.length() == expectedCompressed.length();
        System.out.println((pass ? "PASS" : "FAIL") + "  " + name
                + "  got=" + got + " (len " + got.length() + ")"
                + "  expected=" + expectedCompressed);
        return pass ? 0 : 1;
    }

    public static void main(String[] args) {
        System.out.println("=== RLE string compression ===\n");
        int fails = 0;

        fails += check("ex sample", "aaabbccc", "a3b2c3");

        fails += check("T1 single letter", "a", "a");
        fails += check("T2 two same", "aa", "a2");
        fails += check("T3 all unique", "abc", "abc");
        fails += check("T4 ends with a singleton", "aaab", "a3b");
        fails += check("T5 starts with a singleton", "abbb", "ab3");
        fails += check("T6 two-digit count (10 a's)", "aaaaaaaaaa", "a10");
        fails += check("T7 three-digit count (100 a's)",
                repeat('a', 100), "a100");
        fails += check("T8 alternating", "ababab", "ababab");
        fails += check("T9 z's then a's", "zzza", "z3a");
        fails += check("T10 large n=100000 all 'x'",
                repeat('x', 100_000), "x100000");

        System.out.println(fails == 0 ? "\nAll extra tests passed." : "\nFailed: " + fails);
    }

    private static String repeat(char c, int n) {
        char[] buf = new char[n];
        java.util.Arrays.fill(buf, c);
        return new String(buf);
    }

    /**
     * Solution as submitted for competitive programming (stdin / stdout).
     */
    public static void mainTemplate(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String compressed = compress(br.readLine());
        bw.write(Integer.toString(compressed.length()));
        bw.newLine();
        bw.write(compressed);
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
    }
}
