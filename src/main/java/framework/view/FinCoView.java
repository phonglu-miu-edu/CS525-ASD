package framework.view;

import framework.FinCo;
import framework.command.ReportGenerate;
import framework.model.IAccount;
import framework.model.Person;
import framework.reports.AllAccountsReport;
import framework.reports.IReport;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.List;

/**
 * A basic JFC based application.
 **/
public class FinCoView extends JFrame {
    /****
     * init variables in the object
     ****/
    public String accountNumber, clientName, street, city, zip, state, accountType, clientType, amountDeposit, email,
        birthDate, noOfEmployee;
    public boolean newaccount;
    public DefaultTableModel model;
    public JTable JTable1;
    public JScrollPane JScrollPane1;
    public FinCoView myFrame;
    public ViewType viewType;
    public IFinCoViewController viewController;
    public FinCo finCo;

    public FinCoView(IFinCoViewController viewController, ViewType viewType) {
        this.viewController = viewController;
        this.finCo = viewController.getFinCo();

        this.viewType = viewType;
        myFrame = this;

        setTitle("Framework Application");
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout(0, 0));
        setSize(580, 300);
        setVisible(false);
        JPanel1.setLayout(null);
        getContentPane().add(BorderLayout.CENTER, JPanel1);
        JPanel1.setBounds(0, 0, 600, 322);
        /*
         * /Add five buttons on the pane /for Adding personal account, Adding company
         * account /Deposit, Withdraw and Exit from the system
         */
        JScrollPane1 = new JScrollPane();
        model = new DefaultTableModel();
        JTable1 = new JTable(model);

        List<String> columnNames = getTableColumnNames();

        for (String column : columnNames) {
            model.addColumn(column);
        }

        newaccount = false;

        JPanel1.add(JScrollPane1);
        JScrollPane1.setBounds(12, 92, 440, 160);
        JScrollPane1.getViewport().add(JTable1);
        JTable1.setBounds(0, 0, 422, 0);

        Collection<IAccount> accounts = this.viewController.getAccounts();

        loadAccountData(accounts, model, JTable1);
        setUpButtons();

        JButton_PerAC.setActionCommand("jbutton");

        SymWindow aSymWindow = new SymWindow();
        this.addWindowListener(aSymWindow);
        SymAction lSymAction = new SymAction();
        JButton_Exit.addActionListener(lSymAction);
        JButton_PerAC.addActionListener(lSymAction);
        JButton_CompAC.addActionListener(lSymAction);
        JButton_Deposit.addActionListener(lSymAction);
        JButton_Withdraw.addActionListener(lSymAction);
        JButton_AddInterest.addActionListener(lSymAction);
        JButton_ReportGenerate.addActionListener(lSymAction);
    }

    public JPanel JPanel1 = new JPanel();
    public JButton JButton_PerAC = new JButton();
    public JButton JButton_CompAC = new JButton();
    public JButton JButton_Deposit = new JButton();
    public JButton JButton_Withdraw = new JButton();
    public JButton JButton_AddInterest = new JButton();
    public JButton JButton_ReportGenerate = new JButton();
    public JButton JButton_Exit = new JButton();

    public void setUpButtons() {
        JButton_PerAC.setText("Add personal account");
        JPanel1.add(JButton_PerAC);
        JButton_PerAC.setBounds(24, 20, 192, 33);

        JButton_CompAC.setText("Add company account");
        JButton_CompAC.setActionCommand("jbutton");
        JPanel1.add(JButton_CompAC);
        JButton_CompAC.setBounds(240, 20, 190, 33);

        JButton_AddInterest.setText("Add Interest");
        JPanel1.add(JButton_AddInterest);
        JButton_AddInterest.setBounds(448, 20, 106, 33);

        JButton_Deposit.setText("Deposit");
        JPanel1.add(JButton_Deposit);
        JButton_Deposit.setBounds(468, 90, 96, 33);

        JButton_Withdraw.setText("Withdraw");
        JPanel1.add(JButton_Withdraw);
        JButton_Withdraw.setBounds(468, 140, 96, 33);

        JButton_ReportGenerate.setText("Generate Report");
        JPanel1.add(JButton_ReportGenerate);
        JButton_ReportGenerate.setBounds(468, 190, 116, 33);

        JButton_Exit.setText("Exit");
        JPanel1.add(JButton_Exit);
        JButton_Exit.setBounds(468, 248, 96, 30);
    }

    void exitApplication() {
        try {
            this.setVisible(false); // hide the Frame
            this.dispose(); // free the system resources
            System.exit(0); // close the application
        } catch (Exception e) {
        }
    }

    void BankFrm_windowClosing(WindowEvent event) {
        // to do: code goes here.

        try {
            this.exitApplication();
        } catch (Exception e) {
        }
    }

    class SymWindow extends java.awt.event.WindowAdapter {
        public void windowClosing(WindowEvent event) {
            Object object = event.getSource();
            if (object == FinCoView.this)
                BankFrm_windowClosing(event);
        }
    }

    class SymAction implements java.awt.event.ActionListener {
        public void actionPerformed(ActionEvent event) {
            Object object = event.getSource();
            if (object == JButton_Exit)
                JButtonExit_actionPerformed(event);
            else if (object == JButton_PerAC)
                JButtonPerAC_actionPerformed(event);
            else if (object == JButton_CompAC)
                JButtonCompAC_actionPerformed(event);
            else if (object == JButton_Deposit)
                JButtonDeposit_actionPerformed(event);
            else if (object == JButton_Withdraw)
                JButtonWithdraw_actionPerformed(event);
            else if (object == JButton_AddInterest)
                JButtonAddInterest_actionPerformed(event);
            else if (object == JButton_ReportGenerate)
                JButtonReportGenerate_actionPerformed(event);;
        }
    }

    public List<String> getTableColumnNames() {
        return List.of("Name", "CC number", "Exp date", "Type", "Balance");
    }

    public void loadAccountData(Collection<IAccount> accounts, DefaultTableModel model, JTable table) {
        model.setRowCount(0);

        if (accounts != null) {
            for (IAccount account : accounts) {
                Object[] row = new Object[8];
                row[0] = account.getAccountNum();
                row[1] = account.getCustomer().getName();
                row[2] = account.getCustomer().getCity();

                if (account.getCustomer() instanceof Person) {
                    row[3] = 'P';
                } else {
                    row[3] = 'C';
                }

                row[4] = account.getCurrentBalance();

                model.addRow(row);
            }
        }

        table.getSelectionModel().setAnchorSelectionIndex(-1);
        newaccount = false;
    }

    // When the Exit button is pressed this code gets executed
    // this will exit from the system
    void JButtonExit_actionPerformed(ActionEvent event) {
        System.exit(0);
    }

    public void JButtonPerAC_actionPerformed(ActionEvent event) {
        JDialog_AddPAcc pac = new JDialog_AddPAcc(myFrame);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (newaccount) {
            // add row to table
            try {
                Integer.parseInt(zip);
            }
            catch( Exception e ) {
                zip = "0";
            }

            this.viewController.createCustomer(accountNumber, clientName, street, city, state, Integer.parseInt(zip), email, birthDate);
            Collection<IAccount> accounts = this.viewController.getAccounts();
            loadAccountData(accounts, model, JTable1);
        }
    }

    public void JButtonCompAC_actionPerformed(ActionEvent event) {
        JDialog_AddCompAcc pac = new JDialog_AddCompAcc(myFrame);
        pac.setBounds(450, 20, 300, 330);
        pac.show();

        if (newaccount) {
            // add row to table
            try {
                Integer.parseInt(zip);
            }
            catch( Exception e ) {
                zip = "0";
            }

            this.viewController.createOrg(accountNumber, clientName, street, city, state, Integer.parseInt(zip), email,
                noOfEmployee);
            Collection<IAccount> accounts = this.viewController.getAccounts();
            loadAccountData(accounts, model, JTable1);
        }
    }

    public void JButtonReportGenerate_actionPerformed(ActionEvent event) {
        Collection<IAccount> all_accounts = viewController.getAccounts();
        IReport report = new AllAccountsReport(all_accounts);
        this.finCo.setReport(report);
        ReportGenerate generateReport = new ReportGenerate(this.finCo);
        this.finCo.getCommandManager().invoke(generateReport);

        String reportDetails = report.getReport();

        JDialog_GenReport billFrm = new JDialog_GenReport(this, reportDetails, "Report Details");
        billFrm.setBounds(450, 20, 400, 350);
        billFrm.show();
    }

    public void JButtonDeposit_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accountNumber = (String) model.getValueAt(selection, 0);

            // Show the dialog for adding deposit amount for the current mane
            JDialog_Deposit dep = new JDialog_Deposit(myFrame, accountNumber);
            dep.setBounds(430, 15, 275, 140);
            dep.show();

            double deposit = Double.parseDouble(amountDeposit);

            IAccount acc = viewController.getAccounts().stream()
                                        .filter(x -> x.getAccountNum().equals(model.getValueAt(selection, 0))).findFirst().get();

            viewController.deposit(acc, deposit);
            model.setValueAt(String.valueOf(acc.getCurrentBalance()), selection, 4);
        } else {
            JOptionPane.showMessageDialog(JButton_AddInterest, "Please first select an account to deposit to.",
                "Deposit", JOptionPane.WARNING_MESSAGE);
        }
    }

    public void JButtonWithdraw_actionPerformed(ActionEvent event) {
        // get selected name
        int selection = JTable1.getSelectionModel().getMinSelectionIndex();
        if (selection >= 0) {
            String accountNumber = (String) model.getValueAt(selection, 0);

            // Show the dialog for adding withdraw amount for the current mane
            JDialog_Withdraw wd = new JDialog_Withdraw(myFrame, accountNumber);
            wd.setBounds(430, 15, 275, 140);
            wd.show();

            double withdrawAmount = Double.parseDouble(amountDeposit);

            IAccount acc = viewController.getAccounts().stream()
                                        .filter(x -> x.getAccountNum().equals(model.getValueAt(selection, 0))).findFirst().get();

            viewController.withdraw(acc, withdrawAmount);
            model.setValueAt(String.valueOf(acc.getCurrentBalance()), selection, 4);
        } else {
            JOptionPane.showMessageDialog(JButton_AddInterest, "Please first select an account to withdraw from.",
                "Withdraw", JOptionPane.WARNING_MESSAGE);
        }
    }

    void JButtonAddInterest_actionPerformed(ActionEvent event) {
        if (JOptionPane.showConfirmDialog(JButton_AddInterest, "Add interest to all accounts",
            "Add interest to all accounts", JOptionPane.YES_NO_OPTION) == JOptionPane.OK_OPTION) {
            viewController.addInterest();
            loadAccountData(viewController.getAccounts(), model, JTable1);
        }
    }
}
