package Queues;

public class CircularQueueUsingArray_Client {

	public static void main(String[] args) {
		CircularQueueUsingArray_Implementation Cqueue = new CircularQueueUsingArray_Implementation(4);
		Cqueue.enqueue(10);
		Cqueue.enqueue(20);
		Cqueue.enqueue(30);
		Cqueue.enqueue(70);
		Cqueue.displayQueue();
		Cqueue.sizeOfQueue();
		Cqueue.dequeue();
		Cqueue.enqueue(80);
		Cqueue.displayQueue();
		Cqueue.sizeOfQueue();
	}

}
