package repository;

import Model.Avl;
import Model.Product;

public class ProductRepo {
    Avl<Product> products = new Avl<>();

    public boolean updateProductPrice(int id, double newPrice) {
        Avl<Product> avl = new Avl<>();
        Product p = avl.searchHelper(id);
        if (p == null)
            return false;
        if (newPrice >= 0)
            p.setPrice(newPrice);
        return true;
    }

    public boolean updateProductQuantity(int id, int newQuantity) {
        Avl<Product> avl = new Avl<>();
        Product p = avl.searchHelper(id);
        if (p == null)
            return false;
        if (newQuantity >= 0 && newQuantity <= 1000)
            p.setQuantity(newQuantity);
        return true;
    }
}
