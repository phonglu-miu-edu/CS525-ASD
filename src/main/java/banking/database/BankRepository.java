package banking.database;

import framework.IFinCo;
import framework.database.Repository;
import framework.models.Customer;
import org.json.simple.JSONArray;


public class BankRepository extends Repository {
	public BankRepository(IFinCo finco) {
		super(finco);
	}

	@Override
	public void loadAccount(Customer customer, JSONArray jsonArray) {
	}

	@Override
	public void write(String path) {

	}
}
