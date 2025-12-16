package Tree.BinarySearchTree;

public class Bst {
    private class Node {
        int data;
        Node left;
        Node right;

        Node(int data, Node left, Node right) {
            this.left = left;
            this.right = right;
            this.data = data;
        }
    }


    private Node root = null;

    private int size = 0;

    public int getSize() {
        return size;
    }

    public void add(int data) {
        if (root == null) {
            this.root = new Node(data, null, null);
            this.size++;
        } else {
            this.add(this.root, data);
        }
    }

    private void add(Node node, int data) {
        if (data < node.data) { // left tree
            if (node.left != null) {
                this.add(node.left, data); // asking recursion to go in left subtree because thats where this node belongs
            } else {
                node.left = new Node(data, null, null); // we always add in the leaf so this tells that we reached leaf node
                this.size++;
            }
        } else if (data >= node.data) { // right tree
            if (node.right != null) {
                this.add(node.right, data); // asking recursion to go in right subtree because thats where this node belongs
            } else {
                node.right = new Node(data, null, null);
                this.size++;
            }

        }


    }

    public void display() {
        this.display(this.root);
    }

    private void display(Node node) {
        if (node.left != null) {
            System.out.print(node.left.data + "=>");
        } else {
            System.out.print("END=>");
        }

        System.out.print(node.data);

        if (node.right != null) {
            System.out.print("<=" + node.right.data);
        } else {
            System.out.print("<=END");
        }
        System.out.println();

        if (node.left != null) {
            this.display(node.left); // Now go and display left subtree
        }

        if (node.right != null) {
            this.display(node.right); // Now go and display right subtree
        }
    }

    public int max() {
        return this.max(this.root);
    }

    private int max(Node node) {
        if (node.right != null) {// since right most element will be
            // the biggest therefore we simply go till the end
            return this.max(node.right); // if we can go right means there is even bigger element so go and check
        } else {
            return node.data; // means we reached left most element therefore simply return it
        }
    }

    public int min() {
        return this.min(this.root);
    }

    private int min(Node node) {
        if (node.left != null) { // since left most element will be
            // the smallest therefore we simply go till the end
            return this.min(node.left);
        } else {
            return node.data; // means we reached left most element therefore simply return it
        }
    }

    public void remove(int data) {
        this.root = this.remove(this.root, data); //we assume that removing
        // the given element can change the root of tree therefore this will
        // make sure that if that is the case then we reset the root to correct one
    }

    private Node remove(Node node, int data) { // This function returns the new root of subtree after deletion
        if (node == null) { // if there's nothing then we don't have to delete anything
            return null;
        }
        if (data < node.data) {
            node.left = this.remove(node.left, data);
            return node;
        } else if (data > node.data) {
            node.right = this.remove(node.right, data);
            return node;
        } else { // this means we found the node
            if (node.right != null && node.left != null) { // i.e. if node that we want to remove has both children
                int lmax = max(node.left); // we can also find the right most minimum element
                node.data = lmax; // replace current node's data with lmax's data
                node.left = remove(node.left, lmax); // basically we are
                // telling that remove the left max element of current node
                // and then attach the new root of the left subtree to the left of current node
                // Note : No need to do size-- because that will be done when we actually delete the lmax node
                return node;
            } else if (node.left != null) {
                size--;
                return node.left; // we will return the left child so that it could
                // be attached to its parent and the current node will become orphan
                // and will be removed by garbage collector
            } else if (node.right != null) {
                size--;
                return node.right;
            } else { // if both children are null that means it's the leaf node so we can directly delete it
                size--;
                return null; // this null will be attached to the parent of this node this way current node will be deleted
            }
        }
    }


    public boolean isBST() {
        return this.isBst(this.root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }

    private boolean isBst(Node node, int min, int max) { // checks if tree is bst or not
        if (node == null) { //base case: empty tree is a bst
            return true;
        }
        if (node.data < min || node.data > max) {
            return false;
        } else if (!isBst(node.left, min, node.data)) {// check for left subtree , since we are going
            // in left of current element therefore current node
            // will be the maximum element for it since in a bst
            // all the left elements of a node should be smaller than the node
            return false;
        } else if (!isBst(node.right, node.data, max)) {// check for right subtree
            return false;
        } else {
            return true;
        }
    }
}
