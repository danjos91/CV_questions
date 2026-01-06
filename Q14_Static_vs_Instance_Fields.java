/**
 * Question 14: Static vs Instance Fields
 * 
 * Russian: "Что будет выведено при выполнении следующего кода?"
 * English: "What will be output when the following code is executed?"
 * 
 * Code:
 * class Test {
 *     public int field = 10;
 *     public static int staticField = 20;
 *     public void increment() {
 *         field++;
 *         staticField++;
 *     }
 * }
 * 
 * Test obj1 = new Test();
 * Test obj2 = new Test();
 * obj1.increment();
 * obj2.increment();
 * System.out.println(obj1.field + " " + obj2.field + " " + Test.staticField);
 * 
 * Options:
 * 1. 11 11 23
 * 2. 11 11 21
 * 3. 11 11 22
 * 4. 10 10 22
 * 5. 10 10 23
 * 
 * ANSWER: 11 11 22
 * 
 * EXPLANATION:
 * - obj1.field starts at 10, increment() makes it 11
 * - obj2.field starts at 10, increment() makes it 11
 * - staticField is shared: starts at 20, obj1.increment() makes it 21, 
 *   obj2.increment() makes it 22
 * - Each object has its own instance field, but shares static field
 */

public class Q14_Static_vs_Instance_Fields {
    static class Test {
        public int field = 10;
        public static int staticField = 20;
        
        public void increment() {
            field++;
            staticField++;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Static vs Instance Fields ===\n");
        
        Test obj1 = new Test();
        Test obj2 = new Test();
        
        System.out.println("Initial state:");
        System.out.println("obj1.field = " + obj1.field);
        System.out.println("obj2.field = " + obj2.field);
        System.out.println("Test.staticField = " + Test.staticField);
        
        System.out.println("\nCalling obj1.increment():");
        obj1.increment();
        System.out.println("obj1.field = " + obj1.field + " (instance - unique to obj1)");
        System.out.println("obj2.field = " + obj2.field + " (instance - unique to obj2)");
        System.out.println("Test.staticField = " + Test.staticField + " (static - shared)");
        
        System.out.println("\nCalling obj2.increment():");
        obj2.increment();
        System.out.println("obj1.field = " + obj1.field + " (instance - unique to obj1)");
        System.out.println("obj2.field = " + obj2.field + " (instance - unique to obj2)");
        System.out.println("Test.staticField = " + Test.staticField + " (static - shared)");
        
        System.out.println("\nFinal output:");
        System.out.println(obj1.field + " " + obj2.field + " " + Test.staticField);
        
        System.out.println("\n=== Key Points ===");
        System.out.println("Instance fields (field):");
        System.out.println("  - Each object has its own copy");
        System.out.println("  - obj1.field and obj2.field are independent");
        System.out.println("  - Changes to one don't affect the other");
        
        System.out.println("\nStatic fields (staticField):");
        System.out.println("  - Shared across ALL instances of the class");
        System.out.println("  - Only ONE copy exists for the entire class");
        System.out.println("  - Changes affect all instances");
        System.out.println("  - Accessed via ClassName.fieldName");
    }
}
