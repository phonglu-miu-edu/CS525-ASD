package bank.database;

import bank.model.Checking;
import bank.model.Saving;
import framework.FinCo;
import framework.model.*;
import framework.repository.Repository;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class BankRepository extends Repository {
    public BankRepository(FinCo finCo, String repoPath) {
        super(finCo, repoPath);
    }

    @Override
    public void loadAccount(ICustomer customer, JSONArray jsonArray) {
        if (jsonArray == null)
            return;

        for (int i = 0; i < jsonArray.size(); i++) {
            JSONObject o = (JSONObject) jsonArray.get(i);
            IAccount a;

            if (o.get("accType") == null) {
                a = new Account(customer, (String) o.get("accountNum"));
            } else if (o.get("accType").equals("checking")) {
                a = new Checking(customer, (String) o.get("accountNum"));
            } else {
                a = new Saving(customer, (String) o.get("accountNum"));
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

            if (customer.getClass().equals(Company.class))
                c.put("type", "company");
            else if (customer.getClass().equals(Person.class))
                c.put("type", "person");

            for (IAccount account : customer.getAccounts()) {
                JSONObject a = new JSONObject();
                JSONArray entries = new JSONArray();

                a.put("accountNum", account.getAccountNum());
                a.put("currentBalance", account.getCurrentBalance());

                if (account instanceof Checking) {
                    a.put("accType", "checking");
                } else if (account instanceof Saving) {
                    a.put("accType", "saving");
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

        this.write(getRepoPath(), jsonObject);
    }
}
