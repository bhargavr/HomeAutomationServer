/**
 * 
 */
package com.sjsu.cmpe273Server.service;

import java.util.List;
import java.util.Map;

import com.sjsu.cmpe273Server.model.*;

/**
 * @author bhargav
 *
 */
public interface ServerDataService {

	void save(String name) throws Exception;
	
	List<ServerData> findAll() throws Exception;
	
	void update(String name, Integer val) throws Exception;
	
	void delete(String name) throws Exception;
	
	Map<String,Object> getChartData(String name) throws Exception;
	
	ServerData findServerObj(String name) throws Exception;
	
	Map<String,Object> getDeviceData(String userName) throws Exception;
	
}
