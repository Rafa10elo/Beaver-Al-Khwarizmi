package View;

import Model.Product;
import Model.Shipment;

import javax.swing.*;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class ShipmentPanel extends JPanel {
    Shipment shipment;
    public CustomDialog editShipmentDialog  ;
    public  JButton editButton;
    public  JButton deleteButton;
    public JButton confirmButton;

    public ShipmentPanel(Shipment shipment) {
        this.shipment = shipment;
        setBorder(BorderFactory.createMatteBorder(1, 10, 1, 1, MainFrame.red));
        setSize(new Dimension(300,100));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
        setBackground(MainFrame.white);

        add(Box.createRigidArea(new Dimension(150, 0)));
        add(createField("ID:", String.valueOf(shipment.getShipmentId()), MainFrame.FONT_BOLD.deriveFont(22F), MainFrame.FONT_BOLD.deriveFont(22F), MainFrame.black, MainFrame.black));
        add(Box.createRigidArea(new Dimension(100, 0)));
        add(createField("Destination:", shipment.getDestination(), MainFrame.FONT_BOLD.deriveFont(22F), MainFrame.FONT_LIGHT.deriveFont(22F), MainFrame.black, MainFrame.blue));
        add(Box.createRigidArea(new Dimension(100, 0)));
        add(createField("Price:", String.valueOf(shipment.getPrice()), MainFrame.FONT_BOLD.deriveFont(22F), MainFrame.FONT_LIGHT.deriveFont(22F), MainFrame.black, MainFrame.blue));
        add(Box.createRigidArea(new Dimension(100, 0)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        add(createField("Delivery Date:", shipment.getDeliveryDate().format(formatter), MainFrame.FONT_BOLD.deriveFont(22F), MainFrame.FONT_LIGHT.deriveFont(22F), MainFrame.black, MainFrame.blue));
        add(Box.createRigidArea(new Dimension(300, 0)));

        ImageIcon editIcon=new ImageIcon(getClass().getResource("Assets/edit.png"));
        Image scaledEditIcon = editIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledEditIcon);
        editButton = new JButton(scaledIcon);
        editButton.setBorderPainted(false);
        editButton.setContentAreaFilled(false);
        add(editButton);

        ImageIcon deleteIcon =new ImageIcon(getClass().getResource("Assets/delete.png"));
        Image scaledDeleteIcon = deleteIcon.getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH);
        ImageIcon scaleddIcon = new ImageIcon(scaledDeleteIcon);
        deleteButton = new JButton(scaleddIcon);
        deleteButton.setBorderPainted(false);
        deleteButton.setContentAreaFilled(false);
        add(deleteButton);
    }

    JPanel createField(String labelText, String valueText, Font labelFont, Font valueFont, Color labelColor, Color valueColor) {
        JPanel panel = new JPanel();
        panel.setOpaque(false); // inherit background
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel label = MainPanel.createLabel(labelText, labelFont, labelColor);
        JLabel value = MainPanel.createLabel(valueText, valueFont, valueColor);

        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        panel.add(label);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));
        panel.add(value);
        panel.add(Box.createRigidArea(new Dimension(0, 30)));
        return panel;
    }

    public CustomDialog createEditShipmentDialog () {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

        JTextField editDestination = new JTextField(shipment.getDestination());
        JTextField editPrice = new JTextField(String.valueOf(shipment.getPrice()));
        JTextField editDeliveryDate = new JTextField();
        JButton confirmButton = new JButton("okay");

        if(shipment.isPriority()){
            editShipmentDialog = new CustomDialog("Destination: ","Price: ","Days until Arrival: ",editDestination,editPrice,editDeliveryDate,confirmButton, frame);
            editShipmentDialog.setSize(new Dimension(400, 550));
            editShipmentDialog.setLocationRelativeTo(null);
            editShipmentDialog.setVisible(true);
            return editShipmentDialog;
        }
        else{

            editShipmentDialog = new CustomDialog(this,"Destination: ","Price: ",editDestination,editPrice,editDeliveryDate,confirmButton, frame);
            editShipmentDialog.setSize(new Dimension(400, 550));
            editShipmentDialog.setLocationRelativeTo(null);
            editShipmentDialog.setVisible(true);
            return editShipmentDialog;        }
    }

    public Shipment getShipment (){
        return shipment;
    }
}
