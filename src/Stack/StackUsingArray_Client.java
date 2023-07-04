package Stack;

public class StackUsingArray_Client {
	//LIFO
	//Can be implemented using Array and LinkedList 
	public static void main(String[] args) {
		StackUsingArray_Implementation stack = new StackUsingArray_Implementation(4);
		stack.push(10);
		stack.pop();
		stack.pop();
		stack.displayStack();
	}

	
}
