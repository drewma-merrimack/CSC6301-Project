# Module 05: Assignment 01 - Project 05

## Overview
This project is a maintenance task adapting the previous SortedList program (Module 04) to use a Stack instead of a LinkedList. The program reads integers from either keyboard input or a text file (input.txt), sorts them using the Collections Framework, stores them in a Stack in ascending order, and prints the sorted list. It includes Javadocs documentation with example output, error handling, and reuses `Collections.sort` instead of reimplementing a sorting algorithm.

## Feedback Reflection
Module 04's suggestion to improve loose coupling for better adaptability in future assignments, and should be planned for non-maintenance update. This maintenance task retains the single-file structure as required, but a more advanced, non-maintenance update could involve refactoring the code into separate classes (e.g., `InputHandler` for reading data, `Sorter` for sorting logic) to reduce tight coupling and enhance modularity.

## Files
- `SortedList.java`: Program that reads, sorts, and stores integers in a Stack, with Javadocs and error handling.
- `input.txt`: Sample input file with integers (e.g., "5 2 8 1 9").
- `diagram.puml`: PlantUML source for the UML diagram.
- `diagram.pdf`: UML class diagram.
- `diagram.odg`: Open document drawing (LibreOffice precursor to PDF file)
- `README.md`: This File
- 
## Commands to Run
1. **Compile**:
   ```bash
   javac SortedList.java

## CSC6301 Projects
Repository for Merrimack CSC6301 assignments.
- **Project 04 (v1.0)**: Initial SortedList with LinkedList.
- **Project 05 (v2.0)**: Maintenance task with Stack adaptation.
See individual folders for details.

## License

  MIT License (see `LICENSE` file).