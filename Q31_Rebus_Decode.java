/**
 * Question 31: Rebus Decode (Ребус)
 *
 * Russian: "Ребус состоит из слов-картинок и апострофов вокруг них.
 *           i апострофов слева означают удалить i букв с начала слова,
 *           j апострофов справа означают удалить j букв с конца слова.
 *           Нужно склеить результаты для всех частей."
 * English: "A rebus consists of picture-words and apostrophes around them.
 *           i apostrophes on the left mean delete i letters from the start,
 *           j apostrophes on the right mean delete j letters from the end.
 *           Concatenate the results for all parts."
 *
 * Problem:
 * - Input: one line, length <= 100
 * - Contains lowercase latin letters, spaces, and apostrophes only
 * - Each token is one picture-word with optional apostrophes around it
 * - The rebus is guaranteed to be correct
 *
 * Example:
 * - Input:  yandex'''' 'algo''' trainings''''
 * - Output: yatrain
 *
 * Key insight:
 * - For each token, count apostrophes on the left and right
 * - If there are i on the left, total skipped from the left is 2*i:
 *   i apostrophes themselves + i letters to delete from the word
 * - If there are j on the right, total skipped from the right is 2*j
 * - Therefore, for token part:
 *     start = 2 * leftApostrophes
 *     end   = part.length() - 2 * rightApostrophes
 *     answer piece = part.substring(start, end)
 *
 * Time:
 * - solveWithSplit:  O(n)
 * - solveSinglePass: O(n)
 *
 * Space:
 * - solveWithSplit:  O(n) because split creates an array of parts
 * - solveSinglePass: O(1) extra excluding the output builder
 *
 * Java version: Java 8+ compatible.
 */
public class Q31_Rebus_Decode {

    /**
     * Clear and simple approach:
     * - split by spaces
     * - decode each token
     * - append to StringBuilder
     */
    public static String solveWithSplit(String rebus) {
        String[] parts = rebus.split(" ");
        StringBuilder answer = new StringBuilder();

        for (String part : parts) {
            answer.append(decodePart(part));
        }

        return answer.toString();
    }

    /**
     * Slightly more memory-efficient approach:
     * - scan the input line manually
     * - process one token at a time without split()
     */
    public static String solveSinglePass(String rebus) {
        StringBuilder answer = new StringBuilder();
        int start = 0;

        for (int i = 0; i <= rebus.length(); i++) {
            if (i == rebus.length() || rebus.charAt(i) == ' ') {
                String part = rebus.substring(start, i);
                answer.append(decodePart(part));
                start = i + 1;
            }
        }

        return answer.toString();
    }

    /**
     * Decode one rebus part.
     *
     * Example:
     * - "test''"     -> "te"
     * - "''amigo"    -> "igo"
     * - "'algo'''"   -> ""
     */
    private static String decodePart(String part) {
        int leftApostrophes = 0;
        while (leftApostrophes < part.length() && part.charAt(leftApostrophes) == '\'') {
            leftApostrophes++;
        }

        int rightApostrophes = 0;
        int index = part.length() - 1;
        while (index >= 0 && part.charAt(index) == '\'') {
            rightApostrophes++;
            index--;
        }

        int start = leftApostrophes * 2;
        int end = part.length() - rightApostrophes * 2;
        return part.substring(start, end);
    }

    public static void main(String[] args) {
        String rebus1 = "yandex'''' 'algo''' trainings''''";
        System.out.println("Example 1:");
        System.out.println("Input:    " + rebus1);
        System.out.println("Split:    " + solveWithSplit(rebus1));
        System.out.println("1-pass:   " + solveSinglePass(rebus1));
        System.out.println("Expected: yatrain");
        System.out.println();

        String rebus2 = "''amigo";
        System.out.println("Example 2:");
        System.out.println("Input:    " + rebus2);
        System.out.println("Output:   " + solveWithSplit(rebus2));
        System.out.println("Expected: igo");
        System.out.println();

        String rebus3 = "test''";
        System.out.println("Example 3:");
        System.out.println("Input:    " + rebus3);
        System.out.println("Output:   " + solveWithSplit(rebus3));
        System.out.println("Expected: te");
    }
}
