package Queues;

public class QueueUsingArray_Implementation {

	private int capacity;
	private int front = 0;
	private int rear = 0;
	private int queueArray[];

	public QueueUsingArray_Implementation(int capacity) {
		this.capacity = capacity;
		queueArray = new int[capacity];
	}

	public void sizeOfQueue() {
		if (rear == 0) {
			System.out.println("Queue is empty");
		} else if (rear >= capacity) {
			System.out.println("Queue is full");
		} else {
			System.out.println(rear - front);
		}
	}

	public void enqueue(int val) {
		if (rear < capacity) {
			queueArray[rear] = val;
			rear++;
		} else {
			System.out.println("Queue is full");
		}
	}

	public void dequeue() {
		if (front == rear) {// this is true in start also when u haven't added any element and in the end
							// too when u completely traversed the queue
			System.out.println("Queue is empty");
		} else {
			int valToBeDeleted = queueArray[front];
			queueArray[front] = 0;
			front++;
			System.out.println("Value " + valToBeDeleted + "successfully deleted");
		}
	}

	public void displayQueue() {
		for (int i = 0; i < rear; i++) {
			if (i == 0) {
				System.out.print("| " + queueArray[i] + " | ");
			} else {
				System.out.print(queueArray[i] + " | ");
			}
		}
		System.out.println();
	}
}
