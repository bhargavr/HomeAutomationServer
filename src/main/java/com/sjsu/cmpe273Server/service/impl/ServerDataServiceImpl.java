/**
 * 
 */
package com.sjsu.cmpe273Server.service.impl;

import com.sjsu.cmpe273Server.dao.SequenceDao;
import com.sjsu.cmpe273Server.dao.ServerDataDAO;
import com.sjsu.cmpe273Server.dao.impl.InDoorCameraDaoImpl;
import com.sjsu.cmpe273Server.dao.impl.OutDoorCameraDaoImpl;
import com.sjsu.cmpe273Server.dao.impl.SecurityAlarmDaoImpl;
import com.sjsu.cmpe273Server.dao.impl.ThermostatDaoImpl;
import com.sjsu.cmpe273Server.model.HVACSystem;
import com.sjsu.cmpe273Server.model.InDoorCamera;
import com.sjsu.cmpe273Server.model.OutDoorCamera;
import com.sjsu.cmpe273Server.model.SecuritySystem;
import com.sjsu.cmpe273Server.model.ServerData;
import com.sjsu.cmpe273Server.model.Thermostat;
import com.sjsu.cmpe273Server.service.ServerDataService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author bhargav
 *
 */
@Service
public class ServerDataServiceImpl implements ServerDataService {

	private static final String SERVER_SEQ_KEY = "client";

	@Autowired
	private SequenceDao sequenceDao;

	@Autowired
	private ServerDataDAO serverDataDAO;
	
	@Autowired
	private InDoorCameraDaoImpl inDoorCameraDao;
	
	@Autowired
	private OutDoorCameraDaoImpl outDoorCameraDao;
	
	@Autowired
	private ThermostatDaoImpl thermostatDao;
	
	@Autowired
	private SecurityAlarmDaoImpl securityAlarmDao;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.sjsu.cmpe273.service.ServerDataService#save(java.lang.String)
	 */
	public void save(String name) throws Exception {

		ServerData serverData = new ServerData();

		Random rand = new Random();

		// serverData.setId(sequenceDao.getNextSequenceId(SERVER_SEQ_KEY));
		serverData.setId(rand.nextInt(50) + 1);
		serverData.setClientName(name);
		serverDataDAO.save(serverData);

		System.out.println(serverData);

	}

	@Override
	public List<ServerData> findAll() throws Exception {
		return serverDataDAO.findAll();
	}

	@Override
	public void update(String name, Integer val) throws Exception {
		serverDataDAO.update(name, val);
	}

	@Override
	public void delete(String name) throws Exception {
		serverDataDAO.delete(name);
	}

	@Override
	public Map<String,Object> getChartData(String name) throws Exception {
		ServerData serverData = serverDataDAO.getServerObject(name);
		List<Map<String, Object>> logList = serverData.getSensorData();
		List<Object> tempLog = new ArrayList<Object>();
		List<List<Object>> result = new ArrayList<List<Object>>();
		Map<String,Object> resultObj = new HashMap<String,Object>();
		int i = 1;
		int j = 0;
		if (logList != null) {
			// iterate
			for (Map<String, Object> obj : logList) {
				tempLog = new ArrayList<Object>();
				tempLog.add("Snapshot#" + i++);
				tempLog.add(obj.get("logValue"));
				result.add(tempLog);
				j =  (int)obj.get("logValue");
			}
		} else {
			result.add(tempLog);
		}
		resultObj.put("data", result);
		if(j < 60){
			resultObj.put("message",serverData.getClientName() + " :  Temperature Looks Cold !!");
			resultObj.put("color","blue");
		}else if(j > 80){
			resultObj.put("message",serverData.getClientName() + " :  Temperature Looks Hot !!");
			resultObj.put("color","red");
		}else{
			resultObj.put("message",serverData.getClientName() + " :  Temperature Looks Normal !!");
			resultObj.put("color","white");
		}
		
		
		return resultObj;
	}

	@Override
	public ServerData findServerObj(String name) throws Exception {
		return serverDataDAO.getServerObject(name);
	}

	@Override
	public Map<String, Object> getDeviceData(String userName) throws Exception {
		Map<String,Object> resultObj = new HashMap<String,Object>();
		List<InDoorCamera> listInCamera = new ArrayList<InDoorCamera>();
		List<Thermostat> thermoList = new ArrayList<Thermostat>();
		
		List<InDoorCamera> listofAllIndoorCam = inDoorCameraDao.getAll();
		List<Thermostat> listofAllThermostat = thermostatDao.getAll();
		
		for(InDoorCamera inDoorCamObj : listofAllIndoorCam){
			if(indoorCamCheck(listInCamera, inDoorCamObj)){
				listInCamera.add(inDoorCamObj);
			}
		}
		
		for(Thermostat thermoObj : listofAllThermostat){
			if(thermoCheck(thermoList, thermoObj)){
				thermoList.add(thermoObj);
			}
		}
		
		resultObj.put("security",listInCamera);
		resultObj.put("hvac",thermoList);
		return resultObj;
	}

	private boolean indoorCamCheck(List<InDoorCamera> listInCamera, InDoorCamera inDoorCamObj) {
		for(InDoorCamera inCam : listInCamera){
			if(inCam.getCameraID() ==inDoorCamObj.getCameraID())
				return false;
			else
				return true;
		}
		
		return true;
	}

	private boolean thermoCheck(List<Thermostat> listThermo, Thermostat thermoObj) {
		for(Thermostat thermo : listThermo){
			if(thermo.getThermostatID() == thermoObj.getThermostatID())
				return false;
			else
				return true;
		}
		
		return true;
	}
	
}
