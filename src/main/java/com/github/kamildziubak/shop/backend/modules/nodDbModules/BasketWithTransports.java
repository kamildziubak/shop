package com.github.kamildziubak.shop.backend.modules.nodDbModules;

import com.github.kamildziubak.shop.backend.modules.dbModules.BasketProduct;
import com.github.kamildziubak.shop.backend.modules.dbModules.Transport;
import lombok.Getter;

import java.util.ArrayList;

@Getter
public class BasketWithTransports {
    private ArrayList<BasketProduct> basketProduct;
    private ArrayList<Transport> transports;

    public BasketWithTransports(ArrayList<BasketProduct> basketProduct, ArrayList<Transport> transports) {
        this.basketProduct = basketProduct;
        this.transports = transports;
    }
}