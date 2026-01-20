/**
 * Question 23: Building Binary Search Tree from Sorted Array
 * 
 * Russian: "Как построить сбалансированное бинарное дерево поиска из отсортированного массива?"
 * English: "How to build a balanced binary search tree from a sorted array?"
 * 
 * Question:
 * Given a sorted array [1, 2, 3, 4, 5, 6, 7], what is the optimal approach
 * to construct a balanced Binary Search Tree?
 * 
 * Options:
 * 1. Insert elements in order (left to right) - O(n log n)
 * 2. Insert elements in reverse order (right to left) - O(n log n)
 * 3. Use middle element as root, recursively build left and right subtrees - O(n)
 * 4. Insert elements randomly - O(n log n)
 * 5. Build complete binary tree first, then sort - O(n log n)
 * 
 * ANSWER: Option 3 - Use middle element as root, recursively build left and right subtrees
 * 
 * EXPLANATION:
 * - Since array is sorted, middle element divides array into two balanced halves
 * - Left half: all elements < middle (goes to left subtree)
 * - Right half: all elements > middle (goes to right subtree)
 * - Recursively apply same logic to both halves
 * - Time complexity: O(n) - each element visited once
 * - Space complexity: O(log n) for recursion stack (balanced tree)
 * - Results in a balanced BST (height = log n)
 * - Other approaches result in skewed trees (height = n) or worse time complexity
 */

public class Q23_BST_From_Sorted_Array {
    
    // Node class for Binary Search Tree
    static class Node {
        int val;
        Node left;
        Node right;
        
        Node(int val) {
            this.val = val;
        }
    }
    
    public static void main(String[] args) {
        System.out.println("=== Building BST from Sorted Array ===\n");
        
        // Example: sorted array
        int[] sortedArray = {1, 2, 3, 4, 5, 6, 7};
        System.out.println("Input array: " + java.util.Arrays.toString(sortedArray));
        System.out.println("Array is sorted: ✓\n");
        
        // Build balanced BST using optimal approach
        Node root = buildBSTFromSortedArray(sortedArray);
        
        System.out.println("=== BST Structure (In-order traversal should match array) ===");
        System.out.print("In-order traversal: ");
        inOrderTraversal(root);
        System.out.println("\n(Should match original array: 1 2 3 4 5 6 7)\n");
        
        System.out.println("=== Tree Structure (Visual) ===");
        printTree(root, 0);
        
        System.out.println("\n=== Complexity Analysis ===");
        System.out.println("Time Complexity: O(n) - each element visited exactly once");
        System.out.println("Space Complexity: O(log n) - recursion stack depth (balanced tree)");
        System.out.println("Tree Height: " + getHeight(root) + " (optimal for " + sortedArray.length + " nodes)");
        System.out.println("Optimal height for balanced BST: " + (int)Math.ceil(Math.log(sortedArray.length + 1) / Math.log(2)));
        
        System.out.println("\n=== Algorithm Explanation ===");
        System.out.println("1. Find middle element of array → becomes root");
        System.out.println("2. Left half → recursively build left subtree");
        System.out.println("3. Right half → recursively build right subtree");
        System.out.println("4. Base case: empty array/subarray → return null");
        System.out.println("\nWhy this works:");
        System.out.println("- Sorted array property: left < middle < right");
        System.out.println("- Middle element naturally divides array into balanced halves");
        System.out.println("- Results in balanced BST (not skewed)");
        
        System.out.println("\n=== Comparison with Other Approaches ===");
        System.out.println("❌ Insert in order: Creates skewed tree (height = n), O(n log n)");
        System.out.println("❌ Insert in reverse: Creates skewed tree (height = n), O(n log n)");
        System.out.println("✅ Middle element approach: Balanced tree (height = log n), O(n)");
    }
    
    /**
     * Builds a balanced BST from a sorted array.
     * Optimal approach: O(n) time, O(log n) space
     */
    public static Node buildBSTFromSortedArray(int[] arr) {
        return buildBST(arr, 0, arr.length - 1);
    }
    
    /**
     * Recursive helper method to build BST from sorted array segment.
     * 
     * @param arr The sorted array
     * @param left Start index (inclusive)
     * @param right End index (inclusive)
     * @return Root node of the constructed BST
     */
    private static Node buildBST(int[] arr, int left, int right) {
        // Base case: empty subarray
        if (left > right) {
            return null;
        }
        
        // Find middle element (prevents overflow compared to (left + right) / 2)
        int mid = left + (right - left) / 2;
        
        // Create root node with middle element
        Node root = new Node(arr[mid]);
        
        // Recursively build left subtree from left half
        root.left = buildBST(arr, left, mid - 1);
        
        // Recursively build right subtree from right half
        root.right = buildBST(arr, mid + 1, right);
        
        return root;
    }
    
    /**
     * In-order traversal to verify BST property and sorted order
     */
    private static void inOrderTraversal(Node root) {
        if (root != null) {
            inOrderTraversal(root.left);
            System.out.print(root.val + " ");
            inOrderTraversal(root.right);
        }
    }
    
    /**
     * Calculate height of the tree
     */
    private static int getHeight(Node root) {
        if (root == null) {
            return 0;
        }
        return 1 + Math.max(getHeight(root.left), getHeight(root.right));
    }
    
    /**
     * Print tree structure (simple visualization)
     */
    private static void printTree(Node root, int depth) {
        if (root == null) {
            return;
        }
        
        // Print right subtree first (top to bottom)
        printTree(root.right, depth + 1);
        
        // Print current node
        for (int i = 0; i < depth; i++) {
            System.out.print("    ");
        }
        System.out.println(root.val);
        
        // Print left subtree
        printTree(root.left, depth + 1);
    }
}
