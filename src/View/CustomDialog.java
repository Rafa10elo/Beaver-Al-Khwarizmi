package View;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomDialog extends JDialog{
    public JTextField amount;
    public JTextField price;
    public JTextField name;
    public JTextField numberOfDays;
    public String l1;
    public String l2;
    public String l3;
    public JPanel parentPanel;
    public JPanel centerPanel;
    public JCheckBox checkBox;


   public JButton confirmButton;

   //general(used in edit and add product)
   public CustomDialog(JPanel parentPanel,String l1,String l2,String l3,JTextField name , JTextField price , JTextField amount, JButton confirmButton, JFrame frame){
       super(frame,true);
       this.parentPanel=parentPanel;
       this.l1=l1;
       this.l2=l2;
       this.l3=l3;
       this.name = name;
       this.amount=amount;
       this.price = price;
       this.confirmButton=confirmButton;

       name = makeEmLookNicer(name);
       amount = makeEmLookNicer(amount);
       price = makeEmLookNicer(price);

       setBackground(MainFrame.white);
       setLayout(new BorderLayout());

       JPanel panel =  new JPanel();
       panel.setBackground(MainFrame.blue);
       panel.setSize(new Dimension(10,50));
       panel.setBorder(new EmptyBorder(20,10,40,10));
       this.add(panel,BorderLayout.NORTH);

       JLabel label1 = createLabel(l1);
       JLabel label2 = createLabel(l2);
       JLabel label3 = createLabel(l3);

       centerPanel = new JPanel();
       centerPanel.setBackground(MainFrame.white);
       centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
       centerPanel.setBorder(new EmptyBorder(20,25,40,25));
       centerPanel.add(Box.createRigidArea(new Dimension(0,30)));
       centerPanel.add(label1);
       centerPanel.add(name);
       centerPanel.add(Box.createRigidArea(new Dimension(0,25)));
       centerPanel.add(label2);
       centerPanel.add(price);
       centerPanel.add(Box.createRigidArea(new Dimension(0,25)));
       centerPanel.add(label3);
       centerPanel.add(amount);
       centerPanel.add(Box.createRigidArea(new Dimension(0,40)));

       this.add(centerPanel,BorderLayout.CENTER);

       JPanel lastPanelISwear= new JPanel(new FlowLayout(FlowLayout.CENTER));
       confirmButton.setBackground(MainFrame.dark_blue);
       confirmButton.setForeground(MainFrame.white);
       confirmButton.setFont(MainFrame.FONT_BOLD.deriveFont(16f));
       confirmButton.addActionListener(confirmButtonListener);
       confirmButton.setFocusPainted(false);
       lastPanelISwear.add(confirmButton);
       lastPanelISwear.setBorder(new EmptyBorder(20,50,20,50));
       lastPanelISwear.setBackground(MainFrame.blue);
       this.add(lastPanelISwear,BorderLayout.SOUTH);

   }
    //used in edit non vip shipment
    public CustomDialog(JPanel parentPanel,String l1,String l2,JTextField name,JTextField days, JButton confirmButton, JFrame frame){
        super(frame,true);
        this.parentPanel=parentPanel;
        this.l1=l1;
        this.l2=l2;
        this.name = name;
        this.confirmButton=confirmButton;
        this.numberOfDays = days;

        name = makeEmLookNicer(name);
        setBackground(MainFrame.white);
        setLayout(new BorderLayout());
        JPanel panel =  new JPanel();
        panel.setBackground(MainFrame.blue);
        panel.setSize(new Dimension(10,50));
        panel.setBorder(new EmptyBorder(20,10,40,10));
        this.add(panel,BorderLayout.NORTH);

        JLabel label1 = createLabel(l1);
        JLabel label3 = createLabel("Promote to VIP: ");

        centerPanel = new JPanel();
        centerPanel.setBackground(MainFrame.white);
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
        centerPanel.setBorder(new EmptyBorder(20,25,40,25));
        centerPanel.add(Box.createRigidArea(new Dimension(0,30)));
        label1.setAlignmentX(Component.CENTER_ALIGNMENT);
        //dest
        centerPanel.add(label1);
        centerPanel.add(Box.createRigidArea(new Dimension(0,25)));
        centerPanel.add(name);
        centerPanel.add(Box.createRigidArea(new Dimension(0,45)));

        JPanel setPriorityPanel = new JPanel();
        setPriorityPanel.setLayout(new BoxLayout(setPriorityPanel,BoxLayout.X_AXIS));
        setPriorityPanel.add(Box.createRigidArea(new Dimension(50,0)));
        setPriorityPanel.add(label3);
        setPriorityPanel.add(Box.createRigidArea(new Dimension(20,0)));
        setPriorityPanel.setBackground(MainFrame.white);
        checkBox = new JCheckBox();
        checkBox.setBackground(MainFrame.white);
        centerPanel.add(setPriorityPanel);

        JLabel numberOfDaysLabel = createLabel("Days until Arrival : ");
        numberOfDaysLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        numberOfDays = makeEmLookNicer(numberOfDays);
        numberOfDays.setAlignmentX(Component.CENTER_ALIGNMENT);
        numberOfDays.setVisible(false);
        numberOfDaysLabel.setVisible(false);

        checkBox.addActionListener(e -> {
            numberOfDaysLabel.setVisible(checkBox.isSelected());
            numberOfDays.setVisible(checkBox.isSelected());
            this.revalidate(); // Refresh layout
            this.repaint();    // Redraw panel
        });
        setPriorityPanel.add(checkBox);

        centerPanel.add(Box.createRigidArea(new Dimension(0,25)));
        centerPanel.add(numberOfDaysLabel);
        centerPanel.add(Box.createRigidArea(new Dimension(0,25)));
        centerPanel.add(numberOfDays);
        centerPanel.add(Box.createRigidArea(new Dimension(0,40)));

        this.add(centerPanel,BorderLayout.CENTER);

        JPanel lastPanelISwear= new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmButton.setBackground(MainFrame.dark_blue);
        confirmButton.setForeground(MainFrame.white);
        confirmButton.setFont(MainFrame.FONT_BOLD.deriveFont(16f));
        confirmButton.addActionListener(confirmButtonListener);
        confirmButton.setFocusPainted(false);
        lastPanelISwear.add(confirmButton);
        lastPanelISwear.setBorder(new EmptyBorder(20,50,20,50));
        lastPanelISwear.setBackground(MainFrame.blue);
        this.add(lastPanelISwear,BorderLayout.SOUTH);

    }
    //used in edit vip shipment
    public CustomDialog(String l1,String l2,JTextField name , JTextField amount, JButton confirmButton, JFrame frame){
        super(frame,true);
        this.l1=l1;
        this.l2=l2;
        this.name = name;
        this.amount=amount;

        this.confirmButton=confirmButton;

        name = makeEmLookNicer(name);
        amount = makeEmLookNicer(amount);

        setBackground(MainFrame.white);
        setLayout(new BorderLayout());

        JPanel panel =  new JPanel();
        panel.setBackground(MainFrame.blue);
        panel.setSize(new Dimension(10,50));
        panel.setBorder(new EmptyBorder(20,10,40,10));
        this.add(panel,BorderLayout.NORTH);

        JLabel label1 = createLabel(l1);
        JLabel label3 = createLabel(l2);

        centerPanel = new JPanel();
        centerPanel.setBackground(MainFrame.white);
        centerPanel.setLayout(new BoxLayout(centerPanel,BoxLayout.Y_AXIS));
        centerPanel.setBorder(new EmptyBorder(20,25,40,25));
        centerPanel.add(Box.createRigidArea(new Dimension(0,30)));
        centerPanel.add(makeRow(label1));
        centerPanel.add(Box.createRigidArea(new Dimension(0,5)));
        centerPanel.add(name);
        centerPanel.add(Box.createRigidArea(new Dimension(0,25)));
        centerPanel.add(Box.createRigidArea(new Dimension(0,5)));

        centerPanel.add(makeRow(label3));
        centerPanel.add(Box.createRigidArea(new Dimension(0,5)));
        centerPanel.add(amount);
        centerPanel.add(Box.createRigidArea(new Dimension(0,30)));

        JPanel setPriorityPanel = new JPanel();
        JLabel label4 = createLabel("Demote from VIP : ");
        setPriorityPanel.setLayout(new BoxLayout(setPriorityPanel,BoxLayout.X_AXIS));

        setPriorityPanel.add(Box.createRigidArea(new Dimension(40,0)));
        setPriorityPanel.add(label4);
        setPriorityPanel.add(Box.createRigidArea(new Dimension(20,0)));
        setPriorityPanel.setBackground(MainFrame.white);
        checkBox = new JCheckBox();
        checkBox.setBackground(MainFrame.white);
        setPriorityPanel.add(checkBox);
        centerPanel.add(setPriorityPanel);
        centerPanel.add(Box.createRigidArea(new Dimension(0,40)));
        this.add(centerPanel,BorderLayout.CENTER);

        JPanel lastPanelISwear= new JPanel(new FlowLayout(FlowLayout.CENTER));
        confirmButton.setBackground(MainFrame.dark_blue);
        confirmButton.setForeground(MainFrame.white);
        confirmButton.setFont(MainFrame.FONT_BOLD.deriveFont(16f));
        confirmButton.addActionListener(confirmButtonListener);
        confirmButton.setFocusPainted(false);
        lastPanelISwear.add(confirmButton);
        lastPanelISwear.setBorder(new EmptyBorder(20,50,20,50));
        lastPanelISwear.setBackground(MainFrame.blue);
        this.add(lastPanelISwear,BorderLayout.SOUTH);

    }

   JLabel createLabel (String s){
       JLabel label = new JLabel(s);
       label.setForeground(MainFrame.blue);
       label.setFont(MainFrame.FONT_REGULAR.deriveFont(15f));
       label.setHorizontalAlignment(SwingConstants.RIGHT);
       label.setAlignmentX(Component.CENTER_ALIGNMENT);

       return label;
   }

   JTextField makeEmLookNicer(JTextField textField){
       JTextField textField1 = textField;
       textField1.setFont(MainFrame.FONT_REGULAR.deriveFont(15f));
       textField1.setBackground(MainFrame.dark_white);
       textField1.setForeground(MainFrame.black);
       return textField1;
   }

    private JPanel makeRow(JComponent comp) {
        JPanel row = new JPanel(new FlowLayout(FlowLayout.CENTER, 0, 0));
        row.setBackground(MainFrame.white);
        row.add(comp);
        return row;
    }

    ActionListener confirmButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            removeAll();
            dispose();

        }
    };
}
