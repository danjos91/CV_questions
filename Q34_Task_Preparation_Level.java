import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * Question 34: Task Preparation Level
 *
 * Russian (transliterated): "U slushatelya intensiva est spisok iz n zadach, po kotorym on poluchit q zaprosov...
 * Vasha zadacha — poschitat maksimalno vozmozhniy uroven podgotovki slushatelya intensiva."
 *
 * English: "An intensive course student has a list of n tasks and receives q queries.
 * Each query specifies a range [l, r], and its answer is the sum of tasks' usefulness in that range.
 * You can arbitrarily rearrange the tasks to maximize the sum of all query answers.
 * Find the maximum possible preparation level."
 *
 * Key insight:
 * - We need to place the tasks with the highest usefulness at the most frequently queried indices.
 * - To find the query frequency of each index efficiently in O(N + Q) time, we use a Difference Array.
 * - Sort both the frequencies array and the usefulness array, then multiply them pairwise.
 * 
 * Time: O(n log n + q)
 * Space: O(n)
 */
public class Q34_Task_Preparation_Level {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // Read n and q
        StringTokenizer st = new StringTokenizer(reader.readLine());
        if (!st.hasMoreTokens()) {
            return;
        }
        int n = Integer.parseInt(st.nextToken());
        int q = Integer.parseInt(st.nextToken());
        
        // Read the usefulness array a
        long[] a = new long[n];
        st = new StringTokenizer(reader.readLine());
        for (int i = 0; i < n; i++) {
            a[i] = Long.parseLong(st.nextToken());
        }
        
        // Difference array to calculate frequencies efficiently
        int[] diff = new int[n + 2]; // 1-indexed, up to n+1 to handle r+1 safely
        for (int i = 0; i < q; i++) {
            st = new StringTokenizer(reader.readLine());
            int l = Integer.parseInt(st.nextToken());
            int r = Integer.parseInt(st.nextToken());
            diff[l]++;
            diff[r + 1]--;
        }
        
        // Calculate prefix sums of the difference array to get actual frequencies
        long[] freq = new long[n];
        int currentFreq = 0;
        for (int i = 1; i <= n; i++) {
            currentFreq += diff[i];
            freq[i - 1] = currentFreq;
        }
        
        // Sort both arrays to maximize the sum of products
        Arrays.sort(a);
        Arrays.sort(freq);
        
        // Calculate the maximum possible score
        long maxScore = 0;
        for (int i = 0; i < n; i++) {
            maxScore += a[i] * freq[i];
        }
        
        // Write the result
        writer.write(String.valueOf(maxScore));
        writer.newLine();
        
        reader.close();
        writer.close();
    }
}
