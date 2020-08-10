package com.project.drools.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.project.drools.entity.VoucherItem;

@Service
public class DiscountImpl implements DiscountService {

	@Value("${voucherApi.path}")
	private String voucherApi;
	
	@Value("${voucherApi.key}")
	private String voucherKey;
	
	@Override
	public VoucherItem getDiscount(String voucherCode) throws HttpClientErrorException {
		String uri = voucherApi + voucherCode + voucherKey;
		RestTemplate restTemplate = new RestTemplate();
		VoucherItem vouchItem = restTemplate.getForObject(uri, VoucherItem.class);

		return vouchItem;
	}

}
