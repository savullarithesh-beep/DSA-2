import java.util.*;

class AVLNode {
    String destination;
    AVLNode left, right;
    int height;

    AVLNode(String destination) {
        this.destination = destination;
        this.height = 1;
    }
}

public class CO1 {

    AVLNode root;

    int height(AVLNode node) {
        return (node == null) ? 0 : node.height;
    }

    int getBalance(AVLNode node) {
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    AVLNode rightRotate(AVLNode y) {
        AVLNode x = y.left;
        AVLNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    AVLNode leftRotate(AVLNode x) {
        AVLNode y = x.right;
        AVLNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    AVLNode insert(AVLNode node, String destination) {

        if (node == null)
            return new AVLNode(destination);

        if (destination.compareTo(node.destination) < 0)
            node.left = insert(node.left, destination);
        else if (destination.compareTo(node.destination) > 0)
            node.right = insert(node.right, destination);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL Rotation
        if (balance > 1 &&
                destination.compareTo(node.left.destination) < 0)
            return rightRotate(node);

        // RR Rotation
        if (balance < -1 &&
                destination.compareTo(node.right.destination) > 0)
            return leftRotate(node);

        // LR Rotation
        if (balance > 1 &&
                destination.compareTo(node.left.destination) > 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL Rotation
        if (balance < -1 &&
                destination.compareTo(node.right.destination) < 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    boolean search(AVLNode node, String destination) {

        if (node == null)
            return false;

        if (destination.equals(node.destination))
            return true;

        if (destination.compareTo(node.destination) < 0)
            return search(node.left, destination);

        return search(node.right, destination);
    }

    void inorder(AVLNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.println(node.destination);
            inorder(node.right);
        }
    }

    public static void main(String[] args) {

        CO1 tree = new CO1();

        String[] destinations = {
                "Hyderabad",
                "Warangal",
                "Vizag",
                "Araku",
                "NagarjunaSagar",
                "GolcondaFort",
                "RamojiFilmCity"
        };

        for (String d : destinations)
            tree.root = tree.insert(tree.root, d);

        System.out.println("Tourist Destinations (Sorted Order):");
        tree.inorder(tree.root);

        String searchPlace = "Araku";

        if (tree.search(tree.root, searchPlace))
            System.out.println("\nDestination Found: " + searchPlace);
        else
            System.out.println("\nDestination Not Found");

        System.out.println("\nAVL Tree Operations Completed Successfully");
    }
}