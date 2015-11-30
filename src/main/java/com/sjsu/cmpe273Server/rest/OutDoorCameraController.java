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

import com.sjsu.cmpe273Server.dao.impl.OutDoorCameraDaoImpl;
import com.sjsu.cmpe273Server.model.OutDoorCamera;

@RestController
@RequestMapping("/securitysystem/outdoorcamera")
public class OutDoorCameraController {
	@Autowired
	private OutDoorCameraDaoImpl outDoorCameraDao;

	@RequestMapping("/getall")
	public List<OutDoorCamera> getAllOutDoorCamera()
	{
		List<OutDoorCamera> result = null;
		try{
			result = outDoorCameraDao.getAll();
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}
	
	@RequestMapping(value = "/create", method=RequestMethod.POST,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<OutDoorCamera> create(@RequestBody OutDoorCamera outDoorCamera){
		if(outDoorCameraDao.create(outDoorCamera).isPresent()){
			return new ResponseEntity<OutDoorCamera>(HttpStatus.CREATED);
		}
		throw new EntityExistsException("OutDoorCamera with serial number[" + outDoorCamera.getSerialNumber() + "] already exists.");
	}
	
	@RequestMapping(value = "/delete/{serialNumber}", method=RequestMethod.DELETE)
	public ResponseEntity<OutDoorCamera> delete(@PathVariable String serialNumber)
	{
		if(outDoorCameraDao.delete(serialNumber)){
			return new ResponseEntity<OutDoorCamera>(HttpStatus.NO_CONTENT);
		}
		throw new EntityExistsException("OutDoorCamera with serial number[" + serialNumber + "] does not exists.");
	}
	
	@RequestMapping(value = "/read/{serialNumber}", method=RequestMethod.GET,
			produces = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<OutDoorCamera> read(@PathVariable String serialNumber)
	{
		Optional<OutDoorCamera> optional = outDoorCameraDao.read(serialNumber);
		if(optional.isPresent()){
			return new ResponseEntity<OutDoorCamera>(optional.get(), HttpStatus.OK);
		}
		throw new EntityExistsException("OutDoorCamera with serial number[" + serialNumber + "] not found.");
	}
	
	@RequestMapping(value = "/update/{serialNumber}", method=RequestMethod.PUT,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<OutDoorCamera> update(@RequestBody OutDoorCamera outDoorCamera)
	{
		Optional<OutDoorCamera> optional = outDoorCameraDao.update(outDoorCamera);
		if(optional.isPresent()){
			return new ResponseEntity<OutDoorCamera>(HttpStatus.ACCEPTED);
		}
		throw new EntityExistsException("OutDoorCamera with serial number[" + outDoorCamera.getSerialNumber() + "] does not exists.");
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
