import java.util.LinkedList;
import java.util.Collections;
import java.util.Stack;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * <p>
 * Project 05 (v2.0)
 * </p>
 * <p>
 * A program that reads integers from either keyboard input or a text file (input.txt),
 * stores them in a sorted Stack using the Java Collections Framework, and prints
 * the sorted list. This is a maintenance task adapting the previous LinkedList-based
 * SortedList (Module 04) to use a Stack, reusing Collections.sort for sorting.
 * </p>
 * 
 * 
 * <p>
 * Example Ouput:
 * </p>
 * <pre>{@code
user@users-Mac-mini Module4 % javac SortedList.java
user@users-Mac-mini Module4 % java SortedList
Would you like to enter integers or read them from file (T for type or F for File)? T
Enter integers with spaces between them [e.g., 5 2 8 1 9]: 5 2 1 8 9
Sorted Stack: 1, 2, 5, 8, 9
user@users-Mac-mini Module4 % java SortedList
Would you like to enter integers or read them from file (T for type or F for File)? F
Scanned integers from input.txt: [5, 2, 8, 1, 9]
Sorted Stack: 1, 2, 5, 8, 9
Would you like to enter integers or read them from file (T for type or F for File)? G
Invalid input. Enter (T for type or F for File).
Would you like to enter integers or read them from file (T for type or F for File)? T
Enter integers with spaces between them [e.g., 5 2 8 1 9]: 5 a 1 8 9
Warning: Skipped invalid input (non-integer).
Sorted Stack: 1, 5, 8, 9
 * }</pre>
 * 
 * @author Matthew Drew
 * @version 1.0
 */
public class SortedList {
    /**
     * Reads integers from user-specified input (keyboard or file), sorts them,
     * stores them in a Stack in ascending order, and prints the sorted list.
     * Handles invalid inputs and file errors with a loop to ensure a valid
     * choice (T or F) is entered. Reuses `Collections.sort` instead of
     * reimplementing a sorting algorithm.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> tempList = new LinkedList<>(); // Temporary list for sorting
        Stack<Integer> sortedStack = new Stack<>();
        String choice;

        do {
            System.out.print("Would you like to enter integers or read them from file (T for type or F for File)? ");
            choice = scanner.nextLine().trim().toUpperCase();
            if (!"T".equals(choice) && !"F".equals(choice)) {
                System.out.println("Invalid input. Enter (T for type or F for File).");
            }
        } while (!"T".equals(choice) && !"F".equals(choice));

        try {
            if ("T".equals(choice)) {
                // Keyboard input
                System.out.print("Enter integers with spaces between them [e.g., 5 2 8 1 9]: ");
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Error: No integers entered.");
                    return;
                }

                Scanner inputScanner = new Scanner(input);
                while (inputScanner.hasNext()) {
                    if (inputScanner.hasNextInt()) {
                        tempList.add(inputScanner.nextInt());
                    } else {
                        inputScanner.next(); // Skip invalid input
                        System.out.println("Warning: Skipped invalid input (non-integer).");
                    }
                }
                inputScanner.close();
                if (tempList.isEmpty()) {
                    System.out.println("Error: No valid integers entered.");
                    return;
                }

            } else if ("F".equals(choice)) {
                // File input
                File inputFile = new File("input.txt");
                Scanner fileScanner = new Scanner(inputFile);
                if (!fileScanner.hasNextInt()) {
                    System.out.println("Error: input.txt is empty or contains no integers.");
                    fileScanner.close();
                    return;
                }
                while (fileScanner.hasNext()) {
                    if (fileScanner.hasNextInt()) {
                        tempList.add(fileScanner.nextInt());
                    } else {
                        fileScanner.next(); // Skip invalid input
                        System.out.println("Warning: Skipped invalid input (non-integer) in input.txt.");
                    }
                }
                fileScanner.close();
                if (tempList.isEmpty()) {
                    System.out.println("Error: No valid integers found in input.txt.");
                    return;
                }
                System.out.println("Scanned integers from input.txt: " + tempList);
            }

            // Sort the temporary list and push to Stack in ascending order
            Collections.sort(tempList); // Reuse Collections.sort for efficiency
            for (int i = tempList.size() - 1; i >= 0; i--) { // Reverse order to maintain ascending when popped
                sortedStack.push(tempList.get(i));
            }

            // Print the sorted Stack (popping to display in ascending order)
            System.out.print("Sorted Stack: ");
            Stack<Integer> tempStack = (Stack<Integer>) sortedStack.clone(); // Avoid modifying original
            while (!tempStack.isEmpty()) {
                System.out.print(tempStack.pop() + (tempStack.isEmpty() ? "" : ", "));
            }
            System.out.println();

        } catch (FileNotFoundException e) {
            System.out.println("Error: input.txt not found. Please ensure the file exists in the same directory.");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}