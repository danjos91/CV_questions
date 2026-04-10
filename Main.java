import java.io.*;
import java.util.*;

public class Main {

    static int lowerBound(int[] arr, int target) {
        int left = 0;
        int right = arr.length;
        while (left < right) {
            int mid = (left + right) / 2;
            if (arr[mid] < target) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[][] a = new int[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                a[i][j] = Integer.parseInt(st.nextToken());
            }
            Arrays.sort(a[i]);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(x -> x[0]));
        int currentMax = Integer.MIN_VALUE;
        long sum = 0;
        long bestDiff = Long.MAX_VALUE;
        long bestSum = Long.MAX_VALUE;
        int bestLeft = 0;

        for (int i = 0; i < n; i++) {
            pq.add(new int[] {a[i][0], i, 0});
            sum += a[i][0];
            currentMax = Math.max(currentMax, a[i][0]);
        }

        while (true) {
            int[] min = pq.poll();
            long diff = (long) currentMax - min[0];

            if (diff < bestDiff || diff == bestDiff && sum < bestSum) {
                bestDiff = diff;
                bestSum = sum;
                bestLeft = min[0];
            }

            int era = min[1];
            int index = min[2] + 1;
            if (index == m) {
                break;
            }

            sum -= min[0];
            int nextValue = a[era][index];
            pq.add(new int[] {nextValue, era, index});
            sum += nextValue;
            currentMax = Math.max(currentMax, nextValue);
        }

        int[] ans = new int[n];
        for (int i = 0; i < n; i++) {
            ans[i] = a[i][lowerBound(a[i], bestLeft)];
        }
        Arrays.sort(ans);

        for (int i = 0; i < n; i++) {
            if (i > 0) {
                bw.write(" ");
            }
            bw.write(String.valueOf(ans[i]));
        }
        bw.newLine();
        bw.flush();
        br.close();
        bw.close();
    }
}
