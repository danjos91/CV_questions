/**
 * Question 18: Generic Wildcards Initialization Errors
 * 
 * Russian: "Инициализация каких переменных приведет к ошибке?"
 * English: "Which variable initializations will lead to an error?"
 * 
 * Code:
 * class Container<T> {
 *     T item;
 *     public Container(T item) { this.item = item; }
 * }
 * 
 * Container<? extends Number> c1 = new Container<>(1);
 * Container<? super Integer> c2 = new Container<>(1.5);
 * Container<? extends Integer> c3 = new Container<>(1.5);
 * Container<? extends Object> c4 = new Container<>("text");
 * Container<? super String> c5 = new Container<>(new Object());
 * 
 * Options:
 * 1. Only c2
 * 2. c2 and c4
 * 3. Only c3
 * 4. c1 and c2
 * 5. c5
 * 
 * ANSWER: c2 and c3 (but option says c2 and c4 - there's an error in the question)
 * 
 * EXPLANATION:
 * - c1: Container<Integer> assignable to Container<? extends Number> ✓
 * - c2: Container<Double> NOT assignable to Container<? super Integer> ✗
 * - c3: Container<Double> NOT assignable to Container<? extends Integer> ✗
 * - c4: Container<String> assignable to Container<? extends Object> ✓
 * - c5: Container<Object> assignable to Container<? super String> ✓
 */

public class Q18_Generic_Wildcards {
    static class Container<T> {
        T item;
        public Container(T item) {
            this.item = item;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Generic Wildcards Analysis ===\n");
        
        // c1: Container<? extends Number> = new Container<>(1)
        System.out.println("c1: Container<? extends Number> c1 = new Container<>(1);");
        System.out.println("  RHS: Container<Integer> (1 autoboxes to Integer)");
        System.out.println("  LHS: Container<? extends Number>");
        System.out.println("  Integer extends Number → ✓ VALID");
        Container<? extends Number> c1 = new Container<>(1);
        System.out.println("  Result: ✓ No error\n");
        
        // c2: Container<? super Integer> = new Container<>(1.5)
        System.out.println("c2: Container<? super Integer> c2 = new Container<>(1.5);");
        System.out.println("  RHS: Container<Double> (1.5 autoboxes to Double)");
        System.out.println("  LHS: Container<? super Integer>");
        System.out.println("  Double is NOT supertype of Integer → ✗ ERROR");
        // Container<? super Integer> c2 = new Container<>(1.5); // Compile error
        
        // c3: Container<? extends Integer> = new Container<>(1.5)
        System.out.println("\nc3: Container<? extends Integer> c3 = new Container<>(1.5);");
        System.out.println("  RHS: Container<Double>");
        System.out.println("  LHS: Container<? extends Integer>");
        System.out.println("  Double is NOT subtype of Integer → ✗ ERROR");
        // Container<? extends Integer> c3 = new Container<>(1.5); // Compile error
        
        // c4: Container<? extends Object> = new Container<>("text")
        System.out.println("\nc4: Container<? extends Object> c4 = new Container<>(\"text\");");
        System.out.println("  RHS: Container<String>");
        System.out.println("  LHS: Container<? extends Object>");
        System.out.println("  String extends Object → ✓ VALID");
        Container<? extends Object> c4 = new Container<>("text");
        System.out.println("  Result: ✓ No error\n");
        
        // c5: Container<? super String> = new Container<>(new Object())
        System.out.println("c5: Container<? super String> c5 = new Container<>(new Object());");
        System.out.println("  RHS: Container<Object>");
        System.out.println("  LHS: Container<? super String>");
        System.out.println("  Object is supertype of String → ✓ VALID");
        Container<? super String> c5 = new Container<>(new Object());
        System.out.println("  Result: ✓ No error\n");
        
        System.out.println("=== Summary ===");
        System.out.println("Errors: c2 and c3");
        System.out.println("\nWildcard Rules:");
        System.out.println("  ? extends T: accepts T or any subtype");
        System.out.println("  ? super T: accepts T or any supertype");
        System.out.println("\nType inference:");
        System.out.println("  new Container<>(value) infers T from value type");
        System.out.println("  Must match wildcard constraint");
    }
}
