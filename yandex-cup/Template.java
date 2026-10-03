import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;

/**
 * Yandex Cup contest template (Java 21).
 *
 * Copy this file, rename the class to the problem letter, write the solution
 * inside {@link #solve()}. Everything else stays as is.
 *
 * What it gives you:
 *  - byte-level stdin reader (10^6 ints in well under a second; Scanner would TLE)
 *  - PrintWriter with a single flush at the end
 *  - main runs in a thread with a 512 MB stack, so recursive DFS on 10^6 nodes works
 *  - sort() for int[]/long[] that shuffles before Arrays.sort — Arrays.sort on
 *    primitives is dual-pivot quicksort and is regularly attacked with
 *    anti-quicksort tests on Yandex Contest / Codeforces
 *  - hash mixer for HashMap keys when the key set may be adversarial
 *  - small math helpers that show up in A–E problems every year
 *
 * Usage locally:
 *   javac Template.java && java Template < in.txt
 */
public class Template {

    // ------------------------------------------------------------------
    // Solution goes here
    // ------------------------------------------------------------------
    static void solve() throws IOException {
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) a[i] = in.nextLong();
        sort(a);
        long sum = 0;
        for (long x : a) sum += x;
        out.println(sum);
    }

    // ------------------------------------------------------------------
    // Boilerplate — do not touch during the contest
    // ------------------------------------------------------------------
    static FastReader in;
    static PrintWriter out;

    public static void main(String[] args) throws Exception {
        in = new FastReader(System.in);
        out = new PrintWriter(System.out);
        Thread t = new Thread(null, () -> {
            try {
                solve();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, "solve", 1L << 29);
        t.start();
        t.join();
        out.flush();
    }

    // ------------------------------------------------------------------
    // Sorting that survives anti-quicksort tests
    // ------------------------------------------------------------------
    static final Random RNG = new Random(0x5DEECE66DL ^ System.nanoTime());

    static void sort(int[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = RNG.nextInt(i + 1);
            int tmp = a[i]; a[i] = a[j]; a[j] = tmp;
        }
        Arrays.sort(a);
    }

    static void sort(long[] a) {
        for (int i = a.length - 1; i > 0; i--) {
            int j = RNG.nextInt(i + 1);
            long tmp = a[i]; a[i] = a[j]; a[j] = tmp;
        }
        Arrays.sort(a);
    }

    // Arrays.sort on Object[] is TimSort (stable, O(n log n) worst case) — safe.
    // For sorting by a key, prefer int[][] pairs + Arrays.sort(a, (x, y) -> ...)
    // over creating small objects; it's noticeably faster on 10^6 elements.

    // ------------------------------------------------------------------
    // Hashing: use as HashMap<Long, ...> key via mix(key) when keys are adversarial
    // ------------------------------------------------------------------
    static final long HASH_SALT = RNG.nextLong();

    static long mix(long x) {
        x += HASH_SALT + 0x9E3779B97F4A7C15L;
        x = (x ^ (x >>> 30)) * 0xBF58476D1CE4E5B9L;
        x = (x ^ (x >>> 27)) * 0x94D049BB133111EBL;
        return x ^ (x >>> 31);
    }

    // ------------------------------------------------------------------
    // Math helpers
    // ------------------------------------------------------------------
    static final long MOD = 998_244_353L; // or 1_000_000_007L — check the statement

    static long gcd(long a, long b) {
        while (b != 0) { long t = a % b; a = b; b = t; }
        return Math.abs(a);
    }

    static long modPow(long base, long exp, long mod) {
        long result = 1;
        base %= mod;
        if (base < 0) base += mod;
        while (exp > 0) {
            if ((exp & 1) == 1) result = result * base % mod;
            base = base * base % mod;
            exp >>= 1;
        }
        return result;
    }

    static long modInv(long a, long mod) { // mod must be prime
        return modPow(a, mod - 2, mod);
    }

    /** Smallest index i in [lo, hi) with pred(i) true; returns hi if none. */
    interface IntPred { boolean test(int i); }

    static int lowerBound(int lo, int hi, IntPred pred) {
        while (lo < hi) {
            int mid = lo + ((hi - lo) >>> 1);
            if (pred.test(mid)) hi = mid; else lo = mid + 1;
        }
        return lo;
    }

    // ------------------------------------------------------------------
    // Fast input
    // ------------------------------------------------------------------
    static final class FastReader {
        private static final int BUFFER_SIZE = 1 << 16;
        private final DataInputStream din;
        private final byte[] buffer = new byte[BUFFER_SIZE];
        private int bufferPointer, bytesRead;

        FastReader(InputStream stream) {
            din = new DataInputStream(stream);
        }

        private byte read() throws IOException {
            if (bufferPointer == bytesRead) fillBuffer();
            return buffer[bufferPointer++];
        }

        private void fillBuffer() throws IOException {
            bytesRead = din.read(buffer, bufferPointer = 0, BUFFER_SIZE);
            if (bytesRead == -1) { bytesRead = 0; buffer[0] = -1; }
        }

        private byte skipBlanks() throws IOException {
            byte c = read();
            while (c == ' ' || c == '\n' || c == '\r' || c == '\t') c = read();
            return c;
        }

        int nextInt() throws IOException {
            return (int) nextLong();
        }

        long nextLong() throws IOException {
            byte c = skipBlanks();
            boolean neg = c == '-';
            if (neg) c = read();
            long ret = 0;
            while (c >= '0' && c <= '9') {
                ret = ret * 10 + (c - '0');
                c = read();
            }
            return neg ? -ret : ret;
        }

        double nextDouble() throws IOException {
            return Double.parseDouble(next());
        }

        /** Next whitespace-separated token. */
        String next() throws IOException {
            byte c = skipBlanks();
            StringBuilder sb = new StringBuilder();
            while (c != -1 && c != ' ' && c != '\n' && c != '\r' && c != '\t') {
                sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }

        /** Rest of the current line (without the line terminator). */
        String nextLine() throws IOException {
            StringBuilder sb = new StringBuilder();
            byte c = read();
            while (c != -1 && c != '\n') {
                if (c != '\r') sb.append((char) c);
                c = read();
            }
            return sb.toString();
        }

        int[] nextIntArray(int n) throws IOException {
            int[] a = new int[n];
            for (int i = 0; i < n; i++) a[i] = nextInt();
            return a;
        }

        long[] nextLongArray(int n) throws IOException {
            long[] a = new long[n];
            for (int i = 0; i < n; i++) a[i] = nextLong();
            return a;
        }
    }
}
