package service;

import static javax.transaction.Transactional.TxType.SUPPORTS;
import static javax.transaction.Transactional.TxType.REQUIRED;

import java.util.Collection;
import java.util.List;

import javax.enterprise.inject.Default;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

import domain.Account;
import util.JSONUtil;




@Transactional(SUPPORTS)
@Default
public class AccountServiceDBImpl implements AccountInterface {
	
	@PersistenceContext(unitName = "primary")
	private EntityManager manager;
	
	@Inject
	private JSONUtil util;

	
    public String findAllAccounts() {
    	Query query = manager.createQuery("Select a FROM Account a");
		Collection<Account> accounts = (Collection<Account>) query.getResultList();
		return util.getJSONForObject(accounts);
    }
    
    public Account findAccount(Long id) {
    	return manager.find(Account.class, id);
    }



	@Override
	public Account findAccount(int accountID) {
		return manager.find(Account.class, accountID);
	}

    @Transactional(REQUIRED)
	@Override
	public void createAccount(Account userAccount) {
		manager.persist(userAccount);
	}

	@Transactional(REQUIRED)
	@Override
	public String deleteAccount(Integer accountToRemove) {
		Account AccountInDB = findAccount(accountToRemove);
		if (AccountInDB != null) {
			manager.remove(AccountInDB);
		}
		return "{\"message\": \"Account sucessfully deleted\"}";
	}
	
	
	@Override
	@Transactional(REQUIRED)
	public String updateAccount(int id, String accountToUpdate) {
		Account updatedAccount = util.getObjectForJSON(accountToUpdate, Account.class);
		Account accountFromDB = findAccount(id);
		if (accountToUpdate != null) {
			accountFromDB = updatedAccount;
			manager.merge(accountFromDB);
		}
		return "{\"message\": \"account sucessfully updated\"}";
	}


}
