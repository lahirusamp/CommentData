package com.sl.shopingCart.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@Builder
@JsonInclude(Include.NON_NULL)
@AllArgsConstructor
@NoArgsConstructor
public class ProductDetailResponse {
	@JsonProperty("id")
    private Long id;
    @JsonProperty("Carton_name")
    private String Carton_name;
    @JsonProperty("carton_size")
    private Integer cartonSize;
    @JsonProperty("carton_price")
    private Float cartonPrice;
    @JsonProperty("carton_price_ratio")
    private Float carton_price_ratio;
    @JsonProperty("carton_discount")
    private Float carton_discount;
    
}
