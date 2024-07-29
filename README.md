# Hash Table Implementation in Java

This project implements a hash table using linked lists to handle collisions. The implementation includes the following classes:

1. **listNode**: Represents a node in the linked list.
2. **hashTable**: Represents the hash table and includes methods for insertion, deletion, and retrieval.
3. **Shaxzod_Karimov_Main**: Contains the main method that drives the program.

## listNode Class

The `listNode` class represents a node in a linked list used within the hash table.

### Attributes
- `String data`: The data stored in the node.
- `listNode next`: A pointer to the next node in the list.

### Constructor
- `listNode(String data, listNode next)`: Initializes the node with the given data and next node reference.

## hashTable Class

The `hashTable` class manages the hash table operations.

### Attributes
- `char op`: The operation to be performed ('+', '-', '?').
- `String data`: The data to be inserted, deleted, or retrieved.
- `int bucketSize`: The size of the hash table (number of buckets).
- `listNode[] hashTable`: An array of `listNode` objects representing the hash table.

### Constructor
- `hashTable(int bucketSize)`: Initializes the hash table with a specified number of buckets, each starting with a dummy node.

### Methods
- `int Doit(String data)`: Computes the hash value for the given data.
- `void informationProcessing(Scanner inFile, FileWriter outFile)`: Processes the input file, performing the specified operations on the hash table.
- `listNode findSpot(int index, String data)`: Finds the appropriate spot for a given data in the list.
- `void hashInsert(int index, String data, FileWriter outFile)`: Inserts data into the hash table.
- `void hashDelete(int index, String data, FileWriter outFile)`: Deletes data from the hash table.
- `void hashRetrieval(int index, String data, FileWriter outFile)`: Retrieves data from the hash table.
- `void printList(int index, FileWriter outFile)`: Prints the list at the specified index.
- `void printHashTable(FileWriter outFile)`: Prints the entire hash table.

## Shaxzod_Karimov_Main Class

The `Shaxzod_Karimov_Main` class contains the `main` method, which serves as the entry point for the program.

### main Method

The `main` method:
1. Reads the input file name, bucket size, and output file names from the command line arguments.
2. Initializes a `hashTable` object with the specified bucket size.
3. Calls the `informationProcessing` method to process the input file.
4. Prints the final state of the hash table to the specified output files.

### Usage

To use this program:
1. **Compile the Code**: Ensure all source files are in the same directory and compile the code using a Java compiler.
   ```bash
   javac Shaxzod_Karimov_Main.java
