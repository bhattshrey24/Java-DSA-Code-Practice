package Tree.BinaryTree;

import java.util.Scanner;

public class BinaryTree_Client {
// there are 3 types of tree
	// 1)Full tree
	// 2)Complete tree
	// 3)Perfect tree

	// Binary Tree - In binary tree we have a limit that every node can have at max
	// 2 children
	// Narry Tree - In 'narry tree' we don't have a limit of children i.e. each node
	// can have n number of childrens

	// Level starts from 0 i.e. root node is at level 0
	// Height starts from 1 i.e. root node is at

	// Properties of BT:-
	// 1)at any level max number of nodes can be 2^Level
	// 2)There exists a maximum of (2^h-1) nodes in a binary tree if its height is h
	// 3)If there exist L leaf nodes in a binary tree, then it has at least L+1
	// levels
	// 4)The minimum and the maximum height of a binary tree having n nodes are
	// |log↓2(n)| and |n|, respectively.

	// We can Implement tree using array or LinkedList
	// In array left child of i is at = 2*i , right child of i is at = 2*i+1
	public static void main(String[] args) {
		BinaryTree_Implementation bt = new BinaryTree_Implementation();
		Scanner sc = new Scanner(System.in);
		
		BinaryTree_Implementation.BinaryTreeNode root = bt.createNode(sc);
		
		bt.displayInOrder(root);
	}

}
