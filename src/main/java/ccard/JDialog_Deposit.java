package ccard;

import ccard.framework.JDialog_BasicNameAmount;

public class JDialog_Deposit extends JDialog_BasicNameAmount {
    private CardFrm parentframe;

    @Override
    public void onOKClicked(String name, String amount) {
        this.parentframe.clientName = name;
        this.parentframe.amountDeposit = amount;
    }

    public JDialog_Deposit(CardFrm parent, String name) {
        super(parent, "Deposit", name);
        parentframe = parent;
    }
}