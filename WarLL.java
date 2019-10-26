public class WarLL {
	private Node head;
	
	public class Node{
		String shape;   //shape should be one of hearts, diamonds, etc.
		int num;			//the number on the card
		public Node next;
		
		//creation of the Node/Card and its information
		public Node(int i, String j) {
			num = i;
			shape = j;
		}
		
		//the code so that when printing a card it prints it in a readable way
		public String toString() {
			String output = "";
			
			output += "Card: " +num + " of " +shape+ "\n";
			return output;
		}
		
	}
	
	//the method to add a card to the back of the deck. Needs the number and shape in order to add it.
	public void add(int o, String j) {
		Node n = new Node(o,j);
		Node temp = head; 
		head = n;
		head.next = temp;
	}
	
	//the method to add a card while taking into consideration where it should be added in the deck
	public void add(int n, int k, String o) {
		Node added = new Node(k , o);
		Node x = head;
		Node first = head;
		Node after = head;
		
		for(int i = 0; i<n-1; i++) {
			x = x.next;	
		}
		first = x;
		after = first.next;
		first.next = added;
		added.next = after;	
	}
	
	//get the card in a specific position in the deck
	public String get(int n) {
		Node y = head;
		for(int i = 0; i<n; i++) {
			y = y.next;
		}
		return "Card:"+y.num+"of"+y.shape;
	}
	
	//same as above, but instead of returning it as a string, it returns the Node entirely
	public Node getting(int n) {
		Node y = head;
		for(int i = 0; i<n; i++) {
			y = y.next;
		}
		return y;
	}
	
	//counts how many cards are in the deck
	public int size() {
		Node currs = head;
		int count = 1;
		while(currs.next != null) {
			count++;
			currs = currs.next;
		}
		return count;
	}
	
	//draws the first card 
	public Node remove() {
	Node removed = head;
	
	if(head != null)
		head = head.next;
	
	
	return removed;
	}
	
	
	//prints out the deck
	public String toString() {
		
		if(head == null) 
			return "The list is empty";
			
		Node curr = head;
		String output = "";
		
		while(curr.next != null) {
			output += "Card: " +curr.num + " of " +curr.shape+ "\n";
			curr = curr.next;
		}
		
		output += "Card: " +curr.num + " of " +curr.shape+ "\n";
		return output;
	}
		
	
	
	public static void main(String[] args) {
		WarLL lists = new WarLL();
		lists.add(7, "hearts");
		lists.add(6, "clubs");
		lists.add(10, "hearts");
		lists.add(14, "diamonds");
		System.out.println(lists);
		
		System.out.println(lists.remove());
		System.out.print(lists);

	}

}
