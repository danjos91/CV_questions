/**
 * Question 10: Double Division and Casting
 * 
 * Russian: "Что выведет следующая программа?"
 * English: "What will the following program output?"
 * 
 * Code:
 * double d = 10.0 / 3;
 * int result = (int) d;
 * System.out.println(result);
 * 
 * Options:
 * 1. 10
 * 2. 3.0
 * 3. 3
 * 4. 3.33
 * 5. Error: incompatible types
 * 
 * ANSWER: 3
 * 
 * EXPLANATION:
 * - 10.0 / 3 performs floating-point division → 3.333...
 * - d = 3.3333333333333335 (approximately)
 * - (int) d truncates (not rounds) → 3
 * - Casting double to int removes decimal part
 * - No error - explicit cast is valid
 */

public class Q10_Double_Division_Casting {
    public static void main(String[] args) {
        System.out.println("=== Double Division and Casting ===\n");
        
        // Step 1: Floating-point division
        double d = 10.0 / 3;
        System.out.println("Step 1: double d = 10.0 / 3;");
        System.out.println("d = " + d);
        System.out.println("(10.0 / 3 = 3.3333333333333335...)");
        
        // Step 2: Cast to int (truncation)
        int result = (int) d;
        System.out.println("\nStep 2: int result = (int) d;");
        System.out.println("Casting truncates (removes decimal part)");
        System.out.println("result = " + result);
        
        // Step 3: Print result
        System.out.println("\nStep 3: System.out.println(result);");
        System.out.println("Output: " + result);
        
        System.out.println("\n=== Key Points ===");
        System.out.println("1. 10.0 / 3 → floating-point division → ~3.333...");
        System.out.println("2. (int) cast truncates, does NOT round");
        System.out.println("3. Truncation: 3.999 → 3 (not 4)");
        System.out.println("4. No compilation error - explicit cast is valid");
        System.out.println("5. If you want rounding: Math.round(d) or (int) Math.round(d)");
        
        // Additional examples
        System.out.println("\n=== Additional Examples ===");
        System.out.println("(int) 3.7 = " + (int) 3.7);      // 3 (truncate)
        System.out.println("(int) 3.2 = " + (int) 3.2);      // 3 (truncate)
        System.out.println("(int) 3.999 = " + (int) 3.999);  // 3 (truncate)
        System.out.println("(int) -3.7 = " + (int) -3.7);    // -3 (truncate)
        
        System.out.println("\nFor rounding:");
        System.out.println("Math.round(3.7) = " + Math.round(3.7));      // 4
        System.out.println("Math.round(3.2) = " + Math.round(3.2));      // 3
        System.out.println("(int) Math.round(3.7) = " + (int) Math.round(3.7)); // 4
    }
}
