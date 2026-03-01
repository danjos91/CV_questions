/**
 * Question 22: Exception Propagation
 * 
 * Russian: "Что будет выведено при выполнении следующего кода?"
 * English: "What will be output when the following code is executed?"
 * 
 * Code:
 * public static void main(String[] args) {
 *     try {
 *         bar();
 *     } catch(RuntimeException e) {
 *         System.out.println("main");
 *     }
 * }
 * 
 * public static void bar() {
 *     try {
 *         foo();
 *     } catch(Error e) {
 *         System.out.println("bar");
 *     }
 * }
 * 
 * public static void foo() {
 *     String name = null;
 *     System.out.println(name.length());  // NullPointerException
 *     System.out.println("foo");
 * }
 * 
 * Options:
 * 1. main
 * 2. bar, then foo, then main
 * 3. bar
 * 4. main, then foo, then bar
 * 5. foo
 * 
 * ANSWER: main
 * 
 * EXPLANATION:
 * - foo() throws NullPointerException (name is null)
 * - NPE is RuntimeException, not Error
 * - bar() catches Error, not RuntimeException → exception propagates
 * - main() catches RuntimeException → handles NPE
 * - Prints "main"
 * - "foo" is never printed (exception thrown before)
 *
 * Java version: Java 8+ compatible.
 */

public class Q22_Exception_Propagation {
    public static void main(String[] args) {
        demonstrateExceptionHierarchy();
        
        System.out.println("\n=== Exception Propagation Demo ===\n");
        
        try {
            bar();
        } catch(RuntimeException e) {
            System.out.println("main");
            System.out.println("  (Caught: " + e.getClass().getSimpleName() + ")");
        }
        
        System.out.println("\n=== Execution Flow ===");
        System.out.println("1. main() calls bar()");
        System.out.println("2. bar() calls foo()");
        System.out.println("3. foo() throws NullPointerException");
        System.out.println("4. bar() catches Error → doesn't match (NPE is RuntimeException)");
        System.out.println("5. Exception propagates to main()");
        System.out.println("6. main() catches RuntimeException → handles it");
        System.out.println("7. Prints \"main\"");
        System.out.println("8. \"foo\" never printed (exception before that line)");
    }
    
    public static void bar() {
        System.out.println("bar() called");
        try {
            foo();
        } catch(Error e) {
            System.out.println("bar");
            System.out.println("  (Caught Error, but NPE is RuntimeException)");
        }
        System.out.println("bar() - after try-catch (not reached)");
    }
    
    public static void foo() {
        System.out.println("foo() called");
        String name = null;
        System.out.println("Attempting name.length() where name = null");
        System.out.println(name.length()); // NullPointerException
        System.out.println("foo"); // Never reached
    }
    
    public static void demonstrateExceptionHierarchy() {
        System.out.println("\n=== Exception Hierarchy ===");
        System.out.println("Throwable");
        System.out.println("├── Error");
        System.out.println("│   ├── OutOfMemoryError");
        System.out.println("│   └── StackOverflowError");
        System.out.println("└── Exception");
        System.out.println("    └── RuntimeException");
        System.out.println("        ├── NullPointerException");
        System.out.println("        ├── IllegalArgumentException");
        System.out.println("        └── ...");
        
        System.out.println("\nNullPointerException:");
        System.out.println("  - Extends RuntimeException");
        System.out.println("  - NOT an Error");
        System.out.println("  - Unchecked exception");
    }
    
}
