package Model;

public class Product implements Comparable<Product>{
    int productID;
    String productName;
    double price;
    int amount;

  public   Product ( int id ){
      this.productID = id;
    }


    @Override
    public int compareTo(Product o) {
     return  Integer.compare(o.productID,this.productID);
    }

    @Override
    public String toString(){
      return "" + productID;
    }
}
