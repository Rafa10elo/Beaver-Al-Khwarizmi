package Control;

import Model.Product;
import Model.Shipment;
import View.ReportPanel;
import repository.ProductRepo;
import repository.ShipmentRepo;

import java.util.ArrayList;

public class ReportController {
    ProductRepo<Product> products;
    ShipmentRepo shipments;
    ReportPanel reportPanel;

    public ReportController(ReportPanel reportPanel, ProductRepo<Product> products, ShipmentRepo shipments) {
        this.products = products;
        this.shipments = shipments;
        this.reportPanel = reportPanel;

        addData();
        reportPanel.revalidate();
        reportPanel.repaint();
    }

    void loadHighValShipments(){
        ArrayList<Shipment> shipmentArrayList= shipments.mostExpensiveShipment();
        for (Shipment shipment : shipmentArrayList) {
            reportPanel.addShipmentToHighValPanel(shipment);
        }
    }

    void addData(){
        reportPanel.clearHighValShipments();
        loadHighValShipments();
        reportPanel.clearMostPriorityShipment();
        reportPanel.addPriorityShipment(shipments.theMostPriorityShipment());
        String allCosts = String.valueOf(shipments.allCosts());
        String InventoryValue = String.valueOf(products.InventoryValue());
        int totalProd = products.getList().size();
        int totalShip = shipments.getList().size();
        String totalProducts = String.valueOf(totalProd);
        String totalShipments = String.valueOf(totalShip);
        reportPanel.addReportDetailsToPanel(allCosts,InventoryValue,totalShipments,totalProducts);
        reportPanel.revalidate();
        reportPanel.repaint();
    }

}
