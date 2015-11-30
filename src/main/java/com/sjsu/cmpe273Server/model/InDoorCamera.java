package com.sjsu.cmpe273Server.model;

import java.util.Date;

public class InDoorCamera extends SecuritySystem {
	private int cameraID;
	private boolean motionDetection;
	private boolean enableRecording;
	private Date intrusion;
	
	public InDoorCamera(){
	}
	
	public InDoorCamera(String Manufacturer, String ModelNumber, String SerialNumber,
			String FirmwareVersion, int CameraID, boolean MotionDetection, boolean EnableRecording,
			Date Intrusion){
		super(Manufacturer, ModelNumber, SerialNumber, FirmwareVersion);
		cameraID = CameraID;
		motionDetection = MotionDetection;
		enableRecording = EnableRecording;
		intrusion = Intrusion;
	}
	
	public int getCameraID(){
		return cameraID;
	}
	
	public void setCameraID(int cameraID)
	{
		this.cameraID = cameraID;
	}
	
	public boolean getMotionDetection(){
		return motionDetection;
	}
	
	public void setMotionDetection(boolean motionDetection){
		this.motionDetection = motionDetection;
	}
	
	public boolean getEnableRecording(){
		return enableRecording;
	}
	
	public void setEnableRecording(boolean enableRecording){
		this.enableRecording = enableRecording;
	}
	
	public Date getIntrusion(){
		return intrusion;
	}
	
	public void setIntrusion(Date intrusion){
		this.intrusion = intrusion;
	}
	
	@Override
	public String toString()
	{
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(super.toString());
		stringBuilder.append("Camera ID: " + this.cameraID);
		stringBuilder.append("Motion Detection Status: " + this.motionDetection);
		stringBuilder.append("Recording Status: " + this.enableRecording);
		stringBuilder.append("Last Intrusion Timestamp: " + this.intrusion.toString());
		return stringBuilder.toString();
	}
	
	public boolean equals(Object other)
	{
		if (!(other instanceof InDoorCamera)) {
	        return false;
	    }
		
		InDoorCamera that = (InDoorCamera) other;

	    // Custom equality check here.
	    return this.cameraID == that.cameraID;
	}
}