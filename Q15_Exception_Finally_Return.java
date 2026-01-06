/**
 * Question 15: Exception Finally Return
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
 *         return someClass.i;
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
 * - someClass is null, so someClass.i throws NullPointerException
 * - NullPointerException is caught by catch(Exception e) → would return 2
 * - BUT finally block ALWAYS executes
 * - If finally has return, it OVERRIDES any previous return
 * - Result: 4 (from finally block)
 * - Note: catch(Throwable) is unreachable because Exception is a subclass of Throwable
 */

public class Q15_Exception_Finally_Return {
    static class SomeClass {
        static int i = 1;
    }
    
    static int foo() {
        try {
            SomeClass someClass = null;
            return someClass.i; // NullPointerException
        } catch(Exception e) {
            System.out.println("Caught Exception: " + e.getClass().getSimpleName());
            return 2;
        } catch(Throwable e) {
            // This catch is UNREACHABLE because Exception extends Throwable
            // Exception is caught first, so Throwable catch never executes
            return 3;
        } finally {
            System.out.println("Finally block executing");
            return 4; // This OVERRIDES any previous return
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Exception Handling with Finally Return ===\n");
        
        System.out.println("Execution flow:");
        System.out.println("1. someClass = null");
        System.out.println("2. someClass.i throws NullPointerException");
        System.out.println("3. catch(Exception e) catches it → would return 2");
        System.out.println("4. finally block ALWAYS executes");
        System.out.println("5. finally return 4 OVERRIDES previous return");
        
        System.out.println("\nCalling foo():");
        int result = foo();
        System.out.println("\nResult: " + result);
        
        System.out.println("\n=== Key Points ===");
        System.out.println("1. finally block ALWAYS executes (except System.exit())");
        System.out.println("2. If finally has return, it OVERRIDES try/catch returns");
        System.out.println("3. This is considered bad practice - avoid return in finally");
        System.out.println("4. catch(Throwable) is unreachable here (Exception caught first)");
        System.out.println("5. Static field access: someClass.i works even if someClass is null");
        
        System.out.println("\n=== Static Field Access with Null Reference ===");
        SomeClass obj = null;
        System.out.println("obj = null");
        System.out.println("obj.i = " + obj.i); // Works! Static fields don't need instance
        System.out.println("But obj.i in code throws NPE because obj is null");
        System.out.println("(Compiler doesn't optimize this)");
    }
}
