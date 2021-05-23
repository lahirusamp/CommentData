package com.sl.shopingCart.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sl.shopingCart.exception.AppException;
import com.sl.shopingCart.exception.ProductNotFoundException;
import com.sl.shopingCart.response.ProductDetailResponse;
import com.sl.shopingCart.response.ProductPriceDetailsResponse;
import com.sl.shopingCart.service.ShopingCartService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@RequestMapping("/cart")
@CrossOrigin("*")
public class ShopingCartController {
	Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	ShopingCartService productService;
	
	@GetMapping("/getAllProducts")
    @ApiOperation(value = "Get all products", nickname = "getAllProducts")
    @ApiResponses({@ApiResponse(code = 200, message = "Successful"),
    @ApiResponse(code = 400, message = "Bad Request"),
    @ApiResponse(code = 500, message = "Internal server error")})
    public ResponseEntity<List<ProductDetailResponse>>  getAllProduct()  {
     try {
    	 logger.info("=========Start the query products details process in ShopingCartController==========");
		return ResponseEntity.ok(productService.getAllProductsDetails());
		
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		logger.error("=========Error ocurred when product details quering from the DB in ShopingCartController=========== : Error :",e.getMessage());
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);

	}
    }
	
	//////////////////////////////////////////////////////////
	
	 @GetMapping("/productPriceDetailsById/{id}")
	    @ApiOperation(value = "Calculate product price By Id", nickname = "calculateProductPriceById")
	    @ApiResponses({@ApiResponse(code = 200, message = "Successful"),
	    @ApiResponse(code = 400, message = "Somthing went wrong the `product price calculation "),
	    @ApiResponse(code = 500, message = "Internal server error")
	    })
	    public ResponseEntity<ProductPriceDetailsResponse> productPriceDetailsById(
	            @PathVariable("id") Long id,
	            @RequestParam(value = "qty", required = true)  int qty
	    ) {
	        try {
	        	
	       	     logger.info("========= Start the productPriceDetailsById process in ShopingCartController =========");
	            return ResponseEntity.ok(productService.getCartonPriceDetails(qty, id));
	        } catch (ProductNotFoundException e) {
	          logger.info("=========== product Price Details not found in ShopingCartController============: Error :", e.getMessage());
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
	        } catch (AppException e) {
	        	
	            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
			}
	        catch (Exception e) {
	          logger.error("============= Unknown error ocurred in product Price Details process in ShopingCartController==========: Error :", e.getMessage());
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
	        }
	    }
	
}
