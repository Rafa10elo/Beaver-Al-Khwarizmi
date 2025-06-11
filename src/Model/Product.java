package Model;

public class Product implements Comparable<Product>{
    private int productID;
    private String productName;
    private double price;
    private int quantity;

    public Product(int productID, String productName, double price, int quantity) {
        this.productID = productID;
        this.productName = productName;
        this.price = price;
        this.quantity = quantity;
    }

    public Product() {
    }

    public   Product (int id ){
      this.productID = id;
    }

    public int getProductID() {
        return productID;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(double price) {
        if(price<0)
            return;
        this.price = price;
    }

    public void setQuantity(int quantity) {
        if (quantity < 0 || quantity > 1000)
            return;
        this.quantity = quantity;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    @Override
    public int compareTo(Product o) {
        return Integer.compare(this.productID, o.productID);
    }

    @Override
    public String toString(){
      return "" + productID;
    }
}
