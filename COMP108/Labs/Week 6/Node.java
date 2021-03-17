//
// by Prudence Wong 2021-03-08
//

class Node {
	public int data; 
	public Node next;
	public Node prev;
    
	// constructor to create a new node with data equals to parameter i
	public Node (int i) {
		next = null;
		prev = null;
		data = i;
	}
}

