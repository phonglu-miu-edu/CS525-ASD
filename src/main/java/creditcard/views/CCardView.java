package creditcard.views;

import creditcard.models.CreditCardAccount;
import creditcard.reports.MonthlyBillingReport;
import framework.commands.GenerateReportOperation;
import framework.IFramework;
import framework.models.Account;
import framework.views.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.List;

public class CCardView extends FinCoView  {
    private JButton JButton_GenerateReport;
    public ICCardViewController controller;

    public CCardView(ICCardViewController controller, VIEW_TYPE ui_types) {
        super(controller, ui_types);
        this.controller = controller;
        setTitle("Credit Card Processing Application");

        SymAction lSymAction = new SymAction();
        JButton_GenerateReport.addActionListener(lSymAction);
    }

    @Override
    public void setUpButtons() {
        JButton_PerAC.setText("Add Credit-Card account");
        JPanel1.add(JButton_PerAC);
        JButton_PerAC.setBounds(24,20,192,33);

        JButton_GenerateReport = new JButton();
        JButton_GenerateReport.setText("Generate Monthly bills");
        JPanel1.add(JButton_GenerateReport);
        JButton_GenerateReport.setBounds(220,20,192,33);

        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468,104,96,33);

        JButton_Withdraw.setText("Charge");
        JPanel1.add(JButton_Withdraw);
        JButton_Withdraw.setBounds(468,160,96,33);

        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468,220,96,30);
    }

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_GenerateReport)
                JButtonGenerateReport_actionPerformed(event);
        }
    }

    @Override
    public List<String> getTableColumnNames() {
        return List.of("Name", "CC number", "Exp date", "Type", "Balance");
    }

    @Override
    public void loadAccountData(Collection<Account> accounts, DefaultTableModel model, JTable table) {
        model.setRowCount(0);

        for (Account account : accounts) {
            CreditCardAccount ccAccount = (CreditCardAccount) account;

            Object[] row = new Object[] {
                    ccAccount.getCustomer().getName(),
                    ccAccount.getAccountNum(),
                    ccAccount.getExpiryDate(),
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
        JDialog_AddCreditCardAccount addCreditCardAccount = new JDialog_AddCreditCardAccount(this, controller);
        addCreditCardAccount.setBounds(450, 20, 300, 400);
        addCreditCardAccount.setVisible(true);

        if (newaccount){
            // add row to table
            Collection<Account> accounts = this.viewController.getAccounts();
            loadAccountData(accounts, model, JTable1);
        }
    }

    public void JButtonGenerateReport_actionPerformed(ActionEvent event) {
        IFramework framework = controller.getFrameworkApplication();
        Collection<Account> all_accounts = controller.getAccounts();
        MonthlyBillingReport billingReport = new MonthlyBillingReport(all_accounts);
        framework.getFinCo().setReport(billingReport);
        GenerateReportOperation generateReport = new GenerateReportOperation(framework.getFinCo());
        framework.getOperationManager().invoke(generateReport);

        String billingReportDetails = billingReport.getBillingReport();

        JDialog_GenReport billFrm = new JDialog_GenReport(this, billingReportDetails, "Monthly billing report");
        billFrm.setBounds(450, 20, 400, 350);
        billFrm.show();
    }

    @Override
    public void JButtonDeposit_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accountNumber = (String) model.getValueAt(selection, 1);

            // Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(myFrame, accountNumber);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            double deposit = Double.parseDouble(amountDeposit);

            Account acc = viewController.getAccounts().stream()
                    .filter(x -> x.getAccountNum().equals(model.getValueAt(selection, 1))).findFirst().get();

            viewController.deposit(acc, deposit);
            model.setValueAt(String.valueOf(acc.getCurrentBalance()), selection, 4);
        } else {
            JOptionPane.showMessageDialog(JButton_AddInterest, "Please first select an account to deposit to.",
                    "Deposit", JOptionPane.WARNING_MESSAGE);
        }
    }

    @Override
    public void JButtonWithdraw_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accountNumber = (String) model.getValueAt(selection, 1);

            // Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(myFrame, accountNumber);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            double withdrawAmount = Double.parseDouble(amountDeposit);

            Account acc = viewController.getAccounts().stream()
                    .filter(x -> x.getAccountNum().equals(model.getValueAt(selection, 1))).findFirst().get();

            viewController.withdraw(acc, withdrawAmount);
            model.setValueAt(String.valueOf(acc.getCurrentBalance()), selection, 4);
        } else {
            JOptionPane.showMessageDialog(JButton_AddInterest, "Please first select an account to withdraw from.",
                    "Withdraw", JOptionPane.WARNING_MESSAGE);
        }
    }

}