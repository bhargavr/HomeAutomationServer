/**
 * 
 */
package com.sjsu.cmpe273Server.rest;

import java.util.List;

import javax.persistence.EntityExistsException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.sjsu.cmpe273Server.dao.impl.AlertMessageDaoImpl;
import com.sjsu.cmpe273Server.model.AlertMessages;

/**
 * @author bhargav
 *
 */
@RestController
@RequestMapping("/alertMessage")
class AlertMessageController {

	
	@Autowired
	private AlertMessageDaoImpl alertMessageDao;
	
	@RequestMapping("/getall")
	public List<AlertMessages> getAllAlertMessages()
	{
		List<AlertMessages> result = null;
		try{
			result = alertMessageDao.getAll();
		}catch(Exception e){
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return result;
	}

	@RequestMapping(value = "/create", method=RequestMethod.POST,
			consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<AlertMessages> create(@RequestBody AlertMessages alertMessages){
		if(alertMessageDao.create(alertMessages).isPresent()){
			return new ResponseEntity<AlertMessages>(HttpStatus.CREATED);
		}
		throw new EntityExistsException("Issue adding AlertMessage for [" + alertMessages.getDeviceType() + "]");
	}
}
