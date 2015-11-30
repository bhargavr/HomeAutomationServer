package com.sjsu.cmpe273Server.dao;

import java.util.List;
import java.util.Optional;

import com.sjsu.cmpe273Server.model.Thermostat;

public interface ThermostatDao {
	public List<Thermostat> getAll();
	public List<Thermostat> getDistinctList();
	public Optional<Thermostat> read(String serialNumber);
	public Optional<Thermostat> create(Thermostat thermostat);
	public Optional<Thermostat> update(Thermostat thermostat);
	public boolean delete(String serialNumber);
}
