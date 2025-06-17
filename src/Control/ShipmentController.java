package Control;
import Model.Product;
import Model.Shipment;
import View.*;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ShipmentController {

    ShipmentsPanel shipmentsPanel;
    ArrayList<ShipmentPanel> shipmentPanels;
    ArrayList<Shipment> shipments;


    public ShipmentController(ShipmentsPanel shipmentsPanel,ArrayList<ShipmentPanel> shipmentPanels,ArrayList<Shipment> shipments) {
        this.shipmentsPanel = shipmentsPanel;
        this.shipmentPanels = shipmentPanels;
        this.shipments = shipments;

        System.out.println("in shipController"+shipments.getFirst().getDestination());

        shipmentsPanel.addProductButton.addActionListener(addListener);
        shipmentsPanel.searchButton.addActionListener(searchButtonListener);
        loadShipments();

    }

    public void loadShipments() {
        shipmentsPanel.clearShipments();
        for (Shipment shipment : shipments) {
            System.out.println(shipment.getShipmentId());
            shipmentsPanel.addShipmentPanel(shipment);
        }
        for(ShipmentPanel shipmentPanel: shipmentPanels){
            shipmentPanel.deleteButton.addActionListener(deleteListener);
            shipmentPanel.editButton.addActionListener(editListener);
        }
    }

    ActionListener deleteListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ShipmentPanel shipmentPanel= (ShipmentPanel) (((JButton)e.getSource()).getParent());
            Shipment shipment = shipmentPanel.getShipment();

            shipments.remove(shipment);
            shipmentPanels.remove(shipmentPanel);
            System.out.println(shipment.getShipmentId());
            loadShipments();
            System.out.println("delete");

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
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
            shipment.setDeliveryDate(LocalDate.parse(dialog.amount.getText(),formatter));
            shipmentPanel.repaint();
            loadShipments();
            System.out.println("edit");
        }
    };


    ActionListener addListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            CustomDialog dialog = shipmentsPanel.createAddShipment();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            Shipment shipment = new Shipment(ShipmentsPanel.cnt++,dialog.name.getText(),Double.parseDouble(dialog.price.getText()));

            shipmentsPanel.addShipmentPanel(shipment);
            shipments.add(shipment);
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

            for (Shipment shipment: shipments){
                if(id==shipment.getShipmentId()){
                    shipmentsPanel.clearShipments();
                    shipmentsPanel.addShipmentPanel(shipment);
                    break;
                }
                else {
                    shipmentsPanel.clearShipments();
                    JLabel notFoundLabel = new JLabel("sorry, shipment not found :(");
                    notFoundLabel.setFont(MainFrame.FONT_REGULAR);
                    notFoundLabel.setForeground(MainFrame.dark_blue);
                    shipmentsPanel.addToProductsPanel(notFoundLabel);


                }
            }
        }
    };


}


