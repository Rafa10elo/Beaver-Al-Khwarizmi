package View;

import Model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.nio.channels.ClosedSelectorException;
import java.util.ArrayList;

public class AddShipmentDialog extends JDialog {
    public JTextField destField;
    public JTextField daysField;
    public JCheckBox checkBox;
    public JPanel productsPanel;
    public  JButton confirmButton;
    public ArrayList<MiniProductPanel> miniProductPanels = new ArrayList<>();

    public AddShipmentDialog(JTextField destField, JTextField daysField, JCheckBox checkBox,JFrame frame) {
        super(frame,true);
        this.destField = destField;
        this.daysField = daysField;
        this.checkBox = checkBox;
        this.setPreferredSize(new Dimension(1200,800));
        this.setLayout(new BorderLayout());

        JPanel titlePanel = new JPanel();
        titlePanel.setBackground(MainFrame.blue);
        titlePanel.setBorder(new MatteBorder(0,0,5,0,MainFrame.red));
        titlePanel.setPreferredSize(new Dimension(this.getWidth(),80));
        this.add(titlePanel,BorderLayout.NORTH);

        JPanel shipmentDetailPanel = createAddShipmentDetailsPanel();
        this.add(shipmentDetailPanel,BorderLayout.WEST);

        JPanel BorderPanel = new JPanel(new BorderLayout());
        BorderPanel.setBorder(new MatteBorder(0,3,0,0,MainFrame.dark_white));

        productsPanel = new JPanel() ;
        productsPanel.setLayout(new BoxLayout(productsPanel,BoxLayout.Y_AXIS));
        productsPanel.setBackground(MainFrame.white);
        productsPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        JScrollPane scrollProductMenu = new JScrollPane(productsPanel);
        scrollProductMenu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollBar verticalScrollBar = scrollProductMenu.getVerticalScrollBar();
        scrollProductMenu.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        verticalScrollBar.setUI(new BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = MainFrame.dark_white;
                this.trackColor = MainFrame.white;
                this.scrollBarWidth = 20;
            }
        });

        BorderPanel.add(scrollProductMenu,BorderLayout.CENTER);
        this.add(BorderPanel,BorderLayout.CENTER);

        JPanel footerBar = new JPanel();
        footerBar.setBorder(new EmptyBorder(15, 15, 15, 15));
        footerBar.setBackground(MainFrame.blue);
        confirmButton = createTextButton("add");
        footerBar.add(confirmButton);
        add(footerBar,BorderLayout.SOUTH);

        this.pack();
        this.setLocationRelativeTo(null);

    }

    JPanel createAddShipmentDetailsPanel(){
        JPanel panel = new JPanel();
        panel.setPreferredSize(new Dimension(400,getHeight()));

        JLabel label1 = createLabel("Enter Shipment Destination: ");
        JLabel label2 = createLabel("To Specify Days until Arrival ");
        JLabel label3 = createLabel("Upgrade to VIP");
        label3.setForeground(MainFrame.red);

        destField = makeEmLookNicer(destField);

        panel.setBackground(MainFrame.white);
        panel.setLayout(new BoxLayout(panel,BoxLayout.Y_AXIS));

        panel.setBorder(new EmptyBorder(20,25,40,25));
        panel.add(Box.createRigidArea(new Dimension(0,70)));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label1);
        panel.add(Box.createRigidArea(new Dimension(0,20)));
        panel.add(destField);
        panel.add(Box.createRigidArea(new Dimension(0,100)));
        label2.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(label2);
        panel.add(Box.createRigidArea(new Dimension(0,50)));


        JPanel setPriorityPanel = new JPanel();
        setPriorityPanel.setLayout(new BoxLayout(setPriorityPanel,BoxLayout.X_AXIS));
        setPriorityPanel.add(Box.createRigidArea(new Dimension(50,0)));
        setPriorityPanel.add(label3);
        setPriorityPanel.add(Box.createRigidArea(new Dimension(20,0)));
        setPriorityPanel.setBackground(MainFrame.white);
        checkBox = new JCheckBox();
        checkBox.setBackground(MainFrame.white);


        panel.add(setPriorityPanel);
        JLabel numberOfDaysLabel = createLabel("Days until Arrival : ");
        numberOfDaysLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        daysField = makeEmLookNicer(daysField);
        daysField.setAlignmentX(Component.CENTER_ALIGNMENT);
        daysField.setBackground(MainFrame.white);
        numberOfDaysLabel.setForeground(MainFrame.white);
        daysField.setEditable(false);
        daysField.setBorder(null);


        checkBox.addActionListener(e -> {
            daysField.setEditable(checkBox.isSelected());
            if(checkBox.isSelected()){
                numberOfDaysLabel.setForeground(MainFrame.blue);
                daysField.setBorder(UIManager.getBorder("TextField.border"));
                daysField.setBackground(MainFrame.dark_white);
            }
            else {

                daysField.setBorder(null);
                numberOfDaysLabel.setForeground(MainFrame.white);
                daysField.setBackground(MainFrame.white);
            }
            this.revalidate(); // Refresh layout
            this.repaint();    // Redraw panel
        });

        setPriorityPanel.add(checkBox);
        panel.add(Box.createRigidArea(new Dimension(0,50)));
        panel.add(numberOfDaysLabel);
        panel.add(daysField);
        panel.add(Box.createRigidArea(new Dimension(0,80)));

        return panel;
    }

    public void addProductPanel(Product product){
       MiniProductPanel productPanel = new MiniProductPanel(product);
       miniProductPanels.add(productPanel);
       productsPanel.add(productPanel);
    }

    JTextField makeEmLookNicer(JTextField textField){
        JTextField textField1 = textField;
        textField1.setFont(MainFrame.FONT_REGULAR.deriveFont(20f));
        textField1.setBackground(MainFrame.dark_white);
        textField1.setForeground(MainFrame.black);
        return textField1;
    }

    JLabel createLabel (String s){
        JLabel label = new JLabel(s);
        label.setForeground(MainFrame.blue);
        label.setFont(MainFrame.FONT_REGULAR.deriveFont(20f));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setAlignmentX(Component.RIGHT_ALIGNMENT);

        return label;
    }

    private JButton createTextButton(String text) {
        JButton button = new JButton(text);
        button.setFont(MainFrame.FONT_BOLD.deriveFont(23f));
        button.setFocusPainted(false);
        button.setBackground(MainFrame.dark_blue);
        button.setForeground(MainFrame.white);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }

    JPanel createField(String labelText, String valueText, Font labelFont, Font valueFont, Color labelColor, Color valueColor) {
        JPanel panel = new JPanel();
        panel.setOpaque(false);
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

    public class MiniProductPanel extends JPanel{
        public Product product;

        public JSpinner quantitySpinner;

        public MiniProductPanel(Product product) {
            this.product = product;
            setBorder(BorderFactory.createMatteBorder(1, 10, 1, 1, MainFrame.blue));
            setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            setBackground(MainFrame.white);


            add(Box.createRigidArea(new Dimension(60, 0)));
            add(createField("ID:          ", String.valueOf(product.getProductID()), MainFrame.FONT_BOLD.deriveFont(18F), MainFrame.FONT_BOLD.deriveFont(18F), MainFrame.black, MainFrame.black));
            add(createField("Name:          ", product.getProductName(), MainFrame.FONT_BOLD.deriveFont(18F), MainFrame.FONT_LIGHT.deriveFont(18F), MainFrame.black, MainFrame.blue));
            add(createField("Price:          ", String.valueOf(product.getPrice()), MainFrame.FONT_BOLD.deriveFont(18F), MainFrame.FONT_LIGHT.deriveFont(18F), MainFrame.black, MainFrame.blue));
            add(createField("Quantity:          ", String.valueOf(product.getQuantity()), MainFrame.FONT_BOLD.deriveFont(18F), MainFrame.FONT_LIGHT.deriveFont(18F), MainFrame.black, MainFrame.blue));
            add(Box.createRigidArea(new Dimension(40, 0)));

            JPanel panel = new JPanel();
            panel.setOpaque(false); // inherit background
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));


            JLabel label = MainPanel.createLabel("Order Quantity: ", MainFrame.FONT_BOLD.deriveFont(16f), MainFrame.red);
            quantitySpinner = new JSpinner(new SpinnerNumberModel(0, 0, product.getQuantity(), 1));

            JComponent editor = quantitySpinner.getEditor();
            JFormattedTextField textField = ((JSpinner.DefaultEditor) editor).getTextField();
            textField.setBackground(MainFrame.dark_white);
            textField.setForeground(MainFrame.dark_blue);
            textField.setBorder(BorderFactory.createLineBorder(MainFrame.white));
            textField.setEditable(false);
            quantitySpinner.setBorder(BorderFactory.createLineBorder(MainFrame.dark_white));

            panel.add(Box.createRigidArea(new Dimension(0, 30)));
            panel.add(label);
            panel.add(Box.createRigidArea(new Dimension(0, 10)));
            panel.add(quantitySpinner);
            panel.add(Box.createRigidArea(new Dimension(0, 30)));
            add(panel);
            add(Box.createRigidArea(new Dimension(60, 0)));


            SwingUtilities.invokeLater(() -> {
                Component[] components = quantitySpinner.getComponents();
                for (Component comp : components) {
                    if (comp instanceof JButton) {
                        JButton button = (JButton) comp;
                        button.setBackground(MainFrame.blue);
                        button.setForeground(MainFrame.white);
                        button.setBorder(BorderFactory.createLineBorder(MainFrame.blue));
                        button.setFocusable(false);
                    }
                }
            });

        }
    }

}

