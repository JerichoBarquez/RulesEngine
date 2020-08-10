package com.project.drools.service;

import org.springframework.web.client.HttpClientErrorException;

import com.project.drools.entity.VoucherItem;

public interface DiscountService {
	
	public VoucherItem getDiscount(String voucherCode) throws HttpClientErrorException;

}
