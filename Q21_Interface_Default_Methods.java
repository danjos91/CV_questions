/**
 * Question 21: Interface Default Methods and Inheritance
 * 
 * Russian: "Что выведет на экран программа?"
 * English: "What will the program output to the screen?"
 * 
 * Code:
 * interface I {
 *     default void method() {
 *         System.out.println("I");
 *     }
 * }
 * 
 * class A implements I {
 *     // No override - uses default from I
 * }
 * 
 * class B extends A {
 *     @Override
 *     public void method() {
 *         System.out.println("B");
 *     }
 * }
 * 
 * B b = new B();
 * ((A) b).method();
 * 
 * Options:
 * 1. Only B
 * 2. Compilation error
 * 3. B, then I
 * 4. Only I
 * 5. I, then B
 * 
 * ANSWER: Only B
 * 
 * EXPLANATION:
 * - Method resolution is based on RUNTIME type, not compile-time type
 * - b is instance of B
 * - Casting to A doesn't change the actual object type
 * - B overrides method() → prints "B"
 * - Default method from I is NOT called (overridden in B)
 * - Output: "B"
 */

public class Q21_Interface_Default_Methods {
    interface I {
        default void method() {
            System.out.println("I");
        }
    }
    
    static class A implements I {
        // Inherits default method from I
    }
    
    static class B extends A {
        @Override
        public void method() {
            System.out.println("B");
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Interface Default Methods and Polymorphism ===\n");
        
        B b = new B();
        System.out.println("Created: B b = new B();");
        System.out.println("Runtime type: B");
        System.out.println("Compile-time type after cast: A");
        
        System.out.println("\nCalling: ((A) b).method();");
        System.out.println("Note: Cast doesn't change runtime type!");
        
        ((A) b).method();
        
        System.out.println("\n=== Key Points ===");
        System.out.println("1. Method resolution uses RUNTIME type (dynamic dispatch)");
        System.out.println("2. Casting to A doesn't change actual object type");
        System.out.println("3. B overrides method() → B's version is called");
        System.out.println("4. Default method from I is NOT called (overridden)");
        System.out.println("5. Output: \"B\" (only B, not I)");
        
        System.out.println("\n=== Inheritance Chain ===");
        System.out.println("I (interface)");
        System.out.println("  └─ default method() → \"I\"");
        System.out.println("A (implements I)");
        System.out.println("  └─ inherits default method()");
        System.out.println("B (extends A)");
        System.out.println("  └─ overrides method() → \"B\"");
        System.out.println("\nWhen calling on B instance: B's override wins");
        
        System.out.println("\n=== Testing Different Scenarios ===");
        A a = new A();
        System.out.println("\nA a = new A();");
        System.out.print("a.method() → ");
        a.method(); // Uses default from I
        
        I iRef = new B();
        System.out.println("\nI iRef = new B();");
        System.out.print("iRef.method() → ");
        iRef.method(); // Still calls B's override
    }
}
