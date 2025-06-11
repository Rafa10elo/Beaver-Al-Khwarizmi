package Model;

//import javax.crypto.KEM;

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

    public Product searchHelper(int key){
        Product product = new Product(key);
        return search(root,key);
    }

    private Product search(NODE node,int key){
        if (node == null) return null;
        Product current = (Product) node.key;
        if (key == current.getProductID())
            return current;
        if (key < current.getProductID())
            return search(node.left, key);
        else
            return search(node.right, key);
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
        Product product = new Product(id);
        root = delete(root, (T)product);
    }

    private NODE delete(NODE root, T key) {
        if (root == null)
            return null;

        int tony = key.compareTo(root.key);
        if (tony < 0)
            root.left = delete(root.left, key);
        else if (tony > 0)
            root.right = delete(root.right, key);
        else {
            if ((root.left == null) || (root.right == null)) {
                NODE temp ;
                if(root.left!=null)
                    temp=root.left;
                else
                    temp=root.right;

                if (temp == null)
                    return null;
                else
                    return temp;
            }

            NODE temp = getMinValueNode(root.right);
            root.key = temp.key;
            root.right = delete(root.right, temp.key);
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;

        int balance = getBalance(root);

        if (balance > 1 && getBalance(root.left) >= 0)
            return rightRotate(root);

        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        if (balance < -1 && getBalance(root.right) <= 0)
            return leftRotate(root);

        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
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
        System.out.println(root.key);
        inorder(root.right);
    }

}
