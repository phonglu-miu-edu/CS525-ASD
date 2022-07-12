package bank.view;

import framework.model.IAccount;
import framework.model.Person;
import framework.view.FinCoView;
import framework.view.JDialog_Deposit;
import framework.view.JDialog_Withdraw;
import framework.view.ViewType;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.List;

public class BankView extends FinCoView {

    private static final long serialVersionUID = 1L;

    public IBankViewController bankViewController;

    public BankView(IBankViewController bankViewController, ViewType viewType) {
        super(bankViewController, viewType);

        this.bankViewController = bankViewController;

        setTitle("Bank Application");
        setSize(650, 330);
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
            } catch (Exception e) {
                zip = "0";
            }

            int type = 1;
            if (accountType.equals("S")) {
                type = 2;
            }

            this.bankViewController.createCompanyAndAccountByType(accountNumber, clientName, street, city, state,
                Integer.parseInt(zip), email, noOfEmployee, type);

            Collection<IAccount> accounts = this.bankViewController.getAccounts();
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

        if (newaccount) {
            // add row to table
            try {
                Integer.parseInt(zip);
            } catch (Exception e) {
                zip = "0";
            }

            this.bankViewController.createCustomerAndAccountByType(accountNumber, clientName, street, city, state,
                Integer.parseInt(zip), email, birthDate, type);

            Collection<IAccount> accounts = this.bankViewController.getAccounts();
            loadAccountData(accounts, model, JTable1);
        }
    }

    @Override
    public void JButtonDeposit_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accountNumber = (String) model.getValueAt(selection, 0);

            // Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(myFrame, accountNumber);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            // compute new amount
            double deposit = Double.parseDouble(amountDeposit);

            IAccount acc = bankViewController.getAccounts().stream()
                                             .filter(x -> x.getAccountNum().equals(accountNumber)).findFirst().get();

            bankViewController.deposit(acc, deposit);
            model.setValueAt(String.valueOf(acc.getCurrentBalance()), selection, 5);
        }
    }

    @Override
    public void JButtonWithdraw_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accountNumber = (String) model.getValueAt(selection, 0);

            // Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(myFrame, accountNumber);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            // compute new amount
            double deposit = Double.parseDouble(amountDeposit);

            IAccount acc = bankViewController.getAccounts().stream()
                .filter(x -> x.getAccountNum().equals(accountNumber)).findFirst().get();

            bankViewController.withdraw(acc, deposit);
            model.setValueAt(String.valueOf(acc.getCurrentBalance()), selection, 5);
        }
    }

    @Override
    public void loadAccountData(Collection<IAccount> accounts, DefaultTableModel model, JTable table) {
        model.setRowCount(0);

        for (IAccount account : accounts) {
            Object[] row = new Object[8];
            row[0] = account.getAccountNum();
            row[1] = account.getCustomer().getName();
            row[2] = account.getCustomer().getCity();
            row[3] = account.getCustomer() instanceof Person ? 'P' : 'C';
            row[4] = account.getClass().getSimpleName().substring(0, 2);
            row[5] = account.getCurrentBalance();

            model.addRow(row);
        }

        table.getSelectionModel().setAnchorSelectionIndex(-1);
        newaccount = false;
    }
}
