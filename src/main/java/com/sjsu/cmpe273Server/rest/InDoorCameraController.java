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

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityNotFoundException;

import com.sjsu.cmpe273Server.dao.impl.InDoorCameraDaoImpl;
import com.sjsu.cmpe273Server.model.InDoorCamera;

@RestController
@RequestMapping("/securitysystem/indoorcamera")
public class InDoorCameraController {
	
	@Autowired
	private InDoorCameraDaoImpl inDoorCameraDao;
	
	@RequestMapping("/getAll")
	public List<InDoorCamera> getAllInDoorCamera()
	{
		List<InDoorCamera> result = null;
		try{
			result = inDoorCameraDao.getAll();
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<InDoorCamera> create(@RequestBody InDoorCamera inDoorCamera){
		if(inDoorCameraDao.create(inDoorCamera).isPresent()){
			return new ResponseEntity<InDoorCamera>(HttpStatus.CREATED);
		}
		throw new EntityExistsException("InDoorCamera with serial number[" + inDoorCamera.getSerialNumber() + "] already exists.");
	}
	
	@RequestMapping(value = "/delete/{serialNumber}", method=RequestMethod.DELETE)
	public ResponseEntity<InDoorCamera> delete(@PathVariable String serialNumber)
	{
		if(inDoorCameraDao.delete(serialNumber)){
			return new ResponseEntity<InDoorCamera>(HttpStatus.NO_CONTENT);
		}
		throw new EntityExistsException("InDoorCamera with serial number[" + serialNumber + "] does not exists.");
	}
	
	@RequestMapping(value = "/read/{serialNumber}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<InDoorCamera> read(@PathVariable String serialNumber)
	{
		Optional<InDoorCamera> optional = inDoorCameraDao.read(serialNumber);
		if(optional.isPresent()){
			return new ResponseEntity<InDoorCamera>(optional.get(), HttpStatus.OK);
		}
		throw new EntityExistsException("InDoorCamera with serial number[" + serialNumber + "] not found.");
	}
	
	@RequestMapping(value = "/update/{serialNumber}", method=RequestMethod.PUT,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<InDoorCamera> update(@RequestBody InDoorCamera inDoorCamera)
	{
		Optional<InDoorCamera> optional = inDoorCameraDao.update(inDoorCamera);
		if(optional.isPresent()){
			return new ResponseEntity<InDoorCamera>(HttpStatus.ACCEPTED);
		}
		throw new EntityExistsException("InDoorCamera with serial number[" + inDoorCamera.getSerialNumber() + "] does not exists.");
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
