package com.sjsu.cmpe273Server.dao;

import java.util.List;
import java.util.Optional;

import com.sjsu.cmpe273Server.model.AlertMessages;;

public interface AlertMessageDao {

	public List<AlertMessages> getAll();
	public Optional<AlertMessages> read(String serialNumber);
	public Optional<AlertMessages> create(AlertMessages alertMessages);
	public Optional<AlertMessages> update(AlertMessages alertMessages);
	public boolean delete(String serialNumber);
	
}
