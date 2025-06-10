import Model.Avl;
import Model.Product;
import Model.Shipment;

import javax.swing.text.html.Option;
import java.util.Optional;

public class Main {
    public static void main(String[] args) {

        Avl<Product> productAvl = new Avl<Product>();

        Product product1 = new Product(1);
        Product product2 = new Product(2);
        Product product3 = new Product(3);

        Product product4 = new Product(4);

        Product product5 = new Product(5);
        Product product7 = new Product(19);
        Product product8 = new Product(20);

        Product product9 = new Product(22700);


        Optional<Product> op = Optional.of(product7);

        op.ifPresent(name -> System.out.println(name));
        System.out.println(op.isEmpty());




        productAvl.insertHelper(product1);
        productAvl.insertHelper(product2);
        productAvl.insertHelper(product3);
        productAvl.insertHelper(product4);
        productAvl.insertHelper(product5);
        productAvl.insertHelper(product7);
        productAvl.insertHelper(product8);
        productAvl.insertHelper(product9);



        productAvl.inorderHelper();

        productAvl.print();

    }
}