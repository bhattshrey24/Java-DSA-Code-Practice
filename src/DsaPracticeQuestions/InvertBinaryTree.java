package DsaPracticeQuestions;

public class InvertBinaryTree {
//Q) 226 Leetcode
    public TreeNode invertTree(TreeNode root) {
        if (root == null) { //base case : ie. if tree is empty
            // then there is no need to invert anything so simply return
            // or you can think of it like if we reached end while traversing
            // then no need to swap simply return
            return null;
        }

        TreeNode leftSubTreeRoot = invertTree(root.left);// have faith that recursion
        // will simply reverse the left subtree of current node and return the root
        // of left subtree after that
        TreeNode rightSubTreeRoot = invertTree(root.right);// have faith that recursion
        // will simply reverse the right subtree of current node and return the root
        // of right subtree after that

        root.left = rightSubTreeRoot; // simply assign left inverted
        // subtree to right of current node and right inverted subtree to left
        root.right = leftSubTreeRoot;

        return root;
    }

    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
