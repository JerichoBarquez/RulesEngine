package com.project.drools.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import com.project.drools.entity.CostDetailDto;
import com.project.drools.entity.ParcelDto;
import com.project.drools.entity.VoucherItem;
import com.project.drools.exception.VoucherCodeNotFoundException;
import com.project.drools.service.CostDeliveryService;
import com.project.drools.service.DiscountService;

import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/cost/delivery")
@RequiredArgsConstructor
public class CostDeliveryController {
	
	private CostDeliveryService costDeliveryService;
	private DiscountService disService;

	@Autowired
	public CostDeliveryController(DiscountService disService, CostDeliveryService costService) {
		this.disService = disService;
		this.costDeliveryService = costService;
	}

	@PostMapping(path = "compute", consumes =  {MediaType.APPLICATION_JSON_VALUE},
			     produces =  {MediaType.APPLICATION_JSON_VALUE})
	@ApiOperation(value = "Computes the cost based on the configured Drools rule engine", response = CostDetailDto.class)
	public ResponseEntity<CostDetailDto> getCost(@RequestBody ParcelDto parcelDto) {	
		CostDetailDto costDtlDto = new CostDetailDto();
		try {
			costDeliveryService.calculateCost(parcelDto, costDtlDto);
			if (parcelDto.getVoucherCode() != null && parcelDto.getVoucherCode() != "") {
				VoucherItem vItem = disService.getDiscount(parcelDto.getVoucherCode());
				//NO COST =  NO DISCOUNT
				if (costDtlDto.getCost() != 0) {
					costDtlDto.setDiscount(vItem.getDiscount());
					costDtlDto.setExpiry(vItem.getExpiry());
					costDtlDto.setVoucherCode(vItem.getCode());
				}
			}
		} catch (HttpClientErrorException e) {
			throw new VoucherCodeNotFoundException("Voucher code not found : " + parcelDto.getVoucherCode());
		}
		return new ResponseEntity<>(costDtlDto, HttpStatus.OK);
	}
}
