package com.github.kamildziubak.shop.backend.api;

import com.github.kamildziubak.shop.backend.modules.nodDbModules.ProductWithTransports;
import com.github.kamildziubak.shop.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductsController {
    private final ProductService productService;

    @Autowired
    public ProductsController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductWithTransports> getAllProducts() {
        return productService.getAllProducts();
    }
}
