package _09codingInterview;

public class _16_5 {
    public static void main(String[] args) {

        BinaryTree bt = new BinaryTree();

        bt.insert(6);
        bt.insert(4);
        bt.insert(8);
        bt.insert(3);
        bt.insert(5);
        bt.insert(7);
        bt.insert(9);

        System.out.println(bt.contains(8));
        //bt.delete(8);
        System.out.println(bt.contains(8));
        System.out.println(bt.contains(7));

        System.out.println("the sum is " + bt.sum() +"\n");
        bt.inOrder();
        bt.preOrder();
        bt.postOrder();
        System.out.println("BFS: ");
        bt.bfs();
        System.out.println("\n");
        bt.delete(8);
        bt.inOrder();

    }



}
