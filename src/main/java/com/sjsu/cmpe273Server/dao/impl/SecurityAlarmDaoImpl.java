package com.sjsu.cmpe273Server.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mongodb.DuplicateKeyException;
import com.sjsu.cmpe273Server.dao.SecurityAlarmDao;
import com.sjsu.cmpe273Server.model.SecurityAlarm;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Service
public class SecurityAlarmDaoImpl implements SecurityAlarmDao {
	@Autowired
	private MongoOperations mongoOperation;
	
	public List<SecurityAlarm> getAll(){
		List<SecurityAlarm> securityAlarm = new ArrayList<>();
		try{
			securityAlarm = mongoOperation.findAll(SecurityAlarm.class);
		}catch(Exception e){}
	    return securityAlarm;
	}

	public Optional<SecurityAlarm> read(String serialNumber){
		Query query = new Query();
		query.addCriteria(Criteria.where("serialNumber").is(serialNumber));
		SecurityAlarm securityAlarm = mongoOperation.findOne(query, SecurityAlarm.class);
		
		if(securityAlarm !=null)
			return Optional.of(securityAlarm);
		else
			return Optional.empty();
	}

	public boolean delete(String serialNumber){
		Query query = new Query();
		query.addCriteria(Criteria.where("serialNumber").is(serialNumber));
		SecurityAlarm securityAlarm = mongoOperation.findOne(query, SecurityAlarm.class);
		if(securityAlarm != null)
		{
			mongoOperation.remove(query, SecurityAlarm.class);
			return true;
		}
		else
			return false;
	}
	
	public Optional<SecurityAlarm> create(SecurityAlarm securityAlarm){
		try{
			mongoOperation.save(securityAlarm);
			return Optional.of(securityAlarm);
		}
		catch(DuplicateKeyException e)
		{
			return Optional.empty();
		}
	}
	
	public Optional<SecurityAlarm> update(SecurityAlarm securityAlarm){
		Query query = new Query();
		query.addCriteria(Criteria.where("serialNumber").is(securityAlarm.getSerialNumber()));
		SecurityAlarm findSecurityAlarm = mongoOperation.findOne(query, SecurityAlarm.class);
		if(findSecurityAlarm != null)
		{
			Update update = new Update();
			update.set("manufacturer", securityAlarm.getManufacturer());
			update.set("modelNumber", securityAlarm.getModelNumber());
			update.set("serialNumber", securityAlarm.getSerialNumber());
			update.set("firmwareVersion", securityAlarm.getFirmwareVersion());
			update.set("enableAlarm", securityAlarm.getEnableAlarm());
			update.set("alarmStatus", securityAlarm.getAlarmStatus());

			mongoOperation.upsert(query, update, SecurityAlarm.class);
			return Optional.of(securityAlarm);
		}
		else
		{
		    return Optional.empty();
		}

	}
}
