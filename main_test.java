import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class main_test {
    private static final long MOD1 = 1_000_000_007L;
    private static final long MOD2 = 1_000_000_009L;
    private static final long BASE = 911_382_323L;

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

        String s = reader.readLine();
        int n = s.length();

        List<Integer>[] positions = createPositionLists();
        for (int i = 0; i < 26; i++) {
            positions[i] = new ArrayList<>();
        }

        for (int i = 0; i < n; i++) {
            positions[s.charAt(i) - 'a'].add(i);
        }

        int maxFrequency = 0;
        for (int i = 0; i < 26; i++) {
            maxFrequency = Math.max(maxFrequency, positions[i].size());
        }

        long[] power1 = new long[n + 1];
        long[] power2 = new long[n + 1];
        long[] prefix1 = new long[n + 1];
        long[] prefix2 = new long[n + 1];
        buildHashes(s, power1, power2, prefix1, prefix2);

        int answer = 0;
        for (int letter = 0; letter < 26; letter++) {
            if (positions[letter].size() != maxFrequency) {
                continue;
            }

            List<Integer> starts = positions[letter];
            int minGap = n;
            for (int i = 1; i < starts.size(); i++) {
                minGap = Math.min(minGap, starts.get(i) - starts.get(i - 1));
            }

            int commonPrefixLength = longestCommonPrefixAmongAll(
                starts,
                n,
                power1,
                power2,
                prefix1,
                prefix2
            );

            answer = Math.max(answer, Math.min(minGap, commonPrefixLength));
        }

        writer.write(String.valueOf(answer));
        writer.newLine();

        reader.close();
        writer.close();
    }

    @SuppressWarnings("unchecked")
    private static List<Integer>[] createPositionLists() {
        return (List<Integer>[]) new ArrayList[26];
    }

    private static void buildHashes(
        String s,
        long[] power1,
        long[] power2,
        long[] prefix1,
        long[] prefix2
    ) {
        power1[0] = 1;
        power2[0] = 1;

        for (int i = 0; i < s.length(); i++) {
            int value = s.charAt(i) - 'a' + 1;
            power1[i + 1] = (power1[i] * BASE) % MOD1;
            power2[i + 1] = (power2[i] * BASE) % MOD2;
            prefix1[i + 1] = (prefix1[i] * BASE + value) % MOD1;
            prefix2[i + 1] = (prefix2[i] * BASE + value) % MOD2;
        }
    }

    private static int longestCommonPrefixAmongAll(
        List<Integer> starts,
        int n,
        long[] power1,
        long[] power2,
        long[] prefix1,
        long[] prefix2
    ) {
        int left = 0;
        int right = n - starts.get(starts.size() - 1);

        while (left < right) {
            int mid = left + (right - left + 1) / 2;
            if (allPrefixesEqual(starts, mid, power1, power2, prefix1, prefix2)) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }

    private static boolean allPrefixesEqual(
        List<Integer> starts,
        int length,
        long[] power1,
        long[] power2,
        long[] prefix1,
        long[] prefix2
    ) {
        int firstStart = starts.get(0);
        long hash1 = getHash(prefix1, power1, firstStart, firstStart + length, MOD1);
        long hash2 = getHash(prefix2, power2, firstStart, firstStart + length, MOD2);

        for (int i = 1; i < starts.size(); i++) {
            int start = starts.get(i);
            if (getHash(prefix1, power1, start, start + length, MOD1) != hash1
                || getHash(prefix2, power2, start, start + length, MOD2) != hash2) {
                return false;
            }
        }

        return true;
    }

    private static long getHash(long[] prefix, long[] power, int left, int right, long mod) {
        long value = (prefix[right] - (prefix[left] * power[right - left]) % mod) % mod;
        return value < 0 ? value + mod : value;
    }
}
