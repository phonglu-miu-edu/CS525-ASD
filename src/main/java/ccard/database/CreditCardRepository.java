package ccard.database;

import ccard.model.Copper;
import ccard.model.CreditCardAccount;
import ccard.model.Gold;
import ccard.model.Silver;
import framework.IFinCo;
import framework.model.*;
import framework.repository.Repository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class CreditCardRepository extends Repository {
    public CreditCardRepository(IFinCo finCo) {
        super(finCo);
    }

    @Override
    public void loadAccount(ICustomer customer, JSONArray jsonArray) {
        if (jsonArray == null) return;

        for (Object value : jsonArray) {
            JSONObject o = (JSONObject) value;
            Account a;

            if (o.get("accType").equals("gold")) {
                a = new CreditCardAccount(
                    customer,
                    (String) o.get("accountNum"),
                    new Gold(),
                    "12/31/2030"
                );
            } else if (o.get("accType").equals("silver")) {
                a = new CreditCardAccount(
                    customer,
                    (String) o.get("accountNum"),
                    new Silver(),
                    "12/31/2030"
                );
            } else {
                a = new CreditCardAccount(
                    customer,
                    (String) o.get("accountNum"),
                    new Copper(),
                    "12/31/2030"
                );
            }

            a.setCurrentBalance(Double.parseDouble(o.get("currentBalance").toString()));
            loadEntry(a, (JSONArray) o.get("entries"));
            a.setNotification(new Email(customer.getEmail()));

            customer.addAccount(a);
            this.finCo.getAccounts().add(a);
        }
    }

    @Override
    public void write() {
        write(this.getRepoPath());
    }

    @Override
    public void write(String path) {
        JSONObject jsonObject = new JSONObject();
        JSONArray customers = new JSONArray();

        for (ICustomer customer : this.finCo.getCustomers()) {
            JSONObject c = new JSONObject();
            JSONArray accs = new JSONArray();

            c.put("name", customer.getName());
            c.put("city", customer.getCity());
            c.put("email", customer.getEmail());
            c.put("state", customer.getState());
            c.put("street", customer.getStreet());
            c.put("zip", customer.getZip());

            if (customer.getClass().equals(Organization.class))
                c.put("type", "company");
            else if (customer.getClass().equals(Person.class))
                c.put("type", "person");

            for (IAccount account : customer.getAccounts()) {
                JSONObject a = new JSONObject();
                JSONArray entries = new JSONArray();

                CreditCardAccount creditCardAccount = (CreditCardAccount) account;

                a.put("accountNum", account.getAccountNum());
                a.put("currentBalance", account.getCurrentBalance());
                
                if (creditCardAccount.getType() instanceof Gold) {
                    a.put("accType", "gold");
                } else if (creditCardAccount.getType() instanceof Silver) {
                    a.put("accType", "silver");
                } else if (creditCardAccount.getType() instanceof Copper) {
                    a.put("accType", "copper");
                }

                for (Entry entry : account.getEntryHistory()) {
                    JSONObject e = new JSONObject();

                    e.put("amount", entry.getAmount());
                    e.put("date", entry.getDate().toString());
                    if (entry instanceof DepositEntry) {
                        e.put("type", "deposit");
                    } else {
                        e.put("type", "withdraw");
                    }

                    entries.add(e);
                }

                a.put("entries", entries);
                accs.add(a);
            }

            c.put("accounts", accs);
            customers.add(c);
        }

        jsonObject.put("customers", customers);

        this.write(path, jsonObject);
    }
}
