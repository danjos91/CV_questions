/**
 * Question 05: ConcurrentModificationException
 * 
 * Russian: "Какой метод итерации по коллекции ArrayList приведет к 
 *           ConcurrentModificationException, если коллекция будет изменена 
 *           во время итерации?"
 * English: "Which method of iterating over an ArrayList collection will lead 
 *           to a ConcurrentModificationException if the collection is modified 
 *           during iteration?"
 * 
 * Options:
 * 1. Streams and forEach() method
 * 2. For loop with index access
 * 3. For-each loop
 * 4. Iterator and remove() method
 * 5. ListIterator and remove() method
 * 
 * ANSWER: Option 3 - For-each loop
 * 
 * EXPLANATION:
 * - For-each loop internally uses Iterator and checks modCount
 * - If collection is modified during iteration (except through Iterator.remove()), 
 *   ConcurrentModificationException is thrown
 * - Iterator.remove() and ListIterator.remove() are SAFE - they update modCount
 * - For loop with index access is SAFE - no iterator involved
 * - Streams forEach() can throw CME if underlying collection is modified
 */

import java.util.*;

public class Q05_ConcurrentModificationException {
    public static void main(String[] args) {
        System.out.println("=== ConcurrentModificationException Demo ===\n");
        
        // Test 1: For-each loop - WILL throw CME
        System.out.println("Test 1: For-each loop with modification");
        try {
            List<Integer> list1 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
            for (Integer num : list1) {
                if (num == 3) {
                    list1.remove(num); // Modifying during iteration!
                }
            }
        } catch (ConcurrentModificationException e) {
            System.out.println("✓ ConcurrentModificationException thrown!");
        }
        
        // Test 2: Iterator.remove() - SAFE
        System.out.println("\nTest 2: Iterator.remove() - SAFE");
        List<Integer> list2 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        Iterator<Integer> iterator = list2.iterator();
        while (iterator.hasNext()) {
            Integer num = iterator.next();
            if (num == 3) {
                iterator.remove(); // Safe - uses iterator's remove()
            }
        }
        System.out.println("✓ No exception: " + list2);
        
        // Test 3: For loop with index - SAFE
        System.out.println("\nTest 3: For loop with index - SAFE");
        List<Integer> list3 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        for (int i = 0; i < list3.size(); i++) {
            if (list3.get(i) == 3) {
                list3.remove(i); // Safe - no iterator involved
                i--; // Adjust index after removal
            }
        }
        System.out.println("✓ No exception: " + list3);
        
        // Test 4: ListIterator.remove() - SAFE
        System.out.println("\nTest 4: ListIterator.remove() - SAFE");
        List<Integer> list4 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        ListIterator<Integer> listIterator = list4.listIterator();
        while (listIterator.hasNext()) {
            Integer num = listIterator.next();
            if (num == 3) {
                listIterator.remove(); // Safe - uses iterator's remove()
            }
        }
        System.out.println("✓ No exception: " + list4);
        
        // Test 5: Stream forEach() - CAN throw CME
        System.out.println("\nTest 5: Stream forEach() with modification");
        try {
            List<Integer> list5 = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
            list5.stream().forEach(num -> {
                if (num == 3) {
                    list5.remove(num); // Modifying underlying collection!
                }
            });
        } catch (ConcurrentModificationException e) {
            System.out.println("✓ ConcurrentModificationException thrown!");
        }
        
        System.out.println("\n=== Key Points ===");
        System.out.println("1. For-each loop uses Iterator internally");
        System.out.println("2. Modifying collection during for-each throws CME");
        System.out.println("3. Iterator.remove() is SAFE - updates modCount");
        System.out.println("4. For loop with index is SAFE - no iterator");
        System.out.println("5. Stream forEach() can throw CME if collection modified");
    }
}
