/**
 * Question 01: Which data structure is sorted by default?
 * 
 * Russian: "В какой из следующих структур данных элементы будут отсортированы по умолчанию?"
 * English: "In which of the following data structures will elements be sorted by default?"
 * 
 * Options:
 * 1. TreeSet
 * 2. HashSet
 * 3. HashMap
 * 4. ArrayList
 * 5. LinkedList
 * 
 * ANSWER: TreeSet
 * 
 * EXPLANATION:
 * - TreeSet implements NavigableSet and uses a Red-Black tree internally
 * - Elements are automatically sorted in natural order (or by Comparator if provided)
 * - HashSet uses hash table - no ordering guaranteed
 * - HashMap uses hash table - no ordering guaranteed (unless LinkedHashMap)
 * - ArrayList maintains insertion order, not sorted order
 * - LinkedList maintains insertion order, not sorted order
 */

import java.util.*;

public class Q01_TreeSet_SortedByDefault {
    public static void main(String[] args) {
        System.out.println("=== Demonstrating TreeSet (Sorted by Default) ===\n");
        
        // TreeSet - automatically sorted
        TreeSet<Integer> treeSet = new TreeSet<>();
        treeSet.add(30);
        treeSet.add(10);
        treeSet.add(20);
        treeSet.add(5);
        System.out.println("TreeSet (sorted): " + treeSet);
        // Output: [5, 10, 20, 30] - automatically sorted!
        
        System.out.println("\n=== Comparing with other structures ===\n");
        
        // HashSet - no guaranteed order
        HashSet<Integer> hashSet = new HashSet<>();
        hashSet.add(30);
        hashSet.add(10);
        hashSet.add(20);
        hashSet.add(5);
        System.out.println("HashSet (no order): " + hashSet);
        // Output: Order not guaranteed - could be [20, 5, 10, 30] or any order
        
        // HashMap - no guaranteed order
        HashMap<Integer, String> hashMap = new HashMap<>();
        hashMap.put(30, "thirty");
        hashMap.put(10, "ten");
        hashMap.put(20, "twenty");
        hashMap.put(5, "five");
        System.out.println("HashMap keys (no order): " + hashMap.keySet());
        // Output: Order not guaranteed
        
        // ArrayList - maintains insertion order, NOT sorted
        ArrayList<Integer> arrayList = new ArrayList<>();
        arrayList.add(30);
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(5);
        System.out.println("ArrayList (insertion order): " + arrayList);
        // Output: [30, 10, 20, 5] - insertion order, not sorted
        
        // LinkedList - maintains insertion order, NOT sorted
        LinkedList<Integer> linkedList = new LinkedList<>();
        linkedList.add(30);
        linkedList.add(10);
        linkedList.add(20);
        linkedList.add(5);
        System.out.println("LinkedList (insertion order): " + linkedList);
        // Output: [30, 10, 20, 5] - insertion order, not sorted
        
        System.out.println("\n=== Key Takeaway ===");
        System.out.println("Only TreeSet maintains elements in sorted order by default!");
        System.out.println("TreeSet uses Red-Black tree (self-balancing BST) internally.");
    }
}
