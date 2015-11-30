/**
 * 
 */
package com.sjsu.cmpe273Server.model;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author bhargav
 *
 */
@Document(collection = "AlertMessages")
public class AlertMessages {
	
	private String deviceType;
	private  boolean alarmStatus;
	private String message;
	private Date messageDate;
	
	
	public AlertMessages(){
	}
	
	public AlertMessages(String deviceType, boolean alarmStatus, String message, Date messageDate){
		this.deviceType = deviceType;
		this.alarmStatus = alarmStatus;
		this.message = message;
		this.messageDate = messageDate;
	}
	
	
	/**
	 * @return the deviceType
	 */
	public String getDeviceType() {
		return deviceType;
	}
	/**
	 * @param deviceType the deviceType to set
	 */
	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}
	/**
	 * @return the alarmStatus
	 */
	public boolean isAlarmStatus() {
		return alarmStatus;
	}
	/**
	 * @param alarmStatus the alarmStatus to set
	 */
	public void setAlarmStatus(boolean alarmStatus) {
		this.alarmStatus = alarmStatus;
	}
	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}
	/**
	 * @param message the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}
	/**
	 * @return the messageDate
	 */
	public Date getMessageDate() {
		return messageDate;
	}
	/**
	 * @param messageDate the messageDate to set
	 */
	public void setMessageDate(Date messageDate) {
		this.messageDate = messageDate;
	}
	
	@Override
	public String toString(){
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("DeviceType: "+ this.deviceType +"\n");
		stringBuilder.append("AlarmStatus: "+this.alarmStatus+"\n");
		stringBuilder.append("message: "+this.message+"\n");
		stringBuilder.append("MessageDate: "+this.messageDate+"\n");
		return stringBuilder.toString();
		
	}
	
	@Override
	public boolean equals(Object other)
	{
		if (!(other instanceof AlertMessages)) {
	        return false;
	    }
		
		AlertMessages that = (AlertMessages) other;

	    // Custom equality check here.
	    return this.deviceType.equals(that.deviceType)
	        && this.alarmStatus == that.alarmStatus
	        && this.message.equals(that.message)
	        && this.messageDate.equals(that.messageDate);
	}

}
