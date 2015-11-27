/**
 * 
 */
package com.sjsu.cmpe273Server.dao;

import java.util.List;

import com.sjsu.cmpe273Server.model.ServerData;

/**
 * @author bhargav
 *
 */
public interface ServerDataDAO {

	void save(ServerData serverDocument);
	
	List<ServerData> findAll();
	
	void update(String name, Integer val);
	
	void delete(String name);
	
	ServerData getServerObject(String name);
}
