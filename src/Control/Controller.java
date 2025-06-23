package Control;

import Model.Avl;
import Model.Heap;
import Model.Product;
import Model.Shipment;
import View.*;
import repository.ProductRepo;
import repository.ShipmentRepo;

import java.time.LocalDate;
import java.util.*;

public class Controller {
    private ShipmentRepo shipmentList;
    private Avl<Product> productList;
    private ProductRepo<Product> productProductRepo;
    private Avl<Shipment> shipmentAvl;
    private Heap shimp;
    private ShipmentRepo shipmentRepo;

    private MainFrame mainFrame;
    private ShipmentsPanel shipmentsPanel;
    private ProductsPanel productsPanel;
    private ReportPanel reportPanel;

    private ProductController productController;
    private ShipmentController shipmentController;
    private ReportController reportController;

    private MainPanel mainPanel;
    private ArrayList<ProductPanel> productPanels = new ArrayList<>();
    private ArrayList<ShipmentPanel> shipmentPanels = new ArrayList<>();


    public Controller() {

        shipmentAvl = new Avl<>(Shipment::getShipmentId);
        shimp = new Heap();
        shipmentRepo = new ShipmentRepo(shimp,shipmentAvl);
        shipmentRepo.insert(new Shipment( "Paris", 100.5));
        shipmentRepo.insert(new Shipment( "Berlin", 150.0));
        shipmentRepo.insert(new Shipment(3, "Paris", 100.5));
        shipmentRepo.insert(new Shipment(4, "Berlin", 150.0));
        shipmentRepo.insert(new Shipment(5, "Paris", 100.5));
        shipmentRepo.insert(new Shipment(6, "Berlin", 150.0));


        productList = new Avl<Product>(Product::getProductID);
        productList.insertHelper(new Product("Chair", 45.0, 10));
        productList.insertHelper(new Product("Table", 90.0, 5));
        productList.insertHelper(new Product("Chair", 45.0, 10));
        productList.insertHelper(new Product("Table", 90.0, 5));
        productList.insertHelper(new Product("Chair", 45.0, 10));
        productList.insertHelper(new Product("Table", 90.0, 5));


        productProductRepo  = new ProductRepo<>(productList);
        productProductRepo.insertProduct(new Product("Table", 90.0, 5));
        shipmentsPanel = new ShipmentsPanel(shipmentPanels);
        productsPanel = new ProductsPanel(productPanels);
        reportPanel = new ReportPanel();


        productController = new ProductController(productsPanel,productProductRepo,productPanels);
        shipmentController = new ShipmentController(shipmentsPanel,shipmentPanels,productProductRepo,shipmentRepo);
        reportController = new ReportController(reportPanel,productProductRepo,shipmentRepo);


        mainPanel = new MainPanel();
        productsPanel.backButton.addActionListener(e -> MainFrame.showPanel("MAIN"));
        shipmentsPanel.backButton.addActionListener(e -> MainFrame.showPanel("MAIN"));
        reportPanel.backButton.addActionListener(e -> MainFrame.showPanel("MAIN"));

        mainFrame = new MainFrame(shipmentsPanel, productsPanel,reportPanel);
        mainPanel.productsButton.addActionListener(e -> MainFrame.showPanel("PRODUCTS"));
        mainPanel.shipmentsButton.addActionListener(e -> MainFrame.showPanel("SHIPMENTS"));
        mainPanel.reportButton.addActionListener(e -> {reportController.addData();
            MainFrame.showPanel("REPORT");});
        mainFrame.setVisible(true);
    }

    public void showPanel(String name) {
        mainFrame.showPanel(name);
    }

}

