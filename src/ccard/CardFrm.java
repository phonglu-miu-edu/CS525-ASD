package ccard;

import ccard.framework.FwFrm;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * A basic JFC based application.
 **/
public class CardFrm extends FwFrm
{
    /****
     * init variables in the object
     ****/
    String clientName,street,city, zip, state,accountType,amountDeposit,expdate, phone, email, birthDate, ccnumber;
	boolean newaccount;
    CardFrm thisframe;
	javax.swing.JButton JButton_NewCCAccount;
	javax.swing.JButton JButton_GenBill;
	javax.swing.JButton JButton_Deposit;
	javax.swing.JButton JButton_Charge;

	@Override
	public void addTopBar(javax.swing.JPanel topBar) {
		JButton_NewCCAccount = new javax.swing.JButton();
		JButton_NewCCAccount.setText("Add Credit-card account");
		topBar.add(JButton_NewCCAccount);
		JButton_NewCCAccount.setBounds(24, 20, 192, 33);

		JButton_GenBill = new javax.swing.JButton();
		JButton_GenBill.setText("Generate Monthly bills");
		JButton_GenBill.setActionCommand("jbutton");
		topBar.add(JButton_GenBill);
		JButton_GenBill.setBounds(240, 20, 192, 33);

		JButton_Deposit = new javax.swing.JButton();
		JButton_Deposit.setText("Deposit");
		topBar.add(JButton_Deposit);
		JButton_Deposit.setBounds(468, 104, 96, 33);

		JButton_Charge = new javax.swing.JButton();
		JButton_Charge.setText("Charge");
		topBar.add(JButton_Charge);
		JButton_Charge.setBounds(468, 164, 96, 33);
	}

	public CardFrm()
	{
		super();
		thisframe=this;

		setTitle("Credit Card Processing Application.");

		newaccount = false;
	}

	@Override
	public void addActionListener(SymAction lSymAction) {
		JButton_NewCCAccount.addActionListener(lSymAction);
        JButton_GenBill.addActionListener(lSymAction);
        JButton_Deposit.addActionListener(lSymAction);
        JButton_Charge.addActionListener(lSymAction);
	}

	
	/*****************************************************
	 * The entry point for this application.
	 * Sets the Look and Feel to the System Look and Feel.
	 * Creates a new JFrame1 and makes it visible.
	 *****************************************************/
	static public void main(String args[])
	{
		try {
		    // Add the following code if you want the Look and Feel
		    // to be set to the Look and Feel of the native system.
		    
		    try {
		        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		    } 
		    catch (Exception e) { 
		    }
		    
			//Create a new instance of our application's frame, and make it visible.
			(new CardFrm()).setVisible(true);
		} 
		catch (Throwable t) {
			t.printStackTrace();
			//Ensure the application exits with an error condition.
			System.exit(1);
		}
	}

	@Override
	public void actionPerformed(java.awt.event.ActionEvent event) {
		Object object = event.getSource();
		if (object == JButton_NewCCAccount)
			JButtonNewCCAC_actionPerformed(event);
		else if (object == JButton_GenBill)
			JButtonGenerateBill_actionPerformed(event);
		else if (object == JButton_Deposit)
			JButtonDeposit_actionPerformed(event);
		else if (object == JButton_Charge)
			JButtonCharge_actionPerformed(event);
	}

	void JButtonNewCCAC_actionPerformed(java.awt.event.ActionEvent event)
	{
		// TODO: comment to make the code could be compiled
		JDialog_AddCCAccount ccac = new JDialog_AddCCAccount(thisframe);
		ccac.setBounds(450, 20, 300, 404);
		ccac.show();

		if (newaccount) {
            // add row to table
			super.addNewRecord(clientName, ccnumber, expdate, accountType);
            newaccount = false;
        }
    }

	void JButtonGenerateBill_actionPerformed(java.awt.event.ActionEvent event)
	{
		JDialogGenBill billFrm = new JDialogGenBill();
		billFrm.setBounds(450, 20, 400, 350);
		billFrm.show();
	}

	void JButtonDeposit_actionPerformed(java.awt.event.ActionEvent event)
	{
	    // get selected name
        int selection = getJTable().getSelectionModel().getMinSelectionIndex();
        if (selection >=0){
			DefaultTableModel model = getDefaultTableModel();
            String name = (String)model.getValueAt(selection, 0);

		    //Show the dialog for adding deposit amount for the current mane
		    JDialog_Deposit dep = new JDialog_Deposit(thisframe, name);
		    dep.setBounds(430, 15, 275, 140);
		    dep.show();

		    // compute new amount
            long deposit = Long.parseLong(amountDeposit);
            String samount = (String)model.getValueAt(selection, 4);
            long currentamount = Long.parseLong(samount);
		    long newamount=currentamount+deposit;
		    model.setValueAt(String.valueOf(newamount),selection, 4);
		}
	}

	void JButtonCharge_actionPerformed(java.awt.event.ActionEvent event)
	{
	    // get selected name
		int selection = getJTable().getSelectionModel().getMinSelectionIndex();
		if (selection >=0){
			DefaultTableModel model = getDefaultTableModel();
            String name = (String)model.getValueAt(selection, 0);

		    //Show the dialog for adding Charge amount for the current mane
		    JDialog_Charge wd = new JDialog_Charge(thisframe, name);
		    wd.setBounds(430, 15, 275, 140);
		    wd.show();

		    // compute new amount
            long deposit = Long.parseLong(amountDeposit);
            String samount = (String)model.getValueAt(selection, 4);
            long currentamount = Long.parseLong(samount);
		    long newamount=currentamount-deposit;
		    model.setValueAt(String.valueOf(newamount),selection, 4);
		    if (newamount <0){
		       JOptionPane.showMessageDialog(JButton_Charge, " "+name+" Your balance is negative: $"+String.valueOf(newamount)+" !","Warning: negative balance",JOptionPane.WARNING_MESSAGE);
		    }
		}
	}
}