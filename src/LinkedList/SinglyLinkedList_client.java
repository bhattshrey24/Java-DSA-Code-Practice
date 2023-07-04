package LinkedList;

public class SinglyLinkedList_client {
// In LL always check your answer for 3 cases
	//case 1: 0 node in List 
	//case 2: 1 node in List 
	//case 3: 2 or more nodes in List 
	public static void main(String[] args) {
		SinglyLinkedList_Implementation LL = new SinglyLinkedList_Implementation();
		LL.sizeOfLL();
		LL.add(10);
		LL.add(20);
		LL.add(30);
		LL.add(40);
		LL.sizeOfLL();
		LL.displayLL();
		LL.remove();
		LL.displayLL();
		LL.remove();
		LL.displayLL();
		LL.remove();
		LL.displayLL();
		LL.remove();
		LL.displayLL();
	}

}
