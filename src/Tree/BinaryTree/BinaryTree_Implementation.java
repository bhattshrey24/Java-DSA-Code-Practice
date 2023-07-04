package Tree.BinaryTree;

import java.util.Scanner;

public class BinaryTree_Implementation {

	class BinaryTreeNode {

		BinaryTreeNode left, right;
		int data;

		BinaryTreeNode(BinaryTreeNode left, int data, BinaryTreeNode right) {
			this.left = left;
			this.right = right;
			this.data = data;
		}
	}

	public BinaryTreeNode createNode(Scanner sc) { // returns root node , this function also works on recursion

		// Always remember har subtree apne mei ek tree hota hai
		
		BinaryTreeNode root = null;

		System.out.println("Enter data : ");

		int data = sc.nextInt();

		if (data == -1) {// -1 means return back i.e. aur nhi nodes nhi add krne
			return null;
		}

		root = new BinaryTreeNode(null, data, null); // creating the node

		System.out.println("Enter left of " + data);
		root.left = createNode(sc); // simply telling recursion to fill the left subtree of current node and giving
									// us the root node of the left sub tree in return so that we can assign it to
									// left of
									// current node

		System.out.println("Enter right of " + data);
		root.right = createNode(sc); // simply telling recursion to fill the right subtree of current node and giving
		// us the root node of the right sub tree in return so that we can assign it to
		// right of
		// current node

		return root;// sb sub tree apne root element return krte rhenge and eventually end mei pure
					// tree ka root node return hoga
	}

	public void displayInOrder(BinaryTreeNode root) {
		if (root == null) {
			return;
		}
		displayInOrder(root.left);
		System.out.print(root.data + " ");
		displayInOrder(root.right);
	}

	public void displayPreOrder(BinaryTreeNode root) {
		if (root == null) {
			return;
		}
		System.out.print(root.data + " ");
		displayInOrder(root.left);
		displayInOrder(root.right);
	}

	public void displayPostOrder(BinaryTreeNode root) {
		if (root == null) {
			return;
		}
		displayInOrder(root.left);
		displayInOrder(root.right);
		System.out.print(root.data + " ");
	}

}
