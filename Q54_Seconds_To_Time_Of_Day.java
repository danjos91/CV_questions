/**
 * Question 54: Seconds offset to time of day (hh:mm:ss)
 *
 * Russian: "Число секунд от 00:00:00 01.01.2024 (может быть отрицательным)
 *          перевести во время суток hh:mm:ss. Дату не выводить."
 * English: "Convert a (possibly negative) second offset from Vitalik’s epoch
 *          00:00:00 01.01.2024 into the time of day hh:mm:ss. Ignore the date
 *          and DST."
 *
 * Example 1:  1000 → 00:16:40
 * Example 2: -1000 → 23:43:20   (86400 − 1000 = 85400 s → 23:43:20)
 *
 * ALGORITHM NAME:
 * Modular (clock) arithmetic; normalize remainder to [0, m).
 *
 * Problem class: convert a second count to civil time-of-day. Only the
 * 24-hour cycle is needed, so t ≡ t (mod 86400).
 *
 * Java pitfall: a % m is negative when a is negative. Always:
 *   t %= 86400; if (t < 0) t += 86400;
 *
 * WHERE TO READ IN SKIENA, 3rd edition:
 *
 *   Ch. 2  Algorithm Analysis — O(1) arithmetic; remainder as a reduction.
 *   Catalog, Combinatorial Problems — “Calendrical Calculations”: mapping
 *          a second/day count onto a clock/calendar. Here we drop the date
 *          and keep only hh:mm:ss.
 *   This is not searching, DP, or graphs.
 *
 * Java version: stdin template uses BufferedReader (Java 8+).
 */

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Q54_Seconds_To_Time_Of_Day {

    public static String solve(long t) {
        final long DAY = 24L * 60 * 60;
        t %= DAY;
        if (t < 0) {
            t += DAY;
        }
        long hours = t / 3600;
        t %= 3600;
        long minutes = t / 60;
        long seconds = t % 60;
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }

    private static int check(String name, String expected, long t) {
        String got = solve(t);
        boolean pass = expected.equals(got);
        System.out.println((pass ? "PASS" : "FAIL") + "  " + name
                + "  t=" + t + "  got=" + got + "  expected=" + expected);
        return pass ? 0 : 1;
    }

    public static void main(String[] args) {
        System.out.println("=== Seconds offset → hh:mm:ss ===\n");
        int fails = 0;

        fails += check("ex1", "00:16:40", 1000);
        fails += check("ex2", "23:43:20", -1000);

        fails += check("T1 midnight", "00:00:00", 0);
        fails += check("T2 last second of the day", "23:59:59", 86399);
        fails += check("T3 exactly one day wraps to midnight", "00:00:00", 86400);
        fails += check("T4 one second after wrap", "00:00:01", 86401);
        fails += check("T5 minus one second", "23:59:59", -1);
        fails += check("T6 minus one full day", "00:00:00", -86400);
        fails += check("T7 minus one day and one second", "23:59:59", -86401);
        fails += check("T8 one hour", "01:00:00", 3600);
        fails += check("T9 ten days + 1h1m1s", "01:01:01", 10L * 86400 + 3661);
        long minNorm = Long.MIN_VALUE % 86400;
        if (minNorm < 0) {
            minNorm += 86400;
        }
        String minExpected = String.format("%02d:%02d:%02d",
                minNorm / 3600, (minNorm % 3600) / 60, minNorm % 60);
        fails += check("T10 Long.MIN_VALUE (negative remainder)", minExpected, Long.MIN_VALUE);

        System.out.println(fails == 0 ? "\nAll extra tests passed." : "\nFailed: " + fails);
    }

    /**
     * Solution as submitted for competitive programming (stdin / stdout).
     */
    public static void mainTemplate(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        long t = Long.parseLong(br.readLine().trim());
        bw.write(solve(t));
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
    }
}
