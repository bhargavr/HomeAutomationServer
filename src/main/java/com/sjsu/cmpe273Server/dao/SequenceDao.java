package com.sjsu.cmpe273Server.dao;

public interface SequenceDao {

	long getNextSequenceId(String key) throws Exception;
	
}
