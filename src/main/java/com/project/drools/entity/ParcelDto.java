package com.project.drools.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ParcelDto {
	
	private double weight;
	private double height;
	private double width;
	private double length;
	private String voucherCode;
}
