package DataStructure.Trees.Binary_Tree;

import java.util.*;

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
    public void display_Top_View(){
        Stack<Integer> stack=new Stack<>();
        int i=0;
        Node current=root;
        for(i=0;i<2000;i++){
            if(current==null){
                break;
            }
            stack.push(current.value);
            current=current.left;
        }
        Stack<Integer> stack2=new Stack<>();
        current=root.right;
        for(i=0;i<2000;i++){
            if(current==null){
                break;
            }
            stack2.push(current.value);
            current=current.right;
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
        while (!stack2.isEmpty()){
            stack.push(stack2.pop());
        }
        while (!stack.isEmpty()){
            System.out.print(stack.pop()+" ");
        }
    }
    public int Diameter(){
        return Diameter(root)[0];
    }
    private int[] Diameter(Node root){
        if(root==null){
            int [] a = {0,0};
            return a;
        }
        int [] left = Diameter(root.left);
        int [] right = Diameter(root.right);

        int opt1 = left[0];
        int opt2 = right[0];
        int opt3 = left[1] + right[1] + 1;

        int [] ans = new int[2];
        ans[0] = Math.max(opt1, Math.max(opt2, opt3));
        ans[1] = Math.max(left[1], right[1])+1;
        return ans;
    }
    public boolean isBalanced(){
        if(isBalanced(root)[0]==1){
            return true;
        }else return false;
    }
    private int[] isBalanced(Node root){
        if(root == null){
            int a[] = {1,0};
            return a;
        }
        int [] left = isBalanced(root.left);
        int [] right = isBalanced(root.right);

        int leftAns = left[0];
        int rightAns= right[0];
        int diff=0;
        if(Math.abs(left[1]-right[1])<=1){
            diff=1;
        }
        int [] ans = new int[2];
        ans[1]=Math.max(left[1], right[1])+1;
        if(leftAns==1 && rightAns==1 && diff==1){
            ans[0]=1;
        }else ans[0]=0;
        return ans;
    }
    public void mirror(){
        mirror(root);
    }
    private void mirror(Node root){
        if(root == null){
            return;
        }
        if(root.left==null && root.right==null){
            return;
        }
        var temp = root.left;
        root.left=root.right;
        root.right=temp;
        mirror(root.left);
        mirror(root.right);
    }


    public void leftView(){
        ArrayList<Integer> al = new ArrayList<>();
        leftView(root, al, 0);
        System.out.println(al);
    }
    public void leftView(Node root, ArrayList<Integer> al, int level){
        if(root==null)
            return;
        if(level == al.size()){
            al.add(root.value);
        }
        leftView(root.left, al, level+1);
        leftView(root.right, al, level+1);
    }

    public void rightView(){
        ArrayList<Integer> rv = new ArrayList<>();
        rightView(root, rv, 0);
        System.out.println(rv);
    }
    private void rightView(Node root, ArrayList<Integer> rv, int level){
        if(root==null){
            return;
        }
        if(level == rv.size()){
            rv.add(root.value);
        }
        rightView(root.right, rv, level+1);
        rightView(root.left, rv, level+1);
    }
    public boolean SumTree(){
        int ans = SumTree(root)[0];
        if(ans==1) return true;
        else return false;
    }

    private int[] SumTree(Node root){
        if(root==null){
            int arr[] = {1,0};
            return arr;
        }
        if(root.left == null && root.right == null){
            int arr[]={1, root.value};
            return arr;
        }
        int leftAns[] = SumTree(root.left);
        int rightAns[]= SumTree(root.right);

        int SumLeft = leftAns[0];
        int SumRight= rightAns[0];

        int condition=0;
        if(root.value==leftAns[1]+rightAns[1]) condition=1;

        int ans[]=new int[2];
        if(SumLeft==1 && SumRight==1 && condition==1) {
            ans[0]=1;
            ans[1]= 2*root.value;
        }
        else ans[0]=0;
        return ans;
    }

    public void zigzag(){
        ArrayList<Integer> al = zigzag(root);
        System.out.println(Arrays.toString(new ArrayList[]{al}));
    }

    private ArrayList<Integer> zigzag(Node root){
        ArrayList<Integer> result = new ArrayList<>();
        if(root==null){
            return result;
        }

        Queue<Node> queue = new ArrayDeque<>();
        queue.add(root);

        boolean leftToRight = true;
        while (!queue.isEmpty()){
            int size = queue.size();
            int al[] = new int[size];
            for (int i=0;i<size;i++){
                Node temp = queue.peek();
                queue.remove();

                int index = leftToRight ? i:size-i-1;
                al[index] = temp.value;

                if(temp.left!=null){
                    queue.add(temp.left);
                }
                if (temp.right!=null) queue.add(temp.right);
            }
            leftToRight = !leftToRight;

            for (var i: al){
                result.add(i);
            }
        }
        return result;
    }

    public void boundary(){
        ArrayList<Integer> al = boundary(root);
        System.out.println(al);
    }

    private ArrayList<Integer> boundary(Node root){
        ArrayList<Integer> ans = new ArrayList<>();
        if(root==null){
            return ans;
        }
        ans.add(root.value);

        leftTraverse(root.left, ans);

        leafTraverse(root.left, ans);
        leafTraverse(root.right, ans);

        rightTraverse(root.right, ans);
        return ans;
    }

    private void rightTraverse(Node root, ArrayList<Integer> ans){
        if((root==null) || (root.left==null && root.right==null)) return;

        if(root.right!=null)
            rightTraverse(root.right, ans);
        else
            rightTraverse(root.left, ans);
        ans.add(root.value);
    }

    private void leafTraverse(Node root, ArrayList<Integer> ans){
        if(root==null){
            return;
        }
        if(root.left==null && root.right==null){
            ans.add(root.value);
            return;
        }
        leafTraverse(root.left, ans);
        leafTraverse(root.right, ans);

    }

    private void leftTraverse(Node root, ArrayList<Integer> ans){
        if ((root==null) || (root.left==null && root.right==null)){
            return ;
        }
        ans.add(root.value);
        if(root.left!=null)
            leftTraverse(root.left, ans);
        else
            leftTraverse(root.right, ans);
    }

    public void topView(){
        ArrayList<Integer> al = topView(root);
        System.out.println(al);
    }

    private ArrayList<Integer> topView(Node root){
        ArrayList<Integer> al = new ArrayList<>();
        class QueueObj{
            Node node;
            int hd;
            QueueObj(Node node, int hd){
                this.node = node;
                this.hd = hd;
            }
        }
        Queue<QueueObj> queue = new LinkedList<QueueObj>();
        Map<Integer, Node> map = new TreeMap<Integer, Node>();
        if (root==null) return al;
        else {
            queue.add(new QueueObj(root,0));
        }
        while (!queue.isEmpty()){
            QueueObj tempNode = queue.peek();
            queue.remove();

            if(!map.containsKey(tempNode.hd)){
                map.put(tempNode.hd, tempNode.node);
            }
            if(tempNode.node.left!=null){
                queue.add(new QueueObj(tempNode.node.left, tempNode.hd-1));
            }
            if(tempNode.node.right!=null){
                queue.add(new QueueObj(tempNode.node.right, tempNode.hd+1));
            }
        }
        for (Map.Entry<Integer, Node> entry: map.entrySet()){
            al.add(entry.getValue().value);
        }
        return al;
    }
    public void bottomView(){
        ArrayList<Integer> al = bottomView(root);
        System.out.println(al);
    }

    private ArrayList<Integer> bottomView(Node root){
        ArrayList<Integer> al=new ArrayList<>();
        class QueueObj{
            Node node;
            int hd;
            QueueObj(Node node, int hd){
                this.node=node;
                this.hd=hd;
            }
        }
        Queue<QueueObj> queue = new LinkedList<QueueObj>();
        Map<Integer, Node> map = new TreeMap<Integer, Node>();

        if (root==null) return al;
        else queue.add(new QueueObj(root, 0));

        while (!queue.isEmpty()){
            QueueObj tempNode = queue.poll();
            map.put(tempNode.hd, tempNode.node);
            if (tempNode.node.left!=null)
                queue.add(new QueueObj(tempNode.node.left, tempNode.hd-1));

            if(tempNode.node.right!=null)
                queue.add(new QueueObj(tempNode.node.right, tempNode.hd+1));
        }
        for (Map.Entry<Integer, Node> entry: map.entrySet()){
            al.add(entry.getValue().value);
        }
        return al;
    }

    public void diagonalTraversal(){
        ArrayList<Integer> al = diagonalTraversal(root);
        System.out.println(al);
    }

    private ArrayList<Integer> diagonalTraversal(Node root){
        ArrayList<Integer> al = new ArrayList<>();
        if (root==null){
            return al;
        }
        class TNode{
            Node node;
            int level;

            TNode(Node node, int level){
                this.node=node;
                this.level=level;
            }
        }
        TreeMap<Integer, List<Integer>> map = new TreeMap<Integer, List<Integer>>();
        Queue<TNode> queue = new LinkedList<TNode>();

        queue.add(new TNode(root,0));
        while (!queue.isEmpty()){
            TNode curr = queue.poll();
            map.putIfAbsent(curr.level, new ArrayList<>());
            map.get(curr.level).add(curr.node.value);

            if(curr.node.left!=null){
                queue.add(new TNode(curr.node.left, curr.level+1));
            }
            if(curr.node.right!=null){
                queue.add(new TNode(curr.node.right, curr.level));
            }
        }

        for (Map.Entry<Integer, List<Integer>> entry: map.entrySet()){
            int k = entry.getKey();
            List<Integer> list = map.get(k);
            for (int i=0;i<list.size();i++){
                al.add(list.get(i));
            }
        }
        return al;

    }

    static int SumLongestBlood;
    static int LengthLongestBlood;
    public void sumOfLongestBloodline(){
        int len = 0;
        LengthLongestBlood = 0;

        int sum = 0;
        SumLongestBlood = Integer.MIN_VALUE;
        sumOfLongestBloodline(root, len, sum);
        System.out.println(SumLongestBlood);
    }

    private int sumOfLongestBloodline(Node root, int len, int sum){
        if (root==null){
            if (len>LengthLongestBlood){
                LengthLongestBlood = len;
                SumLongestBlood = sum;
            }
            else if(len==LengthLongestBlood){
                SumLongestBlood = Math.max(sum, SumLongestBlood);
            }
            return SumLongestBlood;
        }
        sum+=root.value;
        sumOfLongestBloodline(root.left, len+1, sum);
        sumOfLongestBloodline(root.right, len+1, sum);

        return SumLongestBlood;
    }
    public void lowestCommonAncestor(){
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter n1 & n2: ");
        int n1= sc.nextInt();
        int n2=sc.nextInt();
        Node ans = lowestCommonAncestor(root, n1, n2);
        System.out.println(ans.value);
    }

    private Node lowestCommonAncestor(Node root, int n1, int n2){
        if(root == null){
            return null;
        }
        if (root.value == n1 || root.value==n2){
            return root;
        }
        Node leftAns = lowestCommonAncestor(root.left, n1, n2);
        Node rightAns= lowestCommonAncestor(root.right, n1, n2);

        if(leftAns!=null && rightAns!=null){
            return root;
        }
        else if(leftAns!=null && rightAns==null){
            return leftAns;
        }
        else if(rightAns!=null && leftAns==null){
            return rightAns;
        }
        else return null;
    }

    static int countKSum = 0;
    public void KSum(){
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        ArrayList<Integer> list = new ArrayList<>();
        System.out.println(KSum(root, k, list));
    }
    private int KSum(Node root, int k, ArrayList<Integer> list){
        if (root==null){
            return countKSum;
        }
        list.add(root.value);
        KSum(root.left, k, list);
        KSum(root.right, k, list);

        int sum=0;
        int size=list.size();
        for (int i= size-1;i>=0;i--){
            sum+=list.get(i);
            if (sum==k)
                countKSum++;
        }
        list.remove(size-1);
        return countKSum;
    }

    static int kAncVal = 0;
    public void kthAncestor(){
        Scanner sc = new Scanner(System.in);
        int k = sc.nextInt();
        int n = sc.nextInt();
        kAncVal = k;

        Node ans = kthAncestor(root, n);
        if(ans==null || ans.value==n){
            System.out.println("-1");
        }else
            System.out.println(ans.value);
    }

    private Node kthAncestor(Node root, int n){
        if (root==null){
            return null;
        }
        if(root.value==n){
            return root;
        }

        Node leftAns = kthAncestor(root.left, n);
        Node rightAns= kthAncestor(root.right, n);

        if(leftAns!=null && rightAns==null){
            kAncVal-=1;
            if(kAncVal<=0){
                kAncVal=Integer.MAX_VALUE;
                return root;
            }
            return leftAns;
        }
        if(leftAns==null && rightAns!=null){
            kAncVal-=1;
            if(kAncVal<=0){
                kAncVal=Integer.MAX_VALUE;
                return root;
            }
            return rightAns;
        }
        return null;
    }

    public void MaximumSumOfNonAdjacentNodes(){
        int ans[] = MaximumSumOfNonAdjacentNodes(root);
        System.out.println(Math.max(ans[0], ans[1]));
    }
    private int[] MaximumSumOfNonAdjacentNodes(Node root){
        if(root==null) {
            int arr[] = {0, 0};
            return arr;
        }
        int left[] = MaximumSumOfNonAdjacentNodes(root.left);
        int right[]= MaximumSumOfNonAdjacentNodes(root.right);

        int ans[] = new int[2];
        ans[0]=left[1]+right[1]+ root.value;
        ans[1]=Math.max(left[0],left[1]) + Math.max(right[0], right[1]);

        return ans;
    }
    static int preOrderIndex;
    public void constructFromInPre(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int in[] = new int[n];
        int pre[] = new int[n];
        for (int i=0; i<n; i++){
            in[i] = sc.nextInt();
        }
        for (int i=0; i<n; i++){
            pre[i] = sc.nextInt();
        }
        preOrderIndex = 0;
        createMapping(in, n);

        Node ans = constructFromInPre(in, pre, 0, n-1, n);
        System.out.println(ans);
    }



    public Node constructFromInPre(int in[], int pre[], int inOrderStart, int inOrderEnd, int n){
        if((preOrderIndex >= n) || (inOrderStart> inOrderEnd)){
            return null;
        }
        int element = pre[preOrderIndex++];
        Node newNode = new Node(element);
        int position = nodeToIndex.get(element);

        newNode.left = constructFromInPre(in, pre, inOrderStart, position-1, n);
        newNode.right= constructFromInPre(in, pre, position+1, inOrderEnd,n);

        return newNode;
    }

    static int postOrderIndex;
    public void constructFromInPost(){
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        int in[] = new int[n];
        int post[] = new int[n];
        for (int i=0; i<n; i++){
            in[i] = sc.nextInt();
        }
        for (int i=0; i<n; i++){
            post[i] = sc.nextInt();
        }
        postOrderIndex = n-1;
        createMapping(in, n);

        Node ans = constructFromInPost(in, post, 0, n-1, n);
        System.out.println(ans);
    }



    public Node constructFromInPost(int in[], int post[], int inOrderStart, int inOrderEnd, int n){
        if((postOrderIndex < 0) || (inOrderStart> inOrderEnd)){
            return null;
        }
        int element = post[postOrderIndex--];
        Node newNode = new Node(element);
        int position = nodeToIndex.get(element);

        newNode.right= constructFromInPost(in, post, position+1, inOrderEnd,n);
        newNode.left = constructFromInPost(in, post, inOrderStart, position-1, n);


        return newNode;
    }

    static Map<Integer, Integer> nodeToIndex = new HashMap<>();
    public void createMapping(int [] arr, int n){
        for (int i=0;i<n;i++){
            nodeToIndex.put(arr[i],i);
        }
    }

    static Map<Node, Node> NodeToParent = new HashMap<>();
    public void burnTree(){
        Scanner sc = new Scanner(System.in);
        int target = sc.nextInt();
        Node res = MappingNodeToParent(root, target);

        int ans = burnTree(res);
        System.out.println(ans);
    }

    private int burnTree(Node res){
        int ans=0;
        Queue<Node> queue = new ArrayDeque<>();
        Map<Node, Integer> visited = new HashMap<>();
        queue.add(res);
        visited.put(res,1);
        while (!queue.isEmpty()){
            boolean flag = false;
            int size= queue.size();
            for (int i=0;i<size;i++){
                Node front = queue.poll();
                visited.putIfAbsent(front,0);
                visited.putIfAbsent(front.left,0);
                visited.putIfAbsent(front.right,0);
                visited.putIfAbsent(NodeToParent.get(front),0);
                if(front.left!=null && visited.get(front.left)==0){
                    queue.add(front.left);
                    visited.put(front.left, 1);
                    flag=true;
                }
                if (front.right!=null && visited.get(front.right)==0){
                    queue.add(front.right);
                    visited.put(front.right, 1);
                    flag=true;
                }

                if(NodeToParent.get(front)!=null && visited.get(NodeToParent.get(front))==0){
                    queue.add(NodeToParent.get(front));
                    visited.put(NodeToParent.get(front), 1);
                    flag=true;
                }
            }
            if (flag)
                ans++;
        }
        return ans;
    }

    public Node MappingNodeToParent(Node root, int target){
        Node res = null;
        Queue<Node> queue = new ArrayDeque<>();
        NodeToParent.put(root, null);
        queue.add(root);
        while (!queue.isEmpty()){
            Node front = queue.poll();
            if(front.value == target)
                res=front;
            if(front.left!=null){
                NodeToParent.put(front.left, front);
                queue.add(front.left);
            }
            if (front.right!=null) {
                NodeToParent.put(front.right, front);
                queue.add(front.right);
            }
        }
        System.out.println(NodeToParent);
        return res;
    }

    public void inOrderWithoutRecursion(){
        inOrderWithoutRecursion(root);
    }

    private void inOrderWithoutRecursion(Node root){
        Node current,previous;
        if(root == null){
            return;
        }
        current=root;
        while(current != null){
            if(current.left == null){
                System.out.print(current.value+" ");
                current=current.right;
            }else{
                previous = current.left;
                while (previous.right!=null && previous.right!=current){
                    previous=previous.right;
                }

                if(previous.right==null){
                    previous.right=current;
                    current=current.left;
                }else{
                    previous.right=null;
                    System.out.print(current.value+" ");
                    current=current.right;
                }
            }
        }
    }
    public void toLinkedList(){
        toLinkedList(root);
    }

    private void toLinkedList(Node root){
        Node current = root;
        while(current!=null){
            if(current.left!=null){
                Node previous = current.left;
                while (previous.right!=null){
                    previous=previous.right;
                }
                previous.right=current.right;
                current.right=current.left;
                current.left=null;
            }
            current=current.right;
        }
    }
    public void selfTree(){
        Scanner sc = new Scanner(System.in);
        String str = sc.next();
        newNode root = treeFromString(str, 0, str.length()-1);
        preOrderTraversalForString(root);
    }

    static class newNode{
        int value;
        newNode left;
        newNode right;
    };
    static newNode tempNode(int value){
        newNode node = new newNode();
        node.value=value;
        node.left=node.right=null;
        return node;
    }
    public newNode treeFromString(String str, int si, int ei){
        if(si>ei) return null;
        newNode root = tempNode(str.charAt(si)-'0');

        int index=-1;

        if(si+1<=ei && str.charAt(si+1)=='('){
            index = findIndex(str, si+1, ei);
        }
        if(index!=-1){
            root.left=treeFromString(str, si+2, index-1);
            root.right=treeFromString(str, index+2, ei-1);
        }
        return root;
    }

    public int findIndex(String str, int si, int ei){
        if(si>ei) return -1;

        Stack<Character> stack = new Stack<>();
        for(int i=si;i<=ei;i++){
            if(str.charAt(i)=='('){
                stack.push(str.charAt(i));
            }
            else if(str.charAt(i)==')'){
                if(stack.peek()=='('){
                    stack.pop();
                    if(stack.isEmpty()){
                        return i;
                    }
                }
            }
        }
        return -1;
    }

    public void preOrderTraversalForString(newNode root){
        if(root == null){
            return;
        }
        System.out.print(root.value+" ");
        preOrderTraversalForString(root.left);
        preOrderTraversalForString(root.right);
    }

//    public void toDoublyLinkedList(){
//        Node node = toDoublyLinkedList(root);
//    }

    public void transformToSumTree(){
        transformToSumTree(root);
        preOrderTraversal(root);
    }
    private int transformToSumTree(Node root){
        if(root==null) return 0;
        int val = root.value;
        root.value = transformToSumTree(root.left)+transformToSumTree(root.right);
        return val + root.value;
    }

    public void leafAtSameLevel(){
        leafAtSameLevel(root, 0);
        if (leafAtSame.size()==1){
            System.out.println(1);
        }else System.out.println(0);
    }
    HashSet<Integer> leafAtSame = new HashSet<>();
    private void leafAtSameLevel(Node root, int level){
        if(root == null){
            return;
        }
        if(root.left==null && root.right==null){
            leafAtSame.add(level);
            return;
        }
        leafAtSameLevel(root.left, level+1);
        leafAtSameLevel(root.right, level+1);

    }

    static int SubTreeSum;
    public int largestSubTreeSum(){
        if(root==null) return 0;
        SubTreeSum = Integer.MIN_VALUE;
        largestSubTreeSum(root);
        return SubTreeSum;
    }
    private int largestSubTreeSum(Node root){
        int currSum = root.value + largestSubTreeSum(root.left) + largestSubTreeSum(root.right);
        if (SubTreeSum < currSum)
            SubTreeSum = currSum;
        return currSum;
    }

    static int dist;
    public void minDistanceTwoNodes(){
        Scanner sc = new Scanner(System.in);
        int n1=sc.nextInt();
        int n2=sc.nextInt();
        Node lca = lowestCommonAncestor(root, n1, n2);
        int l = distance2(lca, n1);
        int r = distance2(lca, n2);
        System.out.println(l+r-2);
    }


    private int distance2(Node root, int ans){
        if(root==null)
            return 0;
        if(root.value==ans)
            return 1;
        int l = distance2(root.left, ans);
        int r = distance2(root.right, ans);
        if(l==0 && r==0)
            return 0;
        else
            return Math.max(l,r)+1;
    }


}



class Main{
    public static void main(String[] args) {
        Tree tree = new Tree();
        Scanner sc=new Scanner(System.in);
        int n=sc.nextInt();
        for(int i=0;i<n;i++){
            tree.insert(sc.nextInt());
        }
//        tree.minDistanceTwoNodes();
//        System.out.println(tree.largestSubTreeSum());
//        tree.leafAtSameLevel();
//        tree.transformToSumTree();
//        tree.toDoublyLinkedList();
//        tree.selfTree();
//        tree.toLinkedList();
//        tree.inOrderWithoutRecursion();
//        tree.burnTree();
//        tree.constructFromInPost();
//        tree.constructFromInPre();
//        tree.MaximumSumOfNonAdjacentNodes();
//        tree.kthAncestor();
//        Tree tree2 = new Tree();
//        int m=sc.nextInt();
//        for(int i=0;i<n;i++){
//            tree2.insert(sc.nextInt());
//        }
//        tree.lowestCommonAncestor();
//        tree.sumOfLongestBloodline();
//        tree.diagonalTraversal();
//        tree.bottomView();
//        tree.boundary();
//        tree.zigzag();
//        System.out.println(tree.SumTree());
//        tree.display_Top_View();
//        System.out.println(tree.isBalanced());
//        tree.inOrderTraversal();
//        System.out.println();
//        tree.postOrderTraversal();
//        System.out.println();
//        tree.preOrderTraversal();
//        System.out.println();
//        tree.inOrderTraversal();
//        System.out.println();
//        tree.mirror();
//        System.out.println();
//        tree.inOrderTraversal();
//        System.out.println();
//        tree.leftView();
//        tree.rightView();
//        tree.inOrderTraversal();
//        Tree tree2= new Tree();
//        tree.insert(10);
//        tree.insert(20);
//        tree.insert(30);
//        tree.insert(25);
//        tree.insert(5);
//        tree.insert(15);
//        tree.insert(18);
//        tree.distance(3);
//        tree.swap();
//        System.out.println(tree.check());
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
