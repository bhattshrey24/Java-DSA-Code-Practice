package Queues;

public class QueueUsingArray_Client {
//Queue is FIFO
//Can be implemented using array and LinkedList	
//Functions are :-
	// size
	// enqueue
	// dequeue
	// Display
	
// This is a normal queue 
	public static void main(String[] args) {
		QueueUsingArray_Implementation queue = new QueueUsingArray_Implementation(4);
	queue.enqueue(1);
	queue.enqueue(3);
	queue.enqueue(4);
	queue.dequeue();
	

	
	queue.displayQueue();
	queue.sizeOfQueue();
	}

}
