package com.sjsu.cmpe273Server.model;

public class SecuritySystem {
	private String manufacturer;
	private String modelNumber;
	private String serialNumber;
	private String firmwareVersion;
	
	public SecuritySystem(){
	}
	
	public SecuritySystem(String Manufacturer, String ModelNumber, String SerialNumber, String FirmwareVersion){
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
	
	@Override
	public boolean equals(Object other)
	{
		if (!(other instanceof SecuritySystem)) {
	        return false;
	    }
		
		SecuritySystem that = (SecuritySystem) other;

	    // Custom equality check here.
	    return this.manufacturer.equals(that.manufacturer)
	        && this.modelNumber.equals(that.modelNumber)
	        && this.serialNumber.equals(that.serialNumber)
	        && this.firmwareVersion.equals(that.firmwareVersion);
	}
}
