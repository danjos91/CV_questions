//Get two values of the array to get the sum
// Java version: Java 8+ compatible.

import java.util.*;

public class GoogleTask {
    public static void main(String[] args) {
        
    
    }

    public static boolean hasPairWithSum(List<Integer> arr, int sum) {
        Set<Integer> mySet = new HashSet<>();
        int len = mySet.size();

        for (int i=0; i<len; i++) {
            if (mySet.contains(arr.get(i))) {
                return true;
            }
            mySet.add(sum - arr.get(i));
        }
        return false;
    }
}
