/**
 * Question 11: Stream Skip and Limit
 * 
 * Russian: "Что выведет на экран код?"
 * English: "What will the code output to the screen?"
 * 
 * Code:
 * List<String> strings = Arrays.asList("a", "b", "c", "d", "e");
 * String result = strings.stream()
 *     .skip(2)
 *     .limit(2)
 *     .collect(Collectors.joining(","));
 * System.out.println(result);
 * 
 * Options:
 * 1. b,c
 * 2. d,e
 * 3. c,d,e
 * 4. c, d  (with space)
 * 5. a,b
 * 
 * ANSWER: c,d (Option 4, but without space - the delimiter is ",")
 * 
 * EXPLANATION:
 * - skip(2) skips first 2 elements: ["a", "b"] → remaining: ["c", "d", "e"]
 * - limit(2) takes next 2 elements: ["c", "d"]
 * - joining(",") joins with comma: "c,d"
 * - No spaces in the delimiter
 */

import java.util.*;
import java.util.stream.*;

public class Q11_Stream_SkipLimit {
    public static void main(String[] args) {
        System.out.println("=== Stream skip() and limit() ===\n");
        
        List<String> strings = Arrays.asList("a", "b", "c", "d", "e");
        System.out.println("Original list: " + strings);
        
        System.out.println("\nStep-by-step execution:");
        
        // Step 1: Create stream
        System.out.println("\n1. strings.stream()");
        System.out.println("   Stream: [a, b, c, d, e]");
        
        // Step 2: skip(2)
        System.out.println("\n2. .skip(2)");
        System.out.println("   Skips first 2 elements: [a, b]");
        System.out.println("   Remaining: [c, d, e]");
        
        // Step 3: limit(2)
        System.out.println("\n3. .limit(2)");
        System.out.println("   Takes next 2 elements");
        System.out.println("   Result: [c, d]");
        
        // Step 4: collect with joining
        System.out.println("\n4. .collect(Collectors.joining(\",\"))");
        System.out.println("   Joins with comma delimiter");
        
        String result = strings.stream()
            .skip(2)
            .limit(2)
            .collect(Collectors.joining(","));
        
        System.out.println("\nFinal result: \"" + result + "\"");
        System.out.println("Output: " + result);
        
        System.out.println("\n=== Key Points ===");
        System.out.println("1. skip(n) - skips first n elements");
        System.out.println("2. limit(n) - takes next n elements");
        System.out.println("3. Operations are chained: skip then limit");
        System.out.println("4. joining(\",\") - joins with comma, NO spaces");
        System.out.println("5. Stream operations are lazy - executed on terminal operation");
        
        // Additional examples
        System.out.println("\n=== Additional Examples ===");
        
        System.out.println("\nskip(1).limit(3):");
        String result2 = strings.stream()
            .skip(1)
            .limit(3)
            .collect(Collectors.joining(","));
        System.out.println("Result: " + result2); // b,c,d
        
        System.out.println("\nskip(0).limit(2):");
        String result3 = strings.stream()
            .skip(0)
            .limit(2)
            .collect(Collectors.joining(","));
        System.out.println("Result: " + result3); // a,b
        
        System.out.println("\nskip(4).limit(2):");
        String result4 = strings.stream()
            .skip(4)
            .limit(2)
            .collect(Collectors.joining(","));
        System.out.println("Result: " + result4); // e (only 1 element available)
    }
}
