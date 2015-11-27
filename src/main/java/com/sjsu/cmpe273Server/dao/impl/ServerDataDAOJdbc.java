/**
 * 
 */
package com.sjsu.cmpe273Server.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Service;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.sjsu.cmpe273Server.dao.ServerDataDAO;
import com.sjsu.cmpe273Server.model.ServerData;

/**
 * @author bhargav
 *
 */
@Service
public class ServerDataDAOJdbc implements ServerDataDAO {

	@Autowired
	private MongoOperations mongoOperation;
	
	/* (non-Javadoc)
	 * @see com.sjsu.cmpe273.dao.ServerDataDAO#save(com.sjsu.cmpe273.model.ServerDocument)
	 */
	public void save(ServerData serverDocument) {
		
		mongoOperation.save(serverDocument);
	}

	public List<ServerData> findAll() {
		return mongoOperation.findAll(ServerData.class);
	}

	@Override
	public void update(String name, Integer val) {
		Query query2 = new Query();
		query2.addCriteria(Criteria.where("clientName").is(name));
		ServerData serverData = mongoOperation.findOne(query2, ServerData.class);
		List<Map<String,Object>> logList = null;
		Query query = new Query();
		query.addCriteria(Criteria.where("clientName").is(name));
		if(serverData.getSensorData() != null){
			logList = serverData.getSensorData();
		}else{
			logList = new ArrayList<Map<String,Object>>();
		}
		
		Map<String,Object> log = new HashMap<String,Object>();
		log.put("logName", "temp");
		log.put("logValue", val);
		log.put("sensorDataCreateTime", new Date());
		logList.add(log);
		
		Update update = new Update();
		update.set("sensorData", logList);
		
		mongoOperation.upsert(query, update, ServerData.class);
	}

	@Override
	public void delete(String name) {
		Query query2 = new Query();
		query2.addCriteria(Criteria.where("clientName").is(name));
		ServerData serverData = mongoOperation.findOne(query2, ServerData.class);
		mongoOperation.remove(serverData);
		
	}

	@Override
	public ServerData getServerObject(String name) {
		Query query2 = new Query();
		query2.addCriteria(Criteria.where("clientName").is(name));
		ServerData serverData = mongoOperation.findOne(query2, ServerData.class);
		return serverData;
	}
}
