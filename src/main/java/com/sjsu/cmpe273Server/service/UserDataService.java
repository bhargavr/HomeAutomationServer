/**
 * 
 */
package com.sjsu.cmpe273Server.service;

import java.util.List;

import com.sjsu.cmpe273Server.model.Account;

/**
 * @author bhargav
 *
 */
public interface UserDataService {

	void save(Account account) throws Exception;
	
	List<Account> findAll() throws Exception;
	
}
