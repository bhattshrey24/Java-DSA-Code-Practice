package Heap;

public class HeapUsingHeapify_Client {

	public static void main(String[] args) {
		// See this video https://www.youtube.com/watch?v=UVW0NfG_YWA

		HeapUsingHeapify_Implementation minHeap = new HeapUsingHeapify_Implementation();
		int arr[] = new int[] { 20, 15, 30, 8, 10, 50, 12 };

		minHeap.minHeapifyArray(arr);
		System.out.println();
		System.out.println("Ele Deleted : "+minHeap.getMin(arr));
		minHeap.display(arr);
		System.out.println();
		System.out.println("Ele Deleted : "+minHeap.getMin(arr));
		minHeap.display(arr);
		System.out.println();
		System.out.println("Ele Deleted : "+minHeap.getMin(arr));
		minHeap.display(arr);
		System.out.println();
		System.out.println("Ele Deleted : "+minHeap.getMin(arr));
		minHeap.display(arr);
		System.out.println();
		System.out.println("Ele Deleted : "+minHeap.getMin(arr));
		minHeap.display(arr);
		System.out.println();
		System.out.println("Ele Deleted : "+minHeap.getMin(arr));
		minHeap.display(arr);
		System.out.println();
		System.out.println("Ele Deleted : "+minHeap.getMin(arr));
		
	}

}
