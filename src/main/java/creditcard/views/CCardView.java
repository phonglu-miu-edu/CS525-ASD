package creditcard.views;

import creditcard.models.CreditCardAccount;
import creditcard.reports.MonthlyBillingReport;
import framework.commands.ReportGenerate;
import framework.IFramework;
import framework.models.Account;
import framework.models.Person;
import framework.views.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.List;

public class CCardView extends FinCoView  {

    public ICCardViewController controller;

    public CCardView(ICCardViewController controller, VIEW_TYPE ui_types) {
        super(controller, ui_types);
        this.controller = controller;
        setTitle("Credit Card Processing Application");
    }

    @Override
    public void setUpButtons() {
        JButton_PerAC.setText("Add personal credit card account");
        JPanel1.add(JButton_PerAC);
        JButton_PerAC.setBounds(24,20,192,33);

        JButton_CompAC.setText("Add company credit card account");
        JButton_CompAC.setActionCommand("jbutton");
        JPanel1.add(JButton_CompAC);
        JButton_CompAC.setBounds(240,20,190,33);

        JButton generateBillBt = new JButton("Generate Monthly Billing report");
        JPanel1.add(generateBillBt);
        generateBillBt.setBounds(104,55,252,33);
        generateBillBt.addActionListener(e -> {

            IFramework framework = controller.getFrameworkApplication();
            Collection<Account> all_accounts = controller.getAccounts();
            MonthlyBillingReport billingReport = new MonthlyBillingReport(all_accounts);
            framework.getFinCo().setReport(billingReport);
            ReportGenerate generateReport = new ReportGenerate(framework.getFinCo());
            framework.getOperationManager().invoke(generateReport);

            String billingReportDetails = billingReport.getBillingReport();

            JDialogGenReport billFrm = new JDialogGenReport(this, billingReportDetails, "Monthly billing report");
            billFrm.setBounds(450, 20, 400, 350);
            billFrm.show();
        });

        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468,104,96,33);

        JButton_Withdraw.setText("Charge");
        JPanel1.add(JButton_Withdraw);
        JButton_Addinterest.setBounds(448,20,106,33);

        JButton_Addinterest.setText("Add interest");
        JPanel1.add(JButton_Addinterest);
        JButton_Withdraw.setBounds(468,164,96,33);

        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468,248,96,30);
    }

    @Override
    public List<String> getTableColumnNames() {
        return List.of("Name", "CC number", "Exp date", "P/C", "Type", "Balance");
    }

    @Override
    public void loadAccountData(Collection<Account> accounts, DefaultTableModel model, JTable table) {
        model.setRowCount(0);

        for (Account account : accounts) {
            CreditCardAccount ccAccount = (CreditCardAccount) account;
            String customerType;
            if (ccAccount.getCustomer() instanceof Person) {
                customerType = "P";
            } else {
                customerType = "C";
            }
            Object[] row = new Object[] {
                    ccAccount.getCustomer().getName(),
                    ccAccount.getAccountNum(),
                    ccAccount.getExpiryDate(),
                    customerType,
                    ccAccount.getType(),
                    ccAccount.getCurrentMonthlyBalance()
            };

            model.addRow(row);
        }

        table.getSelectionModel().setAnchorSelectionIndex(-1);
        newaccount=false;
    }

    @Override
    public void JButtonPerAC_actionPerformed(ActionEvent event) {
		/*
		 JDialog_AddPAcc type object is for adding personal information
		 construct a JDialog_AddPAcc type object
		 set the boundaries and show it
		*/

        JDialog_AddPersonalCCAccount ccac = new JDialog_AddPersonalCCAccount(this, controller);
        ccac.setBounds(450, 20, 300, 400);
        ccac.show();

        if (newaccount){
            // add row to table
            Collection<Account> accounts = this.viewController.getAccounts();
            loadAccountData(accounts, model, JTable1);
        }
    }

    @Override
    public void JButtonCompAC_actionPerformed(ActionEvent event) {
		/*
		 construct a JDialog_AddCompAcc type object
		 set the boundaries and
		 show it
		*/

        JDialog_AddCompAcc pac = new JDialog_AddCompAcc(this, controller);
        pac.setBounds(450, 20, 300, 400);
        pac.show();

        if (newaccount){
            // add row to table
            Collection<Account> accounts = this.viewController.getAccounts();
            loadAccountData(accounts, model, JTable1);
        }
    }

    @Override
    public void JButtonDeposit_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) model.getValueAt(selection, 1);

            // Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(myframe, accnr);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            double deposit = Double.parseDouble(amountDeposit);

            Account acc = viewController.getAccounts().stream()
                    .filter(x -> x.getAccountNum().equals(model.getValueAt(selection, 1))).findFirst().get();

            viewController.deposit(acc, deposit);
            model.setValueAt(String.valueOf(acc.getCurrentBalance()), selection, 5);
        } else {
            JOptionPane.showMessageDialog(JButton_Addinterest, "Please first select an account to deposit to.",
                    "Deposit", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void JButtonWithdraw_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accnr = (String) model.getValueAt(selection, 1);

            // Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(myframe, accnr);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            double withdrawAmount = Double.parseDouble(amountDeposit);

            Account acc = viewController.getAccounts().stream()
                    .filter(x -> x.getAccountNum().equals(model.getValueAt(selection, 1))).findFirst().get();

            viewController.withdraw(acc, withdrawAmount);
            model.setValueAt(String.valueOf(acc.getCurrentBalance()), selection, 5);
        } else {
            JOptionPane.showMessageDialog(JButton_Addinterest, "Please first select an account to withdraw from.",
                    "Withdraw", JOptionPane.WARNING_MESSAGE);
        }
    }

}