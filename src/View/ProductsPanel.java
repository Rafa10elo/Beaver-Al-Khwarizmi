package View;
import Model.Product;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class ProductsPanel extends JPanel {
    ArrayList<ProductPanel> productPanels ;
    JPanel contentPanel;

    CustomDialog addProductDialog ;
    public JButton backButton;
    public JButton addProductButton;
    public JTextField search;
    public JButton searchButton;
    public static int cnt = 0;

    public ProductsPanel(ArrayList<ProductPanel> productPanels) {
        this.productPanels= productPanels;

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
//        searchButton.setBorder(new FlatBorder());
        searchButton.setCursor(new Cursor(Cursor.HAND_CURSOR));
        searchButton.setBorder( new EmptyBorder(7,4,7,6));


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
            }
        });
        add(scrollProductMenu, BorderLayout.CENTER) ;

        // ---- the add button bar
        JPanel footerBar = new JPanel();
        footerBar.setBorder(new EmptyBorder(15, 15, 15, 15));
        footerBar.setBackground(MainFrame.blue);
        addProductButton = createTextButton("add product");
        footerBar.add(addProductButton);
        add(footerBar,BorderLayout.SOUTH);


    }


    public void addProductPanel(Product product) {
        System.out.println("made a wajha");
        ProductPanel p = new ProductPanel(product);

        contentPanel.add(p);
        productPanels.add(p);

        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.revalidate();
    }

    public void addToProductsPanel(JLabel label){
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        contentPanel.add(label);
        contentPanel.add(Box.createRigidArea(new Dimension(0, 10)));
    }

    public void clearProducts() {

        contentPanel.removeAll();
        contentPanel.revalidate();
        contentPanel.repaint();
    }

    public CustomDialog createAddProductDialog () {
        JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(this);

        JTextField name = new JTextField(" name");
        JTextField price = new JTextField("price");
        JTextField amount = new JTextField(" Amount");
        name.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                name.setText("");
            }
        });
        price.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                price.setText("");
            }
        });
        amount.addMouseListener(new MouseAdapter(){
            @Override
            public void mouseClicked(MouseEvent e){
                amount.setText("");
            }
        });

        JButton confirmButton = new JButton("okay");
        addProductDialog = new CustomDialog(this,"Name: ","Price: ","Quantity",name,price,amount,confirmButton,frame);
        addProductDialog.setSize(new Dimension(400, 550));
        addProductDialog.setLocationRelativeTo(null);
        addProductDialog.setVisible(true);
        return addProductDialog;
    }



    private JButton createTextButton(String text) {
        JButton button = new JButton(text);
        button.setFont(MainFrame.FONT_BOLD.deriveFont(23f));
        button.setFocusPainted(false);
        button.setBackground(MainFrame.dark_blue);
        button.setForeground(MainFrame.white);
        button.setCursor(new Cursor(Cursor.HAND_CURSOR));
//        button.setBorder(new FlatBorder());
        return button;
    }
}
