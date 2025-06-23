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
        loadHighValShipments();
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
        System.out.println("jimmy ain't working");
        String allCosts = String.valueOf(shipments.allCosts());
        String InventoryValue = String.valueOf(products.InventoryValue());
        int totalProd = products.getList().size();
        int totalShip = shipments.getList().size();
        String totalProducts = String.valueOf(totalProd);
        String totalShipments = String.valueOf(totalShip);

        System.out.println(totalProd);
        reportPanel.addReportDetailsToPanel(allCosts,InventoryValue,totalShipments,totalProducts);
        reportPanel.revalidate();
        reportPanel.repaint();
    }

}
