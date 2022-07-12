package banking.views;

import framework.models.Account;
import framework.models.Person;
import framework.views.FinCoView;
import framework.views.JDialog_Deposit;
import framework.views.JDialog_Withdraw;
import framework.views.VIEW_TYPE;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.List;

public class BankView extends FinCoView {

	private static final long serialVersionUID = 1L;

	public IBankViewController bankViewController;

	public BankView(IBankViewController bankViewController, VIEW_TYPE view_Type) {
		super(bankViewController, view_Type);

		this.bankViewController = bankViewController;
		setSize(600, 310);
	}

	@Override
	public List<String> getTableColumnNames() {
		return List.of("AccNr", "Name", "City", "P/C", "Ch/Sa", "Balance");
	}

	@Override
	public void JButtonCompAC_actionPerformed(ActionEvent event) {
		/*
		 * construct a JDialog_AddCompAcc type object set the boundaries and show it
		 */

		BankJDialog_AddCompAcc pac = new BankJDialog_AddCompAcc(myFrame);
		pac.setBounds(450, 20, 300, 330);
		pac.show();

		if (newaccount) {
			try {
		        Integer.parseInt(zip);
		    }
		    catch( Exception e ) {
		        zip = "0";
		    }

			int type = 1;
			if (accountType.equals("S")) {
				type = 2;
			}
			
			this.bankViewController.createCompanyAndAccountByType(accountNumber, clientName, street, city, state,
					Integer.parseInt(zip), email, noOfEmployee, type);

			Collection<Account> accounts = this.bankViewController.getAccounts();
			loadAccountData(accounts, model, JTable1);

		}
	}

	@Override
	public void JButtonPerAC_actionPerformed(ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object
		 set the boundaries and show it
		*/

        BankJDialog_AddPAcc pac = new BankJDialog_AddPAcc(myFrame);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

		int type = 1;
		if (accountType.equals("S")) {
			type = 2;
		}

        if (newaccount){
            // add row to table
        	try {
		        Integer.parseInt(zip);
		    }
		    catch( Exception e ) {
		        zip = "0";
		    }
        	
			this.bankViewController.createCustomerAndAccountByType(accountNumber, clientName, street, city, state,
					Integer.parseInt(zip), email, birthDate, type);

            Collection<Account> accounts = this.bankViewController.getAccounts();
            loadAccountData(accounts, model, JTable1);
        }
	}

	@Override
	public void JButtonDeposit_actionPerformed(ActionEvent event) {
		// get selected name
		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String accnr = (String) model.getValueAt(selection, 0);

			// Show the dialog for adding deposit amount for the current mane
			JDialog_Deposit dep = new JDialog_Deposit(myFrame, accnr);
			dep.setBounds(430, 15, 275, 140);
			dep.show();

			// compute new amount
			double deposit = Double.parseDouble(amountDeposit);

			Account acc = bankViewController.getAccounts().stream()
					.filter(x -> x.getAccountNum().equals((String) model.getValueAt(selection, 0))).findFirst().get();

			bankViewController.deposit(acc, deposit);
			model.setValueAt(String.valueOf(acc.getCurrentBalance()),selection, 5);
		}
	}

	@Override
	public void JButtonWithdraw_actionPerformed(ActionEvent event) {
		// get selected name
		int selection = JTable1.getSelectionModel().getMinSelectionIndex();
		if (selection >= 0) {
			String accnr = (String) model.getValueAt(selection, 0);

			// Show the dialog for adding withdraw amount for the current mane
			JDialog_Withdraw wd = new JDialog_Withdraw(myFrame, accnr);
			wd.setBounds(430, 15, 275, 140);
			wd.show();

			// compute new amount
			double deposit = Double.parseDouble(amountDeposit);

			Account acc = bankViewController.getAccounts().stream()
					.filter(x -> x.getAccountNum().equals((String) model.getValueAt(selection, 0))).findFirst().get();

			bankViewController.withdraw(acc, deposit);
			model.setValueAt(String.valueOf(acc.getCurrentBalance()),selection, 5);
		}
	}
	
	@Override
	public void loadAccountData(Collection<Account> accounts, DefaultTableModel model, JTable table) {
		model.setRowCount(0);

		for (Account account : accounts) {
			Object[] row = new Object[8];
			row[0] = account.getAccountNum();
			row[1] = account.getCustomer().getName();
			row[2] = account.getCustomer().getCity();

			if (account.getCustomer() instanceof Person) {
				row[3] = 'P';
			} else {
				row[3] = 'C';
			}
			row[4] = account.getClass().getSimpleName().substring(0, 2);
			row[5] = account.getCurrentBalance();

			model.addRow(row);
		}

		table.getSelectionModel().setAnchorSelectionIndex(-1);
		newaccount=false;
	}
}
