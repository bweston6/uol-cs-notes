// Coded by Prudence Wong 2020-12-15

// Name: Ben Weston
// Student ID: 201415467

// -------Note for Javadocs------
/*
Ensure to compile javadocs with `-package` to ensure all classes are
included.
*/

// ----------Task 5--------------
/* noEvict()
The time complexity of this algorithm is O(n) as it loops through each request
once.
*/
/* evictFIFo()
The complexity is O(n*p) as it loops through each request once and for each
request it loops through the contents of the cache once.
*/
/* evictLFU()
The time complexity is O(p+pn) as it loops though each element in the cache,
to initialise, then loops through each request. For each request it then loops
through the cache three times. The constant 3 is not significant enough so it
is dropped.
*/
/* evictLFD()
The time complexity is (.5n^3+.5n^2+2pn). When it initialises it loops through
the cache and for each iteration it loops through the requests. It then loops
through each request where it loops through the cache once and the request queue
once less time for each loop. This gives a total time complexity of:
pn+n(p+(n(n-1))/(2)) which simplifies to the expression I began with.
In big-O notation that is: O(n^3+pn) as you keep the most significant powers of
each variable and drop any constant coefficients.
*/

/**
A set of re-implemented functions for use in COMP108A1Paging.
*/
class Functions {
	/**
	States whether an {@code int} is present in an array.
	
	<p> Requires the length of the list to be searched to avoid counting or
	using standard libraries.</p>
	
	@param x The integer to find.
	@param list The list to search for the integer.
	@param listLength The length of the list that will be searched.
	@return A {@code boolean} stating whether the integer was found in the list.
	*/
	public static boolean present(int x, int[] list, int listLength) {
		boolean flag = false;
		// search all element of the list incrementally
		for (int i = 0; i < listLength; i++) {
			if (list[i] == x) {
				flag = true;
				// stop early if element is found
				return flag;
			}
		}
		// return false if element is not found
		return flag;
	}
	/**
	Returns the index of the smallest element in a list.
	
	<p> Values are zero indexed.</p>
	
	<p> Requires the length of the list to be searched to avoid counting or
	using standard libraries.</p>
	
	@param list The list to search.
	@param listLength The length of the list to be searched.
	@return The index of the smallest element in the list.
	*/
	public static int lowestIndex(int[] list, int listLength) {
		// initialise lowest to first value
		int lowestIndex = 0;
		int lowestValue = list[0];
		// start test against second value
		for (int i = 1; i < listLength; i++) {
			// only execute if strictly lower
			if (list[i] < lowestValue) {
				lowestValue = list[i];
				lowestIndex = i;
			}
		}
		return lowestIndex;
	}
	/**
	Returns the index of the largest element in a list.
	
	<p> Values are zero indexed.</p>
	<p> Requires the length of the list to be searched to avoid counting or
	using standard libraries.</p>
	
	@param list The list to search.
	@param listLength The length of the list to be searched.
	@return The index of the largest element in the list.
	*/
	public static int highestIndex(int[] list, int listLength) {
		// initialise highest to first value
		int highestIndex = 0;
		int highestValue = list[0];
		// start test against second value
		for (int i = 1; i < listLength; i++) {
			// only execute if strictly greater
			if (list[i] > highestValue) {
				highestValue = list[i];
				highestIndex = i;
			}
		}
		return highestIndex;
	}
	/**
	Finds the index of an integer in a list of integers.
	
	<p> Values are zero indexed.</p>
	<p> This method presumes that the element exists in the list. Ensure you
	have checked for the presence of the element before calling this method.</p>
	<p> Requires the length of the list to be searched to avoid counting or
	using standard libraries.</p>
	
	@param x The integer to be found.
	@param list The list to search in.
	@param listLength The length of {@code list}
	@return If the element is found the index of the element will be returned.
		If not then {@code -1} is returned.
	*/
	public static int findIndex(int x, int[] list, int listLength) { // only to be called when element is in list
		for (int i = 0; i < listLength; i++) {
			if (list[i] == x)
				return i; // if element is found, return index
		}
		return -1;
	}
	/**
	Counts forward in {@code list} until {@code x} is found. If the element is
	not found then the length of the list is returned.
	
	<p>To account for requests in the array that have been served
	{@code counter} can be used to offset the search. Ideally the index of the
	request currently being served from {@code list} should be provided.
	However, all indexes from {@code counter + 1} will be considered in the
	search.</p>
	<p> Requires the length of the list to be searched to avoid counting or
	using standard libraries.</p>
	
	@param x The element to forward search for.
	@param list The list to search for {@code x} in.
	@param listLength The length of {@code list}.
	@param counter The index to search forward from. Use {@code -1} to search
		all elements.
	@return The index of {@code list} where {@code x} is found. If the element
		is not found then {@code listLength} is returned to simulate infinity.
	*/
	public static int forwardDistance(int x, int[] list, int listLength, int counter) {
		// use counter from calling function
		for (int i = counter + 1; i < listLength; i++) {
			if (list[i] == x)
				// return index of element, by forward search, if in list
				return i;
		}
		// equivalent of infinity
		return listLength;
	}
}
/**
The implementation of four paging algorithms to be called by
{@code COMP108A1PagingApp}.
*/
class COMP108A1Paging {
	/**
	An algorithm that evicts no pages from the cache.
	
	<p>This function iterates through each element in the request queue.</p>
	<p>If the element is in the cache then it will add a hit to
	{@code output.hitCount} and append an {@code "h"} onto
	{@code output.hitPattern}.</p>
	<p>If the element is not in the cache then it will add a miss to
	{@code output.missCount} and append an {@code "m"} onto
	{@code output.hitPattern}.</p>
	
	@param cArray The contents of the cache.
	@param cSize The number of elements in the cache.
	@param rArray An array of pending requests.
	@param rSize The number of elements in the request queue.
	@return A construct of the form {@code COMP108A1Output} that contains the
		number of cache hits, number of cache misses and a string indicating
		the order of the hits and misses.
	*/
	static COMP108A1Output noEvict(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		// iterate through request array
		for (int i = 0; i < rSize; i++){ 
			if (Functions.present(rArray[i], cArray, cSize)) {
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
	/**
	An algorithm that evicts the number present in the cache for the longest
	time in the event of a miss.
	
	<p>This function notes the first element in the cache and iterates through
	each element in the request queue.</p>
	<p>If the element is in the cache then it will add a hit to
	{@code output.hitCount} and append an {@code "h"} onto
	{@code output.hitPattern}.</p>
	<p>If the element is not in the cache then it will add a miss to
	{@code output.missCount} and append an {@code "m"} onto
	{@code output.hitPattern}.</p>
	<p>The cache is updated by assigning the missed value to the current
	{@code first} element in the cache. The {@code first} pointer is then
	incremented (mod {@code cSize}) to point to the next oldest value.</p>
	
	@param cArray The contents of the cache.
	@param cSize The number of elements in the cache.
	@param rArray An array of pending requests.
	@param rSize The number of elements in the request queue.
	@return A construct of the form {@code COMP108A1Output} that contains the
		number of cache hits, number of cache misses and a string indicating
		the order of the hits and misses.
	*/
	static COMP108A1Output evictFIFO(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		// initialise location of "first" element
		int first = 0;
		// iterate through request array
		for (int i = 0; i < rSize; i++){
			if (Functions.present(rArray[i], cArray, cSize)) {
				output.hitCount++;
				output.hitPattern += "h";
			}
			else {
				output.missCount++;
				output.hitPattern += "m";
				// push current request to cache
				cArray[first] = rArray[i]; 
				// increment first location, loop if larger than cSize
				first = (first + 1) % cSize;
			}
		}
		return output;
	}
	/**
	An algorithm evicts the least frequently used element of the cache in
	the event of a miss. If there is more than one then the left-most is used.
	
	<p>This function initially sets the frequency of all elements in the cache
	to 1.</p>
	<p>If the element is in the cache then it will add a hit to
	{@code output.hitCount} and append an {@code "h"} onto
	{@code output.hitPattern}. It will then increment the frequency of the cache
	element that was hit. {@link Functions#present(int, int[], int)} is used
	to find the location of said element.</p>
	<p>If the element is not in the cache then it will add a miss to
	{@code output.missCount} and append an {@code "m"} onto
	{@code output.hitPattern}. It will then write the request to the least used
	element of the cache using {@link Functions#lowestIndex(int[], int)}, and
	and 1 to the frequency of the same element.</p>
	
	@param cArray The contents of the cache.
	@param cSize The number of elements in the cache.
	@param rArray An array of pending requests.
	@param rSize The number of elements in the request queue.
	@return A construct of the form {@code COMP108A1Output} that contains the
		number of cache hits, number of cache misses and a string indicating
		the order of the hits and misses.
	*/
	static COMP108A1Output evictLFU(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		int[] cFreq = new int[cSize];
		// initialise frequencies to 1
		for (int i = 0; i < cSize; i++)
			cFreq[i] = 1;
		// iterate through request array
		for (int i = 0; i < rSize; i++){
			if (Functions.present(rArray[i], cArray, cSize)) {
				output.hitCount++;
				output.hitPattern += "h";
				// increment frequency
				cFreq[Functions.findIndex(rArray[i], cArray, cSize)]++;
			}
			else {
				output.missCount++;
				output.hitPattern += "m";
				// push current request to least used cache
				cArray[Functions.lowestIndex(cFreq, cSize)] = rArray[i];
				// reset frequency to 1
				cFreq[Functions.lowestIndex(cFreq, cSize)] = 1;
			}
		}
		return output;
	}
	/**
	An algorithm evicts the cache element which is requested latest in the
	cache.
	
	<p>If the cache contains elements which are not in the request queue then
	that element is preferred. If there are more than one the left-most is
	chosen.</p>
	<p>Initially, the distance of all elements in the cache are set using
	{@link Functions#forwardDistance(int, int[], int, int)}.</p>
	<p>If the requested element is in the cache then it will add a hit to
	{@code output.hitCount} and append an {@code "h"} onto
	{@code output.hitPattern}.</p>
	<p>If the element is not in the cache then it will add a miss to
	{@code output.missCount} and append an {@code "m"} onto
	{@code output.hitPattern}. It will then write the request to the cache index
	with the highest distance using
	{@link Functions#highestIndex(int[], int)}.</p>
	<p>The distances are then refreshed using the same method they were
	initialised with except the request counter {@code i} is passed, to search
	forward from that index.</p>
	
	@param cArray The contents of the cache.
	@param cSize The number of elements in the cache.
	@param rArray An array of pending requests.
	@param rSize The number of elements in the request queue.
	@return A construct of the form {@code COMP108A1Output} that contains the
		number of cache hits, number of cache misses and a string indicating
		the order of the hits and misses.
	*/
	static COMP108A1Output evictLFD(int[] cArray, int cSize, int[] rArray, int rSize) {
		COMP108A1Output output = new COMP108A1Output();
		int[] cDistance = new int[cSize];
		for (int i = 0; i < cSize; i++)
			// -1 to start counter at 0
			cDistance[i] = Functions.forwardDistance(cArray[i], rArray, rSize, -1);
		// iterate through request array
		for (int i = 0; i < rSize; i++){
			if (Functions.present(rArray[i], cArray, cSize)) {
				output.hitCount++;
				output.hitPattern += "h";
			}
			else {
				output.missCount++;
				output.hitPattern += "m";
				// push current request to cache with greatest distance
				cArray[Functions.highestIndex(cDistance, cSize)] = rArray[i];
			}
			// refresh distances
			for (int j = 0; j < cSize; j++)
				cDistance[j] = Functions.forwardDistance(cArray[j], rArray, rSize, i);
		}
		return output;
	}
}
