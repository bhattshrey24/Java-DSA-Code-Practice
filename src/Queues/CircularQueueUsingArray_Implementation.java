package Queues;

public class CircularQueueUsingArray_Implementation {
	private int capacity;
	private int front = -1;// initialized with front = -1 and rear =-1 is a state that tells us that queue
							// is empty
	private int rear = -1;
	private int queueArray[];

	private int size = 0;
//2 Important Things to REMEBER :- 
//1)whenever we reach the limit/capacity of the queue while enqueuing we simply reinitialize it by making rear and front again = -1
//2)(rear+1)%capacity can also work as rear++ , eg if capacity = 4 and rear = 2 then (rear+1)%capacity => (2+1)%4=3 and if rear = 3 then (3+1)%4= 0 , so it handles the outOfBound error as well as do normal increment  

	public CircularQueueUsingArray_Implementation(int capacity) {
		this.capacity = capacity;
		queueArray = new int[capacity];
	}

	public boolean isEmpty() {// returns whether queue is empty or not
		return rear == -1 && front == -1;// -1 means they have not been initialized which means queue is empty
	}

	public boolean isFull() {// returns whether queue is full or not
		return (rear + 1) % capacity == front; // i.e. front is just next to rear
	}

	public void sizeOfQueue() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
		} else {
			if (size == capacity) {
				System.out.println("Capacity Reached i.e. " + size);
			} else {
				System.out.println(size);
			}
		}
	}

	public void enqueue(int val) {
		if (isFull()) {
			System.out.println("Queue is full");
		} else {
			// we first move the rear pointer and then assign value
			rear = (rear + 1) % capacity;// it works even for rear=-1 , see (-1+1)%capacity => 0%capacity => 0
			queueArray[rear] = val;
			if (front == -1) {// this means queue was empty
				front = 0;// so simply let front point to first element of queue
			}
			size++;
		}
	}

	public void dequeue() {
		if (isEmpty()) {
			System.out.println("Queue is emppty");
		} else {
			queueArray[front] = 0;// removing the value
			if (front == rear) { // this means that current element is the last element of the queue hence after
									// deleting it simply reinitialize front and rear to -1
				front = -1;
				rear = -1;
			} else {
				
				front = (front + 1) % capacity;
			}
			size--;
		}
	}

	public void displayQueue() {
		if (isEmpty()) {
			System.out.println("Queue is empty");
		} else {
			int dummyFront = front;
			while (dummyFront != rear) {// using 'front' to move forward till we reach rear
				System.out.print("| " + queueArray[dummyFront] + " | ");
				dummyFront = (dummyFront + 1) % capacity;
			}
			System.out.print("| " + queueArray[dummyFront] + " | ");// last element will not be printed by above loop
																	// because of its condition therefore isko alag se
																	// print krna pdega
		}
		System.out.println();
	}
}
