package LinkedList;

//Classic doubly linked list with only one pointer i.e. Head , You can have 2 pointers too i.e. One head and One tail
public class DoublyLinkedList_Implementation {

	private Node head;
	private int size = 0;

	public class Node {
		int data;
		Node next;
		Node prev;

		Node(Node next, int data, Node prev) {
			this.data = data;
			this.next = next;
			this.prev = prev;
		}
	}

	public void sizeOfLL() {
		if (head == null) {
			System.out.println("List is empty");
		} else {
			System.out.println(size);
		}
	}

	public void addFirst(int val) {
		if (head == null) {
			head = new Node(null, val, null);
			size++;
		} else {
			Node newNode = new Node(null, val, null);
			head.prev = newNode;
			newNode.next = head;
			head = head.prev;
			size++;
		}
	}

	public void addLast(int val) {
		if (head == null) {
			head = new Node(null, val, null);
			size++;
		} else {
			Node newNode = new Node(null, val, null);
			Node temp = head;
			while (temp.next != null) {
				temp = temp.next;
			}
			temp.next = newNode;
			newNode.prev = temp;
			size++;
		}
	}

	public void removeFirst() {
		if (head == null) {
			System.out.println("LL is empty");
		} else if (head.next == null) {// i.e. only one element is present in LL
			head = null;
			size--;
		} else {
			Node temp = head.next;
			head.next = null;
			temp.prev = null;
			head = temp;
			size--;
		}
	}

	public void removeLast() {
		if (head == null) {
			System.out.println("LL is empty");
		} else if (head.next == null) {// i.e. only one element is present in LL
			head = null;
			size--;
		} else {
			Node temp = head;
			while (temp.next.next != null) {// stopping at one element before the last ele
				temp = temp.next;
			}
			temp.next.prev = null;
			temp.next=null;
			size--;
		}
	}

	public void displayLLFromForward() {
		if (head == null) {
			System.out.println("LL is empty");
		} else {
			Node temp = head;
			while (temp != null) {
				System.out.print("<-|" + temp.data + "|->");
				temp = temp.next;
			}
			System.out.println();
		}
	}

	public void displayLLFromBackward() {
		if (head == null) {
			System.out.println("LL is empty");
		} else {
			Node temp = head;
			while (temp.next != null) {// going forward to reach end cause we only have one pointer
				temp = temp.next;
			}
			while (temp != null) {
				System.out.print("<-|" + temp.data + "|->");
				temp = temp.prev;// going backwards
			}
			System.out.println();
		}
	}
}
