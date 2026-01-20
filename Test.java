import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class Test {
    public static void main(String[] args) throws IOException {
        try(BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            int n = Integer.parseInt(br.readLine());
            int k = Integer.parseInt(br.readLine());
            List<Integer> numbers = Arrays.stream(br.readLine().strip().split(" "))
                    .map(Integer::parseInt)
                    .toList();
            int maxValue = 0;
            for (int i=0; i<k; i++) {
                maxValue += numbers.get(i);
            }
            int sum = maxValue;
            for (int i=1; i<=k; i++) {
                sum = sum - numbers.get(k - i) + numbers.get(n - i);
                maxValue = Math.max(sum, maxValue);
            }
            System.out.println(maxValue);
        }
    }
}