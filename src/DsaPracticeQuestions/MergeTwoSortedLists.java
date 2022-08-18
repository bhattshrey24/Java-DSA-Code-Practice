package DsaPracticeQuestions;

public class MergeTwoSortedLists {


    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {// This is space optimized solution , If confused just do a dry run

        ListNode prev = null; // this is used to link previous node with newNode
        ListNode newListHead = null;

        if (list1 == null && list2 == null) { // To handle the case when both lists are empty
            return null;
        }
        if (list2 == null && list1 != null) {// To handle the case when just 1 list is empty , in this case we simply return the other list
            return list1;
        }
        if (list2 != null && list1 == null) {// To handle the case when just 1 list is empty , in this case we simply return the other list
            return list2;
        }
        while (true) {
            // I'm using pointers list1 and list2 (which are heads of list1 and list2) to traverse list1 and list2 instead of creating new temp pointers for both lists
            // I'm basically reattaching the links of the nodes of both list and combining them into one list which is sorted
            // using prev , list1 and list2 pointers

            if (list1 == null) { //  filling remaining list 2 ele and leaving the loop
                while (list2 != null) {
                    prev.next = list2;
                    prev = list2;
                    list2 = list2.next;
                }
                break;
            }
            if (list2 == null) { //  filling remaining list 2 ele and leaving the loop
                while (list1 != null) {
                    prev.next = list1;
                    prev = list1;
                    list1 = list1.next;
                }
                break;
            }


            if (list1.val < list2.val) {
                if (newListHead == null) { // ie. when it is the 1st node of newList
                    newListHead = list1;
                    prev = newListHead;
                } else {
                    prev.next = list1;
                    prev = list1;
                }
                list1 = list1.next;
            } else {// list1.val >= list2.val
                if (newListHead == null) {// ie. when it is the 1st node of newList
                    newListHead = list2;
                    prev = newListHead;
                } else {
                    prev.next = list2;
                    prev = list2;
                }
                list2 = list2.next;
            }
        }

        return newListHead;
    }

    public class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
