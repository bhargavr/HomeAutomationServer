package com.sjsu.cmpe273Server.dao;

import com.sjsu.cmpe273Server.dto.OutDoorCamera;
import java.util.List;
import java.util.Optional;

public interface OutDoorCameraDao{
	public List<OutDoorCamera> getAll();
	public Optional<OutDoorCamera> read(String serialNumber);
	public Optional<OutDoorCamera> create(OutDoorCamera outDoorCamera);
	public Optional<OutDoorCamera> update(OutDoorCamera outDoorCamera);
	public boolean delete(String serialNumber);
}