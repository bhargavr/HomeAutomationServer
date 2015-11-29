/**
 * 
 */
package com.sjsu.cmpe273Server.rest;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.sjsu.cmpe273Server.model.Account;
import com.sjsu.cmpe273Server.service.UserDataService;

/**
 * @author bhargav
 *
 */
@RestController
public class UserDataController {

	@Autowired
	private UserDataService userDataService;
	
    @RequestMapping(value = "/registerController", method = RequestMethod.POST)
    public ModelAndView registerController(@RequestBody String jsonString, Model model) throws JSONException {
    	
		System.out.println(jsonString);
		
		JSONObject userobj = new JSONObject(jsonString);
    	
    		Account registerUser = new Account();
    		registerUser.setUsername(userobj.getString("userName"));
    		registerUser.setPassword(userobj.getString("password"));
    		registerUser.setDisplayName(userobj.getString("displayName"));

			try
			{
				userDataService.save(registerUser);
			}
			catch (Exception e)
			{
				e.printStackTrace();
			}
    	
    	
    	return new ModelAndView("redirect:login");
    }
	
}
