//
// Enter your name: Ben Weston
// Enter your student ID: 201415467
//

class COMP108W06 {

	public Node head, tail;
	
	public COMP108W06() {
		head = null;
		tail = null;
	}

	// sequential search if key is in the list
	// return true or false accordingly
	// Do NOT change its signature
	// You should implement a list traversal algorithm here
	public boolean seqSearchList(int key) {
		boolean found = false;
		Node curr = head;
		while (curr != null && found == false) {
			if (curr.data == key)
				found = true;
			
			else
				curr = curr.next;
		}
		return found;		
	}

	// sequential search to count how many times key appears is in the list
	// return the count
	// Do NOT change its signature
	// You should implement a list traversal algorithm here
	public int countList(int key) {
		int count=0;
		Node curr = head;
		while (curr != null) {
			if (curr.data == key)
				count++;
			curr = curr.next;
		}
		return count;
	}

	// finding the minimum number in the list
	// return the minimum
	// Do NOT change its signature
	// You should implement a list traversal algorithm here
	public int searchMin() {
		Node curr = head;
		int min = curr.data;
		curr = curr.next;
		while (curr!= null) {
			if (curr.data < min)
				min = curr.data;
			else
				curr = curr.next;
		}
		return min;
	}

	// finding the maximum number in the list
	// return the maximum
	// Do NOT change its signature
	// You should implement a list traversal algorithm here
	public int searchMax() {
		Node curr = head;
		int max= curr.data;
		curr = curr.next;
		while (curr!= null) {
			if (curr.data > max)
				max = curr.data;
			else
				curr = curr.next;
		}
		return max;
	}

	// DO NOT change this method
	// insert newNode to the head of the list
	public void insertHead(Node newNode) {
		newNode.next = head;
		newNode.prev = null;
		if (head == null)
			tail = newNode;
		else
			head.prev = newNode;
		head = newNode;
	}

	// DO NOT change this method
	// insert newNode to the tail of the list
	public void insertTail(Node newNode) {
		newNode.next = null;
		newNode.prev = tail;
		if (tail != null)
			tail.next = newNode;
		else head = newNode;
		tail = newNode;
	}

	// DO NOT change this method
	// this will turn the list into a String from head to tail
	// This is only here to ease outputing the list content.
	// You should not use it in your list traversal.
	public String headToTail() {
		Node curr;
		String outString="";
		
		curr = head;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.next;
		}
		return outString;
	}

	// DO NOT change this method
	// this will turn the list into a String from tail to head
	// This is only here to ease outputing the list content.
	// You should not use it in your list traversal.
	public String tailToHead() {
		Node curr;
		String outString="";
		
		curr = tail;
		while (curr != null) {
			outString += curr.data;
			outString += ",";
			curr = curr.prev;
		}
		return outString;
	}

}
