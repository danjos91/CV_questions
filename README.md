# Java Senior Interview Preparation Guide

This repository contains 64 interview and exam questions covering key topics for senior-level positions.

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
11. [Q27: Mushroom Sharing](Q27_Mushroom_Sharing.java)
12. [Q28: Cybersecurity — Distinct Passwords](Q28_Cybersecurity_Passwords.java)
13. [Q29: Constructor Order and equals/hashCode Contract](Q29_Constructor_Order_HashEquals.java)
14. [Q31: Rebus Decode](Q31_Rebus_Decode.java)
15. [Q32: Exam Seating Balance](Q32_Exam_Seating_Balance.java)
16. [Q33: Two Sum (numbers vs indices)](Q33_TwoSum.java)
17. [Q34: Basketball — top scorer from score records](Q31_Basketball_Score_By_Player.java)
18. [Q34: Task Preparation Level](Q34_Task_Preparation_Level.java)
19. [Q35: Courier unknown position (max X)](Q32_Courier_Unknown_Position.java)
20. [Q36: Non-overlapping substrings (maximize length)](Q33_Non_Overlapping_Substrings.java)
21. [Q37: Artifact exhibition — minimize adjacent differences (smallest range)](Q37_Artifact_Exhibition_Min_Adjacent_Sum.java)
22. [Q50: Card dealing — maximize aces for player 1](Q50_Card_Dealing_Max_Aces.java)
23. [Q51: Mirror-symmetric binary tree](Q51_Mirror_Symmetric_Tree.java)
24. [Q52: Fastest kilometer (starting minute)](Q52_Fastest_Kilometer.java)
25. [Q53: Optimal temperature range (closer / further)](Q53_Optimal_Temperature_Range.java)
26. [Q54: Seconds offset to time of day](Q54_Seconds_To_Time_Of_Day.java)
27. [Q55: Coupon pairs summing to S (two sorted lists)](Q55_Coupon_Two_Category_Pairs.java)
28. [Q56: Run-Length Encoding (RLE)](Q56_Run_Length_Encoding.java)
29. [Q57: Range geometric mean (prefix of logs)](Q57_Range_Geometric_Mean.java)
30. [Q58: Three subjects closest to load X (3Sum Closest)](Q58_Three_Sum_Closest.java)

### Concurrency & Threading
31. [Q02: ExecutorService shutdownNow()](Q02_ExecutorService_ShutdownNow.java)
32. [Q08: ReentrantLock vs Synchronized](Q08_ReentrantLock_vs_Synchronized.java)
33. [Q17: ThreadLocal Correct Usage](Q17_ThreadLocal_CorrectUsage.java)
34. [Q14: Static vs Instance Fields](Q14_Static_vs_Instance_Fields.java)

### Exceptions & Error Handling
35. [Q09: Unchecked Exceptions](Q09_Unchecked_Exceptions.java)
36. [Q15: Exception Finally Return](Q15_Exception_Finally_Return.java)
37. [Q20: Static Field Null Access](Q20_Static_Field_Null_Access.java)
38. [Q22: Exception Propagation](Q22_Exception_Propagation.java)

### Generics & Type System
39. [Q18: Generic Wildcards](Q18_Generic_Wildcards.java)
40. [Q12: Interface Keyword](Q12_Interface_Keyword.java)

### Object-Oriented Programming
41. [Q21: Interface Default Methods](Q21_Interface_Default_Methods.java)
42. [Q29: Constructor Order and equals/hashCode Contract](Q29_Constructor_Order_HashEquals.java)

### Memory Management & GC
43. [Q19: Cyclic References Memory Leak](Q19_Cyclic_References_MemoryLeak.java)

### Java Fundamentals
44. [Q03: Java Serialization Format](Q03_Java_Serialization_Format.java)
45. [Q10: Double Division Casting](Q10_Double_Division_Casting.java)
46. [Q29: Constructor Order and equals/hashCode Contract](Q29_Constructor_Order_HashEquals.java)

### Git Commands
47. [Q04: Git Log Graph](Q04_Git_Log_Graph.md)
48. [Q07: Git Log Function History](Q07_Git_Log_Function_History.md)

### SQL & Databases
49. [Q30: SQL Query Optimization](Q30_SQL_Query_Optimization.md)

### Mathematics
50. [Q34: Distance from a Point to a Plane](Q34_Point_To_Plane_Distance.md)
51. [Q38: Probability of the Next Click (Exponential Distribution)](Q38_Exponential_Click_Probability.md)
52. [Q38: Half-Disk Area Under Sine Curve](Q38_Half_Disk_Sine_Area.md)
53. [Q39: Area of a Figure on a Grid](Q39_Grid_Figure_Area.md)
54. [Q39: Inverse of a 2×2 Matrix](Q39_Inverse_Matrix.md)
55. [Q40: Largest Eigenvalue of a Matrix](Q40_Largest_Eigenvalue.md)
56. [Q40: Phone Password — Unique Digits Reduction](Q40_Phone_Password_Unique_Digits.md)
57. [Q41: Grid figure — why the answer 18 is wrong](Q41_Figure_Area.md)
58. [Q41: Coin Toss — More Heads Than Tails](Q41_Coin_Toss_More_Heads.md)
59. [Q42: Binary Classification Threshold](Q42_Binary_Classification_Threshold.md)
60. [Q43: Two Boxes — Maximize Probability of a Blue Ball](Q43_Two_Boxes_Blue_Ball_Probability.md)
61. [Q44: Area of a Figure on a 6×4 Grid](Q44_Grid_Figure_Area.md)
62. [Q45: Linear Regression Slope (Least Squares)](Q45_Linear_Regression_Slope.md)
63. [Q46: Shooting Gallery Prizes](Q46_Shooting_Gallery_Prizes.md)
64. [Q47: Limit of a Recursive Sequence](Q47_Recursive_Sequence_Limit.md)
65. [Q48: Gold Stealing (Dasha and Zulik)](Q48_Gold_Stealing.md)
66. [Q49: Partial Derivative \(f_x(3, 4)\)](Q49_Partial_Derivative_fx.md)

### Yandex Cup 2026 — Algorithm track
- [Preparation kit: plan, checklists, weekly log](yandex-cup/README.md)
- [Java contest template (fast IO, safe sort, big-stack thread)](yandex-cup/Template.java)

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
- Data structures (Binary Search Trees, balanced tree construction, prefix/suffix sums, sliding window, frequency counting, modular arithmetic, graph algorithms, dynamic programming, HashMap score aggregation, interval intersection, string processing, multi-list smallest range / k-way merge, circular dealing / modular indexing, mirror-symmetric trees, two pointers / shortest subarray with sum ≥ S, 1D constraint / half-line intersection, clock arithmetic / negative modulo, sorted two-sum / two pointers without hashing, run-length encoding, prefix sums of logarithms / range geometric mean, 3-sum closest, seating balance / row packing, rebus string decoding)
- Concurrency (ExecutorService, ReentrantLock, ThreadLocal, synchronized)
- Exception handling (checked vs unchecked, propagation, finally blocks)
- Generics and wildcards
- Memory management and garbage collection
- Java fundamentals (interfaces, static fields, casting)
- Git commands for code history
- SQL query optimization and indexing
- Analytic geometry (point-to-plane distance in 3D, half-disk area under a sine curve)
- Linear algebra (matrix inverse, eigenvalues)
- Combinatorics (permutations without repetition, password counting)
- Probability (exponential distribution, waiting time / Poisson process, law of total probability, optimal allocation, binomial / coin tosses)
- Grid geometry (polygon area by decomposition and the shoelace formula)
- Statistics (ordinary least squares, linear regression slope)
- Sequences and limits (recursive sequences, monotone convergence)
- Multivariable calculus (partial derivatives, chain rule, logarithms)
