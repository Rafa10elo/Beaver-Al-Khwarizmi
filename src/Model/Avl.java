package Model;

import javax.crypto.KEM;
import java.awt.event.KeyAdapter;
import java.util.Optional;

public class Avl <T extends Comparable<T>> {
   public NODE root ;
    class NODE {
        public T key;
        public int height;
        public NODE left, right;
        NODE(T key) {
            left = right = null;
            this.key = key;
            height = 0;
        }
    }

    public void print(){


        System.out.println(root.right.key);
    }

    public Avl (){
        root= null;
    }


    int height(NODE node) {
        // returns the height of the actual node and -1 for nulls
        return (node == null) ? -1 : node.height;
    }

    int getBalance(NODE node) {
        //  this will return (the height of the left tree - the height of the right tree)
        // if the node is null return 0  since both children are null (height -1)
        return (node == null) ? 0 : height(node.left) - height(node.right);
    }

    NODE rightRotate(NODE y) {
        //left child of the original node
        NODE x = y.left;
        // the right child of the left child of the original node
        NODE T2 = x.right;

        // the right child of the left child of the original node is the original node
        x.right = y;
        // the new left child of the original node (T2)
        y.left = T2;

        // new heights
        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        //this returns the left child (it will become the new root)
        return x;
    }

    NODE leftRotate(NODE y) {
        //right child of the original node
        NODE x = y.right;
        // the left child of the right child of the original node
        NODE T2 = x.left;

        // the left child of the right child of the original node is the original node
        x.left = y;
        // the new right child of the original node (T2)
        y.right = T2;


        // new heights
        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        //this returns the right child (it will become the new root)
        return x;
    }

    public NODE search(NODE node,T key){

        if (node == null) return null;

   int tony =  node.key.compareTo((T)key);
        if (tony<0)
            node.left = search(node.left, key);
        else if ( tony > 0)
            node.right = search(node.right, key);
        else
            return node;

        return node;


    }

    public void insertHelper(T key){
        root = insert(root,key);
    }
    public void searchHelper(Integer key){

        Product product = new Product(key);
        search(root,(T)key);



    }

  public NODE insert(NODE node, T key) {
        //common insertion
        if (node == null) return new NODE(key);

        int tony = key.compareTo(node.key);

        if (tony > 0)
            node.left = insert(node.left, key);
        else if (tony < 0)
            node.right = insert(node.right, key);
        else
            return node;

        //calculate the new height
        node.height = 1 + Math.max(height(node.left), height(node.right));

        //the difference between the heights of the children
        int balance = getBalance(node);
      int m1= 0;
      int m2=0;

        //right rotation case
        if(node.left!=null)
            m1 = key.compareTo(node.left.key);
        if(node.right!=null)
            m2 = key.compareTo(node.right.key);

        if (balance > 1 && m1 > 0)
            return rightRotate(node);

        //left rotation case
        if (balance < -1 && m2 <= 0)
            return leftRotate(node);

        //left-right rotation case
        if (balance > 1 && m1 <= 0) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        //right-left rotation case
        if (balance < -1 && m2 > 0) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;


    }



    public void inorderHelper(){
        inorder(root);
    }
    public void inorder(NODE root){

        if (root == null)
          return;
        inorder(root.left);
        System.out.println(root.key);
        inorder(root.right);


    }
}
