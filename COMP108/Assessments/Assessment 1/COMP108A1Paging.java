//
// Note: You are allowed to add additional methods if you need.
// Coded by Prudence Wong 2020-12-15
//
// Name: Ben Weston
// Student ID: 201415467
//
// Time Complexity and explanation: You can use the following variables for easier reference.
// n denotes the number of requests, p denotes the size of the cache
// n and p can be different and there is no assumption which one is larger
//
// noEvict():
//
// evictFIFO():
//
// evictLFU():
//
// evictLFD():
//
class Functions {
	public static boolean present(int x, int[] list, int listLength) {
		boolean flag = false;
		for (int i = 0; i < listLength; i++) { // search all element of the list incrementally
			if (list[i] == x) {
				flag = true;
				return flag; // stop early if element is found
			}
		}
		return flag; // return false if element is not found
	}
	public static int lowestIndex(int[] list, int listLength) {
		int lowestIndex = 0; // initialise lowest to first value
		int lowestValue = list[0];
		for (int i = 1; i < listLength; i++) { // start test against second value
			if (list[i] < lowestValue) { // only execute if strictly lower
				lowestValue = list[i]; 
				lowestIndex = i;
			}
		}
		return lowestIndex;
	}
	public static int highestIndex(int[] list, int listLength) {
		int highestIndex = 0; // initialise lowest to first value
		int highestValue = list[0];
		for (int i = 1; i < listLength; i++) { // start test against second value
			if (list[i] > highestValue) { // only execute if strictly lower
				highestValue = list[i]; 
				highestIndex = i;
			}
		}
		return highestIndex;
	}
	public static int findIndex(int x, int[] list, int listLength) { // only to be called when element is in list
		for (int i = 0; i < listLength; i++) {
			if (list[i] == x)
				return i; // if element is found, return index
		}
		return -1;
	}
	public static int forwardDistance(int x, int[] list, int listLength, int counter) {
		for (int i = counter + 1; i < listLength; i++) { // use counter from calling function
			if (list[i] == x)
				return i; // return index of element, by forward search, if in list
		}
		return listLength; // equivalent of infty
	}
}
class COMP108A1Paging {


	// no eviction
	// Aim: 
	// do not evict any page
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output noEvict(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		for (int i = 0; i < rSize; i++){ // iterate through request array
			if (Functions.present(rArray[i], cArray, cSize)) { // if request is in cache
				output.hitCount++;
				output.hitPattern += "h";
			}
			else {
				output.missCount++;
				output.hitPattern += "m";
			}
		}
		return output;
	}

	// evict FIFO
	// Aim: 
	// evict the number present in cache for longest time if next request is not in cache
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input: 
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output evictFIFO(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		int first = 0; // initialise location of "first" element
		for (int i = 0; i < rSize; i++){ // iterate through request array
			if (Functions.present(rArray[i], cArray, cSize)) { // if request is in cache
				output.hitCount++;
				output.hitPattern += "h";
			}
			else {
				output.missCount++;
				output.hitPattern += "m";
				cArray[first] = rArray[i]; // push current request to cache
				first = (first + 1) % cSize; // increment first location, loop if larger than cSize
			}
		}
		return output;
	}

	// evict LFU
	// Aim:
	// evict the number that is least freently used so far if next request is not in cache
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries
	static COMP108A1Output evictLFU(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		int[] cFreq = new int[cSize];
		for (int i = 0; i < cSize; i++) // initialise frequencies to 1
			cFreq[i] = 1;
		for (int i = 0; i < rSize; i++){ // iterate through request array
			if (Functions.present(rArray[i], cArray, cSize)) { // if request is in cache
				output.hitCount++;
				output.hitPattern += "h";
				cFreq[Functions.findIndex(rArray[i], cArray, cSize)]++; // increment frequency
			}
			else {
				output.missCount++;
				output.hitPattern += "m";
				cArray[Functions.lowestIndex(cFreq, cSize)] = rArray[i]; // push current request to least used cache
				cFreq[Functions.lowestIndex(cFreq, cSize)] = 1; // reset frequency to 1
			}
		}
		return output;
	}

	// evict LFD
	// Aim:
	// evict the number whose next request is the latest
	// count number of hit and number of miss, and find the hit-miss pattern; return an object COMP108A1Output
	// Input:
	// cArray is an array containing the cache with cSize entries
	// rArray is an array containing the requeset sequence with rSize entries	
	static COMP108A1Output evictLFD(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		int[] cDistance = new int[cSize];
		for (int i = 0; i < cSize; i++)
			cDistance[i] = Functions.forwardDistance(cArray[i], rArray, rSize, -1); // start counter at 0
		for (int i = 0; i < rSize; i++){ // iterate through request array
			if (Functions.present(rArray[i], cArray, cSize)) { // if request is in cache
				output.hitCount++;
				output.hitPattern += "h";
			}
			else {
				output.missCount++;
				output.hitPattern += "m";
				cArray[Functions.highestIndex(cDistance, cSize)] = rArray[i]; // push current request to least used cache
			}
			for (int j = 0; j < cSize; j++)
				cDistance[j] = Functions.forwardDistance(cArray[j], rArray, rSize, i); // refresh distances 
		}

		return output;
	}

}

