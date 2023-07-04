package Heap;

// Reference from this video : https://www.youtube.com/watch?v=ywx-Onrdx4U&t=6s
public class HeapUsingHeapify_Implementation {
	private int lastEleIdx; // keeps track of the index of last element in array

	private void swapHeapEle(int arr[], int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	private int leftChildIdx(int i) {
		return ((2 * i) + 1);
	}

	private int rightChildIdx(int i) {
		return ((2 * i) + 2);
	}

	private boolean isLeaf(int i, int lastEleIdx) {
		return (leftChildIdx(i) >= lastEleIdx && rightChildIdx(i) >= lastEleIdx);
	}

	public void minHeapifyArray(int arr[]) {// minHeapify cause we are creating min heap

		int maxSize = arr.length;
		// here we are assuming that the array given to us is like a 'tree' which needs
		// to
		// be heapified i.e. converted into heap

		for (int i = arr.length - 1; i >= 0; i--) {// ulta loop chlaenge cause leaf node se start krte hai heapify krna
			minHeapifyArrayImple(arr, i, maxSize);// minHeapifying every element
		}

		lastEleIdx = arr.length - 1;

		// just displaying the heapified array
		display(arr);
	}

	// Must watch to understand this https://www.youtube.com/watch?v=UVW0NfG_YWA

	// In heap we start from last element to top element and heapify in downward
	// direction of tree
	public void minHeapifyArrayImple(int[] arr, int eleIdx, int maxSize) { // It's a recursive function

		if (!isLeaf(eleIdx, maxSize)) {// since single element is already heap therefore we do not check for leaf
										// element cause there's no child of leaf element to compare with

			int currSmallestEleIdx = eleIdx;// assuming in the start that current element is the smallest element
			int lc = leftChildIdx(eleIdx);
			int rc = rightChildIdx(eleIdx);

			// These 2 'if' conditions is simply putting the 'currSmallestEleIdx' pointer to
			// the element which is the smallest among the three i.e. current element , it's
			// left child
			// and
			// right child

			if (lc < maxSize && arr[lc] < arr[currSmallestEleIdx]) {// first we check if left child even exists or not ,
																	// if yes
																	// then simply compare
				currSmallestEleIdx = lc;
			}
			if (rc < maxSize && arr[rc] < arr[currSmallestEleIdx]) {
				currSmallestEleIdx = rc;
			}

			if (currSmallestEleIdx != eleIdx) {// this means current element is not the smallest of the three so we have
												// to swap current element with the smallest

				swapHeapEle(arr, currSmallestEleIdx, eleIdx);

				minHeapifyArrayImple(arr, currSmallestEleIdx, maxSize); // let recursion heapify rest of the tree

			} else {
				return;
			}
		} else {
			return;
		}
	}

	public int getMin(int[] arr) {// returns the minimum element i.e. the root element and deletes it from the
									// heap
		int deletedEle = arr[0];

		arr[0] = arr[lastEleIdx];// putting last element in the root elements place

		arr[lastEleIdx] = -1;// deleting last element , -1 means element is deleted

		lastEleIdx--;// reducing the size of the heap

		minHeapifyArrayImple(arr, 0, lastEleIdx + 1);// simply Heapifying the root element , minHeapify will simply
														// put root element to it's correct place in heap

		return deletedEle;
	}

	public void display(int[] arr) {
		System.out.println();
		for (int ele : arr) {
			System.out.print(ele + "| ");
		}
	}

}
