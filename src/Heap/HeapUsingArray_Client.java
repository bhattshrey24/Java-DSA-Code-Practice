package Heap;

public class HeapUsingArray_Client {

    public static void main(String[] args) {

        HeapUsingArray_Implementation minHeap = new HeapUsingArray_Implementation(10);

        // TC of Operations on Sorted Array :-
        // TC of accessing min and max element in sorted arrayList = O(1)
        // TC of Addition or Deletion of element = O(N) because even though finding correct
        //   position can be done in log(N) using binary search but then also have to shift elements
        //   in order to make space or eliminate space which take O(N)

        // TC of Operations on Binary Search Tree :-
        // TC of Addition or Deletion of element is = O(Log(N))
        // TC of accessing min and max element = O(Log(N))

        // TC of Operations on Heap :-
        // TC of Addition or Deletion of element is = O(Log(N))
        // TC of accessing min and max element = O(1)

        // Properties of Heap
        // 1) Heap is a Complete Binary Tree
        // 2) In Max Heap the main root is the biggest element of the heap and all subtrees are heap themselves.
        // 3) For Max Heap for every node its true that
        //      node.data >= node.left.data
        //      node.data >= node.right.data
        // 4) There is no relationship between left and right child i.e. anyone can be
        //    smaller than the other in min heap and anyone can be bigger than the other in max heap. Basically
        //    no relation between left and right subtree of every root either

        // There are 2 ways to make heap :-
        //1) Normally (TC - O(n(Log(n))) )
        //2) Using heapify method (TC - O(n))

        // Method 1 :- Inserting in Heap normally
        minHeap.insertEleInHeap(20);
        minHeap.insertEleInHeap(50);
        minHeap.insertEleInHeap(30);
        minHeap.insertEleInHeap(10);
        minHeap.insertEleInHeap(40);
        minHeap.insertEleInHeap(60);
        minHeap.insertEleInHeap(80);
        minHeap.insertEleInHeap(70);
        minHeap.insertEleInHeap(90);

        int size = minHeap.currSizeOfHeap;

        for (int i = 0; i < size; i++) {
            System.out.println("Element at the top : " + minHeap.peak());
            boolean isSuccessful = minHeap.deleteElement();
            if (isSuccessful) {
                System.out.println("Top element deleted");
            }
        }

        // Method 2 - Inserting in heap using Heapify method. Heapify is simply a way of creating a heap

        int arrToBeHeapified[] = new int[]{10, 30, 40, 50, 70, 60, 90, 80, 20};

        int heap[] = minHeap.makeHeapUsingHeapify(arrToBeHeapified);
        System.out.println("Displaying heap made from heapify method");

        for (int i = 0; i < heap.length; i++) {
            System.out.println("heap ele " + i + ": " + heap[i]);
        }

        // Doing HeapSort
        int sortedArr[] = minHeap.deleteAndDoHeapSort(heap); // Sorted array will be in descending
        // order because we removed and added elements in the back of same heap array

        System.out.println("Displaying Sorted heap : - ");
        for (int ele : sortedArr) {
            System.out.println(ele);
        }

    }
}
