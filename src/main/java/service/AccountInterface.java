package service;

import java.util.List;

import domain.Account;

interface AccountInterface {
	
public String findAllAccounts();

public Account findAccount(int accountID);

public void createAccount(Account userAccount);

public String deleteAccount(Integer accountToRemove);

public String updateAccount(int id, String accountToUpdate);

}
