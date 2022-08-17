package Heap;

public class HeapUsingArray_Implementation { // We are making min heap
    int[] heapArr; // can use arrayList too but i'll use array instead
    int currSizeOfHeap;

    HeapUsingArray_Implementation(int maxCapacity) {
        heapArr = new int[maxCapacity];
        currSizeOfHeap = 0;
        heapArr[0] = Integer.MIN_VALUE; //0th index is not
        // needed because then all the other properties will be violated
        // if we start putting elements from index 0 that's why leave
        // the index 0 with null since we can't put null in integer array therefore we put
        // Integer.min_value
        currSizeOfHeap++;
    }

    public int getParentIdx(int node) {
        int idx = (node) / 2;
        return idx;
    }

    public int getRightIdx(int node) {
        int idx = 2 * node + 1;
        return idx;
    }

    public int getLeftIdx(int node) {
        int idx = 2 * node;
        return idx;
    }

    public void insertEleInHeap(int data) { //tc- O(logn)
        if (currSizeOfHeap == heapArr.length) {
            System.out.println("Heap is full");
        } else {
            heapArr[currSizeOfHeap] = data;
            rearrangeHeap();
            currSizeOfHeap++;
        }
    }

    private void rearrangeHeap() {
        int currEleIdx = currSizeOfHeap; // I have not yet increased the size therefore this will be pointing to the last element of array
        while (heapArr[getParentIdx(currEleIdx)] > heapArr[currEleIdx]) { // this won't give index out of bound problem
            // because we placed integer.min_value at
            // 0th index which is technically parent of root element of heap which is at 1st index
            // and since nothing can be smaller than -infinity therefore no swap will happen and we come out of loop safely
            int parentIdx = getParentIdx(currEleIdx);
            swapElements(parentIdx, currEleIdx);
            currEleIdx = parentIdx;
        }
    }

    public void swapElements(int i, int j) {
        int temp = heapArr[i];
        heapArr[i] = heapArr[j];
        heapArr[j] = temp;
    }

    public boolean deleteElement() { // always the root node is deleted ,  tc - O(logn), This functions returns whether deletion was successful or not
        if (currSizeOfHeap <= 1) {
            System.out.println("Heap is empty can't delete");
            return false; // i.e. deletion was not successful because there is no element left to delete
        }

        //Step 1:  overwrite root element with last element of heap
        heapArr[1] = heapArr[(currSizeOfHeap - 1)];

        //Step 2:  delete last element by putting infinity
        heapArr[(currSizeOfHeap - 1)] = Integer.MAX_VALUE;

        // Step 3: rearrange from top to bottom so that it follows the heap property
        int currNodeIdx = 1;
        while (currSizeOfHeap > currNodeIdx) {

            int currNodeValue = heapArr[currNodeIdx];
            int currNodeLeftChildIdx = getLeftIdx(currNodeIdx);
            int currNodeRightChildIdx = getRightIdx(currNodeIdx);

            if (currNodeLeftChildIdx < currSizeOfHeap && currNodeRightChildIdx < currSizeOfHeap) {
                int currNodeLeftChildValue = heapArr[currNodeLeftChildIdx];
                int currNodeRightChildValue = heapArr[currNodeRightChildIdx];

                int smallerChildsIdx = currNodeLeftChildValue > currNodeRightChildValue ? currNodeRightChildIdx : currNodeLeftChildIdx;

                if (heapArr[currNodeIdx] > heapArr[smallerChildsIdx]) {
                        swapElements(smallerChildsIdx, currNodeIdx);
                        currNodeIdx = smallerChildsIdx;
                } else {
                        break;
                }
            } else if (currNodeLeftChildIdx < currSizeOfHeap && heapArr[currNodeLeftChildIdx] < currNodeValue) {
                swapElements(currNodeLeftChildIdx, currNodeIdx);
                currNodeIdx = currNodeLeftChildIdx;
            } else if (currNodeRightChildIdx < currSizeOfHeap && heapArr[currNodeRightChildIdx] < currNodeValue) {
                swapElements(currNodeRightChildIdx, currNodeIdx);
                currNodeIdx = currNodeRightChildIdx;
            } else {
                break;
            }
        }
        currSizeOfHeap--;
        return true;
    }

    public int peak() {
        return heapArr[1];
    }


}
