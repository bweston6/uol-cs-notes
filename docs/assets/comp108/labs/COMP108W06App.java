//
// by Prudence Wong 2021-03-08
//
import java.util.*;
import java.io.*;


class COMP108W06App {

	private static Scanner keyboardInput = new Scanner (System.in);
	private static final int MaxCount = 100;

	public static void main(String[] args) throws Exception {
		COMP108W06 list = new COMP108W06();
		int[] inputData = new int[MaxCount];
		int count = 0, key = -1;

		try {
			System.out.println();
			System.out.print("Enter the number of values to store (1-" + MaxCount + "): ");
			count = keyboardInput.nextInt();
			if (count > MaxCount || count <= 0)
				System.exit(0);
			System.out.print("Enter " + count + " integers: ");
			for (int i=0; i<count; i++)
				inputData[i] = keyboardInput.nextInt();				
		}
		catch (Exception e) {
			keyboardInput.next();
			System.exit(0);
		}

		
		for (int i=count-1; i>=0; i--) {
			list.insertHead(new Node(inputData[i]));
		}
		System.out.println();
		System.out.println("Head to tail: " + list.headToTail());
		System.out.println("Tail to head: " + list.tailToHead());


		try {
			System.out.print("Enter a number to search (-1 to exit): ");
			key = keyboardInput.nextInt();
			if (key != -1) {
				System.out.println(key + " is " + (list.seqSearchList(key)? "" : "not ")  + "found.");
				System.out.println(key + " appears " + list.countList(key) + " times.");
			}
		}
		catch (Exception e) {
			keyboardInput.next();
			System.exit(0);
		}
		
		System.out.println("The minimum is " + list.searchMin());
		System.out.println("The maximum is " + list.searchMax());


	
	}


/*
	// Do NOT change this method!
	// print array[0]..array[size-1]
	static void printArray(int[] array, int size) {
		for (int i=0; i<size; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
*/

}
