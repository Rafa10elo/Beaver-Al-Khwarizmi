package repository;

import Model.Avl;
import Model.Product;

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

}
