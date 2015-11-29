package com.sjsu.cmpe273Server.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sjsu.cmpe273Server.dao.UserDataDAO;
import com.sjsu.cmpe273Server.model.Account;
import com.sjsu.cmpe273Server.service.UserDataService;

@Service
public class UserDataServiceImpl implements UserDataService {

	@Autowired
	private UserDataDAO userDataDAO;
	
	@Override
	public void save(Account account) throws Exception {
		userDataDAO.save(account);
	}

	@Override
	public List<Account> findAll() throws Exception {
		return userDataDAO.findAll();
	}

}
