package Control;

import Model.Avl;
import Model.Product;
import repository.ProductRepo;

public class ProductController {
    Avl<Product> avl;

    public ProductController(Avl<Product> avl) {
        this.avl = avl;
    }

    ProductRepo productRepo=new ProductRepo(avl);
    //am I stupid because I don't understand what's the matter with this?
}
