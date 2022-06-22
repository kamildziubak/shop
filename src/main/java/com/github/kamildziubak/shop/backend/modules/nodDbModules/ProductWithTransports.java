package com.github.kamildziubak.shop.backend.modules.nodDbModules;

import com.github.kamildziubak.shop.backend.modules.dbModules.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;

@Getter
@Setter
public class ProductWithTransports extends Product{
    ArrayList<Integer> transports;

    @Autowired
    public ProductWithTransports(Product product, ArrayList<Integer> transports){
        super(product.getProdId(),
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getDiscount(),
                product.getQuantity(),
                product.getCategory());

        this.transports = transports;
    }

}