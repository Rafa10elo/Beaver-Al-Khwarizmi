package repository;

import Model.Avl;
import Model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductRepo <T extends Comparable<T>>{

    private Avl<Product> products ;

    public ProductRepo(Avl<Product> products) {
        this.products = products;
    }

    public boolean updateProductPrice(int id, double newPrice) {
        Product p = products.searchHelper(id);
        if (p == null)
            return false;
        if (newPrice >= 0)
            p.setPrice(newPrice);
        return true;
    }

    public boolean updateProductQuantity(int id, int newQuantity) {
        Product p = products.searchHelper(id);
        if (p == null)
            return false;
        if (newQuantity >= 0 && newQuantity <= 1000)
            p.setQuantity(newQuantity);
        return true;
    }

    public void deleteProduct(int id){
        products.deleteHelper(id);
    }

    public void insertProduct(Product product){
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
