package repository;

import Model.Avl;
import Model.Product;

import java.util.ArrayList;


public class ProductRepo <T extends Comparable<T>>{

    private Avl<Product> products ;

    public ProductRepo(Avl<Product> products) {
        this.products = products;
    }

    public void updateProductPrice(Product product, double newPrice) {
        if (newPrice >= 0)
            product.setPrice(newPrice);
    }

    public void updateProductQuantity(Product product, int newQuantity) {
        if (newQuantity >= 0 && newQuantity <= 1000){
            product.setQuantity(newQuantity);
        }

    }

    public void deleteProduct(int id){
        products.deleteHelper(id);
    }

    public void insertProduct(Product product){
        if(product.getQuantity()<0||product.getQuantity()>1000||product.getPrice()<0)
            return;
        products.insertHelper(product);
    }

    public Product searchProduct(int id){
        return products.searchHelper(id);
    }

    public ArrayList<Product> getList(){
        ArrayList <Product> James= new ArrayList<>();
        products.getAllStuff(James);
        return James;

    }

    public double InventoryValue(){
        ArrayList<Product> product=getList();
        double value=0.0;
        for(int i=0;i<product.size();i++){
            Product p=product.get(i);
            value+=p.getPrice()*p.getQuantity();
        }
        return value;
    }

}
