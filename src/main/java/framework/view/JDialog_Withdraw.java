package framework.view;

import javax.swing.*;
import java.awt.*;


public class JDialog_Withdraw extends JDialog {
    private FinCoView parentframe;
    private String accountNumber;

    public JDialog_Withdraw(FinCoView parent, String aaccountNumber) {
        super(parent);
        parentframe = parent;
        accountNumber = aaccountNumber;

        // This code is automatically generated by Visual Cafe when you add
        // components to the visual environment. It instantiates and initializes
        // the components. To modify the code, only use code syntax that matches
        // what Visual Cafe can generate, or Visual Cafe may be unable to back
        // parses your Java file into its visual envirenment.

        setTitle("Withdraw");
        setModal(true);
        getContentPane().setLayout(null);
        setSize(277, 134);
        setVisible(false);
        JLabel1.setText("Acc Nr");
        getContentPane().add(JLabel1);
        JLabel1.setForeground(Color.black);
        JLabel1.setBounds(12, 12, 48, 24);
        JLabel2.setText("Amount");
        getContentPane().add(JLabel2);
        JLabel2.setForeground(Color.black);
        JLabel2.setBounds(12, 36, 48, 24);
        JTextField_NAME.setEditable(false);
        getContentPane().add(JTextField_NAME);
        JTextField_NAME.setBounds(84, 12, 156, 20);
        getContentPane().add(JTextField_AMT);
        JTextField_AMT.setBounds(84, 36, 156, 20);
        JButton_OK.setText("OK");
        JButton_OK.setActionCommand("OK");
        getContentPane().add(JButton_OK);
        JButton_OK.setBounds(48, 84, 84, 24);
        JButton_Cancel.setText("Cancel");
        JButton_Cancel.setActionCommand("Cancel");
        getContentPane().add(JButton_Cancel);
        JButton_Cancel.setBounds(156, 84, 84, 24);

        JTextField_NAME.setText(accountNumber);


        SymAction lSymAction = new SymAction();
        JButton_OK.addActionListener(lSymAction);
        JButton_Cancel.addActionListener(lSymAction);

    }

    JLabel JLabel1 = new JLabel();
    JLabel JLabel2 = new JLabel();
    JTextField JTextField_NAME = new JTextField();
    JTextField JTextField_AMT = new JTextField();
    JButton JButton_OK = new JButton();
    JButton JButton_Cancel = new JButton();

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_OK)
                JButtonOK_actionPerformed(event);
            else if (object == JButton_Cancel)
                JButtonCancel_actionPerformed(event);
        }
    }

    void JButtonOK_actionPerformed(java.awt.event.ActionEvent event) {
        parentframe.amountDeposit = JTextField_AMT.getText();
        dispose();
    }

    void JButtonCancel_actionPerformed(java.awt.event.ActionEvent event) {
        dispose();
    }
}