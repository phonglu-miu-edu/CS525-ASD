package ccard.view;

import ccard.model.CreditCardAccount;
import ccard.report.MonthlyBillingReport;
import framework.command.ReportGenerate;
import framework.model.IAccount;
import framework.model.Person;
import framework.reports.IReport;
import framework.view.*;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.util.Collection;
import java.util.List;

public class CCardView extends FinCoView {
    public ICCardViewController controller;

    public CCardView(ICCardViewController controller, ViewType viewType) {
        super(controller, viewType);
        this.controller = controller;

        setTitle("Credit Card Processing Application");
    }

    @Override
    public void setUpButtons() {
        JButton_PerAC.setText("Add Personal CC account");
        JPanel1.add(JButton_PerAC);
        JButton_PerAC.setBounds(10, 20, 170, 33);

        JButton_CompAC.setText("Add Company CC account");
        JPanel1.add(JButton_CompAC);
        JButton_CompAC.setBounds(180,20,195,33);

        JButton_ReportGenerate.setText("Generate Monthly bills");
        JPanel1.add(JButton_ReportGenerate);
        JButton_ReportGenerate.setBounds(380, 20, 180, 33);

        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468, 104, 96, 33);

        JButton_Withdraw.setText("Charge");
        JPanel1.add(JButton_Withdraw);
        JButton_Withdraw.setBounds(468, 160, 96, 33);

        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468, 220, 96, 30);
    }

    @Override
    public List<String> getTableColumnNames() {
        return List.of("Name", "CC number", "Exp date", "P/C", "Type", "Balance");
    }

    @Override
    public void loadAccountData(Collection<IAccount> accounts, DefaultTableModel model, JTable table) {
        model.setRowCount(0);

        for (IAccount account : accounts) {
            CreditCardAccount ccAccount = (CreditCardAccount) account;
            String customerType = ccAccount.getCustomer() instanceof Person ? "P" : "C";

            Object[] row = new Object[]{
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
        newaccount = false;
    }

    @Override
    public void JButtonPerAC_actionPerformed(ActionEvent event) {
        JDialog_AddPersonalCCAccount addCreditCardAccount = new JDialog_AddPersonalCCAccount(this, controller);
        addCreditCardAccount.setBounds(450, 20, 300, 400);
        addCreditCardAccount.show();

        if (newaccount) {
            Collection<IAccount> accounts = this.viewController.getAccounts();
            loadAccountData(accounts, model, JTable1);
        }
    }

    @Override
    public void JButtonCompAC_actionPerformed(ActionEvent event) {
        JDialog_AddCompanyCCAccount addCompanyCCAccount = new JDialog_AddCompanyCCAccount(this, controller);
        addCompanyCCAccount.setBounds(450, 20, 300, 400);
        addCompanyCCAccount.show();

        if (newaccount) {
            Collection<IAccount> accounts = this.viewController.getAccounts();
            loadAccountData(accounts, model, JTable1);
        }
    }

    @Override
    public void JButtonReportGenerate_actionPerformed(ActionEvent event) {
        Collection<IAccount> all_accounts = viewController.getAccounts();
        IReport report = new MonthlyBillingReport(all_accounts);
        viewController.getFrameworkApplication().getFinCo().setReport(report);

        ReportGenerate generateReport = new ReportGenerate(viewController.getFrameworkApplication().getFinCo());
        viewController.getFrameworkApplication().getCommandManager().invoke(generateReport);

        String reportDetails = report.getReport();

        JDialog_GenReport billFrm = new JDialog_GenReport(this, reportDetails, "Monthly Billing Report");
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
            dep.setBounds(430, 15, 400, 140);
            dep.show();

            double deposit = Double.parseDouble(amountDeposit);

            IAccount acc = viewController.getAccounts().stream()
                                        .filter(x -> x.getAccountNum().equals(model.getValueAt(selection, 1))).findFirst().get();

            viewController.deposit(acc, deposit);
            model.setValueAt(String.valueOf(acc.getCurrentBalance()), selection, 5);
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

            IAccount acc = viewController.getAccounts().stream()
                                        .filter(x -> x.getAccountNum().equals(model.getValueAt(selection, 1))).findFirst().get();

            viewController.withdraw(acc, withdrawAmount);
            model.setValueAt(String.valueOf(acc.getCurrentBalance()), selection, 5);
        } else {
            JOptionPane.showMessageDialog(JButton_AddInterest, "Please first select an account to withdraw from.",
                "Withdraw", JOptionPane.WARNING_MESSAGE);
        }
    }

}