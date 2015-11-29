package com.sjsu.cmpe273Server.model;

public class Thermostat extends HVACSystem{
	private int thermostatID;
	private String temperature;
	private boolean operationStatus;
	
	public Thermostat(){
	}
	
	public Thermostat(String Manufacturer, String ModelNumber, String SerialNumber, String FirmwareVersion, 
			int ThermostatID, String Temperature, boolean OperationStatus){
		super(Manufacturer, ModelNumber, SerialNumber, FirmwareVersion);
		thermostatID = ThermostatID;
		temperature = Temperature;
		operationStatus = OperationStatus;
	}
	
	public int getThermostatID(){
		return thermostatID;
	}
	
	public void setThermostatId(int thermostatID){
		this.thermostatID = thermostatID;
	}
	
	public String getTemperature(){
		return temperature;
	}
	
	public void setTemperature(String temperature){
		this.temperature = temperature;
	}
	
	public boolean getOperationStatus(){
		return operationStatus;
	}
	
	public void setOperationStatus(boolean operationStatus){
		this.operationStatus = operationStatus;
	}
	
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("Thermostat ID: " + this.thermostatID+"\n");
		stringBuilder.append("Temperature: " + this.temperature+"\n");
		stringBuilder.append("Operation Status: " + this.operationStatus+"\n");
		return stringBuilder.toString();
		
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (!(other instanceof Thermostat)) {
	        return false;
	    }
		
		Thermostat that = (Thermostat) other;

	    // Custom equality check here.
	    return this.thermostatID == that.thermostatID;
	}
	
	
}
