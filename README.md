# Java Senior Interview Preparation Guide

This repository contains 36 Java interview questions covering key topics for senior-level positions.

## Question Index

### Collections & Data Structures
1. [Q01: TreeSet Sorted by Default](Q01_TreeSet_SortedByDefault.java)
2. [Q05: ConcurrentModificationException](Q05_ConcurrentModificationException.java)
3. [Q06: PriorityQueue Poll Output](Q06_PriorityQueue_Poll.java)
4. [Q11: Stream Skip and Limit](Q11_Stream_SkipLimit.java)
5. [Q16: Iterator Remove Behavior](Q16_Iterator_Remove.java)
6. [Q13: ByteBuffer Remaining](Q13_ByteBuffer_Remaining.java)
7. [Q23: BST from Sorted Array](Q23_BST_From_Sorted_Array.java)
8. [Q24: Card Game Maximum Sum](Q24_Card_Game_Maximum_Sum.java)
9. [Q25: Pairs Divisible by 200](Q25_Pairs_Divisible_By_200.java)
10. [Q26: Longest Increasing Path in Matrix](Q26_Longest_Increasing_Path_Matrix.java)
11. [Q29: Constructor Order and equals/hashCode Contract](Q29_Constructor_Order_HashEquals.java)
12. [Q31: Rebus Decode](Q31_Rebus_Decode.java)
13. [Q32: Exam Seating Balance](Q32_Exam_Seating_Balance.java)
14. [Q33: Two Sum (numbers vs indices)](Q33_TwoSum.java)
15. [Q34: Basketball — top scorer from score records](Q31_Basketball_Score_By_Player.java)
16. [Q35: Courier unknown position (max X)](Q32_Courier_Unknown_Position.java)
17. [Q36: Non-overlapping substrings (maximize length)](Q33_Non_Overlapping_Substrings.java)

### Concurrency & Threading
7. [Q02: ExecutorService shutdownNow()](Q02_ExecutorService_ShutdownNow.java)
8. [Q08: ReentrantLock vs Synchronized](Q08_ReentrantLock_vs_Synchronized.java)
9. [Q17: ThreadLocal Correct Usage](Q17_ThreadLocal_CorrectUsage.java)
10. [Q14: Static vs Instance Fields](Q14_Static_vs_Instance_Fields.java)

### Exceptions & Error Handling
11. [Q09: Unchecked Exceptions](Q09_Unchecked_Exceptions.java)
12. [Q15: Exception Finally Return](Q15_Exception_Finally_Return.java)
13. [Q20: Static Field Null Access](Q20_Static_Field_Null_Access.java)
14. [Q22: Exception Propagation](Q22_Exception_Propagation.java)

### Generics & Type System
15. [Q18: Generic Wildcards](Q18_Generic_Wildcards.java)
16. [Q12: Interface Keyword](Q12_Interface_Keyword.java)

### Object-Oriented Programming
17. [Q21: Interface Default Methods](Q21_Interface_Default_Methods.java)
18. [Q29: Constructor Order and equals/hashCode Contract](Q29_Constructor_Order_HashEquals.java)

### Memory Management & GC
18. [Q19: Cyclic References Memory Leak](Q19_Cyclic_References_MemoryLeak.java)

### Java Fundamentals
19. [Q03: Java Serialization Format](Q03_Java_Serialization_Format.java)
20. [Q10: Double Division Casting](Q10_Double_Division_Casting.java)
21. [Q29: Constructor Order and equals/hashCode Contract](Q29_Constructor_Order_HashEquals.java)

### Git Commands
21. [Q04: Git Log Graph](Q04_Git_Log_Graph.md)
22. [Q07: Git Log Function History](Q07_Git_Log_Function_History.md)

## How to Use

1. Each Java file contains:
   - Question text (Russian and English)
   - Detailed explanation
   - Correct answer with reasoning
   - Runnable code demonstrating the concept
   - Expected output

2. Each Markdown file contains:
   - Question text
   - Explanation of Git concepts
   - Correct command with examples
   - Why other options are incorrect

3. To run a Java question:
   ```bash
   javac Q##_QuestionName.java
   java Q##_QuestionName
   ```

## Topics Covered

- Collections framework (TreeSet, HashSet, PriorityQueue, ArrayList, Iterator)
- Data structures (Binary Search Trees, balanced tree construction, prefix/suffix sums, sliding window, frequency counting, modular arithmetic, graph algorithms, dynamic programming, HashMap score aggregation, interval intersection, string processing)
- Concurrency (ExecutorService, ReentrantLock, ThreadLocal, synchronized)
- Exception handling (checked vs unchecked, propagation, finally blocks)
- Generics and wildcards
- Memory management and garbage collection
- Java fundamentals (interfaces, static fields, casting)
- Git commands for code history
