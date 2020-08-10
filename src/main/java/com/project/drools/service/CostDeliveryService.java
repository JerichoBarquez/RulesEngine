package com.project.drools.service;

import com.project.drools.entity.CostDetailDto;
import com.project.drools.entity.ParcelDto;

public interface CostDeliveryService {
	
	public CostDetailDto calculateCost(ParcelDto parcelDto, CostDetailDto costDetailDto);
 

}
