package com.sjsu.cmpe273Server.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.sjsu.cmpe273Server.dao.impl.SecurityAlarmDaoImpl;
import com.sjsu.cmpe273Server.model.SecurityAlarm;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/securitysystem/alarm")
public class SecurityAlarmController {
	@Autowired
	private SecurityAlarmDaoImpl securityAlarmDao;
	
	@RequestMapping("/getall")
	public List<SecurityAlarm> getAllSecurityAlarm()
	{
		List<SecurityAlarm> result = null;
		try{
			result = securityAlarmDao.getAll();
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/create", method=RequestMethod.POST,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SecurityAlarm> create(@RequestBody SecurityAlarm inDoorCamera){
		if(securityAlarmDao.create(inDoorCamera).isPresent()){
			return new ResponseEntity<SecurityAlarm>(HttpStatus.CREATED);
		}
		throw new EntityExistsException("Security Alarm with serial number[" + inDoorCamera.getSerialNumber() + "] already exists.");
	}
	
	@RequestMapping(value = "/delete/{serialNumber}", method=RequestMethod.DELETE)
	public ResponseEntity<SecurityAlarm> delete(@PathVariable String serialNumber)
	{
		if(securityAlarmDao.delete(serialNumber)){
			return new ResponseEntity<SecurityAlarm>(HttpStatus.NO_CONTENT);
		}
		throw new EntityExistsException("Security Alarm with serial number[" + serialNumber + "] does not exists.");
	}
	
	@RequestMapping(value = "/read/{serialNumber}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SecurityAlarm> read(@PathVariable String serialNumber)
	{
		Optional<SecurityAlarm> optional = securityAlarmDao.read(serialNumber);
		if(optional.isPresent()){
			return new ResponseEntity<SecurityAlarm>(optional.get(), HttpStatus.OK);
		}
		throw new EntityExistsException("Security Alarm with serial number[" + serialNumber + "] not found.");
	}
	
	@RequestMapping(value = "/update/{serialNumber}", method=RequestMethod.PUT,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<SecurityAlarm> update(@RequestBody SecurityAlarm inDoorCamera)
	{
		Optional<SecurityAlarm> optional = securityAlarmDao.update(inDoorCamera);
		if(optional.isPresent()){
			return new ResponseEntity<SecurityAlarm>(HttpStatus.ACCEPTED);
		}
		throw new EntityExistsException("Security Alarm with serial number[" + inDoorCamera.getSerialNumber() + "] does not exists.");
	}
	
	@ExceptionHandler({EntityNotFoundException.class})
	public ResponseEntity<String> handleEntityNotFoundException(EntityNotFoundException e) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<String>(e.getMessage(), headers, HttpStatus.NOT_FOUND);
	}

	@ExceptionHandler({EntityExistsException.class})
	public ResponseEntity<String> handleEntityExistsException(EntityExistsException e) {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.TEXT_PLAIN);
		return new ResponseEntity<String>(e.getMessage(), headers, HttpStatus.BAD_REQUEST);
	}
}
