//
// Enter your name: Ben Weston
// Enter your student ID: 201415467
//
// 1.   Time complexity of notExists() is size1 * size2 as the program iterates through
//      the 2nd array the amount of times as there are items in array1.
// 2.   The time complexity of count() is the same as notExists() as it iterates the
//      same amount of times.
// 3.   The puzzle question requires using a form of binary search to weigh all the eggs,
//      see which side is heaver, and then split the heavier eggs to be weighed again.
//      The same concept applies for the second part of the question.
//
import java.util.*;
import java.io.*;

class COMP108W04 {

	// print the content of an array of size n
	static void printArray(int[] data, int n) {
		int i;

		for (i=0; i < n; i++)
			System.out.print(data[i] + " ");
		System.out.println();
	}

	// Input: array1[] with size1 entries and array2[] with size2 entries
	// print all entries of array1[] that does not exist in array2[]
	static void notExists(int array1[], int size1, int array2[], int size2) {
                for (int i = 0; i < size1; i++) {
                        boolean found = false;
                        for (int j = 0; j < size2; j++) {
                                if (array1[i] == array2[j])
                                        found = true;
                        }
                        if (found == false)
                                System.out.print(array1[i] + " ");
                }
                System.out.println();
	}
		
	// Input: array1[] with size1 entries and array2[] with size2 entries
	// for each entry in array2[], count how many times it appears in array1[]
	static void count(int array1[], int size1, int array2[], int size2) {
               for (int i = 0; i < size2; i++) {
                       int n = 0;
                       for (int j = 0; j < size1; j++) {
                                if (array2[i] == array1[j])
                                        n++;
                       }
                       System.out.print(n + " ");
               } 
               System.out.println();
	}

}
