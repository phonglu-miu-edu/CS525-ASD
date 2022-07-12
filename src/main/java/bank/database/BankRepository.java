package banking.database;

import framework.database.Repository;
import framework.IFinco;
import framework.models.*;
import org.json.simple.JSONArray;


public class BankRepository extends Repository {
	public BankRepository(IFinco finco) {
		super(finco);
	}

	@Override
	public void loadAccount(Customer customer, JSONArray jsonArray) {
	}

	@Override
	public void write(String path) {

	}
}
