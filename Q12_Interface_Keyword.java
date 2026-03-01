/**
 * Question 12: Interface Keyword
 * 
 * Russian: "С помощью какого ключевого слова можно создать интерфейс в Java?"
 * English: "Which keyword can be used to create an interface in Java?"
 * 
 * Options:
 * 1. interface
 * 2. implements
 * 3. abstract
 * 4. enum
 * 5. class
 * 
 * ANSWER: interface
 * 
 * EXPLANATION:
 * - 'interface' is the keyword to declare an interface
 * - 'implements' is used to implement an interface (not declare it)
 * - 'abstract' is a modifier for classes/methods (not a keyword for interfaces)
 * - 'enum' is for creating enumerated types
 * - 'class' is for creating classes
 *
 * Java version: Java 8+ compatible.
 */

public class Q12_Interface_Keyword {
    public static void main(String[] args) {
        System.out.println("=== Java Interface Keyword ===\n");
        
        System.out.println("Correct way to create an interface:");
        System.out.println("  interface MyInterface { }");
        
        System.out.println("\n=== Keyword Usage ===");
        System.out.println("1. 'interface' - declares an interface ✓");
        System.out.println("   Example: interface Drawable { }");
        
        System.out.println("\n2. 'implements' - implements an interface ✗");
        System.out.println("   Example: class Circle implements Drawable { }");
        System.out.println("   (Used to implement, not declare)");
        
        System.out.println("\n3. 'abstract' - modifier for abstract classes/methods ✗");
        System.out.println("   Example: abstract class Shape { }");
        System.out.println("   (Modifier, not interface keyword)");
        
        System.out.println("\n4. 'enum' - creates enumerated types ✗");
        System.out.println("   Example: enum Color { RED, BLUE }");
        System.out.println("   (For enums, not interfaces)");
        
        System.out.println("\n5. 'class' - creates classes ✗");
        System.out.println("   Example: class MyClass { }");
        System.out.println("   (For classes, not interfaces)");
        
        System.out.println("\n=== Interface Syntax ===");
        System.out.println("interface InterfaceName {");
        System.out.println("    // constants (implicitly public static final)");
        System.out.println("    int CONSTANT = 10;");
        System.out.println("    ");
        System.out.println("    // abstract methods (implicitly public abstract)");
        System.out.println("    void method();");
        System.out.println("    ");
        System.out.println("    // default methods (Java 8+)");
        System.out.println("    default void defaultMethod() { }");
        System.out.println("    ");
        System.out.println("    // static methods (Java 8+)");
        System.out.println("    static void staticMethod() { }");
        System.out.println("}");
    }
}

// Example interface
interface Drawable {
    void draw();
    
    default void print() {
        System.out.println("Drawing...");
    }
    
    static void info() {
        System.out.println("This is a drawable interface");
    }
}
