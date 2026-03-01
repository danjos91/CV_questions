/**
 * Question 09: Unchecked Exceptions
 * 
 * Russian: "Какие исключения считаются непроверяемыми?"
 * English: "Which exceptions are considered unchecked?"
 * 
 * Options:
 * 1. All, except user-defined exceptions
 * 2. Descendants of the RuntimeException class
 * 3. Descendants of the Throwable class
 * 4. Descendants of the Exception class
 * 5. Descendants of the Error class
 * 
 * ANSWER: Option 2 - Descendants of RuntimeException (and Error)
 * 
 * EXPLANATION:
 * - Unchecked exceptions = RuntimeException and its subclasses + Error and its subclasses
 * - Checked exceptions = Exception and its subclasses EXCEPT RuntimeException
 * - RuntimeException subclasses: NullPointerException, IllegalArgumentException, etc.
 * - Error subclasses: OutOfMemoryError, StackOverflowError, etc.
 * - Throwable is the root - includes both Exception and Error
 * - User-defined exceptions can be checked or unchecked depending on inheritance
 *
 * Java version: Java 8+ compatible.
 */

public class Q09_Unchecked_Exceptions {
    public static void main(String[] args) {
        System.out.println("=== Java Exception Hierarchy ===\n");
        
        System.out.println("Throwable (root)");
        System.out.println("├── Error (unchecked)");
        System.out.println("│   ├── OutOfMemoryError");
        System.out.println("│   ├── StackOverflowError");
        System.out.println("│   └── ...");
        System.out.println("└── Exception");
        System.out.println("    ├── RuntimeException (unchecked)");
        System.out.println("    │   ├── NullPointerException");
        System.out.println("    │   ├── IllegalArgumentException");
        System.out.println("    │   ├── ArrayIndexOutOfBoundsException");
        System.out.println("    │   └── ...");
        System.out.println("    └── Checked Exceptions");
        System.out.println("        ├── IOException");
        System.out.println("        ├── SQLException");
        System.out.println("        └── ...");
        
        System.out.println("\n=== Demonstrating Unchecked Exceptions ===\n");
        
        // RuntimeException examples (unchecked)
        System.out.println("1. RuntimeException (unchecked):");
        try {
            String str = null;
            int length = str.length(); // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("   ✓ NullPointerException - unchecked, no 'throws' required");
        }
        
        try {
            int[] arr = new int[5];
            int value = arr[10]; // ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("   ✓ ArrayIndexOutOfBoundsException - unchecked");
        }
        
        // Error examples (unchecked)
        System.out.println("\n2. Error (unchecked):");
        System.out.println("   ✓ OutOfMemoryError - unchecked");
        System.out.println("   ✓ StackOverflowError - unchecked");
        System.out.println("   ✓ VirtualMachineError - unchecked");
        
        // Checked exception example
        System.out.println("\n3. Checked Exception:");
        System.out.println("   ✗ IOException - checked, must be handled or declared");
        System.out.println("   ✗ SQLException - checked, must be handled or declared");
        
        System.out.println("\n=== Key Points ===");
        System.out.println("Unchecked Exceptions:");
        System.out.println("  - RuntimeException and all its subclasses");
        System.out.println("  - Error and all its subclasses");
        System.out.println("  - Do NOT need to be declared in 'throws' clause");
        System.out.println("  - Do NOT need try-catch (but can use it)");
        System.out.println("  - Compiler does NOT check for handling");
        
        System.out.println("\nChecked Exceptions:");
        System.out.println("  - Exception and subclasses EXCEPT RuntimeException");
        System.out.println("  - MUST be handled (try-catch) or declared (throws)");
        System.out.println("  - Compiler ENFORCES handling");
        
        System.out.println("\n=== User-Defined Exceptions ===");
        System.out.println("Can be checked or unchecked:");
        System.out.println("  - extends Exception → checked");
        System.out.println("  - extends RuntimeException → unchecked");
    }
}

// Example user-defined exceptions
class MyCheckedException extends Exception {
    // Checked exception
}

class MyUncheckedException extends RuntimeException {
    // Unchecked exception
}
