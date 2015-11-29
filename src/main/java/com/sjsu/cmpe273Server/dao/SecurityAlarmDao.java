package com.sjsu.cmpe273Server.dao;

import com.sjsu.cmpe273Server.model.SecurityAlarm;
import java.util.List;
import java.util.Optional;

public interface SecurityAlarmDao{
	public List<SecurityAlarm> getAll();
	public Optional<SecurityAlarm> read(String serialNumber);
	public Optional<SecurityAlarm> create(SecurityAlarm securityAlarm);
	public Optional<SecurityAlarm> update(SecurityAlarm securityAlarm);
	public boolean delete(String serialNumber);
}