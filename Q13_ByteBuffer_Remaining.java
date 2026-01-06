/**
 * Question 13: ByteBuffer Remaining
 * 
 * Russian: "Что выведет на экран программа?"
 * English: "What will the program output to the screen?"
 * 
 * Code:
 * ByteBuffer buffer = ByteBuffer.allocate(5);
 * buffer.put((byte) 10);
 * buffer.put((byte) 20);
 * buffer.put((byte) 30);
 * buffer.flip();
 * buffer.get();
 * buffer.get();
 * System.out.println(buffer.remaining());
 * 
 * Options:
 * 1. 3
 * 2. 0
 * 3. 1
 * 4. 30
 * 5. 10
 * 
 * ANSWER: 1
 * 
 * EXPLANATION:
 * - allocate(5): capacity=5, position=0, limit=5
 * - put(10): position=1, limit=5
 * - put(20): position=2, limit=5
 * - put(30): position=3, limit=5
 * - flip(): sets limit=position(3), position=0 → limit=3, position=0
 * - get(): reads byte at position 0, position=1
 * - get(): reads byte at position 1, position=2
 * - remaining() = limit - position = 3 - 2 = 1
 */

import java.nio.*;

public class Q13_ByteBuffer_Remaining {
    public static void main(String[] args) {
        System.out.println("=== ByteBuffer remaining() Demo ===\n");
        
        // Step 1: Allocate buffer
        ByteBuffer buffer = ByteBuffer.allocate(5);
        System.out.println("Step 1: ByteBuffer.allocate(5)");
        printBufferState(buffer, "After allocation");
        
        // Step 2: Put bytes
        buffer.put((byte) 10);
        System.out.println("\nStep 2: buffer.put((byte) 10)");
        printBufferState(buffer, "After put(10)");
        
        buffer.put((byte) 20);
        System.out.println("\nStep 3: buffer.put((byte) 20)");
        printBufferState(buffer, "After put(20)");
        
        buffer.put((byte) 30);
        System.out.println("\nStep 4: buffer.put((byte) 30)");
        printBufferState(buffer, "After put(30)");
        
        // Step 3: Flip (switch to read mode)
        buffer.flip();
        System.out.println("\nStep 5: buffer.flip()");
        System.out.println("flip() sets: limit = old position, position = 0");
        printBufferState(buffer, "After flip()");
        
        // Step 4: Get bytes
        byte b1 = buffer.get();
        System.out.println("\nStep 6: buffer.get() - reads " + b1);
        printBufferState(buffer, "After first get()");
        
        byte b2 = buffer.get();
        System.out.println("\nStep 7: buffer.get() - reads " + b2);
        printBufferState(buffer, "After second get()");
        
        // Step 5: Check remaining
        int remaining = buffer.remaining();
        System.out.println("\nStep 8: buffer.remaining()");
        System.out.println("remaining() = limit - position");
        System.out.println("remaining() = " + buffer.limit() + " - " + buffer.position());
        System.out.println("remaining() = " + remaining);
        System.out.println("\nFinal Output: " + remaining);
        
        System.out.println("\n=== Key Points ===");
        System.out.println("ByteBuffer has three key properties:");
        System.out.println("  - capacity: total size (never changes)");
        System.out.println("  - limit: end of valid data");
        System.out.println("  - position: current read/write position");
        System.out.println("\nremaining() = limit - position");
        System.out.println("\nflip() switches from write mode to read mode:");
        System.out.println("  - Sets limit = old position");
        System.out.println("  - Sets position = 0");
    }
    
    private static void printBufferState(ByteBuffer buffer, String label) {
        System.out.println("  " + label + ":");
        System.out.println("    capacity: " + buffer.capacity());
        System.out.println("    position: " + buffer.position());
        System.out.println("    limit: " + buffer.limit());
        System.out.println("    remaining: " + buffer.remaining());
    }
}
