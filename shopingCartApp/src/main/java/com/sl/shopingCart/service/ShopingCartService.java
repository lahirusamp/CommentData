package com.sl.shopingCart.service;

import java.util.List;


import com.sl.shopingCart.response.ProductDetailResponse;
import com.sl.shopingCart.response.ProductPriceDetailsResponse;

public interface ShopingCartService {

	List<ProductDetailResponse> getAllProductsDetails() throws Exception ;
	ProductPriceDetailsResponse getCartonPriceDetails(int qty,Long id) throws Exception;

}
