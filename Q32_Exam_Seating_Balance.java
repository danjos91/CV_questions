/**
 * Question 32: Exam Seating Balance (Rassadka studentov)
 *
 * Russian (transliterated): "Nuzhno rassadit n studentov po ryadam tak, chtoby:
 *                           1) v lyubyh dvuh ryadah chisla studentov otlichalis
 *                              ne bolee chem na 1;
 *                           2) esli est ryady raznoy dliny, to v lyubyh dvuh
 *                              sosednih ryadah chisla studentov dolzhny
 *                              razlichatsya;
 *                           Trebuetsya minimizirovat |chislo ryadov - maksimum
 *                              studentov v ryadu|."
 *
 * English: "We need to seat n students in rows so that:
 *           1) the sizes of any two rows differ by at most 1;
 *           2) if rows of two sizes exist, then every pair of adjacent rows
 *              must have different sizes;
 *           Minimize |number of rows - maximum students in a row|."
 *
 * Problem:
 * - Input: one integer n, 1 <= n <= 10^12
 * - Let:
 *     r = number of rows
 *     c = maximum number of students in one row
 * - Because row sizes differ by at most 1, every row has either c or c - 1 students
 * - If both sizes exist, they must alternate, so the number of short rows can only be:
 *     0, floor(r / 2), or ceil(r / 2)
 * - We need the minimum possible value of |r - c|
 *
 * Key insight:
 * - Any valid arrangement can be written as:
 *     n = r * c - s
 *   where s is the number of shortened rows and
 *     s in {0, floor(r / 2), ceil(r / 2)}
 * - Therefore, for a fixed number of rows r:
 *     c = ceil(n / r)
 *   and we only need to check whether the missing amount
 *     s = r * c - n
 *   belongs to the valid set above
 * - It is enough to iterate up to O(sqrt(n)), because in an optimal valid pair
 *   at least one of r or c is at most about sqrt(2n)
 *
 * Time:  O(sqrt(n))
 * Space: O(1)
 *
 * Java version: Java 8+ compatible.
 */
public class Q32_Exam_Seating_Balance {

    /**
     * Optimal solution for n up to 10^12.
     */
    public static long solveOptimal(long n) {
        long best = Long.MAX_VALUE;
        long limit = integerSqrt(2L * n) + 2;

        for (long rows = 1; rows <= limit; rows++) {
            long maxInRow = (n + rows - 1) / rows;
            long shortenedRows = rows * maxInRow - n;

            if (shortenedRows == 0 || shortenedRows == rows / 2 || shortenedRows == (rows + 1) / 2) {
                best = Math.min(best, Math.abs(rows - maxInRow));
            }
        }

        for (long maxInRow = 1; maxInRow <= limit; maxInRow++) {
            if (n % maxInRow == 0) {
                long rows = n / maxInRow;
                best = Math.min(best, Math.abs(rows - maxInRow));
            }

            long denominator = 2 * maxInRow - 1;

            if (n % denominator == 0) {
                long half = n / denominator;
                long rows = 2 * half;
                best = Math.min(best, Math.abs(rows - maxInRow));
            }

            if (n >= maxInRow && (n - maxInRow) % denominator == 0) {
                long half = (n - maxInRow) / denominator;
                long rows = 2 * half + 1;
                best = Math.min(best, Math.abs(rows - maxInRow));
            }

            if (n >= maxInRow && (n - (maxInRow - 1)) % denominator == 0) {
                long half = (n - (maxInRow - 1)) / denominator;
                if (half > 0) {
                    long rows = 2 * half + 1;
                    best = Math.min(best, Math.abs(rows - maxInRow));
                }
            }
        }

        return best;
    }

    private static long integerSqrt(long value) {
        long root = (long) Math.sqrt(value);

        while ((root + 1) * (root + 1) <= value) {
            root++;
        }

        while (root * root > value) {
            root--;
        }

        return root;
    }

    public static void main(String[] args) {
        long n1 = 3;
        System.out.println("Example 1:");
        System.out.println("Input:    " + n1);
        System.out.println("Output:   " + solveOptimal(n1));
        System.out.println("Expected: 0");
        System.out.println();

        long n2 = 50;
        System.out.println("Example 2:");
        System.out.println("Input:    " + n2);
        System.out.println("Output:   " + solveOptimal(n2));
        System.out.println("Expected: 3");
        System.out.println();

        long n3 = 1;
        System.out.println("Example 3:");
        System.out.println("Input:    " + n3);
        System.out.println("Output:   " + solveOptimal(n3));
        System.out.println("Expected: 0");
    }
}
