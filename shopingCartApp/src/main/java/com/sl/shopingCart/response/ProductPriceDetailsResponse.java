package com.sl.shopingCart.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonInclude(Include.NON_NULL)
public class ProductPriceDetailsResponse {

	private int units;
    private Float price;
    private int Cartons; 
	
}
