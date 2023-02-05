//package project;
import java.io.*;
import java.util.*;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

class listNode {
    public String data;
    public listNode next;

    listNode(String data, listNode next) {
        this.data = data;
        this.next = next;
    }
}


class hashTable {
    public char op;
    public String data;
    public int bucketSize;
    public listNode hashTable[];

    hashTable(int bucketSize) {
        hashTable = new listNode[bucketSize];
        this.bucketSize = bucketSize;
        for (int i = 0; i < bucketSize; i++) {
            hashTable[i] = new listNode("dummy", null);
        }
    }

    int Doit(String data) {
        long value = 1;
        int i = 0;
        int length = data.length();
        for (i = 0; i < length; i++) {
            char oneCh = data.charAt(i);
            value = value * 32 + (int) oneCh;
        }
        return (int)(value % bucketSize);
    }

    void informationProcessing(Scanner inFile, FileWriter outFile) throws IOException {
        outFile.write("Enter information Processing method \n");
        while (inFile.hasNextLine()) {
            String dataIn = inFile.nextLine();
            String dataSplitByWhiteSpace[] = dataIn.split("\\s+");
            String op = dataSplitByWhiteSpace[0];
            String data = dataSplitByWhiteSpace[1];
            outFile.write("Data inputted: " + op + " " + data + " \n");
            int index = Doit(data);
            outFile.write("Index: " + index + "\nPrinting on bucket " + index + " \n");
            printList(index, outFile);
            if (op.equals("+")) {
                hashInsert(index, data, outFile);
            } else if (op.equals("-")) {
                hashDelete(index, data, outFile);
            } else if (op.equals("?")) {
                hashRetrieval(index, dataIn, outFile);
            } else {
                outFile.write(" op is an unrecognize operation! \n");
            }
        }
    }

    public listNode findSpot(int index, String data) {
        listNode Spot = hashTable[index];
        while (Spot.next != null && Spot.next.data.compareTo(data) < 0) {
            Spot = Spot.next;
        }
        return Spot;
    }


    public void hashInsert(int index, String data, FileWriter outFile) throws IOException {
        outFile.write("*** enter hashInsert method. Performing hashInsert \n");
        listNode Spot = findSpot(index, data);
        if (Spot.next != null && Spot.next.data.equals(data) == true) {
            outFile.write("*** Warning, data is already in the database! \n");
        } else {
            listNode newNode = new listNode(data, Spot.next);
            Spot.next = newNode;
            outFile.write("After hashInsert operation ... \n");
            printList(index, outFile);
        }
    }

    public void hashDelete(int index, String data, FileWriter outFile) throws IOException {
        outFile.write("** Inside hashDelete method. Performing hashDelete \n");
        listNode Spot = findSpot(index, data);
        if (Spot.next == null || Spot.next.data != data) {
            outFile.write("*** Warning: data is *not* in the database! \n");
        } else {
            listNode temp = Spot.next;
            Spot.next = temp.next;
            temp.next = null;
            outFile.write("After hashDelete operation ... \n");
            printList(index, outFile);
        }
    }

    public void hashRetrieval(int index, String data, FileWriter outFile) throws IOException {
        outFile.write("** Inside hashRetrieval. Performing hashRetrieval \n");
        listNode Spot = findSpot(index, data);
        if (Spot.next == null || Spot.next.data.equals(data) == false) {
            outFile.write("** Warning, the record is *not* in the database! \n");
        } else {
            outFile.write("** Yes, the record is in the database! \n");
        }
    }

    public void printList(int index, FileWriter outFile) throws IOException {
        String result = "";
        listNode selectedNode = hashTable[index];
        while (selectedNode != null) {
            if (selectedNode.next != null) {
                result += ("(" + selectedNode.data + " " + selectedNode.next.data + ") -> ");
                selectedNode = selectedNode.next;
            } else {
                result += "(" + selectedNode.data + " null) ";
                break;
            }
        }
        outFile.write("HashTable[" + String.valueOf(index) + "]: " + result + "\n");
    }

    public void printHashTable(FileWriter outFile1) throws IOException {
        //FileWriter fstream = new FileWriter(outFile1, true);
        for (int i = 0; i < bucketSize; i++) {
            printList(i, outFile1);
        }
    }
}


class Shaxzod_Karimov_Main {
    public static void main(String[] args) throws IOException {
        Scanner inFile = new Scanner(new FileReader(args[0]));
        int bucketSize = Integer.parseInt(args[1]);
        FileWriter myWriter1 = new FileWriter(args[2]);
        FileWriter myWriter2 = new FileWriter(args[3]);
        hashTable H = new hashTable(bucketSize);
        H.informationProcessing(inFile, myWriter2);
        H.printHashTable(myWriter1);
        inFile.close();
        myWriter1.close();
        myWriter2.close();
    }
}