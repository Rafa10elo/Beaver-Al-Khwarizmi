package View;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.plaf.basic.BasicBorders;
import java.awt.*;

public class MainPanel extends JPanel {
    public static JButton productsButton;
    public static JButton shipmentsButton;
    public static JButton reportButton;


    public MainPanel() {
        setLayout(new BorderLayout());

        //title panel
        JPanel titlePanel = new JPanel(new GridBagLayout());
        titlePanel.setBorder(BorderFactory.createMatteBorder(0, 0, 10, 0, MainFrame.red));
        GridBagConstraints gbc0 = new GridBagConstraints();
        gbc0.gridx=1;
        gbc0.gridy=1;
        gbc0.insets=new Insets(20,30,20,30);
        gbc0.fill  = HEIGHT;
        JLabel titleLabel = createLabel("BEAVER TRANSPORT",MainFrame.FONT_BOLD,MainFrame.black);
        titlePanel.setBackground(MainFrame.blue);
        titlePanel.add(titleLabel,gbc0);
        add(titlePanel,BorderLayout.NORTH);

        JPanel contentPanel = new JPanel(new GridBagLayout());
        contentPanel.setBackground(MainFrame.white);
        GridBagConstraints gbc = new GridBagConstraints();

        // subtitle
        JLabel subtitleLabel = createLabel("welcome to Beaver Transport!", MainFrame.FONT_REGULAR, MainFrame.blue);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(30, 0, 50, 0);
        contentPanel.add(subtitleLabel, gbc);

        // -------------- Shipments Panel --------------
        JPanel shipmentsPanel = new JPanel();
        shipmentsPanel.setLayout(new BoxLayout(shipmentsPanel,BoxLayout.Y_AXIS));

        shipmentsPanel.setBackground(MainFrame.white);
        shipmentsPanel.setBorder(BorderFactory.createLineBorder(MainFrame.blue, 2));

        JLabel shipmentDesc = createLabel("View and manage shipments:", MainFrame.FONT_LIGHT, MainFrame.black);
        shipmentDesc.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        shipmentDesc.setHorizontalAlignment(SwingConstants.CENTER);

        shipmentsButton = createTextButton("Shipments");
        shipmentsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        shipmentsPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        shipmentsPanel.add(shipmentDesc);
        shipmentsPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        shipmentsPanel.add(shipmentsButton);

        // ------------ Products Panel -----------
        JPanel productsPanel = new JPanel();
        productsPanel.setLayout(new BoxLayout(productsPanel,BoxLayout.Y_AXIS));

        productsPanel.setBackground(MainFrame.white);
        productsPanel.setBorder(BorderFactory.createLineBorder(MainFrame.blue, 2));

        JLabel productDesc = createLabel("Explore your product list:", MainFrame.FONT_LIGHT, MainFrame.black);
        productDesc.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        productDesc.setHorizontalAlignment(SwingConstants.CENTER);

        productsButton = createTextButton("Products");
        productsButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        productsPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        productsPanel.add(productDesc);
        productsPanel.add(Box.createRigidArea(new Dimension(0, 30)));
        productsPanel.add(productsButton);

        // ------------ Report Panel -----------
        JPanel reportPanel = new JPanel();
        reportPanel.setLayout(new BoxLayout(reportPanel,BoxLayout.Y_AXIS));

        reportPanel.setBackground(MainFrame.white);
        reportPanel.setBorder(BorderFactory.createLineBorder(MainFrame.blue, 2));

        JLabel reportDesc = createLabel("View Detailed and Precise Analysis of our Transport Activity:", MainFrame.FONT_LIGHT, MainFrame.black);
        reportDesc.setAlignmentX(Component.CENTER_ALIGNMENT); // Center horizontally
        reportDesc.setHorizontalAlignment(SwingConstants.CENTER);

        reportButton = createTextButton("Report");
        reportButton.setAlignmentX(Component.CENTER_ALIGNMENT);

        reportPanel.add(Box.createRigidArea(new Dimension(0, 50)));
        reportPanel.add(reportDesc);
        reportPanel.add(Box.createRigidArea(new Dimension(0, 20)));
        reportPanel.add(reportButton);


        // ===== Add Both Panels Side by Side =====
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(10, 30, 30, 15);
        gbc.gridx = 0;
        gbc.weightx = 1;
        gbc.weighty = 1;
        gbc.fill = GridBagConstraints.BOTH;
        contentPanel.add(shipmentsPanel, gbc);

        gbc.insets = new Insets(10, 15, 30, 30);
        gbc.gridx = 1;
        contentPanel.add(productsPanel, gbc);

        gbc.gridwidth = 2;
        gbc.gridx=0;
        gbc.gridy = 2;
        contentPanel.add(reportPanel,gbc);

        add(contentPanel, BorderLayout.CENTER);
    }

    static JLabel createLabel (String text,Font font, Color color){
        JLabel label = new JLabel(text);
        label.setFont(font);
        label.setForeground(color);
        return label;
    }

    private JButton createTextButton(String text) {
        JButton button = new JButton(text);
        button.setFont(MainFrame.FONT_REGULAR);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        button.setBorderPainted(false);
        button.setForeground(MainFrame.red);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
        return button;
    }
}

