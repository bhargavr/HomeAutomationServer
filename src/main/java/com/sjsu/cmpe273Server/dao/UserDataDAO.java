/**
 * 
 */
package com.sjsu.cmpe273Server.dao;

import java.util.List;

import com.sjsu.cmpe273Server.model.Account;

/**
 * @author bhargav
 *
 */
public interface UserDataDAO {

	void save(Account account);
	
	List<Account> findAll();
	
}
