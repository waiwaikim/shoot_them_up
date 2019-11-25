package _09codingInterview;

import java.util.LinkedList;
import java.util.Queue;

public class BinaryTree {
    Node root;

    BinaryTree(int key){
        root = new Node(key);
    }
    BinaryTree(){
        root = null;
    }

    public void insert(int value) {
        root = insertRecursive(root, value);
    }
    private Node insertRecursive(Node current, int value){
        if(current == null){
            return new Node(value);
        }

        if(value < current.value){
            current.left  = insertRecursive(current.left, value);
        }
        else if(value > current.value){
            current.right = insertRecursive(current.right, value);
        }
        else{
            return current;
        }
        return current;
    }

    private boolean containsRecursive(Node current, int value){
        if(current == null) return false;
        if(current.value == value) return true;

        return (value < current.value) ? containsRecursive(current.left, value) : containsRecursive(current.right, value);
    }
    public boolean contains(int value){
        return containsRecursive(root, value);
    }
    private int findSmallest(Node current) {
        return (current.left == null) ? current.value : findSmallest(current.left);
    }
    public void delete(int value){
        root = deleteRecursive(root, value);
    }
    private Node deleteRecursive(Node current, int value){
        if(current == null){
            return null;
        }

        if (value == current.value){
            if(current.left == null && current.right==null) return null;

            else if(current.left == null && current.right != null) return current.right;

            else if(current.left != null && current.right == null) return current.left;

            else {
                int smallest = findSmallest(current.right);
                current.value = smallest;
                current.right = deleteRecursive(current.right, smallest);
                return current;
            }

        }
        if(value < current.value){
            current.left = deleteRecursive(current.left, value);
            return current;
        }
        current.right = deleteRecursive(current.right, value);
        return current;
    }

    public int sum(){
        return sumRecursive(root);
    }
    public int sumRecursive(Node node){
        if(node == null){
            return 0;
        }
        return node.value + sumRecursive(node.left) + sumRecursive(node.right);

    }
    public void inOrder(){
        System.out.println("DFS In-order: ");
        dfsInOrder(root);
        System.out.println("\n");
    }
    public void preOrder(){
        System.out.println("DFS Pre-order: ");
        dfsPreOrder(root);
        System.out.println("\n");
    }

    public void postOrder(){
        System.out.println("DFS Post-order: ");
        dfsPostOrder(root);
        System.out.println("\n");
    }

    public void dfsInOrder(Node node){
        //left, root, right
        if(node != null){
            dfsInOrder(node.left);
            System.out.print(" " + node.value);
            dfsInOrder(node.right);
        }
    }
    public void dfsPreOrder(Node node){
        //root, left, right
        if(node != null){
            System.out.print(" " + node.value);
            dfsPreOrder(node.left);
            dfsPreOrder(node.right);
        }
    }
    public void dfsPostOrder(Node node){
        //left, right, root
        if(node != null){
            dfsPostOrder(node.left);
            dfsPostOrder(node.right);
            System.out.print(" " + node.value);
        }
    }
    public void bfs(){
        if (root == null){
            return;
        }
        Queue<Node> nodes = new LinkedList<>();
        nodes.add(root);
        while(!nodes.isEmpty()){
            Node temp = nodes.remove();
            System.out.print(" " + temp.value);

            if(temp.left != null) nodes.add(temp.left);
            if(temp.right != null) nodes.add(temp.right);
        }
    }
}
