package framework.repository;

import framework.FinCo;
import framework.model.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Repository extends ConnectionDb implements IRepository {
	protected FinCo finCo;
	private String repoPath;

	public Repository(FinCo finCo, String repoPath) {
		this.finCo = finCo;
		this.repoPath = repoPath;
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
			
			if(customer.getClass().equals(Company.class))
					c.put("type", "company");
			else if(customer.getClass().equals(Person.class))
				c.put("type", "person");

			for (IAccount account : customer.getAccounts()) {
				JSONObject a = new JSONObject();
				JSONArray entries = new JSONArray();

				a.put("accountNum", account.getAccountNum());
				a.put("currentBalance", account.getCurrentBalance());

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

		this.write(repoPath, jsonObject);
	}

	@Override
	public void load() {
		JSONObject jsonObject = this.read(repoPath);

		if (jsonObject == null)
			return;

		this.loadCustomer(jsonObject);
	}

	@Override
	public void loadAccount(ICustomer customer, JSONArray jsonArray) {
		if (jsonArray == null)
			return;

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject o = (JSONObject) jsonArray.get(i);

            Account a = new Account(
                customer,
                (String) o.get("accountNum")
            );			

			a.setCurrentBalance(Double.parseDouble(o.get("currentBalance").toString()));
			customer.addAccount(a);
			a.setNotification(new Email(customer.getEmail()));
			
			loadEntry(a, (JSONArray) o.get("entries"));

			this.finCo.getAccounts().add(a);
		}
	}

	@Override
	public void loadEntry(IAccount account, JSONArray jsonArray) {
		if (jsonArray == null)
			return;

		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject o = (JSONObject) jsonArray.get(i);

			if (o.get("type").equals("deposit")) {
				Entry a = new DepositEntry(account, Double.parseDouble(o.get("amount").toString()));

				account.getEntryHistory().add(a);
			} else {
				Entry a = new WithdrawEntry(account, Double.parseDouble(o.get("amount").toString()));

				account.getEntryHistory().add(a);
			}
		}
	}

	@Override
	public void loadCustomer(JSONObject data) {
		JSONArray arr = (JSONArray) data.get("customers");
		if (arr == null)
			return;

		for (Object value : arr) {
			JSONObject o = (JSONObject) value;
			Customer c;

			if (o.get("type") != null && o.get("type").equals("company")) {
				c = new Company((String) o.get("name"), (String) o.get("street"), (String) o.get("city"),
					(String) o.get("state"), Integer.parseInt(o.get("zip").toString()), (String) o.get("email"),
					(String) o.get("noEmployees"));
			} else {
				c = new Person((String) o.get("name"), (String) o.get("street"), (String) o.get("city"),
					(String) o.get("state"), Integer.parseInt(o.get("zip").toString()), (String) o.get("email"),
					(String) o.get("birthDate"));
			}

			this.finCo.getCustomers().add(c);

			loadAccount(c, (JSONArray) o.get("accounts"));
		}
	}

	@Override
	public void setRepoPath(String path) {
		this.repoPath = path;
	}

	@Override
	public String getRepoPath() {
		return repoPath;
	}
}
