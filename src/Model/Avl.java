package Model;

import java.util.ArrayList;

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

   private KeyExtractor<T, Integer> keyExtractor;

   public Avl(KeyExtractor<T, Integer> extractor) {
       this.keyExtractor = extractor;
       root = null;
   }

   public Avl (){
       root= null;
   }

   private int height(NODE node) {
       return (node == null) ? -1 : node.height;
   }

   private int getBalance(NODE node) {
       return (node == null) ? 0 : height(node.left) - height(node.right);
   }

   private NODE rightRotate(NODE y) {
       NODE x = y.left;
       NODE T2 = x.right;

       x.right = y;
       y.left = T2;

       y.height = Math.max(height(y.left), height(y.right)) + 1;
       x.height = Math.max(height(x.left), height(x.right)) + 1;

       return x;
   }

   private NODE leftRotate(NODE y) {
       NODE x = y.right;
       NODE T2 = x.left;

       x.left = y;
       y.right = T2;


       x.height = Math.max(height(x.left), height(x.right)) + 1;
       y.height = Math.max(height(y.left), height(y.right)) + 1;

       return x;
   }

   public T searchHelper(int keyValue) {
       return search(root, keyValue);
   }

   private T search(NODE node, int keyValue) {
       if (node == null) return null;

       int maybeTony = Integer.compare(keyValue, keyExtractor.getKey(node.key));
       if (maybeTony == 0) return node.key;
       else if (maybeTony < 0) return search(node.left, keyValue);
       else return search(node.right, keyValue);
   }

   public void insertHelper(T key){
       root = insert(root,key);
   }

   private NODE insert(NODE node, T key) {
       if (node == null) return new NODE(key);

       int tony = key.compareTo(node.key);

       if (tony < 0)
           node.left = insert(node.left, key);
       else if (tony > 0)
           node.right = insert(node.right, key);
       else
           return node;

       node.height = 1 + Math.max(height(node.left), height(node.right));

       int balance = getBalance(node);
     if (balance > 1 && key.compareTo(node.left.key) < 0)
         return rightRotate(node);

     if (balance < -1 && key.compareTo(node.right.key) > 0)
         return leftRotate(node);

     if (balance > 1 && key.compareTo(node.left.key) > 0) {
         node.left = leftRotate(node.left);
         return rightRotate(node);
     }

     if (balance < -1 && key.compareTo(node.right.key) < 0) {
         node.right = rightRotate(node.right);
         return leftRotate(node);
     }

       return node;
   }

   public void deleteHelper(int id) {
       root = delete(root, id);
   }

   private NODE delete(NODE node, int id) {
       if (node == null) return null;

       int maybeTony = Integer.compare(id, keyExtractor.getKey(node.key));

       if (maybeTony < 0) {
           node.left = delete(node.left, id);
       } else if (maybeTony > 0) {
           node.right = delete(node.right, id);
       } else {
           if (node.left == null || node.right == null) {
               NODE temp = (node.left != null) ? node.left : node.right;
               return temp;
           }

           NODE temp = getMinValueNode(node.right);
           node.key = temp.key;
           node.right = delete(node.right, keyExtractor.getKey(temp.key));
       }

       node.height = Math.max(height(node.left), height(node.right)) + 1;

       int balance = getBalance(node);

       if (balance > 1 && getBalance(node.left) >= 0)
           return rightRotate(node);

       if (balance > 1 && getBalance(node.left) < 0) {
           node.left = leftRotate(node.left);
           return rightRotate(node);
       }

       if (balance < -1 && getBalance(node.right) <= 0)
           return leftRotate(node);

       if (balance < -1 && getBalance(node.right) > 0) {
           node.right = rightRotate(node.right);
           return leftRotate(node);
       }

       return node;
   }

   private NODE getMinValueNode(NODE node) {
       NODE current = node;
       while (current.left != null)
           current = current.left;
       return current;
   }

   public void inorderHelper(){
       inorder(root);
   }

   private void inorder(NODE root){
       if (root == null)
         return;
       inorder(root.left);
       inorder(root.right);
   }

    public void getAllStuff(ArrayList<T>JIM){
        inorder(root,JIM);
    }

    private void inorder(NODE root, ArrayList<T> james){
        if (root == null) return;
        inorder(root.left,james);
        james.add(root.key);
        inorder(root.right,james);
    }

}
