package application;

import domain.Account;
import service.AccountService;

public class App {

		public static void main(String[] args) {
			AccountService service = new AccountService();
			Account joeBloggs = new Account("Joe", "Bloggs", "1234");
			Account janeBloggs = new Account("Jane", "Bloggs", "1234");
			service.createAccount(joeBloggs);
			service.createAccount(janeBloggs);
			service.findAccount(1);
		}

}
