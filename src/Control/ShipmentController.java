package Control;
import Model.Product;
import Model.Shipment;
import View.*;
import repository.ProductRepo;
import repository.ShipmentRepo;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ShipmentController {
    ShipmentsPanel shipmentsPanel;
    ArrayList<ShipmentPanel> shipmentPanels;
    ProductRepo<Product> products;
    ShipmentRepo shipments;

    public ShipmentController(ShipmentsPanel shipmentsPanel, ArrayList<ShipmentPanel> shipmentPanels, ProductRepo<Product> products, ShipmentRepo shipments) {
        this.shipmentsPanel = shipmentsPanel;
        this.shipmentPanels = shipmentPanels;
        this.products = products;
        this.shipments = shipments;

        shipmentsPanel.addProductButton.addActionListener(addListener);
        shipmentsPanel.searchButton.addActionListener(searchButtonListener);
        loadShipments();

    }

    public void loadShipments() {
        System.out.println("size11"+shipmentPanels.size());

        shipments.expiredShipments();
        shipmentsPanel.clearShipments();
        ArrayList<Shipment> shipmentArrayList=shipments.getList();
        for (Shipment shipment : shipmentArrayList) {
            shipmentsPanel.addShipmentPanel(shipment);
        }
        for(ShipmentPanel shipmentPanel: shipmentPanels){
            shipmentPanel.deleteButton.addActionListener(deleteListener);
            shipmentPanel.editButton.addActionListener(editListener);
        }
        System.out.println("size"+shipmentPanels.size());
    }


    public void addProductsToAddShipmentDialog(){

    }

    ActionListener deleteListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShipmentPanel shipmentPanel= (ShipmentPanel) (((JButton)e.getSource()).getParent());
            Shipment shipment = shipmentPanel.getShipment();

            shipments.delete(shipment.getShipmentId());
            shipmentPanels.remove(shipmentPanel);
            loadShipments();

        }
    };

    ActionListener editListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            ShipmentPanel shipmentPanel= (ShipmentPanel) ((JButton)e.getSource()).getParent();
            Shipment shipment = shipmentPanel.getShipment();
            CustomDialog dialog = shipmentPanel.createEditShipmentDialog();
            shipment.setDestination(dialog.name.getText()) ;
            shipment.setPrice(Double.parseDouble(dialog.price.getText())) ;
            if(shipment.isPriority()){
                shipments.demoteFromVip(shipment);
                loadShipments();//this is a hopeless trial to make it work
            }
            else
            {
                if(dialog.checkBox.isSelected()){
                    shipments.promoteToVip(shipment,Integer.parseInt(dialog.numberOfDays.getText()));
                    System.out.println("i wanna dia "+Integer.parseInt(dialog.numberOfDays.getText()));
                    loadShipments();
                }
            }
            shipmentPanel.repaint();
            loadShipments();
            System.out.println("edit");
        }
    };

    public void soso(Double total , double james ,int quantity){
        total+= james*quantity;


        System.out.println("gorj  " +total);

    }
    ActionListener addListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            //idk why i'm alive atp
            AddShipmentDialog dialog = shipmentsPanel.createAddShipment();

            for(Product p: products.getList()){
                dialog.addProductPanel(p);
                dialog.productsPanel.add(Box.createRigidArea(new Dimension(0,10)));
            }

            Double []totalCost=new Double[]{0.0};
            Shipment []shipment = new Shipment[1];


            dialog.confirmButton.addActionListener(e1->{
                dialog.dispose();
                for(AddShipmentDialog.MiniProductPanel miniProductPanel: dialog.miniProductPanels){
                    products.updateProductQuantity(miniProductPanel.product,miniProductPanel.product.getQuantity()-(int)miniProductPanel.quantitySpinner.getValue());
//                    System.out.println("AAAAAAAAAAAAAAAAAAA "+(int)miniProductPanel.quantitySpinner.getValue());
                }
                for(AddShipmentDialog.MiniProductPanel miniProductPanel: dialog.miniProductPanels){
//                    System.out.println("2323  "+   (int)miniProductPanel.quantitySpinner.getValue()+ " " + miniProductPanel.product.getPrice());
                totalCost[0] += miniProductPanel.product.getPrice()*(int)miniProductPanel.quantitySpinner.getValue();
                    System.out.println(totalCost[0]);
                }
                shipment[0] = new Shipment(dialog.destField.getText(),totalCost[0]);

                System.out.println("disposing dialog");
            });



            dialog.setVisible(true);
            shipmentsPanel.addShipmentPanel(shipment[0]);
            shipments.insert(shipment[0]);
            loadShipments();
        }
    };


    ActionListener searchButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            String input = shipmentsPanel.search.getText().trim();
            //to get back all panels search "." (ill make it more user-friendly later)
            if(input.equals(".")){
                System.out.println("im in dot");
                shipmentsPanel.clearShipments();
                loadShipments();
                return;
            }
            else if (!input.matches("\\d+")) {
                JOptionPane.showMessageDialog(shipmentsPanel, "Please enter a valid integer ID", "Invalid Input", JOptionPane.ERROR_MESSAGE);
                return;

            }

            int id = Integer.parseInt(input);

            Shipment shipment=shipments.searchShipment(id);
            if(shipment!=null){
                shipmentsPanel.clearShipments();
                shipmentsPanel.addShipmentPanel(shipment);
            }
            else {
                shipmentsPanel.clearShipments();
                JLabel notFoundLabel = new JLabel("sorry, shipment not found :(");
                notFoundLabel.setFont(MainFrame.FONT_REGULAR);
                notFoundLabel.setForeground(MainFrame.dark_blue);
                shipmentsPanel.addToProductsPanel(notFoundLabel);

                }
            }
    };

}


