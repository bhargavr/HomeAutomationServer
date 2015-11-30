/**
 * 
 */
package com.sjsu.cmpe273Server.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;

import com.mongodb.DuplicateKeyException;
import com.sjsu.cmpe273Server.dao.AlertMessageDao;
import com.sjsu.cmpe273Server.model.AlertMessages;
import com.sjsu.cmpe273Server.model.SecurityAlarm;

/**
 * @author bhargav
 *
 */
@Service
public class AlertMessageDaoImpl implements AlertMessageDao {

	
	@Autowired
	private MongoOperations mongoOperation;
	/* (non-Javadoc)
	 * @see com.sjsu.cmpe273Server.dao.AlertMessageDao#getAll()
	 */
	@Override
	public List<AlertMessages> getAll() {
		List<AlertMessages> alertMessages = new ArrayList<>();
		try{
			alertMessages = mongoOperation.findAll(AlertMessages.class);
		}catch(Exception e){}
	    return alertMessages;
	}

	/* (non-Javadoc)
	 * @see com.sjsu.cmpe273Server.dao.AlertMessageDao#read(java.lang.String)
	 */
	@Override
	public Optional<AlertMessages> read(String serialNumber) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sjsu.cmpe273Server.dao.AlertMessageDao#create(com.sjsu.cmpe273Server.model.AlertMessages)
	 */
	@Override
	public Optional<AlertMessages> create(AlertMessages alertMessages) {
		try{
			mongoOperation.save(alertMessages);
			return Optional.of(alertMessages);
		}
		catch(DuplicateKeyException e)
		{
			return Optional.empty();
		}
	}

	/* (non-Javadoc)
	 * @see com.sjsu.cmpe273Server.dao.AlertMessageDao#update(com.sjsu.cmpe273Server.model.AlertMessages)
	 */
	@Override
	public Optional<AlertMessages> update(AlertMessages alertMessages) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.sjsu.cmpe273Server.dao.AlertMessageDao#delete(java.lang.String)
	 */
	@Override
	public boolean delete(String serialNumber) {
		// TODO Auto-generated method stub
		return false;
	}

}
