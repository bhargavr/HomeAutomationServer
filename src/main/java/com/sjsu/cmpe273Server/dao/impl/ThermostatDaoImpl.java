package com.sjsu.cmpe273Server.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mongodb.DBCollection;
import com.mongodb.DuplicateKeyException;
import com.sjsu.cmpe273Server.dao.ThermostatDao;
import com.sjsu.cmpe273Server.model.Thermostat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Service
public class ThermostatDaoImpl implements ThermostatDao{
	@Autowired
	private MongoOperations mongoOperation;
	
	public List<Thermostat> getAll(){
		List<Thermostat> thermostat = new ArrayList<>();
		try{
			thermostat = mongoOperation.findAll(Thermostat.class);
		}catch(Exception e){}
	    return thermostat;
	}

	public Optional<Thermostat> read(String serialNumber){
		Query query = new Query();
		query.addCriteria(Criteria.where("serialNumber").is(serialNumber));
		Thermostat thermostat = mongoOperation.findOne(query, Thermostat.class);
		
		if(thermostat !=null)
			return Optional.of(thermostat);
		else
			return Optional.empty();
	}

	public boolean delete(String serialNumber){
		Query query = new Query();
		query.addCriteria(Criteria.where("serialNumber").is(serialNumber));
		Thermostat thermostat = mongoOperation.findOne(query, Thermostat.class);
		if(thermostat != null)
		{
			mongoOperation.remove(query, Thermostat.class);
			return true;
		}
		else
			return false;
	}
	
	public Optional<Thermostat> create(Thermostat thermostat){
		try{
			mongoOperation.save(thermostat);
			return Optional.of(thermostat);
		}
		catch(DuplicateKeyException e)
		{
			return Optional.empty();
		}
	}
	
	public Optional<Thermostat> update(Thermostat thermostat){
		Query query = new Query();
		query.addCriteria(Criteria.where("serialNumber").is(thermostat.getSerialNumber()));
		Thermostat findThermostat = mongoOperation.findOne(query, Thermostat.class);
		if(findThermostat != null)
		{
			Update update = new Update();
			update.set("manufacturer", thermostat.getManufacturer());
			update.set("modelNumber", thermostat.getModelNumber());
			update.set("serialNumber", thermostat.getSerialNumber());
			update.set("firmwareVersion", thermostat.getFirmwareVersion());
			update.set("thermostatID", thermostat.getThermostatID());
			update.set("temperature", thermostat.getTemperature());
			update.set("operationStatus", thermostat.getOperationStatus());
			
			mongoOperation.upsert(query, update, Thermostat.class);
			return Optional.of(thermostat);
		}
		else
		{
		    return Optional.empty();
		}

	}

	@Override
	public List<Thermostat> getDistinctList() {
		List<Thermostat> thermostat = new ArrayList<>();
		try{
			DBCollection dbCol = mongoOperation.getCollection("HVACSystem");
			thermostat = dbCol.distinct("thermostatID");
		}catch(Exception e){}
	    return thermostat;
	}
}
