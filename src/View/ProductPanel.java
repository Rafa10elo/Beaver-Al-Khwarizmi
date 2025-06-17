package View;

import Model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class ProductPanel extends JPanel{
    Product product;
    public CustomDialog editProductDialog  ;
    public  JButton editButton;
    public  JButton deleteButton;
//    public JButton confirmButton;


    public ProductPanel(Product product) {

        this.product = product;
        setBorder(BorderFactory.createMatteBorder(1, 10, 1, 1, MainFrame.red));
//        setSize(new Dimension(300,100));
        setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
//        setLayout(new GridBagLayout());
        setBackground(MainFrame.white);


        add(Box.createRigidArea(new Dimension(150, 0)));
        add(createField("ID:", String.valueOf(product.getProductID()), MainFrame.FONT_BOLD.deriveFont(22F), MainFrame.FONT_BOLD.deriveFont(22F), MainFrame.black, MainFrame.black));
        add(Box.createRigidArea(new Dimension(100, 0)));
        add(createField("Name:", product.getProductName(), MainFrame.FONT_BOLD.deriveFont(22F), MainFrame.FONT_LIGHT.deriveFont(22F), MainFrame.black, MainFrame.blue));
        add(Box.createRigidArea(new Dimension(100, 0)));
        add(createField("Price:", String.valueOf(product.getPrice()), MainFrame.FONT_BOLD.deriveFont(22F), MainFrame.FONT_LIGHT.deriveFont(22F), MainFrame.black, MainFrame.blue));
        add(Box.createRigidArea(new Dimension(100, 0)));
        add(createField("Quantity:", String.valueOf(product.getQuantity()), MainFrame.FONT_BOLD.deriveFont(22F), MainFrame.FONT_LIGHT.deriveFont(22F), MainFrame.black, MainFrame.blue));
        add(Box.createRigidArea(new Dimension(300, 0)));


        ImageIcon editIcon=new ImageIcon(getClass().getResource("Assets/edit.png"));
        Image scaledEditIcon = editIcon.getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledEditIcon);
        editButton = new JButton(editIcon);
        editButton.setBorderPainted(false);
        editButton.setContentAreaFilled(false);
        add(editButton);


        ImageIcon deleteIcon =new ImageIcon(getClass().getResource("Assets/delete.png"));
        Image scaledDeleteIcon = deleteIcon.getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT);

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

    public CustomDialog createEditProductDialog () {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

        System.out.println("clicked");
        JTextField editName = new JTextField(product.getProductName());
        JTextField editPrice = new JTextField(String.valueOf(product.getPrice()));
        JTextField editAmount = new JTextField(String.valueOf(product.getQuantity()));
        JButton confirmButton = new JButton("okay");

        editProductDialog = new CustomDialog(this,"Name: ","Price: ","Quantity: ",editName,editPrice,editAmount,confirmButton, frame);
        editProductDialog.setSize(new Dimension(400, 550));

        editProductDialog.setLocationRelativeTo(null);
        editProductDialog.setVisible(true);
         return editProductDialog;

    }

    public Product getProduct(){
        return product;
    }

}
