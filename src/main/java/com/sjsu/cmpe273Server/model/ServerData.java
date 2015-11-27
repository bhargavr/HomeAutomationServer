/**
 * 
 */
package com.sjsu.cmpe273Server.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author bhargav
 *
 */
@Document(collection = "serverData")
public class ServerData {

	@Id
	private long id;

	private String clientName;
	
	private List<Map<String,Object>> sensorData;

	public String getClientName() {
		return clientName;
	}

	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	public List<Map<String, Object>> getSensorData() {
		return sensorData;
	}

	public void setSensorData(List<Map<String, Object>> sensorData) {
		this.sensorData = sensorData;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return "ServerData [id=" + id + ", clientName=" + clientName + "]";
	}
	
}
