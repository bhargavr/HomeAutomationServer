/**
 * 
 */
package com.sjsu.cmpe273Server.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.sjsu.cmpe273Server.dao.UserDataDAO;
import com.sjsu.cmpe273Server.model.Account;

/**
 * @author bhargav
 *
 */
@Service
public class UserDataDAOJdbc implements UserDataDAO {

	@Autowired
	private MongoOperations mongoOperation;
	
	/* (non-Javadoc)
	 * @see com.sjsu.cmpe273.dao.ServerDataDAO#save(com.sjsu.cmpe273.model.Account)
	 */
	public void save(Account account) {
		
		mongoOperation.save(account);
	}

	public List<Account> findAll() {
		return mongoOperation.findAll(Account.class);
	}
	
}
