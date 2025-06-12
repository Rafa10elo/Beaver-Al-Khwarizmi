import Control.ProductController;
import Model.Avl;
import Model.Product;
import repository.ProductRepo;

public class Main {
    public static void main(String[] args) {
//        Avl<Product> productAvl = new Avl<Product>();
//        Product product1 = new Product(1);
//        Product product2 = new Product(2);
//        Product product3 = new Product(3);
//        Product product4 = new Product(4);
//        Product product5 = new Product(5);
//        Product product7 = new Product(19);
//        Product product8 = new Product(20);
//        Product product9 = new Product(22700);
//        productAvl.insertHelper(product1);
//        productAvl.insertHelper(product2);
//        productAvl.insertHelper(product3);
//        productAvl.insertHelper(product4);
//        productAvl.insertHelper(product5);
//        productAvl.insertHelper(product7);
//        productAvl.insertHelper(product8);
//        productAvl.insertHelper(product9);
//        productAvl.inorderHelper();
//        Optional<Product> op = Optional.of(product7);
//        op.ifPresent(name -> System.out.println(name));
//        System.out.println(op.isEmpty());
//        productAvl.print();
        Avl<Product> avl = new Avl<>(Product::getProductID);
        ProductController pc=new ProductController(avl);
//        Avl<Product> avl = new Avl<>(new KeyExtractor<Product, Integer>() {
//            @Override
//            public Integer getKey(Product p) {
//                return p.getProductID();
//            }
//        });
        Product product1 = new Product(1);
        ProductRepo productRepo=new ProductRepo(avl);
        avl.insertHelper(product1);
        productRepo.insertProduct(new Product(10, "A", 5.0, 100));
        avl.insertHelper(new Product(20, "B", 10.0, 200));
        avl.insertHelper(new Product(30, "C", 15.0, 300));
        avl.insertHelper(new Product(25, "D", 20.0, 400));
        avl.insertHelper(new Product(5, "E", 3.0, 50));
        avl.insertHelper(new Product(8, "F", 7.0, 60));
        avl.inorderHelper();
        avl.print();
        System.out.println("----------");
        System.out.println(productRepo.searchProduct(5));
        productRepo.deleteProduct(8);
        System.out.println("----------");
        avl.inorderHelper();
        avl.print();
        System.out.println("-----------");
        System.out.println(product1.getPrice());
        productRepo.updateProductPrice(1,25.0);
        System.out.println(product1.getPrice());
        System.out.println("-----------");
        System.out.println(product1.getQuantity());
        productRepo.updateProductQuantity(1,5);
        System.out.println(product1.getQuantity());



    }
}