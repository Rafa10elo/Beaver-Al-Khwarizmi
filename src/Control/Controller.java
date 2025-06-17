package Control;

import Model.Avl;
import Model.Product;
import Model.Shipment;
import View.*;
import repository.ProductRepo;

import java.time.LocalDate;
import java.util.*;

public class Controller {
    private ArrayList<Shipment> shipmentList;
    private Avl<Product> productList;
    private ProductRepo<Product> productProductRepo;

    private MainFrame mainFrame;
    private ShipmentsPanel shipmentsPanel;
    private ProductsPanel productsPanel;

    private ProductController productController;
    private ShipmentController shipmentController;

    private MainPanel mainPanel;
    private ArrayList<ProductPanel> productPanels = new ArrayList<>();
    private ArrayList<ShipmentPanel> shipmentPanels = new ArrayList<>();


    public Controller() {

        shipmentList = new ArrayList<>();
        shipmentList.add(new Shipment(1, "Paris", 100.5));
        shipmentList.add(new Shipment(2, "Berlin", 150.0));
        shipmentList.add(new Shipment(3, "Paris", 100.5));
        shipmentList.add(new Shipment(4, "Berlin", 150.0));
        shipmentList.add(new Shipment(5, "Paris", 100.5));
        shipmentList.add(new Shipment(6, "Berlin", 150.0));
        System.out.println("im offing myself "+shipmentList.getFirst().getShipmentId());


        productList = new Avl<Product>(Product::getProductID);
        productList.insertHelper(new Product(1, "Chair", 45.0, 10));
        productList.insertHelper(new Product(2, "Table", 90.0, 5));
        productList.insertHelper(new Product(3, "Chair", 45.0, 10));
        productList.insertHelper(new Product(4, "Table", 90.0, 5));
        productList.insertHelper(new Product(5, "Chair", 45.0, 10));
        productList.insertHelper(new Product(6, "Table", 90.0, 5));


        productProductRepo  = new ProductRepo<>(productList);
        productProductRepo.insertProduct(new Product(7, "Table", 90.0, 5));
        shipmentsPanel = new ShipmentsPanel(shipmentPanels);
        productsPanel = new ProductsPanel(productPanels);


        productController = new ProductController(productsPanel,productProductRepo,productPanels);
        shipmentController = new ShipmentController(shipmentsPanel,shipmentPanels,shipmentList);

        mainPanel = new MainPanel();
        productsPanel.backButton.addActionListener(e -> MainFrame.showPanel("MAIN"));
        shipmentsPanel.backButton.addActionListener(e -> MainFrame.showPanel("MAIN"));

        mainFrame = new MainFrame(shipmentsPanel, productsPanel);
        mainPanel.productsButton.addActionListener(e -> MainFrame.showPanel("PRODUCTS"));
        mainPanel.shipmentsButton.addActionListener(e -> MainFrame.showPanel("SHIPMENTS"));
        mainFrame.setVisible(true);
    }

    public void showPanel(String name) {
        mainFrame.showPanel(name);
    }

}

