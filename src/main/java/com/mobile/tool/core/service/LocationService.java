package com.mobile.tool.core.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mobile.tool.core.datahandler.DataHandler;
import com.mobile.tool.core.entity.Location;

@Service
public class LocationService {

	@Autowired
	private DataHandler dataHandler;
	
	public Location findLocationByLatLong(double lat, double lon) {
		Location location = dataHandler.fetchLocationByLatLong(lat, lon);
		if(location==null){
			location = new Location(lat, lon);
			dataHandler.storeLocationInCacheAndReportDb(location);
		}
		return location;
	}

}
