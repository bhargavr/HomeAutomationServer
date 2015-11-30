package com.sjsu.cmpe273Server.dao.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.mongodb.DuplicateKeyException;
import com.sjsu.cmpe273Server.dao.OutDoorCameraDao;
import com.sjsu.cmpe273Server.model.OutDoorCamera;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

@Service
public class OutDoorCameraDaoImpl implements OutDoorCameraDao{
	@Autowired
	private MongoOperations mongoOperation;
	
	public List<OutDoorCamera> getAll(){
		List<OutDoorCamera> outDoorCamera = new ArrayList<>();
		try{
			outDoorCamera = mongoOperation.findAll(OutDoorCamera.class);
		}catch(Exception e){}
	    return outDoorCamera;
	}

	public Optional<OutDoorCamera> read(String serialNumber){
		Query query = new Query();
		query.addCriteria(Criteria.where("serialNumber").is(serialNumber));
		OutDoorCamera outDoorCamera = mongoOperation.findOne(query, OutDoorCamera.class);
		
		if(outDoorCamera !=null)
			return Optional.of(outDoorCamera);
		else
			return Optional.empty();
	}

	public boolean delete(String serialNumber){
		Query query = new Query();
		query.addCriteria(Criteria.where("serialNumber").is(serialNumber));
		OutDoorCamera outDoorCamera = mongoOperation.findOne(query, OutDoorCamera.class);
		if(outDoorCamera != null)
		{
			mongoOperation.remove(query, OutDoorCamera.class);
			return true;
		}
		else
			return false;
	}
	
	public Optional<OutDoorCamera> create(OutDoorCamera outDoorCamera){
		try{
			mongoOperation.save(outDoorCamera);
			return Optional.of(outDoorCamera);
		}
		catch(DuplicateKeyException e)
		{
			return Optional.empty();
		}
	}
	
	public Optional<OutDoorCamera> update(OutDoorCamera outDoorCamera){
		Query query = new Query();
		query.addCriteria(Criteria.where("serialNumber").is(outDoorCamera.getSerialNumber()));
		OutDoorCamera findOutDoorCamera = mongoOperation.findOne(query, OutDoorCamera.class);
		if(findOutDoorCamera != null)
		{
			Update update = new Update();
			update.set("manufacturer", outDoorCamera.getManufacturer());
			update.set("modelNumber", outDoorCamera.getModelNumber());
			update.set("serialNumber", outDoorCamera.getSerialNumber());
			update.set("firmwareVersion", outDoorCamera.getFirmwareVersion());
			update.set("cameraID", outDoorCamera.getCameraID());
			update.set("enableInfrared", outDoorCamera.getEnableInfrared());
			update.set("motionDetection", outDoorCamera.getMotionDetection());
			update.set("enableRecording", outDoorCamera.getEnableRecording());
			update.set("intrusion", outDoorCamera.getIntrusion());
			
			mongoOperation.upsert(query, update, OutDoorCamera.class);
			return Optional.of(outDoorCamera);
		}
		else
		{
		    return Optional.empty();
		}

	}
}
