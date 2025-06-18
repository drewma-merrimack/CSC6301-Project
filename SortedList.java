import java.util.LinkedList;
import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

/**
 * <p>
 * Project 04 (v1.0)
 * </p>
 * <p>
 * A program that reads integers from either keyboard input or a text file (input.txt),
 * stores them in a sorted LinkedList using the Java Collections Framework, and prints
 * the sorted list. Includes error handling for invalid inputs and file issues, with
 * a loop to ensure valid input method selection. Utilizes `Collections.sort` for code
 * reuse instead of reimplementing a sorting algorithm.
 * </p>
 * 
 * <p>
 * Example Ouput:
 * </p>
 * <pre>{@code
user@users-Mac-mini Module4 % javac SortedList.java
user@users-Mac-mini Module4 % java SortedList
Would you like to enter integers or read them from file (T for type or F for File)? T
Enter integers with spaces between them [e.g., 5 2 8 1 9]: 16 1 3 12 7
Sorted List: [1, 3, 7, 12, 16]
user@users-Mac-mini Module4 % java SortedList
Would you like to enter integers or read them from file (T for type or F for File)? S
Invalid input. Enter (T for type or F for File).
Would you like to enter integers or read them from file (T for type or F for File)? F
Scanned integers from input.txt: [5, 2, 8, 1, 9]
Sorted List: [1, 2, 5, 8, 9]
user@serss-Mac-mini Module4 % java SortedList
Would you like to enter integers or read them from file (T for type or F for File)? T
Enter integers with spaces between them [e.g., 5 2 8 1 9]: 
Error: No integers entered.
 * }</pre>
 * 
 * @author Matthew Drew
 * @version 1.0
 */
public class SortedList {
    /**
     * Reads integers from user-specified input (keyboard or file), sorts them,
     * and prints the sorted list. Handles invalid inputs and file errors with
     * a loop to ensure a valid choice (T or F) is entered. Reuses `Collections.sort`
     * from the Java Collections Framework for efficient sorting.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        LinkedList<Integer> numbers = new LinkedList<>();
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
                        numbers.add(inputScanner.nextInt());
                    } else {
                        inputScanner.next(); // Skip invalid input
                        System.out.println("Warning: Skipped invalid input (non-integer).");
                    }
                }
                inputScanner.close();
                if (numbers.isEmpty()) {
                    System.out.println("Error: No valid integers entered.");
                    return;
                }
                Collections.sort(numbers); // Sort the list for keyboard input
                System.out.println("Sorted List: " + numbers);

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
                        numbers.add(fileScanner.nextInt());
                    } else {
                        fileScanner.next(); // Skip invalid input
                        System.out.println("Warning: Skipped invalid input (non-integer) in input.txt.");
                    }
                }
                fileScanner.close();
                if (numbers.isEmpty()) {
                    System.out.println("Error: No valid integers found in input.txt.");
                    return;
                }
                System.out.println("Scanned integers from input.txt: " + numbers);
                Collections.sort(numbers); // Sort the list for file input
                System.out.println("Sorted List: " + numbers);
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: input.txt not found. Please ensure the file exists in the same directory.");
        } finally {
            if (scanner != null) {
                scanner.close();
            }
        }
    }
}