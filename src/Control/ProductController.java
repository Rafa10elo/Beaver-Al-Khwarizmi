//package Control;
//
//import Model.Avl;
//import Model.Product;
//import repository.ProductRepo;
//
//public class ProductController {
//    Avl<Product> avl;
//
//    public ProductController(Avl<Product> avl) {
//        this.avl = avl;
//    }
//
//    ProductRepo productRepo=new ProductRepo(avl);
//    //am I stupid because I don't understand what's the matter with this?
//}
package Control;

import Model.Avl;
import Model.Product;
import View.CustomDialog;
import View.MainFrame;
import View.ProductPanel;
import View.ProductsPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductController {
    Avl<Product> avl;
    ProductsPanel productsPanel;
    ArrayList<ProductPanel> productPanels;
    ArrayList<Product> products;
    CustomDialog editDialog;


    public ProductController(ProductsPanel productsPanel, ArrayList<Product> products,ArrayList<ProductPanel> productPanels) {
        this.productsPanel = productsPanel;
        this.products = products;
        this.productPanels = productPanels;
        productsPanel.addProductButton.addActionListener(addListener);
        productsPanel.searchButton.addActionListener(searchButtonListener);
        loadProducts();
    }

    public void loadProducts() {
        productsPanel.clearProducts();
        for (Product product : products) {
            productsPanel.addProductPanel(product);
        }
        for(ProductPanel productPanel: productPanels){
            productPanel.deleteButton.addActionListener(deleteListener);
            productPanel.editButton.addActionListener(editListener);
        }
    }

    public ArrayList<ProductPanel> getProductPanels(){
        return productPanels;
    }

    ActionListener deleteListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductPanel productPanel= (ProductPanel) (((JButton)e.getSource()).getParent());
            Product product = productPanel.getProduct();

            products.remove(product);
            productPanels.remove(productPanel);

            loadProducts();
        }
    };


    ActionListener addListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            editDialog = productsPanel.createAddProductDialog();
            Product product = new Product(ProductsPanel.cnt++,editDialog.name.getText(),Double.parseDouble(editDialog.price.getText()),Integer.parseInt(editDialog.amount.getText()));

            productsPanel.addProductPanel(product);
            products.add(product);

            loadProducts();

        }
    };



    ActionListener editListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductPanel productPanel= (ProductPanel) ((JButton)e.getSource()).getParent();
            Product product = productPanel.getProduct();
            CustomDialog dialog = productPanel.createEditProductDialog();
            product.setProductName(dialog.name.getText()) ;
            product.setPrice(Double.parseDouble(dialog.price.getText())) ;
            product.setQuantity(Integer.parseInt(dialog.amount.getText()));
            loadProducts();
        }
    };


    ActionListener searchButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = productsPanel.search.getText().trim();
            //to get back all panels search "." (ill make it more user-friendly later)
            if(input.equals(".")){
                System.out.println("im in dot");
                productsPanel.clearProducts();
                loadProducts();
                return;
            }
            else if (!input.matches("\\d+")) {
                JOptionPane.showMessageDialog(productsPanel, "Please enter a valid integer ID", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;

            }

            int id = Integer.parseInt(input);

            for (Product product: products){
                if(id==product.getProductID()){
                    productsPanel.clearProducts();
                    productsPanel.addProductPanel(product);
                    break;
                }
                else {
                    productsPanel.clearProducts();
                    JLabel notFoundLabel = new JLabel("sorry, product not found :(");
                    notFoundLabel.setFont(MainFrame.FONT_REGULAR);
                    notFoundLabel.setForeground(MainFrame.dark_blue);
                    productsPanel.addToProductsPanel(notFoundLabel);


                }
            }
        }
    };






}
