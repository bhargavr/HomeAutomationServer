/**
 * 
 */
package com.sjsu.cmpe273Server.model;

import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @author bhargav
 *
 */
@Document(collection = "Account")
public class Account {

	private String username;

	private String password;

	private String displayName;
	
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}



	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the displayName
	 */
	public String getDisplayName() {
		return displayName;
	}

	/**
	 * @param displayName the displayName to set
	 */
	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	
	@Override
	public String toString() {
		return "Account [username=" + username + ", password=" + password + ", displayName=" + displayName + "]";
	}
}
