package com.github.kamildziubak.shop;

import com.github.kamildziubak.shop.backend.services.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProductServiceTest {
    ProductService productService;

    @Autowired
    public ProductServiceTest(ProductService productService) {
        this.productService = productService;
    }

    @Test
    public void removeProductTest(){
        int quantity = productService.getProduct(1).getQuantity();
        productService.lowerProductQuantity(1, 1);
        assertEquals(quantity-1, productService.getProduct(1).getQuantity());
    }
}
