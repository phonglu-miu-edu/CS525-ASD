package ccard.framework;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.function.Consumer;


public abstract class FwFrm extends javax.swing.JFrame {
    private DefaultTableModel model;
    private JTable JTable1;
    private JScrollPane JScrollPane1;
    FwFrm thisframe;
    private Object rowdata[];

    public DefaultTableModel getDefaultTableModel() {
        return this.model;
    }

    public JTable getJTable() {
        return this.JTable1;
    }

    public abstract void addTopBar(javax.swing.JPanel topBar);
    public abstract void addActionListener(SymAction synAction);
    public abstract void actionPerformed(java.awt.event.ActionEvent event);

    public FwFrm() {
        thisframe = this;

//        setTitle("Credit Card Processing Application.");
        setDefaultCloseOperation(javax.swing.JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setSize(575, 310);
        setVisible(false);
        JPanel1.setLayout(null);
        getContentPane().add(BorderLayout.CENTER, JPanel1);
        JPanel1.setBounds(0, 0, 575, 310);

        JScrollPane1 = new JScrollPane();
        model = new DefaultTableModel();
        JTable1 = new JTable(model);
        model.addColumn("Name");
        model.addColumn("CC number");
        model.addColumn("Exp date");
        model.addColumn("Type");
        model.addColumn("Balance");
        rowdata = new Object[7];

        JPanel1.add(JScrollPane1);
        JScrollPane1.setBounds(12, 92, 440, 160);
        JScrollPane1.getViewport().add(JTable1);
        JTable1.setBounds(0, 0, 420, 0);

        addTopBar(JPanel1);

        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468, 248, 96, 30);

        FwFrm.SymWindow aSymWindow = new FwFrm.SymWindow();
        this.addWindowListener(aSymWindow);
        FwFrm.SymAction lSymAction = new FwFrm.SymAction(this::actionPerformed);
        JButton_Exit.addActionListener(lSymAction);
        addActionListener(lSymAction);
    }

    /*****************************************************
     * The entry point for this application.
     * Sets the Look and Feel to the System Look and Feel.
     * Creates a new JFrame1 and makes it visible.
     *****************************************************/
    static public void main(String args[]) {
        try {
            // Add the following code if you want the Look and Feel
            // to be set to the Look and Feel of the native system.

            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
            }

            //Create a new instance of our application's frame, and make it visible.
//            (new FwFrm()).setVisible(true);
        } catch (Throwable t) {
            t.printStackTrace();
            //Ensure the application exits with an error condition.
            System.exit(1);
        }
    }

    javax.swing.JPanel JPanel1 = new javax.swing.JPanel();
    javax.swing.JButton JButton_Exit = new javax.swing.JButton();

    void exitApplication() {
        try {
            this.setVisible(false);    // hide the Frame
            this.dispose();            // free the system resources
            System.exit(0);            // close the application
        } catch (Exception e) {
        }
    }

    public class SymWindow extends java.awt.event.WindowAdapter {
        public void windowClosing(java.awt.event.WindowEvent event) {
            Object object = event.getSource();
            if (object == FwFrm.this)
                BankFrm_windowClosing(event);
        }
    }

    void BankFrm_windowClosing(java.awt.event.WindowEvent event) {
        // to do: code goes here.

        BankFrm_windowClosing_Interaction1(event);
    }

    void BankFrm_windowClosing_Interaction1(java.awt.event.WindowEvent event) {
        try {
            this.exitApplication();
        } catch (Exception e) {
        }
    }

    public class SymAction implements java.awt.event.ActionListener {
        private Consumer<java.awt.event.ActionEvent> actionPerformed;

        public SymAction(Consumer<java.awt.event.ActionEvent> actionPerformed) {
            this.actionPerformed = actionPerformed;
        }

        public void actionPerformed(java.awt.event.ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_Exit)
                JButtonExit_actionPerformed(event);
            else
                this.actionPerformed.accept(event);
        }
    }

    //When the Exit button is pressed this code gets executed
    //this will exit from the system
    void JButtonExit_actionPerformed(java.awt.event.ActionEvent event) {
        System.exit(0);
    }

    public void addNewRecord(String clientName, String ccnumber, String expdate, String accountType) {
        rowdata[0] = clientName;
        rowdata[1] = ccnumber;
        rowdata[2] = expdate;
        rowdata[3] = accountType;
        rowdata[4] = "0";
        model.addRow(rowdata);
        JTable1.getSelectionModel().setAnchorSelectionIndex(-1);
    }
}