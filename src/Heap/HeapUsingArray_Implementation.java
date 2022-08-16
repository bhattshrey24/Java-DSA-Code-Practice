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
        int idx = (node - 1) / 2;
        return idx;
    }

    public int getRightIdx(int node) {
        int idx = 2 * node + 1;
        return idx;
    }

    public int getLeftIdx(int node) {
        int idx = 2 * node + 2;
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

    public void deleteElement() { // always the root node is deleted ,  tc - O(logn)
        if (currSizeOfHeap == 1) {
            System.out.println("Heap is empty can't delete");
        }
        // overwrite root element with last element of heap
        heapArr[1] = heapArr[(currSizeOfHeap - 1)];
        int currNodeIdx = 1;

        while (currSizeOfHeap > currNodeIdx) {
            // check out of bound
            int currNodeValue = heapArr[currNodeIdx];
            int currNodeLeftChildIdx = getLeftIdx(currNodeIdx);
            int currNodeLeftChildValue = heapArr[currNodeLeftChildIdx];
            int currNodeRightChildIdx = getRightIdx(currNodeIdx);
            int currNodeRightChildValue = heapArr[currNodeRightChildIdx];

            if (getLeftIdx(currNodeIdx) < currSizeOfHeap && getRightIdx(currNodeIdx) < currSizeOfHeap) {
                int minOfChildren = Math.min(currNodeLeftChildValue, currNodeRightChildValue);
                if (minOfChildren == currNodeLeftChildValue) {
                    swapElements(currNodeLeftChildIdx, currNodeIdx);
                    currNodeIdx = currNodeLeftChildIdx;
                } else {
                    swapElements(currNodeRightChildIdx, currNodeIdx);
                    currNodeIdx = currNodeRightChildIdx;
                }
            } else if (getLeftIdx(currNodeIdx) < currSizeOfHeap) {
                swapElements(currNodeLeftChildIdx, currNodeIdx);
                currNodeIdx = currNodeLeftChildIdx;
            } else if (getRightIdx(currNodeIdx) < currSizeOfHeap) {
                swapElements(currNodeRightChildIdx, currNodeIdx);
                currNodeIdx = currNodeRightChildIdx;
            } else {
                break;
            }
        }
        currSizeOfHeap--;
    }

    public int peak() {
        return heapArr[1];
    }


}
