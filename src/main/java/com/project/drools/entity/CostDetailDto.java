package com.project.drools.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CostDetailDto {

	private String ruleName;
	private double cost;
	private float discount;
	private double volume;
	private Date expiry;
	private String voucherCode;

}
