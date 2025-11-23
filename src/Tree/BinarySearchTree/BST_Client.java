package Tree.BinarySearchTree;

import Tree.BinarySearchTree.Bst;

public class BST_Client {
    public static void main(String[] args) {
        Bst tree = new Bst();
        tree.add(50);
        tree.add(25);
        tree.add(75);
        tree.add(12);
        tree.add(37);
        tree.add(62);
        tree.add(87);
        tree.add(30);
        tree.add(74);
        System.out.println("Size : " + tree.getSize());
        tree.display();
        System.out.println(" Removing");
        tree.remove(378);
        System.out.println("Size : " + tree.getSize());
        System.out.println(" Removing");
        tree.remove(25);
        System.out.println("Size : " + tree.getSize());
    }

}
