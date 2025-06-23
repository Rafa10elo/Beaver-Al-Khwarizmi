package Control;

import Model.Product;
import View.CustomDialog;
import View.MainFrame;
import View.ProductPanel;
import View.ProductsPanel;
import repository.ProductRepo;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ProductController {
    ProductsPanel productsPanel;
    ArrayList<ProductPanel> productPanels;
    ProductRepo<Product> products;
    CustomDialog editDialog;


    public ProductController(ProductsPanel productsPanel, ProductRepo<Product> products,ArrayList<ProductPanel> productPanels) {
        this.productsPanel = productsPanel;
        this.products = products;
        this.productPanels = productPanels;
        productsPanel.addProductButton.addActionListener(addListener);
        productsPanel.searchButton.addActionListener(searchButtonListener);

        loadProducts();
    }

    public void loadProducts() {
        productsPanel.clearProducts();
        ArrayList<Product> james = products.getList();

        for (Product product : james) {
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

            products.deleteProduct(product.getProductID());
            productPanels.remove(productPanel);

            System.out.println(products.getList().size());
            loadProducts();
        }
    };

    ActionListener addListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            editDialog = productsPanel.createAddProductDialog();
            Product product = new Product(editDialog.name.getText(),Double.parseDouble(editDialog.price.getText()),Integer.parseInt(editDialog.amount.getText()));

            productsPanel.addProductPanel(product);
            products.insertProduct(product);

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
            products.updateProductPrice(product,Double.parseDouble(dialog.price.getText()));
            products.updateProductQuantity(product,Integer.parseInt(dialog.amount.getText()));
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

            Product product=products.searchProduct(id);
            if(product!=null){
                productsPanel.clearProducts();
                productsPanel.addProductPanel(product);
            }
            else {
                productsPanel.clearProducts();
                JLabel notFoundLabel = new JLabel("sorry, product not found :(");
                notFoundLabel.setFont(MainFrame.FONT_REGULAR);
                notFoundLabel.setForeground(MainFrame.dark_blue);
                productsPanel.addToProductsPanel(notFoundLabel);


            }
        }
    };

}
