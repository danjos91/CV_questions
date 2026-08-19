import java.io.*;
import java.util.*;

public class Main {
// Выбрать три предмета, сумма нагрузок которых ближе всего к X.
// 3 ≤ N ≤ 10_000, |a_i| ≤ 1e9, |X| ≤ 1e9.
// Сумма трёх чисел может не влезть в int → long.
// Сортировка + два указателя: O(N^2).

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        long x = Long.parseLong(st.nextToken());

        st = new StringTokenizer(br.readLine());
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(a);

        long bestDiff = Long.MAX_VALUE;
        long b0 = 0, b1 = 0, b2 = 0;

        for (int i = 0; i < n - 2; i++) {
            int l = i + 1;
            int r = n - 1;
            while (l < r) {
                long sum = a[i] + a[l] + a[r];
                long diff = Math.abs(sum - x);
                if (diff < bestDiff) {
                    bestDiff = diff;
                    b0 = a[i];
                    b1 = a[l];
                    b2 = a[r];
                    if (diff == 0) {
                        break;
                    }
                }
                if (sum < x) {
                    l++;
                } else {
                    r--;
                }
            }
            if (bestDiff == 0) {
                break;
            }
        }

        bw.write(b0 + " " + b1 + " " + b2);
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
    }
}
