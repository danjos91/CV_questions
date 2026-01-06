/**
 * Question 06: PriorityQueue Poll Output
 * 
 * Russian: "Что выведет этот код?"
 * English: "What will this code output?"
 * 
 * Code:
 * PriorityQueue<Integer> queue = new PriorityQueue<>();
 * queue.add(10);
 * queue.add(20);
 * queue.add(5);
 * System.out.println(queue.poll());
 * 
 * Options:
 * 1. 5
 * 2. 3
 * 3. 10
 * 4. 0
 * 5. 20
 * 
 * ANSWER: 5
 * 
 * EXPLANATION:
 * - PriorityQueue is a min-heap by default (natural ordering)
 * - Smallest element is at the head
 * - poll() retrieves and removes the head (smallest element)
 * - Elements added: 10, 20, 5
 * - After insertion, heap structure: head = 5 (smallest)
 * - poll() returns 5
 */

import java.util.*;

public class Q06_PriorityQueue_Poll {
    public static void main(String[] args) {
        System.out.println("=== PriorityQueue Poll() Demo ===\n");
        
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        queue.add(10);
        queue.add(20);
        queue.add(5);
        
        System.out.println("Queue after adding 10, 20, 5:");
        System.out.println("Queue: " + queue);
        System.out.println("Note: toString() doesn't show heap order!");
        
        System.out.println("\nCalling poll():");
        Integer result = queue.poll();
        System.out.println("Result: " + result);
        
        System.out.println("\nQueue after poll():");
        System.out.println("Queue: " + queue);
        
        System.out.println("\n=== Key Points ===");
        System.out.println("1. PriorityQueue is MIN-HEAP by default");
        System.out.println("2. Smallest element is always at the head");
        System.out.println("3. poll() retrieves and removes the head (smallest)");
        System.out.println("4. Natural ordering: 5 < 10 < 20");
        System.out.println("5. For max-heap: new PriorityQueue<>(Collections.reverseOrder())");
        
        // Demonstrate max-heap
        System.out.println("\n=== Max-Heap Example ===");
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        maxQueue.add(10);
        maxQueue.add(20);
        maxQueue.add(5);
        System.out.println("Max-heap poll(): " + maxQueue.poll()); // Returns 20
    }
}
