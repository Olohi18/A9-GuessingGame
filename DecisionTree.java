/**
 *  Implements decision trees.
 *
 *  @author  Olohi John
 *  @version CSC 210, November 2024
 */
public class DecisionTree extends BinaryTree<String>{ 
    /**
     * Constructor creates a tree with one node
     * @param data
     */
    public DecisionTree(String data){
        super(data);
    }

    /** 
     * This constructor creates a branch node 
     * @param data
     * @param left
     * @param right
     * */
    public DecisionTree(String data, DecisionTree left, DecisionTree right) {
        super(data, left, right);
    }

    /** 
     * This constructor creates a deep copy of the entire tree structure 
     * @param tree
     * */ 
    public DecisionTree(DecisionTree tree) {
        super(tree);
    }

    /** 
     * Accessor for node data 
     * @return data 
     * */
    public String getData() {
        return super.getData();
    }
    
    /** 
     * Accessor for left child 
     * @return left
     * */
    public DecisionTree getLeft() {
        return (DecisionTree)super.getLeft();
    }

    /** 
     * Accessor for right child 
     * @return right
     * */
    public DecisionTree getRight() {
        return (DecisionTree)super.getRight();
    }

    /** 
     * Manipulator for node data 
     * @param data
     * */
    public void setData(String data) {
        super.setData(data);
    }

    /** 
     * Manipulator for left child
     * @param left
     *  */
    public void setLeft(BinaryTree left) { 
        /** check to ensure tree passed in is a decision tree */
        if (left instanceof DecisionTree){
            super.setLeft(left);
        }else{
            throw new UnsupportedOperationException();
        }
    }

    /** 
     * Manipulator for right child
     * @param right
     *  */
    public void setRight(BinaryTree<String> right) {
        /** check to ensure tree passed in is a decision tree */
        if (right instanceof DecisionTree){
            super.setRight(right);
        }else{
            throw new UnsupportedOperationException();
        }
    }

    /**
     * @param <T>
     * @param t
     * @return preorder string
     */
    @SuppressWarnings("unused")
    public static <T> String preorderString(BinaryTree<T> t) {
        if (!(t instanceof DecisionTree)){
            throw new UnsupportedOperationException();
        }else if (t==null) {
            return "";
        }else {
            return "("+t.getData()+" "+preorderString(t.getLeft())+" "+preorderString(t.getLeft())+")";
        }
    }

    /**
     * @param <T>
     * @param t
     * @return inorder string
     */
    @SuppressWarnings("unused")
    public static <T> String inorderString(BinaryTree<T> t) {
        if (!(t instanceof DecisionTree)){
            throw new UnsupportedOperationException();
        }else if (t==null) {
            return "";
        } else {
            return "("+inorderString(t.getLeft())+" "+(t.getData())+" "+inorderString(t.getRight())+")";
        }
    }

    /**
     * @param <T>
     * @param t
     * @return postorder string
     */
    @SuppressWarnings("unused")
    public static <T> String postorderString(BinaryTree<T> t) {
        if (!(t instanceof DecisionTree)){
            throw new UnsupportedOperationException();
        }else if (t==null) {
            return "";
        } else {
            return "("+postorderString(t.getLeft())+" "+postorderString(t.getRight())+" "+t.getData()+")";
        }
    }

    public static void main(String[] args) {
        DecisionTree dTree = new DecisionTree("A");
        DecisionTree dTree2 = new DecisionTree("B");
        DecisionTree dTree3 = new DecisionTree("C");
        System.out.println("left is " + dTree.getLeft() + " and right is " + dTree.getRight());
        dTree.setLeft(dTree2);
        System.out.println("left is " + dTree.getLeft() + " and right is " + dTree.getRight());
        dTree.setRight(dTree3);
        System.out.println("left is " + dTree.getLeft() + " and right is " + dTree.getRight());

        System.out.println("entire tree is  " + dTree);
    }

}