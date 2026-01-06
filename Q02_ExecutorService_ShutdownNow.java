/**
 * Question 02: ExecutorService shutdownNow() behavior
 * 
 * Russian: "Что произойдет, если вызвать метод shutdownNow() у объекта ExecutorService 
 *           с активными задачами?"
 * English: "What will happen if the shutdownNow() method is called on an ExecutorService 
 *           object with active tasks?"
 * 
 * Options:
 * 1. All tasks will be terminated immediately, even if they are in progress
 * 2. The method will return a list of tasks that were not started, and all running 
 *    tasks will be attempted to be interrupted
 * 3. Current tasks will continue to execute until completion, but new tasks will not 
 *    be accepted
 * 4. An InterruptedException will occur
 * 5. The method will complete all tasks in the order they were added and free all threads
 * 
 * ANSWER: Option 2
 * 
 * EXPLANATION:
 * - shutdownNow() attempts to stop all actively executing tasks
 * - It returns a List of tasks that were awaiting execution (never started)
 * - Running tasks are interrupted (Thread.interrupt() is called), but interruption 
 *   is cooperative - tasks must check interrupted status
 * - shutdownNow() does NOT guarantee immediate termination - it depends on tasks 
 *   responding to interruption
 * - shutdown() (without Now) allows current tasks to finish but rejects new tasks
 */

import java.util.*;
import java.util.concurrent.*;

public class Q02_ExecutorService_ShutdownNow {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== Demonstrating ExecutorService.shutdownNow() ===\n");
        
        ExecutorService executor = Executors.newFixedThreadPool(2);
        
        // Submit multiple tasks
        Future<?> task1 = executor.submit(() -> {
            try {
                System.out.println("Task 1 started");
                Thread.sleep(2000);
                System.out.println("Task 1 completed");
            } catch (InterruptedException e) {
                System.out.println("Task 1 interrupted!");
            }
        });
        
        Future<?> task2 = executor.submit(() -> {
            try {
                System.out.println("Task 2 started");
                Thread.sleep(2000);
                System.out.println("Task 2 completed");
            } catch (InterruptedException e) {
                System.out.println("Task 2 interrupted!");
            }
        });
        
        // Submit more tasks that won't start
        Future<?> task3 = executor.submit(() -> {
            System.out.println("Task 3 started");
        });
        
        Future<?> task4 = executor.submit(() -> {
            System.out.println("Task 4 started");
        });
        
        // Give tasks time to start
        Thread.sleep(100);
        
        System.out.println("\nCalling shutdownNow()...\n");
        
        // shutdownNow() returns list of tasks that never started
        List<Runnable> pendingTasks = executor.shutdownNow();
        
        System.out.println("Pending tasks (never started): " + pendingTasks.size());
        System.out.println("shutdownNow() returned " + pendingTasks.size() + 
                         " tasks that were queued but never started");
        
        // Wait a bit to see interruption effects
        Thread.sleep(500);
        
        System.out.println("\n=== Key Points ===");
        System.out.println("1. shutdownNow() returns List<Runnable> of pending tasks");
        System.out.println("2. Running tasks receive interrupt signal (cooperative)");
        System.out.println("3. Tasks must check Thread.interrupted() to respond");
        System.out.println("4. shutdownNow() does NOT guarantee immediate termination");
        System.out.println("5. shutdown() allows current tasks to finish");
        
        // Clean up
        try {
            executor.awaitTermination(1, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
