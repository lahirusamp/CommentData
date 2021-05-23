package com.sl.shopingCart.serviceImpl;

import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sl.shopingCart.entity.Product;
import com.sl.shopingCart.exception.AppException;
import com.sl.shopingCart.exception.ProductNotFoundException;
import com.sl.shopingCart.repository.ProductsRepository;
import com.sl.shopingCart.response.ProductDetailResponse;
import com.sl.shopingCart.response.ProductPriceDetailsResponse;
import com.sl.shopingCart.service.ShopingCartService;

@Service("ShopingCartServiceImpl")
public class ShopingCartServiceImpl implements ShopingCartService{
	
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Autowired
	ProductsRepository productsRepository;
	
	@Override
	public List<ProductDetailResponse> getAllProductsDetails() throws Exception {
		
		// TODO Auto-generated method stub
		 logger.info("============= Start executing getAllProductsDetails in ShopingCartServiceImpl =========");
        List<Product> products = productsRepository.findAll();
        logger.info("{} starting products fetched from DB");
        List<ProductDetailResponse> productResponses = new ArrayList<>();
        products.forEach(product -> {
        	ProductDetailResponse productDetailResponse = ProductDetailResponse.builder()
                    .id(product.getId())
                    .Carton_name(product.getCartonName())
                    .carton_price_ratio(product.getCartonPriceRatio())
                    .cartonPrice(product.getCartonPrice())
                    .cartonSize(product.getCartonSize())
                    .carton_discount(product.getCartonDiscount())
                    .build();
            productResponses.add(productDetailResponse);
        });
        logger.info("========== execution is completed for getAllProductsDetails in ShopingCartServiceImpl=========");
        return productResponses;
		
	}

	@Override
	public ProductPriceDetailsResponse getCartonPriceDetails(int qty, Long id) throws Exception {

		if(qty<0) {
            logger.info("========== Quantity cant be a negative value in ShopingCartServiceImpl =========");
			throw new AppException("Quantity cant be a negative value");
			
		}
		Optional<Product> optionalProduct = productsRepository.findById(id);
       // List<ProductPriceDetailsResponse> productResponses = new ArrayList<>();
        if (optionalProduct.isPresent()) {
            logger.info("=========== Start the getCartonPriceDetails process in ShopingCartServiceImpl ==========");
            Product product = optionalProduct.get();
            return  cartonPriceCal(qty, product);
        } else {
            logger.info("========== Product not found in ShopingCartServiceImpl =========");
            throw new ProductNotFoundException("product is not found");
            
        }
		
	}

	
private ProductPriceDetailsResponse cartonPriceCal(int qty,Product productDetails) {
        
	     DecimalFormat df = new DecimalFormat("0.00");
	                   df.setRoundingMode(RoundingMode.DOWN);

		int units = qty % productDetails.getCartonSize();
		int cartonSize = qty / productDetails.getCartonSize();
		Float productPrice ;
		Float cartonPrice = productDetails.getCartonPrice() * cartonSize;
		Float unitsPrice = (productDetails.getCartonPrice() / productDetails.getCartonSize())*units*productDetails.getCartonPriceRatio();
				
		if (cartonSize>=3) {
			cartonPrice = (float) ((cartonPrice)*(1.0 - productDetails.getCartonDiscount()));
		}
		
		return ProductPriceDetailsResponse.builder()
				.units(units)
				.Cartons(cartonSize)
				.price(Float.parseFloat(df.format((float)cartonPrice.sum(cartonPrice, unitsPrice)))).build();
		
		
	}

}
