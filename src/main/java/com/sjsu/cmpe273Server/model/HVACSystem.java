package com.sjsu.cmpe273Server.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "HVACSystem")
public class HVACSystem {
	
	private String manufacturer;
	private String modelNumber;
	@Id
	private String serialNumber;
	private String firmwareVersion;
	
	public HVACSystem(){
	}
	
	public HVACSystem(String Manufacturer, String ModelNumber, String SerialNumber, String FirmwareVersion){
		manufacturer = Manufacturer;
		modelNumber = ModelNumber;
		serialNumber = SerialNumber;
		firmwareVersion = FirmwareVersion;
	}
	
	public String getManufacturer(){
		return manufacturer;
	}
	
	public void setManufacturer(String manufacturer){
		this.manufacturer = manufacturer;
	}
	
	public String getModelNumber(){
		return modelNumber;
	}
	
	public void setModelNumber(String modelNumber){
		this.modelNumber = modelNumber;
	}
	
	public String getSerialNumber(){
		return serialNumber;
	}
	
	public void setSerialNumber(String serialNumber){
		this.serialNumber = serialNumber;
	}
	
	public String getFirmwareVersion(){
		return firmwareVersion;
	}
	
	public void setFirmwareVersion(String fwVersion ){
		this.firmwareVersion = fwVersion;
	}
	
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Manufacturer: "+ this.manufacturer +"\n");
		stringBuilder.append("Model Number: "+this.modelNumber+"\n");
		stringBuilder.append("Serial Number: "+this.serialNumber+"\n");
		stringBuilder.append("Firmware Version: "+this.firmwareVersion+"\n");
		return stringBuilder.toString();
		
	}
}
