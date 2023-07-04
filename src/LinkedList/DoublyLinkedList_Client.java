package LinkedList;

public class DoublyLinkedList_Client {

	public static void main(String[] args) {
		DoublyLinkedList_Implementation Dll = new DoublyLinkedList_Implementation();
		Dll.displayLLFromForward();
		Dll.displayLLFromBackward();
		Dll.addLast(10);
		Dll.addLast(20);
		Dll.addLast(30);
		Dll.displayLLFromForward();
		Dll.addFirst(80);
		Dll.displayLLFromForward();
		Dll.removeLast();
		Dll.displayLLFromForward();
		Dll.removeFirst();
		Dll.displayLLFromForward();
		Dll.sizeOfLL();
		Dll.displayLLFromBackward();
	}

}
