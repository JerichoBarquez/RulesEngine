package com.project.drools;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.project.drools.controller.CostDeliveryController;
import com.project.drools.entity.CostDetailDto;
import com.project.drools.entity.ParcelDto;
import com.project.drools.exception.VoucherCodeNotFoundException;
import com.project.drools.service.CostDeliveryService;
import com.project.drools.service.DiscountService;

@SpringBootTest
class DroolsApplicationTests {

	@Autowired
	private CostDeliveryService costDeliveryService;
	@Autowired
	private DiscountService disService;

	@Test
	public void test_getCostWeightG50() {
		CostDeliveryController test = new CostDeliveryController(disService, costDeliveryService);
	    ParcelDto parcelDto = new ParcelDto();
	    parcelDto.setWeight(51);
	    ResponseEntity<CostDetailDto> result = test.getCost(parcelDto);
	    assertNotNull(result);
	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    assertEquals(0.0, result.getBody().getCost(),0.0);
	    assertEquals("Reject",result.getBody().getRuleName());
	}
	
	@Test
	public void test_getCostWeightG10() {
		CostDeliveryController test = new CostDeliveryController(disService, costDeliveryService);
	    ParcelDto parcelDto = new ParcelDto();
	    parcelDto.setWeight(20);
	    ResponseEntity<CostDetailDto> result = test.getCost(parcelDto);
	    assertNotNull(result);
	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    assertEquals(400.0, result.getBody().getCost(),400.0);
	    assertEquals("Heavy Parcel",result.getBody().getRuleName());
	}
	
	@Test
	public void test_getCostWeightSmallParcel() {
		CostDeliveryController test = new CostDeliveryController(disService, costDeliveryService);
	    ParcelDto parcelDto = new ParcelDto();
	    parcelDto.setHeight(10);
	    parcelDto.setLength(10);
	    parcelDto.setWidth(10);
	    ResponseEntity<CostDetailDto> result = test.getCost(parcelDto);
	    assertNotNull(result);
	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    assertEquals(30.0, result.getBody().getCost(),30.0);
	    assertEquals(1000.0, result.getBody().getVolume(),1000.0);
	    assertEquals("Small Parcel",result.getBody().getRuleName());
	}
	
	@Test
	public void test_getCostWeightMediumParcel() {
		CostDeliveryController test = new CostDeliveryController(disService, costDeliveryService);
	    ParcelDto parcelDto = new ParcelDto();
	    parcelDto.setHeight(15);
	    parcelDto.setLength(12);
	    parcelDto.setWidth(11);
	    ResponseEntity<CostDetailDto> result = test.getCost(parcelDto);
	    assertNotNull(result);
	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    assertEquals(79.2, result.getBody().getCost(),79.2);
	    assertEquals(1980.0, result.getBody().getVolume(),1980.0);
	    assertEquals("Medium Parcel",result.getBody().getRuleName());
	}
	
	@Test
	public void test_getCostWeightLargeParcel() {
		CostDeliveryController test = new CostDeliveryController(disService, costDeliveryService);
	    ParcelDto parcelDto = new ParcelDto();
	    parcelDto.setHeight(15);
	    parcelDto.setLength(12);
	    parcelDto.setWidth(15);
	    ResponseEntity<CostDetailDto> result = test.getCost(parcelDto);
	    assertNotNull(result);
	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    assertEquals(108.0, result.getBody().getCost(),108.0);
	    assertEquals(2700.0, result.getBody().getVolume(),2700.0);
	    assertEquals("Large Parcel",result.getBody().getRuleName());
	}
	
	@Test
	public void test_getCostWeightMediumParcelWithVoucher() {
		CostDeliveryController test = new CostDeliveryController(disService, costDeliveryService);
	    ParcelDto parcelDto = new ParcelDto();
	    parcelDto.setHeight(15);
	    parcelDto.setLength(12);
	    parcelDto.setWidth(11);
	    parcelDto.setVoucherCode("XXXX");
	    ResponseEntity<CostDetailDto> result = test.getCost(parcelDto);
	    assertNotNull(result);
	    assertEquals(HttpStatus.OK, result.getStatusCode());
	    assertEquals(79.2, result.getBody().getCost(),79.2);
	    assertEquals(1980.0, result.getBody().getVolume(),1980.0);
	    assertEquals("Medium Parcel",result.getBody().getRuleName());
	    assertEquals(12.5,result.getBody().getDiscount(),12.5);
	}
	
	@Test
	public void test_getCostWeightMediumParcelWithVoucherNotExisting() {
		try {
			CostDeliveryController test = new CostDeliveryController(disService, costDeliveryService);
		    ParcelDto parcelDto = new ParcelDto();
		    parcelDto.setHeight(15);
		    parcelDto.setLength(12);
		    parcelDto.setWidth(11);
		    parcelDto.setVoucherCode("HHGG");
		    test.getCost(parcelDto);
		}catch(VoucherCodeNotFoundException e) {
			assertTrue(true);
		}    
	}

}
