package DataStructure.Trees.Binary_Tree;

public class Tree {
    Node root;

    private class Node {
        int value;
        Node left;
        Node right;

        public Node(int value) {
            this.value = value;
        }
    }

    public void insert(int value) {
        Node node = new Node(value);
        if (root == null) {
            root = node;
            root.left = null;
            root.right = null;
        } else {
            var current = root;
            while (true){
                if(current.value > value){
                    if(current.left==null) {
                        current.left = node;
                        break;
                    }
                    current = current.left;
                }else {
                    if(current.right == null) {
                        current.right = node;
                        break;
                    }
                    current = current.right;
                }
            }
        }
    }
    public boolean find(int value){
        var current=root;
        while(current!=null){
            if(value == current.value){
                return true;
            }
            if(value>current.value){
                current=current.right;
            }else{
                current=current.left;
            }
        }
        return false;
    }
    public void preOrderTraversal(){
        preOrderTraversal(root);
    }
    private void preOrderTraversal(Node root){
        if(root==null){
            return;
        }
        System.out.print(root.value+" ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    public void inOrderTraversal(){
        inOrderTraversal(root);
    }
    private void inOrderTraversal(Node root){
        if(root==null){
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.value+" ");
        inOrderTraversal(root.right);
    }
    public void postOrderTraversal(){
        postOrderTraversal(root);
    }
    private void postOrderTraversal(Node root){
        if(root==null)
            return;
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.value+" ");
    }
    public int height(){
        return height(root);
    }

    public int height(Node root){
        if(root==null)
            return -1;
        if(root.left==null && root.right==null)
            return 0;
        return 1+Math.max(height(root.left), height(root.right));
    }

    public boolean equals(Tree tree2){
        return (equals(root, tree2.root));
    }

    private boolean equals(Node first, Node second) {
        if(first==null && second==null){
            return true;
        }
        if(first!=null &&second!=null){
            return first.value==second.value && equals(first.left, second.left) && equals(first.right, second.right);
        }
        return false;
    }
    public int min(){
        if(root==null){
            return -1;
        }
        Node current= root;
        int last;
        while(current.left!=null){
            current=current.left;
        }
        last= current.value;
        return last;
    }
    public void swap(){
        var temp=root.left;
        root.left=root.right;
        root.right=temp;
    }
    public boolean check(){
//        System.out.println(check);
        return check(root);
//        return
    }

    private boolean check(Node root){
        if(root.left==null || root.right==null){
            return true;
        }
        if(root.left.value-1< root.value && root.right.value+1> root.value){
            check(root.left);
            check(root.right);
        }else{
            return false;
        }
        return true;
    }
    public void distance(int dist){
        distance(root, dist);
    }
    private void distance(Node root, int dist){
        if(root==null){
            return ;
        }
        if(dist==0){
            System.out.println(root.value);
            return;
        }
        distance(root.left, dist-1);
        distance(root.right, dist-1);
    }
}

class Main{
    public static void main(String[] args) {
        Tree tree = new Tree();
        Tree tree2= new Tree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(25);
        tree.insert(5);
        tree.insert(15);
        tree.insert(18);
        tree.distance(3);
//        tree.swap();
        System.out.println(tree.check());
//        tree2.insert(10);
//        tree2.insert(20);
//        tree2.insert(30);
//        tree2.insert(5);
//        tree2.insert(15);
//        System.out.println(tree.equals(tree2));
//        System.out.println(tree.min());
//        System.out.println(tree.find(15));
//        System.out.println();
//        tree.preOrderTraversal();
//        System.out.println();
//        tree.inOrderTraversal();
//        System.out.println();
//        tree.postOrderTraversal();
//        System.out.println();
//        System.out.println(tree.height());
    }
}
