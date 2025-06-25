package View;

import Model.Shipment;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ShipmentsPanel extends JPanel {
    ArrayList<ShipmentPanel> shipmentPanels;
    JPanel contentPanel;
    AddShipmentDialog addProductDialog ;
    public JButton backButton;
    public JButton addProductButton;
    public JTextField search;
    public JButton searchButton;
    public static int cnt = 0;

    public ShipmentsPanel( ArrayList<ShipmentPanel> shipmentPanels) {
        this.shipmentPanels = shipmentPanels;

        setLayout(new BorderLayout());
        // ---- top navigation bar
        JPanel navigationBar = new JPanel();
        navigationBar.setBorder(new EmptyBorder(20, 15, 20, 15));
        navigationBar.setLayout(new BoxLayout(navigationBar,BoxLayout.X_AXIS));
        navigationBar.setBackground(MainFrame.blue);
        backButton = createTextButton("back");
        navigationBar.add(Box.createRigidArea(new Dimension(10,0)));
        navigationBar.add(backButton);
        search = new JTextField("  search");
        search.setBackground(MainFrame.dark_blue);
        search.setFont(MainFrame.FONT_REGULAR.deriveFont(16f));
        search.setForeground(MainFrame.white);
        search.setBorder(BorderFactory.createLineBorder(MainFrame.white,1));
        search.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                search.setText("");
            }
        });

        navigationBar.add(Box.createRigidArea(new Dimension(500,0)));
        navigationBar.add(search);

        searchButton = new JButton("search");
        searchButton.setBackground(MainFrame.white);
        searchButton.setForeground(MainFrame.dark_blue);
        searchButton.setFont(MainFrame.FONT_REGULAR.deriveFont(16f));
        searchButton.setFocusPainted(false);
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setBorder( new EmptyBorder(10,6,10,8));

        navigationBar.add(searchButton);

        navigationBar.add(Box.createRigidArea(new Dimension(100,0)));
        add(navigationBar,BorderLayout.NORTH);

        // ----- the white panel in the middle that contains the ProductPanel(s)
        contentPanel = new JPanel() ;
        contentPanel.setLayout(new BoxLayout(contentPanel,BoxLayout.Y_AXIS));
        contentPanel.setBackground(MainFrame.white);
        contentPanel.setBorder(new EmptyBorder(30, 30, 30, 30));
        JScrollPane scrollProductMenu = new JScrollPane(contentPanel);
        scrollProductMenu.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JScrollBar verticalScrollBar = scrollProductMenu.getVerticalScrollBar();
        verticalScrollBar.setUI(new javax.swing.plaf.basic.BasicScrollBarUI() {
            @Override
            protected void configureScrollBarColors() {
                this.thumbColor = MainFrame.dark_white;
                this.trackColor = MainFrame.white;
                this.scrollBarWidth = 20;
                //uh mnrja3 later
                this.incrButton = createZeroButton();
                this.decrButton = createZeroButton();
            }
        });
        add(scrollProductMenu, BorderLayout.CENTER) ;

        // ---- the add button bar
        JPanel footerBar = new JPanel();
        footerBar.setBorder(new EmptyBorder(15, 15, 15, 15));
        footerBar.setBackground(MainFrame.blue);
        addProductButton = createTextButton("add shipment");
        footerBar.add(addProductButton);
        add(footerBar,BorderLayout.SOUTH);

    }

    public void addShipmentPanel(Shipment shipment) {
        ShipmentPanel p = new ShipmentPanel(shipment);
        contentPanel.add(p);
        shipmentPanels.add(p);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.revalidate();
    }

    public void clearShipments() {
        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
        shipmentPanels.clear();
    }

    public AddShipmentDialog createAddShipment () {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

        JTextField destination = new JTextField("destination");
        JTextField days = new JTextField();
        JCheckBox checkbox = new JCheckBox();
        destination.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                destination.setText("");
            }
        });
        days.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                days.setText("");
            }
        });


        JButton confirmButton = new JButton("okay");
        addProductDialog = new AddShipmentDialog(destination,days,checkbox,frame);

        return addProductDialog;
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

    public void addToProductsPanel(JLabel label){
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(label);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    //dw abt this
    private JButton createZeroButton() {
        JButton jbutton = new JButton();
        jbutton.setPreferredSize(new Dimension(0, 0));
        jbutton.setMinimumSize(new Dimension(0, 0));
        jbutton.setMaximumSize(new Dimension(0, 0));
        return jbutton;
    }


}