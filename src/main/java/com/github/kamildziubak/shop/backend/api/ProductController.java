package com.github.kamildziubak.shop.backend.api;

import com.github.kamildziubak.shop.backend.dao.ProductRepository;
import com.github.kamildziubak.shop.backend.modules.dbModules.Product;
import com.github.kamildziubak.shop.backend.modules.nodDbModules.ProductWithTransports;
import com.github.kamildziubak.shop.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/product")
public class ProductController {
    private final ProductService productService;
    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductService productService, ProductRepository productRepository) {
        this.productService = productService;
        this.productRepository = productRepository;
    }

    @GetMapping
    public Product getProductById(@RequestParam("id") int id)
    {
        return productService.getProduct(id);
    }

    @GetMapping("/lastId")
    public int getLastId()
    {
        return productService.getLastId();
    }

    @PostMapping
    public void insertNewProduct(@RequestBody ProductWithTransports product)
    {
        productService.insertNewProduct(product, true);
    }

    @PutMapping
    public void editProduct(@RequestBody ProductWithTransports product){
        productService.insertNewProduct(product, false);
    }

    @DeleteMapping
    public int lowerProductQuantity(@RequestBody Product product){
        return productService.lowerProductQuantity(product.getProdId(), product.getQuantity());
    }
}
