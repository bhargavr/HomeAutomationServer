package com.sjsu.cmpe273Server.dao;

import com.sjsu.cmpe273Server.model.InDoorCamera;
import java.util.List;
import java.util.Optional;

public interface InDoorCameraDao{
	public List<InDoorCamera> getAll();
	public List<InDoorCamera> getDistinctList();
	public Optional<InDoorCamera> read(String serialNumber);
	public Optional<InDoorCamera> create(InDoorCamera inDoorCamera);
	public Optional<InDoorCamera> update(InDoorCamera inDoorCamera);
	public boolean delete(String serialNumber);
}
