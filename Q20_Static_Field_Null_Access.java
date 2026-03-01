/**
 * Question 20: Static Field Null Access
 * 
 * Russian: "Каков результат работы этого кода?"
 * English: "What is the result of this code?"
 * 
 * Code:
 * class SomeClass {
 *     static int i = 1;
 * }
 * 
 * static int foo() {
 *     try {
 *         SomeClass someClass = null;
 *         return someClass.i;  // Accessing static field via null reference
 *     } catch(Exception e) {
 *         return 2;
 *     } catch(Throwable e) {
 *         return 3;
 *     } finally {
 *         return 4;
 *     }
 * }
 * 
 * Options:
 * 1. 1
 * 2. 2
 * 3. 3
 * 4. 4
 * 5. Does not compile
 * 
 * ANSWER: 4
 * 
 * EXPLANATION:
 * - someClass is null
 * - someClass.i accesses static field via null reference
 * - This throws NullPointerException (even though static fields don't need instance)
 * - catch(Exception e) catches NPE → would return 2
 * - BUT finally block ALWAYS executes and returns 4
 * - finally return OVERRIDES try/catch return
 * - Note: catch(Throwable) is unreachable (Exception caught first)
 *
 * Java version: Java 8+ compatible.
 */

public class Q20_Static_Field_Null_Access {
    static class SomeClass {
        static int i = 1;
    }
    
    static int foo() {
        try {
            SomeClass someClass = null;
            System.out.println("Attempting: someClass.i where someClass = null");
            return someClass.i; // NullPointerException!
        } catch(Exception e) {
            System.out.println("Caught: " + e.getClass().getSimpleName());
            return 2;
        } catch(Throwable e) {
            // Unreachable - Exception caught first
            return 3;
        } finally {
            System.out.println("Finally block executing");
            return 4; // Overrides previous return
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Static Field Access with Null Reference ===\n");
        
        System.out.println("Important: Static fields CAN be accessed via class name:");
        System.out.println("  SomeClass.i = " + SomeClass.i + " ✓");
        
        System.out.println("\nBut accessing via null reference throws NPE:");
        System.out.println("  SomeClass obj = null;");
        System.out.println("  obj.i → NullPointerException");
        
        System.out.println("\nExecuting foo():");
        int result = foo();
        System.out.println("\nResult: " + result);
        
        System.out.println("\n=== Key Points ===");
        System.out.println("1. Static fields belong to CLASS, not instance");
        System.out.println("2. Should access via ClassName.field (SomeClass.i)");
        System.out.println("3. Accessing via null reference throws NPE");
        System.out.println("4. Compiler doesn't optimize this - runtime check");
        System.out.println("5. finally return overrides try/catch return");
        
        System.out.println("\n=== Correct Ways to Access ===");
        System.out.println("✓ SomeClass.i");
        System.out.println("✓ (new SomeClass()).i (but unnecessary)");
        System.out.println("✗ nullReference.i (throws NPE)");
    }
}
