package com.sjsu.cmpe273Server.model;

public class SecurityAlarm extends SecuritySystem {
	private boolean enableAlarm;
	private boolean alarmStatus;
	
	public SecurityAlarm(){
	}
	
	public SecurityAlarm(String Manufacturer, String ModelNumber, String SerialNumber,
			String FirmwareVersion, boolean EnableAlarm, boolean AlarmStatus){
		super(Manufacturer, ModelNumber, SerialNumber, FirmwareVersion);
		enableAlarm = EnableAlarm;
		alarmStatus = AlarmStatus;
	}
	
	public boolean getEnableAlarm(){
		return enableAlarm;
	}
	
	public void setEnableAlarm(boolean enableAlarm){
		this.enableAlarm = enableAlarm;
	}
	
	public boolean getAlarmStatus(){
		return alarmStatus;
	}
	
	public void setAlarmStatus(boolean alarmStatus){
		this.alarmStatus = alarmStatus;
	}
	
	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(super.toString());
		stringBuilder.append("Alarm ON/OFF: " + this.enableAlarm);
		stringBuilder.append("Alarm Status: " + this.alarmStatus);
		return stringBuilder.toString();
	}
	
	
}
