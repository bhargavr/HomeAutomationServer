package com.sjsu.cmpe273Server.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mongodb.DuplicateKeyException;
import com.sjsu.cmpe273Server.dao.InDoorCameraDao;
import com.sjsu.cmpe273Server.model.InDoorCamera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Service
public class InDoorCameraDaoImpl implements InDoorCameraDao  {
	
	@Autowired
	private MongoOperations mongoOperation;
	
	public List<InDoorCamera> getAll(){
		List<InDoorCamera> inDoorCamera = new ArrayList<>();
		try{
			inDoorCamera = mongoOperation.findAll(InDoorCamera.class);
		}catch(Exception e){}
	    return inDoorCamera;
	}

	public Optional<InDoorCamera> read(String serialNumber){
		Query query = new Query();
		query.addCriteria(Criteria.where("serialNumber").is(serialNumber));
		InDoorCamera inDoorCamera = mongoOperation.findOne(query, InDoorCamera.class);
		
		if(inDoorCamera !=null)
			return Optional.of(inDoorCamera);
		else
			return Optional.empty();
	}

	public boolean delete(String serialNumber){
		Query query = new Query();
		query.addCriteria(Criteria.where("serialNumber").is(serialNumber));
		InDoorCamera inDoorCamera = mongoOperation.findOne(query, InDoorCamera.class);
		if(inDoorCamera != null)
		{
			mongoOperation.remove(query, InDoorCamera.class);
			return true;
		}
		else
			return false;
	}
	
	public Optional<InDoorCamera> create(InDoorCamera inDoorCamera){
		try{
			mongoOperation.save(inDoorCamera);
			return Optional.of(inDoorCamera);
		}
		catch(DuplicateKeyException e)
		{
			return Optional.empty();
		}
	}
	
	public Optional<InDoorCamera> update(InDoorCamera inDoorCamera){
		Query query = new Query();
		query.addCriteria(Criteria.where("serialNumber").is(inDoorCamera.getSerialNumber()));
		InDoorCamera findInDoorCamera = mongoOperation.findOne(query, InDoorCamera.class);
		if(findInDoorCamera != null)
		{
			Update update = new Update();
			update.set("manufacturer", inDoorCamera.getManufacturer());
			update.set("modelNumber", inDoorCamera.getModelNumber());
			update.set("serialNumber", inDoorCamera.getSerialNumber());
			update.set("firmwareVersion", inDoorCamera.getFirmwareVersion());
			update.set("cameraID", inDoorCamera.getCameraID());
			update.set("motionDetection", inDoorCamera.getMotionDetection());
			update.set("enableRecording", inDoorCamera.getEnableRecording());
			update.set("intrusion", inDoorCamera.getIntrusion());
			
			mongoOperation.upsert(query, update, InDoorCamera.class);
			return Optional.of(inDoorCamera);
		}
		else
		{
		    return Optional.empty();
		}

	}
}
