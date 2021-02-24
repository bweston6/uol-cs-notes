//
// Enter your name: Ben Weston
// Enter your student ID: 201415468
//
class COMP108W03 {

	// print the content of an array of size n
	static void printArray(int[] data, int n) {
		int i;

		for (i=0; i < n; i++)
			System.out.print(data[i] + " ");
		System.out.println();
	}

	// data[] is an array, n is size of array, key is the number we want to find
	static void seqSearch(int[] data, int n, int key) {
		int i, count;
		boolean found;

		// start sequential search on the array called data[]
		found = false;	// to indicate if the number is found
		i = 0;		// an index variable to iterate through the array
		count = 0;	// to count how many comparisons are made
		while (i<n && found==false) {
			if (data[i] == key) {
				found = true;
			} else {
				i = i+1;
			}
			count = count+1;
		}
		System.out.print("The number " + key + " is ");
		if (found == false)
			System.out.print("not ");
		System.out.println("found by sequential search and the number of comparisons used is " + count);
	}

	// data[] is an array in ascending order, n is size of array, key is the number we want to find
	// You can assume that data[] is already sorted
	// refer to Lecture 6
	static void binarySearch(int[] data, int n, int key) {
		int first = 0;
		int last = n - 1;
		int mid;
		int count = 0;
		boolean found = false;
		while(first <= last && found == false) {
			mid = (int)((first + last) / 2);
			if(data[mid] == key) {
				found = true;
			}
			else if(data[mid] > key) { 	// these two cases will trip if first <= last
				last = mid - 1;
			}
			else {
				first = mid + 1;
			}
			count++;
		}
		System.out.print("The number " + key + " is ");
		if (found == false)
			System.out.print("not ");
		System.out.println("found by binary search and the number of comparisons used is " + count);
	}

	// print the smallest number in the array of size n
	static void findMin(int[] data, int n) {
		int i, min;

		min = data[0];
		i = 1;
		while (i < n) {
			if (data[i] < min)
				min = data[i];
			i++;
		}
		System.out.println("The smallest number is " + min + ".");
	}

	// print the largest number in the array of size n
	// refer to Lecture 8
	static void findMax(int[] data, int n) {
		int i, max;
		max = data[0];
		i = 1;
		while (i < n){
			if (data[i] > max)
				max = data[i];
			i++;
		}
		System.out.println("The largest number is " + max + ".");
	}

	// print the second smallest number in the array of size n
	// refer to Lecture 8
	static void findSecondMin(int[] data, int n) {
		int i, min, secmin;

		min = data[0];
		secmin = data[0];
		i = 1;
		while (i < n) {
			if (data[i] < min){
				secmin = min;
				min = data[i];
			}
			i++;
		}
		System.out.println("The smallest number is " + min + ". The second smallest number is " + secmin + ".");
	}

	// print the second largest number in the array of size n
	// refer to Lecture 8
	static void findSecondMax(int[] data, int n) {
		int i, max, secmax;

		max = data[0];
		secmax = data[0];
		i = 1;
		while (i < n) {
			if (data[i] > max){
				secmax = max;
				max = data[i];
			}
			i++;
		}
		System.out.println("The largest number is " + max + ". The second largest number is " + secmax + ".");
	}
	
	// print the smallest number and its position in an array of size n
	// Find the bug and fix it by altering ONE line of code
	static void bugOne(int[] data, int n) {
		int i, pos, min;

		pos = 0;
		min = data[0];
		i = 1;
		while (i < n) {
			if (data[i] < min) {
				min = data[i];
				pos = i;
			}
			i++;
		}
		System.out.println("The smallest number is " + min + " at position " + pos + ".");
	}
}
