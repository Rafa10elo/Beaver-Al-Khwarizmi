package Control;

import Model.Product;
import View.CustomDialog;
import View.MainFrame;
import View.ProductPanel;
import View.ProductsPanel;
import repository.ProductRepo;

import javax.print.DocFlavor;
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
        ArrayList<Product> productList = products.getList();
        for (Product product : productList) {
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
            loadProducts();
            JOptionPane.showMessageDialog(null, "the product  "+ product.getProductName()+"  is deleted successfully", "done!", JOptionPane.PLAIN_MESSAGE);

        }
    };

    ActionListener addListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            editDialog = productsPanel.createAddProductDialog();

boolean done =true;

            if(editDialog.name.getText().length()<=2){
                JOptionPane.showMessageDialog(null, "the name of the product must have at least two chars", "invalid product name", JOptionPane.ERROR_MESSAGE);
                done=false;
                }
            if(!isFullyDouble(editDialog.price.getText())||Double.parseDouble(editDialog.price.getText())<=0 ) {
                JOptionPane.showMessageDialog(null, "the price of the product must be a positive number", "invalid product price", JOptionPane.ERROR_MESSAGE);
                done=false;
            }
            if(!isFullyInt(editDialog.amount.getText())||Integer.parseInt(editDialog.amount.getText())<1||Integer.parseInt(editDialog.amount.getText())>1000)
            {
                JOptionPane.showMessageDialog(null, "the amount of the product must be a number between 1-1000", "invalid product amount", JOptionPane.ERROR_MESSAGE);
                done = false;
            }
        if(done) {
            Product product = new Product(editDialog.name.getText(), Double.parseDouble(editDialog.price.getText()), Integer.parseInt(editDialog.amount.getText()));
            productsPanel.addProductPanel(product);
            products.insertProduct(product);
            JOptionPane.showMessageDialog(null, "the product is added successfully", "done!", JOptionPane.INFORMATION_MESSAGE);

        }
            loadProducts();
        }
    };

    public static boolean isFullyDouble(String input) {
        try {
            Double.parseDouble(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    public static boolean isFullyInt(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
    ActionListener editListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ProductPanel productPanel= (ProductPanel) ((JButton)e.getSource()).getParent();
            Product product = productPanel.getProduct();
            CustomDialog dialog = productPanel.createEditProductDialog();


            String s = product.getProductName() ;
            Double d  = product.getPrice();
            int a = product.getQuantity();

            boolean done= true;

            if(dialog.name.getText().length()>=2)
            product.setProductName(dialog.name.getText()) ;
            else {
                JOptionPane.showMessageDialog(null, "the name of the product must have at least two chars", "invalid product name", JOptionPane.ERROR_MESSAGE);
                done = false;
            }
            if(isFullyDouble(dialog.price.getText()) &&Double.parseDouble(dialog.price.getText())>0)
            products.updateProductPrice(product,Double.parseDouble(dialog.price.getText()));
            else{
            JOptionPane.showMessageDialog(null,"the price of the product must be a number" ,"invalid product price" , JOptionPane.ERROR_MESSAGE);
                done = false;

            }
            if (isFullyInt(dialog.amount.getText())&&Integer.parseInt(dialog.amount.getText())>=1&&Integer.parseInt(dialog.amount.getText())<=1000)
            products.updateProductQuantity(product,Integer.parseInt(dialog.amount.getText()));
            else {
                JOptionPane.showMessageDialog(null, "the amount of the product must be a positive number", "invalid product amount", JOptionPane.ERROR_MESSAGE);
                done = false;

            }
            if(done&&!s.equals(product.getProductName())||d!= product.getPrice()||a != product.getQuantity())
                JOptionPane.showMessageDialog(null, "the product is edited successfully", "done!", JOptionPane.INFORMATION_MESSAGE);



            loadProducts();
        }
    };

    ActionListener searchButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = productsPanel.search.getText().trim();
            //to get back all panels search "." (ill make it more user-friendly later)
            if(input.equals("")){
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
                ProductPanel p = productsPanel.addProductPanel(product);
                p.deleteButton.addActionListener(deleteListener);
                p.editButton.addActionListener(editListener);
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
