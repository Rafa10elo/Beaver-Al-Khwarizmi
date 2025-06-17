package View;



import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.MatteBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CustomDialog extends JDialog{
    public JTextField amount;
    public JTextField price;
    public JTextField name;
    public String l1;
    public String l2;
    public String l3;
    public JPanel parentPanel;


   public JButton confirmButton;

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

       JPanel anotherPanel = new JPanel();
       anotherPanel.setBackground(MainFrame.white);
       anotherPanel.setLayout(new BoxLayout(anotherPanel,BoxLayout.Y_AXIS));
       anotherPanel.setBorder(new EmptyBorder(20,25,40,25));
       anotherPanel.add(Box.createRigidArea(new Dimension(0,30)));
       anotherPanel.add(label1);
       anotherPanel.add(name);
       anotherPanel.add(Box.createRigidArea(new Dimension(0,25)));
       anotherPanel.add(label2);
       anotherPanel.add(price);
       anotherPanel.add(Box.createRigidArea(new Dimension(0,25)));
       anotherPanel.add(label3);
       anotherPanel.add(amount);
       anotherPanel.add(Box.createRigidArea(new Dimension(0,40)));
       this.add(anotherPanel,BorderLayout.CENTER);

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
       return label;
   }

   JTextField makeEmLookNicer(JTextField textField){
       JTextField textField1 = textField;
       textField1.setFont(MainFrame.FONT_REGULAR.deriveFont(15f));
       textField1.setBackground(MainFrame.dark_white);
       textField1.setForeground(MainFrame.black);
//       textField1.setBorder(new FlatBorder());
       return textField1;
   }

    ActionListener confirmButtonListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

            removeAll();
            dispose();

        }
    };




}
