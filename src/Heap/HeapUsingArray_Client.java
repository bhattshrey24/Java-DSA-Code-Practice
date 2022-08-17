package Heap;

public class HeapUsingArray_Client {

    public static void main(String[] args) {
        HeapUsingArray_Implementation myMinHeap = new HeapUsingArray_Implementation(10);

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
            System.out.println("Element at the top : "+myMinHeap.peak());
           boolean isSuccessful = myMinHeap.deleteElement();
           if (isSuccessful){
               System.out.println("Top element deleted");
           }
        }
    }
}
