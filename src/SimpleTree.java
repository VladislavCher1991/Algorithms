import myqueue.MyStack;
import myqueue.SimpleQueue;

public class SimpleTree {
    public static void main(String[] args) {
        Tree root = new Tree(20,
                new Tree(7,
                        new Tree(4, null, new Tree(6)), new Tree(9)),
                new Tree(35,
                        new Tree(31, new Tree(28), null),
                        new Tree(40, new Tree(38), new Tree(52))));
        System.out.println(root.sumDeep(root));
    }
}

class Tree {
    int value;
    Tree left;
    Tree right;

    public Tree(int value, Tree left, Tree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public Tree(int value) {
        this.value = value;
    }

    public int sumRecursive() {
        int summ = value;
        if (left != null) {
            summ += left.sumRecursive();
        }
        if (right != null) {
            summ += right.sumRecursive();
        }
        return summ;
    }
    public int sumDeep (Tree root) {
        SimpleQueue<Tree> queue = new SimpleQueue<>();
        int sum = 0;

        queue.add(root);

        while (!queue.isEmpty()){
            Tree node = queue.remove();
            sum = sum + node.value;
            if (node.right != null){
                queue.add(node.right);
            }
            if (node.left != null){
                queue.add(node.left);
            }
        }
        return sum;
    }
}
