// Class representing a node in the Binary Search Tree (BST)
class Node {
    int key;
    Node left, right, parent;

    public Node(int key) {
        this.key = key;
        left = right = parent = null;
    }
}

public class BSTSort {
    private Node root;
    private static int keyComparisons = 0; // Static counter to track key comparisons

    // Method to insert a node into the BST following the TREE-INSERT pseudocode
    public void treeInsert(Node x, Node z) {
        if (x == null) {
            x = z; // The node z is inserted here
            if (root == null) {
                root = x; // If the tree was empty, set root
            }
            return;
        }

        keyComparisons++; // Increment key comparisons
        if (z.key < x.key) {
            if (x.left == null) {
                x.left = z;
                z.parent = x;
            } else {
                treeInsert(x.left, z); // Recur for left subtree
            }
        } else {
            if (x.right == null) {
                x.right = z;
                z.parent = x;
            } else {
                treeInsert(x.right, z); // Recur for right subtree
            }
        }
    }

    // Method for Inorder-Tree-Walk following the pseudocode
    public void inorderTreeWalk(Node x, int[] sortedArray, int[] index) {
        if (x != null) {
            inorderTreeWalk(x.left, sortedArray, index);  // Visit left child
            sortedArray[index[0]++] = x.key;              // Process current node
            inorderTreeWalk(x.right, sortedArray, index); // Visit right child
        }
    }

    // Method to sort the array using BST sort
    public int[] bstSort(int[] array) {
        // Build the BST by inserting elements into it
        for (int key : array) {
            Node newNode = new Node(key);
            treeInsert(root, newNode);
        }

        // Array to store sorted elements
        int[] sortedArray = new int[array.length];
        inorderTreeWalk(root, sortedArray, new int[]{0});  // Perform Inorder Tree Walk

        return sortedArray;
    }

    // Utility method to print the array (first 20 elements or less)
    public static void printArray(int[] array) {
        int limit = Math.min(array.length, 20);
        for (int i = 0; i < limit; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        // Input array to sort
        int[] inputArray = {12, 11, 13, 5, 6, 7, 15, 9, 10, 3, 2, 8, 4, 1, 14, 20, 19, 18, 16, 17};
        int size = inputArray.length;

        // Initialize the sorting object
        BSTSort bstSort = new BSTSort();

        // Print size of the input array
        System.out.println("Size of the input array: " + size);

        // Print the input array (first 20 elements)
        System.out.print("Input array: ");
        printArray(inputArray);

        // Sort the array using BST Sort
        int[] sortedArray = bstSort.bstSort(inputArray);

        // Print the sorted array (first 20 elements)
        System.out.print("Sorted array: ");
        printArray(sortedArray);

        // Output the number of key comparisons made
        System.out.println("Total number of key comparisons: " + keyComparisons);
    }
}