package Stack;

public class StackUsingArray_Implementation {
	private int capacity;
	private int stackArr[];
	private int top = 0;

	public StackUsingArray_Implementation(int capacity) {
		this.capacity = capacity;
		stackArr = new int[capacity];
	}

	public int sizeOfStack() {
		return top + 1;
	}

	public void push(int val) {
		if (top < capacity) {
			stackArr[top] = val;
			top++;
			System.out.println("Successfully pushed");
		} else {
			System.out.println("Overflow");
		}
	}

	public void pop() {
		if (top > 0) {
			int valueToBePopped = stackArr[top];
			stackArr[top] = 0;
			top--;
			System.out.println("Successfully popped " + valueToBePopped);
		} else {
			System.out.println("Underflow");
		}
	}

	public void displayStack() {
		// printing in reverse order so that it looks like actual stack
		if (top <= 0) {
			System.out.println("Stack Empty");
		} else {
			for (int i = top - 1; i >= 0; i--) { // IMP - 'top-1' cause everytime we push an element then top goes one
													// higher to point to empty
				// space so if we don't do this then it can throw error if suppose u
				// pushed till the capacity and then try to display stack in that case
				// top will be pointing out of array i.e. u will get array out of bound
				// error
				System.out.println("|" + stackArr[i] + "|");
			}
		}

	}
}
