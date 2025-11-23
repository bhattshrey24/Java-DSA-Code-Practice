package Heap;

// Refer from 'anuj bhaiya' YouTube channel if doubt
public class HeapUsingArray_Implementation { // We are making MIN HEAP

    int[] heapArr; // can use arrayList too but I'll use array instead
    int currSizeOfHeap; //keep in mind currSizeOfHeap will always be pointing to the NEXT EMPTY
    // SPACE in array , and if it's pointing to end of array ie. 'currSizeOfHeap == arr.length' then it means all spaces
    // are filled . Always code heap keeping this logic in mind

    HeapUsingArray_Implementation(int maxCapacity) {
        heapArr = new int[maxCapacity];
        currSizeOfHeap = 0;// currently heap is empty so currSizeOfHeap is pointing to next
        // empty space in array which is 0
    }

    public int getParentIdx(int idx) { // its (idx - 1) / 2 and not (idx) / 2 because our heap starts
        // from 0 and not 1 so we modified the code for getParentIdx , getLeftIdx and getRighIdx
        return (idx - 1) / 2;
    }

    public int getLeftChildIdx(int idx) {
        return 2 * idx + 1;
    }
    public int getRightChildIdx(int idx) { // will return the index of left child
        return 2 * idx + 2;
    }

    public void insertEleInHeap(int data) {
        if (currSizeOfHeap == heapArr.length) {
            System.out.println("Heap is full");
        } else {
            // Step 1 : put new element in the last of the heap
            heapArr[currSizeOfHeap] = data;
            currSizeOfHeap++;

            // Step 2: Now rearrange the heap
            rearrangeHeap(); // have faith that this will rearrange the whole array
            // so that it follows the min heap property i.e. father is smaller than both children
        }
    }

    private void rearrangeHeap() { // TC = Log(N) i.e. height of tree
        int currEleIdx = currSizeOfHeap - 1; // since currSizeOfHeap is pointing to the next
        // empty space in array therefore last element of the heap will be at currSizeOfHeap - 1

        // here we start from last and go till top
        while (heapArr[getParentIdx(currEleIdx)] > heapArr[currEleIdx]) { // this won't give index out
            // of bound problem because we placed because parent of node 0 is 0 itself observe the
            // condition in while is '>' and not '>='
            int parentIdx = getParentIdx(currEleIdx);
            swapElements(heapArr, parentIdx, currEleIdx);
            currEleIdx = parentIdx; // now do the same for the parent
        }
    }

    public boolean deleteElement() { // always the root node is deleted , tc - O(logn) , This
        // functions returns whether deletion was successful or not

        if (currSizeOfHeap == 0) { // since currSizeOfHeap always points to
            // the next empty space so if its pointing to first element of array means heap is empty
            System.out.println("Heap is empty can't delete");
            return false; // i.e. deletion was not successful because there is no element left to delete
        }


        //Step 1:  overwrite root element with last element of heap
        int lastElementsIdx = currSizeOfHeap - 1;
        heapArr[0] = heapArr[lastElementsIdx];

        //Step 2:  delete last element by putting infinity in it
        heapArr[(lastElementsIdx)] = Integer.MAX_VALUE;

        // Step 3: rearrange from TOP to BOTTOM so that it follows the heap property
        int currNodeIdx = 0; // i.e. pointing to root element

        while (currSizeOfHeap > currNodeIdx) { // i.e. run until we haven't reached end

            int currNodeValue = heapArr[currNodeIdx];
            int currNodeLeftChildIdx = getLeftChildIdx(currNodeIdx);
            int currNodeRightChildIdx = getRightChildIdx(currNodeIdx);

            if (currNodeLeftChildIdx < currSizeOfHeap && currNodeRightChildIdx < currSizeOfHeap) { // Checking if current node has both children
                int currNodeLeftChildValue = heapArr[currNodeLeftChildIdx];
                int currNodeRightChildValue = heapArr[currNodeRightChildIdx];

                int smallerChildsIdx = currNodeLeftChildValue > currNodeRightChildValue ? currNodeRightChildIdx : currNodeLeftChildIdx;

                if (heapArr[currNodeIdx] > heapArr[smallerChildsIdx]) {
                    swapElements(heapArr, smallerChildsIdx, currNodeIdx);
                    currNodeIdx = smallerChildsIdx;
                } else {
                    break;
                }
            } else if (currNodeLeftChildIdx < currSizeOfHeap && heapArr[currNodeLeftChildIdx] < currNodeValue) {// if current node has just left child
                swapElements(heapArr, currNodeLeftChildIdx, currNodeIdx);
                currNodeIdx = currNodeLeftChildIdx;

            } else if (currNodeRightChildIdx < currSizeOfHeap && heapArr[currNodeRightChildIdx] < currNodeValue) {// if current node has just right child
                swapElements(heapArr, currNodeRightChildIdx, currNodeIdx);
                currNodeIdx = currNodeRightChildIdx;

            } else {// If current node is leaf then don't do anything simply break out of loop
                break;
            }
        }

        currSizeOfHeap--; // decrease since we removed one element

        return true;
    }

    public int peak() {
        return heapArr[0];
    }

    public void swapElements(int arr[], int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public int[] makeHeapUsingHeapify(int arr[]) {
        // In heapify we start from last element and
        // make sure all the elements below it are following heap property

        int startNode = (arr.length / 2) + 1; // we don't process leaf node since
        // they are already heap so (arr.length / 2) + 1 will take us to the
        // first non leaf node from last of array

        for (int i = startNode; i > 0; i--) { // we heapify elements till we reach top
            heapify(arr, arr.length - 1, i);
        }

        return arr;
    }

    public void heapify(int arr[], int heapSize, int nodeIdx) {
        int leftChildIdx = getLeftChildIdx(nodeIdx);
        int rightChildIdx = getRightChildIdx(nodeIdx);
        int smallestNodeIdx = nodeIdx; // currently we are assuming that current node
        // is the smallest among the three ie. current node it's left child and its' right child

        // this function is doing similar work as deleteElement() but in a much cleaner and beautiful way
        if (leftChildIdx <= heapSize && arr[smallestNodeIdx] > arr[leftChildIdx]) {
            smallestNodeIdx = leftChildIdx;// this 'if' check and below 'if' check will
            // simply find the smallest of the three ie. current node its left child and its right child
        }
        if (rightChildIdx <= heapSize && arr[smallestNodeIdx] > arr[rightChildIdx]) { // remember that
            // right now 'smallestNodeIdx' contains the smaller of the 2 ie current node and its left child
            smallestNodeIdx = rightChildIdx;
        }

        if (smallestNodeIdx != nodeIdx) { // i.e. we only swap and heapify
            // further if any of the 2 children of current node is smaller than he is
            // otherwise don't do anything simply return

            swapElements(arr, nodeIdx, smallestNodeIdx);
            heapify(arr, heapSize, smallestNodeIdx); // Have leap of faith that this
            // function will heapify further
        }
    }

    public int[] deleteAndDoHeapSort(int heap[]) {
        int lastEleOfHeapIdx = heap.length - 1;

        // We will create the sorted heap in the same heapArray

        for (int i = 0; i < heap.length - 1; i++) {
            swapElements(heap, lastEleOfHeapIdx, 0); // We swap 1st element with last element and
            // consider the last element as sorted therefore not a part of heap

            lastEleOfHeapIdx -= 1; // Now we reduced the size of heap since we have sorted 1 element
            // and now we will not consider it as a part of heap

            heapify(heap, lastEleOfHeapIdx, 0); // now have faith that this
            // will heapify the remaining heap since its not a heap anymore after we swapped 1st element with last element of heap.
        }
        return heap;
    }

}


