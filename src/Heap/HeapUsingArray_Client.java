package Heap;

public class HeapUsingArray_Client {

    public static void main(String[] args) {

        HeapUsingArray_Implementation myMinHeap = new HeapUsingArray_Implementation(10);

        // There are 2 ways to make heap :-
        //1) Normally (TC - O(n(Log(n))) )
        //2) Using heapify method (TC - O(n))

        // Method 1 :- Inserting in Heap normally
        myMinHeap.insertEleInHeap(20);
        myMinHeap.insertEleInHeap(50);
        myMinHeap.insertEleInHeap(30);
        myMinHeap.insertEleInHeap(10);
        myMinHeap.insertEleInHeap(40);
        myMinHeap.insertEleInHeap(60);
        myMinHeap.insertEleInHeap(80);
        myMinHeap.insertEleInHeap(70);
        myMinHeap.insertEleInHeap(90);

        int size = myMinHeap.currSizeOfHeap;

        for (int i = 0; i < size; i++) {
            System.out.println("Element at the top : " + myMinHeap.peak());
            boolean isSuccessful = myMinHeap.deleteElement();
            if (isSuccessful) {
                System.out.println("Top element deleted");
            }
        }

        // Method 2 - Inserting in heap using Heapify method. Heapify is simply a way of creating a heap

        int arrToBeHeapified[] = new int[]{10, 30, 40, 50, 70, 60, 90, 80, 20};

        int heap[] = myMinHeap.makeHeapUsingHeapify(arrToBeHeapified);
        System.out.println("Displaying heap made from heapify method");

        for (int i = 0; i < heap.length; i++) {
            System.out.println("heap ele " + i + ": " + heap[i]);
        }

        // Doing HeapSort
        int sortedArr[] = myMinHeap.deleteAndDoHeapSort(heap); // Sorted array will be in descending
        // order because we removed and added elements in the back of same heap array

        System.out.println("Displaying Sorted heap : - ");
        for (int ele : sortedArr) {
            System.out.println(ele);
        }

    }
}
