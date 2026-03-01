/**
 * Question 16: Iterator Remove Behavior
 * 
 * Russian: "Что выведет этот код?"
 * English: "What will this code output?"
 * 
 * Code:
 * List<Integer> list = new ArrayList<>();
 * for (int i = 1; i <= 5; i++) {
 *     list.add(i);
 * }
 * Iterator<Integer> iterator = list.iterator();
 * while (iterator.hasNext()) {
 *     Integer number = iterator.next();
 *     if (number == 3) {
 *         iterator.remove();
 *     }
 *     System.out.print(number + " ");
 * }
 * 
 * Options:
 * 1. Will output 123, then ConcurrentModificationException
 * 2. 3
 * 3. 1245
 * 4. 12
 * 5. 12345
 * 
 * ANSWER: 12345
 * 
 * EXPLANATION:
 * - iterator.next() returns the element BEFORE removal
 * - When number == 3, iterator.remove() removes it from list
 * - But number variable already holds 3, so it prints 3
 * - Then continues with remaining elements: 4, 5
 * - Output: 1 2 3 4 5 (all numbers printed)
 * - iterator.remove() is SAFE - updates modCount, so no CME
 *
 * Java version: Java 8+ compatible.
 */

import java.util.*;

public class Q16_Iterator_Remove {
    public static void main(String[] args) {
        System.out.println("=== Iterator Remove Behavior ===\n");
        
        List<Integer> list = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            list.add(i);
        }
        
        System.out.println("Initial list: " + list);
        System.out.println("\nIterating and removing 3:");
        
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()) {
            Integer number = iterator.next(); // Gets element BEFORE removal
            if (number == 3) {
                iterator.remove(); // Removes 3 from list
                System.out.println("  (Removed 3 from list)");
            }
            System.out.print(number + " "); // Prints the number variable
        }
        
        System.out.println("\n\nFinal list: " + list);
        
        System.out.println("\n=== Key Points ===");
        System.out.println("1. iterator.next() returns element BEFORE removal");
        System.out.println("2. The variable 'number' holds the value");
        System.out.println("3. iterator.remove() removes from list, not from variable");
        System.out.println("4. All numbers are printed: 1 2 3 4 5");
        System.out.println("5. iterator.remove() is SAFE - no ConcurrentModificationException");
        System.out.println("6. List after iteration: [1, 2, 4, 5] (3 removed)");
        
        System.out.println("\n=== Step-by-Step Execution ===");
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Iterator<Integer> it = list2.iterator();
        int step = 1;
        while (it.hasNext()) {
            Integer num = it.next();
            System.out.println("Step " + step + ": next() = " + num);
            if (num == 3) {
                it.remove();
                System.out.println("         remove() called, list = " + list2);
            }
            System.out.println("         print(" + num + ")");
            step++;
        }
        System.out.println("Final list: " + list2);
    }
}
