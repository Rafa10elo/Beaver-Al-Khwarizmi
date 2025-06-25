package View;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class MainFrame extends JFrame {

    static CardLayout cardLayout;
    static JPanel cardPanel;
    static MainPanel mainPanel;
    public static Color blue = new Color(16, 65, 124) ;
    public static Color red = new Color(125, 0, 19) ;
    public static Color white = new Color(195, 195, 195) ;
    public static Color black = new Color(7, 6, 31) ;
    public static Color dark_blue = new Color(12, 53, 107);
    public static Color dark_white = new Color(185, 185, 185) ;
    public static Font FONT_REGULAR;
    public static Font FONT_BOLD;
    public static Font FONT_LIGHT;

    static {
        try {
            Font baseFont = Font.createFont(Font.TRUETYPE_FONT,
                    new File("src/View/fonts/Aleo-VariableFont_wght.ttf"));

            FONT_REGULAR = baseFont.deriveFont(Font.PLAIN, 35f);
            FONT_BOLD = baseFont.deriveFont(Font.BOLD, 35f);
            FONT_LIGHT = baseFont.deriveFont(300f).deriveFont(35f); // Light weight (300)

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(baseFont);

        } catch (FontFormatException | IOException e) {
            System.err.println("Failed to load Aleo font, using fallback");
            FONT_REGULAR = new Font("SansSerif", Font.PLAIN, 35);
            FONT_BOLD = new Font("SansSerif", Font.BOLD, 35);
            FONT_LIGHT = new Font("SansSerif", Font.PLAIN, 35);
        }
    }


    public MainFrame(ShipmentsPanel shipmentsPanel, ProductsPanel productsPanel, ReportPanel reportPanel){


        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        mainPanel = new MainPanel();

        cardPanel.add(mainPanel, "MAIN");
        cardPanel.add(shipmentsPanel, "SHIPMENTS");
        cardPanel.add(productsPanel, "PRODUCTS");
        cardPanel.add(reportPanel,"REPORT");

        setContentPane(cardPanel);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(new Dimension(1400, 800));
        setLocationRelativeTo(null);

        setTitle("Beaver Inc.");
    }
    public static void showPanel(String name) {
        cardLayout.show(cardPanel, name);
    }

}
