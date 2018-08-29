package service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;

import org.hibernate.mapping.Value;
import domain.Account;
import util.JSONUtil;

@Alternative
public class AccountService implements AccountInterface {

	private Map<Integer, Account> accountMap;
	@Inject
	private JSONUtil util;
	private int count = 0;

	public AccountService() {
		accountMap = new HashMap<Integer, Account>();
	}


	@Override
	public Account findAccount(int accountID) {
		boolean countExists = accountMap.containsKey(accountID);
		if (countExists) {
		System.out.println(accountID);
		return accountMap.get(accountID);
		}
		return null;
	}

	@Override
	public void createAccount(Account userAccount) {
		accountMap.put(count, userAccount);
		count++;
		
	}

	@Override
	public String deleteAccount(Integer accountToRemove) {
		boolean countExists = accountMap.containsKey(accountToRemove);
		if (countExists) {
			accountMap.remove(accountToRemove);
		}
		return "{\"message\": \"Account sucessfully deleted\"}";
	}


	@Override
	public String findAllAccounts() {
		return util.getJSONForObject(accountMap.values());
	}
	
	@Override
	public String updateAccount(int id, String accountToUpdate) {
		Account newAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		accountMap.put(id, newAccount);
		return accountToUpdate;
	}

	
}
