package LinkedList;

// here we can use 'tail' too but we are implementing classic LL where we only have 1 pointer

public class SinglyLinkedList_Implementation {
	private Node Head = null;// should not be accessible to client therefore private
	private int size;

	public class Node {
		int data;
		Node next;

		Node(int data, Node next) {
			this.data = data;
			this.next = next;
		}
	}

	public void sizeOfLL() {
		if (Head == null) {
			System.out.println("LL is empty");
		} else {
			System.out.println(size);
		}

	}

	public void add(int val) { // classic add last
		if (Head == null) { // case 1 : when list is empty
			Head = new Node(val, null);
			size++;
		} else {// case 2 : 1 or more elements are present in LL
			Node temp = Head;
			while (temp.next != null) {
				temp = temp.next;
			}
			Node newNode = new Node(val, null);// every node starts with pointing to null
			temp.next = newNode;
			size++;
		}

	}

	public void remove() {// classic remove from head
		if (Head == null) {// case 1 : no node is t
			System.out.println("LL is empty");
		} else if (Head.next == null) {// case 2 : 1 node
			Head = null;
			size--;
		} else {// case 3: 2 or more nodes
			Node temp = Head;
			Head = Head.next;
			temp.next = null;
			size--;
		}
	}

	public void displayLL() {
		if (Head == null) {
			System.out.println("LL is empty");
		} else {
			Node temp = Head;
			while (temp != null) {
				System.out.print("|" + temp.data + "|-->");
				temp = temp.next;
			}
		}
		System.out.println();
	}
}
