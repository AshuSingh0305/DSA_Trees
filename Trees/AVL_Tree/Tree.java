package DataStructure.Trees.AVL_Tree;

public class Tree {

    Node root;
    private class Node{
        int value;
        Node left;
        Node right;
        public Node(int value) {
            this.value = value;
        }
    }
    public void insert(int value){
        Node node=new Node(value);
        if(root==null){
            root=node;
        }else{
            Node current = root;
            insert(current, node, value);
        }
    }
    private void insert(Node current, Node node, int val){
        if(current.value<val){
            if(current.right==null){
                current.right=node;
                return;
            }else {
                insert(current.right, node, val);
            }
        }else if(current.value>val){
            if(current.left==null){
                current.left=node;
                return;
            }else {
                insert(current.left, node, val);
            }
        }
    }
}
class main{
    public static void main(String[] args) {
        Tree tree= new Tree();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(5);
        tree.insert(40);
        tree.insert(8);
        tree.insert(35);
    }
}
