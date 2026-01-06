/**
 * Question 17: ThreadLocal Correct Usage
 * 
 * Russian: "Ваша команда разработала многопоточную библиотеку, использующую 
 *           ThreadLocal для хранения контекстной информации для каждого потока. 
 *           Какой из следующих подходов НЕ является корректным в использовании 
 *           ThreadLocal?"
 * English: "Your team has developed a multithreaded library that uses ThreadLocal 
 *           to store contextual information for each thread. Which of the following 
 *           approaches is NOT correct in using ThreadLocal?"
 * 
 * Options:
 * 1. Use ThreadLocal to store database connections
 * 2. Use ThreadLocal to store data that will only be used within a single thread
 * 3. Declare ThreadLocal as static so that data is only available within a single class
 * 4. Do NOT call remove() for ThreadLocal when the thread finishes its work, 
 *    to avoid memory leaks
 * 5. Use ThreadLocal to store user settings unique to each thread
 * 
 * ANSWER: Option 4
 * 
 * EXPLANATION:
 * - Option 4 is INCORRECT - you SHOULD call remove() to prevent memory leaks
 * - In thread pools, threads are reused, so ThreadLocal values persist
 * - Not calling remove() causes memory leaks
 * - Options 1, 2, 3, 5 are all CORRECT uses of ThreadLocal
 * - ThreadLocal should be static final for proper usage
 */

public class Q17_ThreadLocal_CorrectUsage {
    // CORRECT: Static final ThreadLocal
    private static final ThreadLocal<String> userContext = new ThreadLocal<>();
    private static final ThreadLocal<Integer> threadId = new ThreadLocal<>();
    
    public static void demonstrateCorrectUsage() {
        System.out.println("=== Correct ThreadLocal Usage ===\n");
        
        // CORRECT: Set thread-local value
        userContext.set("User123");
        threadId.set(Thread.currentThread().hashCode());
        
        System.out.println("Thread: " + Thread.currentThread().getName());
        System.out.println("User context: " + userContext.get());
        System.out.println("Thread ID: " + threadId.get());
        
        // CORRECT: Use cases
        System.out.println("\n✓ Correct use cases:");
        System.out.println("  1. Database connections per thread");
        System.out.println("  2. User settings unique to each thread");
        System.out.println("  3. Context data used only within single thread");
        System.out.println("  4. Declare as static final");
        
        // CRITICAL: Remove to prevent memory leaks
        System.out.println("\n✓ CRITICAL: Always call remove() when done");
        userContext.remove();
        threadId.remove();
        System.out.println("  Removed ThreadLocal values");
    }
    
    public static void demonstrateMemoryLeak() {
        System.out.println("\n=== Memory Leak Scenario ===\n");
        
        System.out.println("❌ INCORRECT: Not calling remove()");
        System.out.println("  In thread pools, threads are REUSED");
        System.out.println("  ThreadLocal values persist across task executions");
        System.out.println("  Old values accumulate → MEMORY LEAK");
        
        System.out.println("\nExample with thread pool:");
        System.out.println("  Thread-1 executes Task-A: sets user='Alice'");
        System.out.println("  Thread-1 executes Task-B: user='Alice' still there!");
        System.out.println("  Thread-1 executes Task-C: user='Alice' still there!");
        System.out.println("  → Memory leak if not cleaned up");
    }
    
    public static void demonstrateCorrectCleanup() {
        System.out.println("\n=== Correct Cleanup Pattern ===\n");
        
        ThreadLocal<String> context = new ThreadLocal<>();
        
        try {
            // Use ThreadLocal
            context.set("ImportantData");
            System.out.println("Using context: " + context.get());
            
            // Do work...
            
        } finally {
            // ALWAYS remove in finally block
            context.remove();
            System.out.println("✓ Cleaned up ThreadLocal in finally block");
        }
    }
    
    public static void main(String[] args) {
        demonstrateCorrectUsage();
        demonstrateMemoryLeak();
        demonstrateCorrectCleanup();
        
        System.out.println("\n=== Summary ===");
        System.out.println("CORRECT:");
        System.out.println("  ✓ Static final ThreadLocal");
        System.out.println("  ✓ Store thread-specific data");
        System.out.println("  ✓ Use for DB connections, user context, etc.");
        System.out.println("  ✓ ALWAYS call remove() when done");
        
        System.out.println("\nINCORRECT:");
        System.out.println("  ✗ Not calling remove() → memory leaks");
        System.out.println("  ✗ Using for shared data (use regular variables)");
        System.out.println("  ✗ Forgetting cleanup in thread pools");
    }
}
