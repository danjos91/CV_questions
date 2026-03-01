/**
 * Question 29: Constructor Execution Order and equals/hashCode Contract
 *
 * Russian: "Что будет выведено? Что произойдёт при использовании Lombok @EqualsAndHashCode?"
 * English: "What is going to be printed? What happens if we use Lombok @EqualsAndHashCode?"
 *
 *
 * QUESTIONS:
 * 1. What is going to be printed?
 * 2. What happens if we use Lombok @EqualsAndHashCode?
 *
 * ANSWER (Part 1 - Output):
 * First
 * 1
 * Second
 * 6
 * Size: 3
 *
 * ANSWER (Part 2 - With @EqualsAndHashCode):
 * Size would be 2 (First and one Second would be considered duplicates of each other
 * only if they had same field values; two Second instances would be equal).
 *
 * EXPLANATION:
 *
 * PART 1 - Constructor Execution Order:
 * -------------------------------------
 * When new Second() is called:
 * 1. Before ANY code in Second() runs, Java implicitly calls super() → First()
 * 2. First() executes: prints "First", then calls calculate()
 * 3. CRITICAL: calculate() is OVERRIDDEN in Second. At runtime, polymorphic dispatch
 *    calls Second.calculate(), NOT First.calculate()!
 * 4. At this moment, count is still 0 (Second() body hasn't run yet; this.count=5
 *    hasn't been executed). Second.calculate() does count++ → 1, prints 1
 * 5. First() returns. Now Second() body runs: this.count = 5, prints "Second", calculate()
 * 6. Second.calculate(): count++ → 6, prints 6
 *
 * Constructor order: super() runs first (entire parent constructor), then subclass body.
 * Never call overridable methods from constructors - the subclass override runs before
 * the subclass constructor body, so subclass fields may not be initialized!
 *
 * PART 2 - Why Set Has Size 3 (hashCode and equals contract):
 * ----------------------------------------------------------
 * HashSet determines uniqueness by: hashCode() AND equals().
 *
 * - All three objects return hashCode() = 0 (same bucket)
 * - equals() was NOT overridden → Object.equals() is used (reference equality, ==)
 * - new First() != new Second() (different types)
 * - new Second() != new Second() (different object references)
 * - Therefore: all three are considered DISTINCT → size = 3
 *
 * Same hashCode does NOT mean equality! HashSet requires BOTH:
 *   - Same hashCode (to find the bucket)
 *   - equals() returns true (to confirm identity)
 *
 * PART 3 - With Lombok @EqualsAndHashCode:
 * ---------------------------------------
 * @EqualsAndHashCode generates equals() and hashCode() based on all non-static,
 * non-transient fields (and optionally superclass fields with callSuper=true).
 *
 * For First: field 'count'
 * For Second: with callSuper=true, includes 'count' from First
 *
 * - new First(): count = 0 (default, calculate() in First prints but doesn't change it)
 * - new Second(): count = 6 after constructor
 * - new Second(): count = 6 after constructor
 *
 * Two Second instances would have count=6 → same hashCode, equals()=true → DUPLICATE!
 * First has count=0 → different from Second.
 *
 * Result: set.size() = 2 (one First, one Second; second Second is rejected as duplicate)
 *
 * Java version: Java 8+ compatible.
 */

import java.util.HashSet;

public class Q29_Constructor_Order_HashEquals {

    static class First {
        protected int count;

        public First() {
            System.out.println("First");
            calculate();
        }

        public void calculate() {
            System.out.println(count);
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    static class Second extends First {

        public Second() {
            this.count = 5;
            System.out.println("Second");
            calculate();
        }

        @Override
        public void calculate() {
            this.count++;
            System.out.println(count);
        }

        @Override
        public int hashCode() {
            return 0;
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Part 1: Constructor Execution Order ===\n");

        System.out.println("Creating Second s = new Second();");
        System.out.println("Execution flow:");
        System.out.println("  1. super() implicitly called → First() runs first");
        System.out.println("  2. First() prints 'First', calls calculate()");
        System.out.println("  3. Polymorphism: Second.calculate() runs (count=0, then count++ → 1)");
        System.out.println("  4. First() returns, Second() body runs: count=5, prints 'Second'");
        System.out.println("  5. calculate() again: count++ → 6\n");

        Second s = new Second();

        System.out.println("\n=== Part 2: HashSet and equals/hashCode Contract ===\n");

        HashSet<Object> set = new HashSet<>();
        set.add(new First());
        set.add(new Second());
        set.add(new Second());

        System.out.println("\nSize: " + set.size());

        System.out.println("\n=== Why Size is 3 ===");
        System.out.println("- All return hashCode() = 0 (same bucket)");
        System.out.println("- equals() NOT overridden → Object.equals() (reference equality)");
        System.out.println("- new First() != new Second() (different objects)");
        System.out.println("- new Second() != new Second() (different references)");
        System.out.println("- Same hashCode ≠ equality! HashSet needs BOTH hashCode AND equals");

        System.out.println("\n=== With @EqualsAndHashCode (Lombok) ===");
        System.out.println("- Would generate equals/hashCode from field 'count'");
        System.out.println("- Two Second instances both have count=6 → equal → duplicate");
        System.out.println("- Result: set.size() = 2 (one First, one Second)");
    }
}
