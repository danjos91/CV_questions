/**
 * Question 19: Cyclic References and Memory Leaks
 * 
 * Russian: "После длительной работы графический сервис показал постепенный 
 *           рост памяти. Коллега проверил код на утечки памяти и нашел, что 
 *           утечка памяти вызвана 'циклической ссылкой в коде класса реализации 
 *           графа'. Может ли это быть причиной утечки памяти? (ParallelGC используется как GC)"
 * English: "After prolonged operation, a graphics service showed gradual memory growth. 
 *           A colleague inspected the code for memory leaks and found that the memory 
 *           leak is caused by a 'cyclic reference in the code of the graph implementation 
 *           class.' Could this be the cause of a memory leak? (ParallelGC is used as GC)"
 * 
 * Code:
 * class Node {
 *     private Node edge = null;
 *     public Node getEdge() { return edge; }
 *     public void setEdge(Node edge) { this.edge = edge; }
 * }
 * 
 * public void createAndDelete() {
 *     Node node1 = new Node();
 *     Node node2 = new Node();
 *     node1.setEdge(node2);
 *     node2.setEdge(node1);  // Cyclic reference
 * }
 * 
 * Options:
 * 1. No. Thanks to trace-logic GC, cyclic objects are easily cleaned up if they 
 *    are unreachable from root objects.
 * 2. Yes. Because objects with cyclic references need to have the .finalize() 
 *    method called to clean them up. Otherwise, GC cannot remove them.
 * 3. Yes. ParallelGC cannot work with cyclic references, so each call to 
 *    createAndDelete() increases the number of uncollectible objects.
 * 4. Yes. Due to cyclic references, objects cannot be moved from the young 
 *    generation to the old generation.
 * 5. No. These objects are not allocated on the heap with such logic and thus 
 *    do not cause a leak.
 * 
 * ANSWER: Option 1
 * 
 * EXPLANATION:
 * - Modern GC (including ParallelGC) uses tracing algorithms (mark-and-sweep, etc.)
 * - Tracing GC can handle cyclic references - if unreachable from roots, they're collected
 * - finalize() is NOT required for GC to work
 * - ParallelGC CAN handle cyclic references
 * - Objects CAN move between generations even with cycles
 * - The real issue: if references are held (not released), that's the leak, not the cycle
 *
 * Java version: Java 8+ compatible.
 */

public class Q19_Cyclic_References_MemoryLeak {
    static class Node {
        private Node edge = null;
        
        public Node getEdge() {
            return edge;
        }
        
        public void setEdge(Node edge) {
            this.edge = edge;
        }
    }
    
    public static void createAndDelete() {
        Node node1 = new Node();
        Node node2 = new Node();
        node1.setEdge(node2);
        node2.setEdge(node1); // Cyclic reference
        
        // When method ends, node1 and node2 go out of scope
        // They become unreachable from root objects
        // GC can collect them despite the cycle
    }
    
    public static void main(String[] args) {
        System.out.println("=== Cyclic References and Garbage Collection ===\n");
        
        System.out.println("Creating cyclic references:");
        createAndDelete();
        System.out.println("Method completed - local variables out of scope");
        
        System.out.println("\n=== Key Points ===");
        System.out.println("1. Modern GC (ParallelGC, G1, etc.) uses TRACING algorithms");
        System.out.println("2. Tracing GC starts from ROOT objects (static fields, stack, etc.)");
        System.out.println("3. If cyclic objects are UNREACHABLE from roots → collected");
        System.out.println("4. The cycle itself is NOT a problem for GC");
        
        System.out.println("\n=== Why Option 1 is Correct ===");
        System.out.println("✓ Tracing GC (mark-and-sweep) handles cycles");
        System.out.println("✓ If unreachable from roots, cycles are collected");
        System.out.println("✓ ParallelGC uses tracing - can handle cycles");
        
        System.out.println("\n=== Why Other Options are Wrong ===");
        System.out.println("Option 2: finalize() NOT required for GC");
        System.out.println("Option 3: ParallelGC CAN handle cycles");
        System.out.println("Option 4: Objects CAN move between generations");
        System.out.println("Option 5: Objects ARE on heap");
        
        System.out.println("\n=== Real Memory Leak Scenario ===");
        System.out.println("Memory leak occurs when:");
        System.out.println("  ✗ References are held (not released)");
        System.out.println("  ✗ Objects remain reachable from roots");
        System.out.println("  ✗ Not because of cycles, but because of retained references");
        
        System.out.println("\n=== Example of REAL Leak ===");
        System.out.println("static List<Node> nodes = new ArrayList<>();");
        System.out.println("nodes.add(node1); // Keeps reference → LEAK");
        System.out.println("// Even without cycles, this causes leak");
    }
}
