package framework.view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class FinCoView extends JFrame {
    public String accountnr, clientName, street, city, zip, state, accountType, clientType, amountDeposit, email,
            birthDate, noOfEmployee;
    public boolean newaccount;
    public DefaultTableModel model;
    public JTable JTable1;
    public JScrollPane JScrollPane1;
    public FinCoView myframe;
    public ViewType type;
    public IFincoViewController viewController;

}
