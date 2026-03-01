/**
 * Question 08: ReentrantLock vs Synchronized
 * 
 * Russian: "В чем заключается ключевое отличие ReentrantLock от synchronized?"
 * English: "What is the key difference between ReentrantLock and synchronized?"
 * 
 * Options:
 * 1. ReentrantLock, unlike synchronized, allows only one thread to acquire 
 *    a lock at any given time
 * 2. ReentrantLock provides a wider set of tools for locking, whereas 
 *    synchronized does not have such a set
 * 3. ReentrantLock is always faster and more efficient compared to synchronized
 * 4. ReentrantLock manages thread priorities, whereas synchronized does not
 * 5. ReentrantLock is used for communication between threads, while synchronized 
 *    is used for controlling access to resources
 * 
 * ANSWER: Option 2
 * 
 * EXPLANATION:
 * - ReentrantLock provides more features: tryLock(), lockInterruptibly(), 
 *   fairness option, multiple Condition objects
 * - synchronized is simpler but more limited - automatic release, no fairness control
 * - Both allow only one thread at a time (both are mutual exclusion)
 * - Performance: ReentrantLock can be faster in some scenarios, but not always
 * - Neither manages thread priorities directly
 * - Both are for mutual exclusion, not thread communication
 *
 * Java version: Java 8+ compatible.
 */

import java.util.concurrent.locks.*;

public class Q08_ReentrantLock_vs_Synchronized {
    private int counter = 0;
    private final ReentrantLock lock = new ReentrantLock();
    private final Object syncLock = new Object();
    
    // Using synchronized
    public void incrementSync() {
        synchronized (syncLock) {
            counter++;
            System.out.println("Sync counter: " + counter);
        }
    }
    
    // Using ReentrantLock
    public void incrementLock() {
        lock.lock();
        try {
            counter++;
            System.out.println("Lock counter: " + counter);
        } finally {
            lock.unlock(); // Must manually unlock
        }
    }
    
    // ReentrantLock advanced features
    public void demonstrateAdvancedFeatures() {
        System.out.println("\n=== ReentrantLock Advanced Features ===\n");
        
        // 1. tryLock() - non-blocking
        System.out.println("1. tryLock() - non-blocking:");
        if (lock.tryLock()) {
            try {
                System.out.println("   Lock acquired immediately");
            } finally {
                lock.unlock();
            }
        } else {
            System.out.println("   Lock not available, continuing without blocking");
        }
        
        // 2. lockInterruptibly() - interruptible
        System.out.println("\n2. lockInterruptibly() - can be interrupted:");
        try {
            lock.lockInterruptibly();
            try {
                System.out.println("   Lock acquired (interruptible)");
            } finally {
                lock.unlock();
            }
        } catch (InterruptedException e) {
            System.out.println("   Lock acquisition interrupted");
            Thread.currentThread().interrupt();
        }
        
        // 3. Fairness option
        System.out.println("\n3. Fairness option:");
        ReentrantLock fairLock = new ReentrantLock(true); // Fair lock
        System.out.println("   Fair lock created: " + fairLock.isFair());
        
        // 4. Multiple Condition objects
        System.out.println("\n4. Multiple Condition objects:");
        Condition condition1 = lock.newCondition();
        Condition condition2 = lock.newCondition();
        System.out.println("   Can have multiple conditions per lock");
        
        // 5. Query methods
        System.out.println("\n5. Query methods:");
        System.out.println("   isLocked(): " + lock.isLocked());
        System.out.println("   getQueueLength(): " + lock.getQueueLength());
        System.out.println("   isHeldByCurrentThread(): " + lock.isHeldByCurrentThread());
    }
    
    public static void main(String[] args) {
        Q08_ReentrantLock_vs_Synchronized demo = new Q08_ReentrantLock_vs_Synchronized();
        
        System.out.println("=== synchronized vs ReentrantLock ===\n");
        
        // Basic usage comparison
        System.out.println("Basic Usage:");
        demo.incrementSync();
        demo.incrementLock();
        
        // Show advanced features
        demo.demonstrateAdvancedFeatures();
        
        System.out.println("\n=== Key Differences ===");
        System.out.println("synchronized:");
        System.out.println("  ✓ Automatic lock release");
        System.out.println("  ✓ Simpler syntax");
        System.out.println("  ✗ No tryLock()");
        System.out.println("  ✗ No fairness control");
        System.out.println("  ✗ No multiple conditions");
        System.out.println("  ✗ No query methods");
        
        System.out.println("\nReentrantLock:");
        System.out.println("  ✓ tryLock() - non-blocking");
        System.out.println("  ✓ lockInterruptibly() - interruptible");
        System.out.println("  ✓ Fairness option");
        System.out.println("  ✓ Multiple Condition objects");
        System.out.println("  ✓ Query methods (isLocked, etc.)");
        System.out.println("  ✗ Manual unlock required (must use try-finally)");
        System.out.println("  ✗ More verbose");
        
        System.out.println("\n=== When to Use What ===");
        System.out.println("Use synchronized: Simple cases, automatic cleanup");
        System.out.println("Use ReentrantLock: Need advanced features, fairness, or multiple conditions");
    }
}
