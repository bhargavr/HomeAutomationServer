/**
 * 
 */
package com.sjsu.cmpe273Server.rest;

import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sjsu.cmpe273Server.dao.SequenceDao;
import com.sjsu.cmpe273Server.dto.Greeting;
import com.sjsu.cmpe273Server.model.ServerData;
import com.sjsu.cmpe273Server.service.ServerDataService;

/**
 * @author bhargav
 *
 */
@RestController
public class GreetingController {

    private static final String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();
    
	@Autowired
	private ServerDataService serverDataService;

    @RequestMapping("/greeting")
    public Greeting greeting(@RequestParam(value="name", defaultValue="World") String name) {
        
		try {

			serverDataService.save("thermoStat2");

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    	
    	return new Greeting(counter.incrementAndGet(),String.format(template, name));
        
    }
    
    @RequestMapping("/hello")
    public List<ServerData> hello(@RequestParam(value="name", defaultValue="World") String name) {
//        return "Helloo My name is super man!!";
    	List<ServerData> result = null;
    	try {
    		result =  serverDataService.findAll();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	return result;
    }
}
