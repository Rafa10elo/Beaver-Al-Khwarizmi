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
            if(shipment.isPriority()){
                if(dialog.checkBox.isSelected()){
                    shipments.demoteFromVip(shipment);
                }
                else{
                    if(dialog.amount.getText()!=null){
                        shipments.rescheduleShipment(shipment,Integer.parseInt(dialog.amount.getText()));
                    }
                }
                loadShipments();//this is a hopeless trial to make it work
            }
            else
            {
                if(dialog.checkBox.isSelected()){
                    shipments.promoteToVip(shipment,Integer.parseInt(dialog.numberOfDays.getText()));
                    loadShipments();
                }
            }
            shipmentPanel.repaint();
            loadShipments();
        }
    };

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

                }
                for(AddShipmentDialog.MiniProductPanel miniProductPanel: dialog.miniProductPanels){
                totalCost[0] += miniProductPanel.product.getPrice()*(int)miniProductPanel.quantitySpinner.getValue();
                }
                if(dialog.checkBox.isSelected()){
                    shipment[0] = new Shipment(Integer.parseInt(dialog.daysField.getText()),dialog.destField.getText(),totalCost[0]);
                }
                else
                    shipment[0] = new Shipment(dialog.destField.getText(),totalCost[0]);
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
                ShipmentPanel p =shipmentsPanel.addShipmentPanel(shipment);
                p.deleteButton.addActionListener(deleteListener);
                p.editButton.addActionListener(editListener);
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


