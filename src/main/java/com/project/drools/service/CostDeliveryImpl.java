package com.project.drools.service;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.drools.entity.CostDetailDto;
import com.project.drools.entity.ParcelDto;


@Service
public class CostDeliveryImpl implements CostDeliveryService{
	
private KieContainer kieContainer;
	
    @Autowired
	public CostDeliveryImpl(KieContainer kieContainer){
		this.kieContainer = kieContainer;
	}
    public CostDetailDto calculateCost(ParcelDto parcelDto, CostDetailDto costDetailDto) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.setGlobal("costDetailDto", costDetailDto);
        kieSession.insert(parcelDto);
        kieSession.fireAllRules(1);
        kieSession.dispose();
        return costDetailDto;
    }

}
