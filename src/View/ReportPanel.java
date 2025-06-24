package View;

import Model.Shipment;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;
import java.time.format.DateTimeFormatter;

public class ReportPanel extends JPanel {
    public JButton backButton;

    public JPanel highValProductsPanel;
    public JPanel reportDetailsPanel;



    public ReportPanel() {
        setLayout(new BorderLayout());
        // ---- top navigation bar
        JPanel navigationBar = new JPanel();
        navigationBar.setBorder(new EmptyBorder(25, 15, 25, 15));
        navigationBar.setLayout(new BoxLayout(navigationBar,BoxLayout.X_AXIS));
        navigationBar.setBackground(MainFrame.blue);
        backButton = createTextButton("back");
        navigationBar.add(Box.createRigidArea(new Dimension(10,0)));
        navigationBar.add(backButton);
        navigationBar.add(Box.createRigidArea(new Dimension(100,0)));
        add(navigationBar,BorderLayout.NORTH);





        JPanel BorderPanel = new JPanel(new BorderLayout());
        BorderPanel.setBorder(new MatteBorder(0,3,0,0,MainFrame.dark_white));

        JPanel highValPanel = new JPanel();
        JLabel highValLabel = createLabel("High Value Shipments: ");
        highValPanel.setLayout(new BoxLayout(highValPanel,BoxLayout.X_AXIS));
        highValPanel.setBorder(new EmptyBorder(55,60,55,60));
        highValPanel.setBackground(MainFrame.white);
        highValPanel.add(highValLabel);


        BorderPanel.add(highValPanel,BorderLayout.NORTH);


        highValProductsPanel = new JPanel() ;
        highValProductsPanel.setLayout(new BoxLayout(highValProductsPanel,BoxLayout.Y_AXIS));
        highValProductsPanel.setBackground(MainFrame.white);
        highValProductsPanel.setBorder(new EmptyBorder(30, 30, 30, 30));

        JScrollPane scrollProductMenu = new JScrollPane(highValProductsPanel);
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
        footerBar.setBorder(new EmptyBorder(25, 15, 15, 15));
        footerBar.setBackground(MainFrame.blue);
        add(footerBar,BorderLayout.SOUTH);
    }


    JPanel createReportDetailsPanel(String allCosts,String InventoryValue, String totalShipments, String totalProducts){
        System.out.println("i will really off myself "+ totalProducts);
        JPanel reportDetailsPanel = new JPanel();
        reportDetailsPanel.setPreferredSize(new Dimension(400,getHeight()));
        reportDetailsPanel.setBackground(MainFrame.white);
        reportDetailsPanel.setLayout(new BoxLayout(reportDetailsPanel,BoxLayout.Y_AXIS));
        reportDetailsPanel.setBorder(new EmptyBorder(30,80,40,50));

        JPanel allShipCostsPanel = createField("Total Shipments Cost: ",allCosts,MainFrame.FONT_BOLD.deriveFont(20f),MainFrame.FONT_REGULAR.deriveFont(20f),MainFrame.black,MainFrame.dark_blue);
        JPanel allProdCostsPanel = createField("Total Inventory Cost: ",InventoryValue,MainFrame.FONT_BOLD.deriveFont(20f),MainFrame.FONT_REGULAR.deriveFont(20f),MainFrame.black,MainFrame.dark_blue);
        JPanel allShipments = createField("Total Shipments Made: ",totalShipments,MainFrame.FONT_BOLD.deriveFont(20f),MainFrame.FONT_REGULAR.deriveFont(20f),MainFrame.black,MainFrame.dark_blue);
        JPanel allProducts = createField("Total Products: ",totalProducts,MainFrame.FONT_BOLD.deriveFont(20f),MainFrame.FONT_REGULAR.deriveFont(20f),MainFrame.black,MainFrame.dark_blue);

        reportDetailsPanel.add(allShipCostsPanel);
        reportDetailsPanel.add(allProdCostsPanel);
        reportDetailsPanel.add(allShipments);
        reportDetailsPanel.add(allProducts);

        return reportDetailsPanel;
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

    private JButton createTextButton(String text) {
        JButton button = new JButton(text);
        button.setFont(MainFrame.FONT_BOLD.deriveFont(20f));
        button.setFocusPainted(false);
        button.setBackground(MainFrame.dark_blue);
        button.setForeground(MainFrame.white);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        button.setBorder(new FlatBorder());
        return button;
    }


    JLabel createLabel (String s){
        JLabel label = new JLabel(s);
        label.setForeground(MainFrame.black);
        label.setFont(MainFrame.FONT_BOLD.deriveFont(20f));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        label.setAlignmentX(Component.CENTER_ALIGNMENT);

        return label;
    }


    public void addShipmentToHighValPanel (Shipment shipment){
        JPanel shipmentPanel = createReportShipmentPanel(shipment);
        highValProductsPanel.add(shipmentPanel);
        highValProductsPanel.add(Box.createRigidArea(new Dimension(0,15)));
    }



    JPanel createReportShipmentPanel(Shipment shipment){
        JPanel panel= new JPanel();
        panel.setBorder(BorderFactory.createMatteBorder(1, 10, 1, 1, MainFrame.red));
        panel.setSize(new Dimension(300,100));
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        panel.setBackground(MainFrame.white);


        panel.add(Box.createRigidArea(new Dimension(150, 0)));
        panel.add(createField("ID:", String.valueOf(shipment.getShipmentId()), MainFrame.FONT_BOLD.deriveFont(20F), MainFrame.FONT_BOLD.deriveFont(20F), MainFrame.black, MainFrame.black));
        panel.add(Box.createRigidArea(new Dimension(65, 0)));
        panel.add(createField("Destination:", shipment.getDestination(), MainFrame.FONT_BOLD.deriveFont(20F), MainFrame.FONT_LIGHT.deriveFont(20F), MainFrame.black, MainFrame.blue));
        panel.add(Box.createRigidArea(new Dimension(65, 0)));
        panel.add(createField("Price:", String.valueOf(shipment.getPrice()), MainFrame.FONT_BOLD.deriveFont(20F), MainFrame.FONT_LIGHT.deriveFont(20F), MainFrame.black, MainFrame.blue));
        panel.add(Box.createRigidArea(new Dimension(65, 0)));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy");
        panel.add(createField("Delivery Date:", shipment.getDeliveryDate().format(formatter), MainFrame.FONT_BOLD.deriveFont(20F), MainFrame.FONT_LIGHT.deriveFont(20F), MainFrame.black, MainFrame.blue));
        panel.add(Box.createRigidArea(new Dimension(150, 0)));

        return panel;
    }


    public void clearHighValShipments() {
        highValProductsPanel.removeAll();
        highValProductsPanel.revalidate();
        highValProductsPanel.repaint();
    }
    public void clearReportDetailsPanel() {
        reportDetailsPanel.removeAll();
        reportDetailsPanel.revalidate();
        reportDetailsPanel.repaint();
    }


    public void addReportDetailsToPanel(String allCosts,String InventoryValue, String totalShipments, String totalProducts){

//        if(reportDetailsPanel!=null)
//        clearReportDetailsPanel();
        if (reportDetailsPanel != null) {
            this.remove(reportDetailsPanel);
        }

        System.out.println("total shi " + totalProducts);
        reportDetailsPanel = createReportDetailsPanel(allCosts,InventoryValue,totalShipments,totalProducts);
        System.out.println("i will off myself here "+totalProducts);
        this.add(reportDetailsPanel,BorderLayout.WEST);



    }


}
