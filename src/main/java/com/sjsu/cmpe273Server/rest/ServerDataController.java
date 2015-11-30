/**
 * 
 */
package com.sjsu.cmpe273Server.rest;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sjsu.cmpe273Server.dto.Greeting;
import com.sjsu.cmpe273Server.model.ServerData;
import com.sjsu.cmpe273Server.service.ServerDataService;

/**
 * @author bhargav
 *
 */
@RestController
@RequestMapping("/rest")
public class ServerDataController {

    private static final String template = "You have Been Added, %s!";
    private static final String templateUpdate = "Record has been updated, %s!";
    private static final String templateDelete = "Record has been deleted, %s!";
    private static final String templateClientNameUpdate = "Client Name has been update, %s!";
    private final AtomicLong counter = new AtomicLong();
    private String chartClientName = "thermoStat2";
	
	@Autowired
	private ServerDataService serverDataService;

    @RequestMapping("/save")
    public Greeting saveServerData(@RequestParam(value="name", defaultValue="thermoStat1") String name) {
        
		try {
			serverDataService.save(name);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    	
    	return new Greeting(counter.incrementAndGet(),String.format(template, name));
        
    }
    
    @RequestMapping("/getAll")
    public List<ServerData> getAllServerData(@RequestParam(value="name", defaultValue="thermoStat1") String name) {
    	List<ServerData> result = null;
    	try {
    		result =  serverDataService.findAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    @RequestMapping("/update")
    public Greeting updateSererData(@RequestParam(value="name", defaultValue="thermoStat1") String name,@RequestParam(value="val", defaultValue="100") String val) {
        
		try {
			serverDataService.update(name,Integer.parseInt(val));

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    	
    	return new Greeting(counter.incrementAndGet(),String.format(templateUpdate, name));
        
    }
	
    @RequestMapping("/delete")
    public Greeting deleteSererData(@RequestParam(value="name", defaultValue="thermoStat1") String name) {
        
		try {
			serverDataService.delete(name);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
    	
    	return new Greeting(counter.incrementAndGet(),String.format(templateDelete, name));
        
    }
    
    @RequestMapping("/getChartData")
    public Map<String,Object> getChartData(@RequestParam(value="name", defaultValue="thermoStat2") String name) {
    	Map<String,Object> result = null;
    	try {
    		result =  serverDataService.getChartData(chartClientName);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    @RequestMapping("/setName")
    public Greeting setName(@RequestParam(value="name", defaultValue="thermoStat2") String name) {
    	try {
    		if(chartClientName != null && !chartClientName.equals(""))
    			chartClientName = name;
    		
    		
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return new Greeting(counter.incrementAndGet(),String.format(templateClientNameUpdate, name));
    }
    
    @RequestMapping("/discover")
    public ServerData discover(@RequestParam(value="name", defaultValue="thermoStat2") String name) {
    	ServerData result = null;
    	try {
    	 result =  serverDataService.findServerObj(chartClientName);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return result;
    }
    
    @RequestMapping("/getDeviceData")
    public Map<String,Object> getDeviceData(@RequestParam(value="name", defaultValue="thermoStat2") String name) {
    	Map<String,Object> result = null;
    	try {
    		result =  serverDataService.getDeviceData(name);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	return result;
    }
}
