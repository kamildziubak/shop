package com.github.kamildziubak.shop.backend.api;

import com.github.kamildziubak.shop.backend.dao.TransportRepository;
import com.github.kamildziubak.shop.backend.modules.dbModules.Transport;
import com.github.kamildziubak.shop.backend.modules.dbModules.ids.BasketProductId;
import com.github.kamildziubak.shop.backend.modules.dbModules.BasketProduct;
import com.github.kamildziubak.shop.backend.modules.nodDbModules.BasketWithTransports;
import com.github.kamildziubak.shop.backend.modules.nodDbModules.ProductWithTransports;
import com.github.kamildziubak.shop.backend.services.BasketService;
import com.github.kamildziubak.shop.backend.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("api/basket")
public class BasketController {
    private final BasketService basketService;
    private final ProductService productService;
    private final TransportRepository transportRepository;

    @Autowired
    public BasketController(BasketService basketService, ProductService productService, TransportRepository transportRepository) {
        this.basketService = basketService;
        this.productService = productService;
        this.transportRepository = transportRepository;
    }

    @GetMapping
    public List<BasketWithTransports> getBasket(@RequestParam int bsktId, @RequestParam Optional<Integer> prodId) {
        if (prodId.isPresent()) {
            ArrayList<BasketProduct> list = new ArrayList<>();
            list.add(basketService.getBasketProduct(new BasketProductId(bsktId, prodId.get())));
            ProductWithTransports productWithTransports = productService.getProduct(prodId.get());
            ArrayList<Integer> transportIds = productWithTransports.getTransports();
            ArrayList<Transport> transports = new ArrayList<>();
            for(int i=0; i<transportIds.size(); i++){
                transports.add(transportRepository.getOne(transportIds.get(i)));
            }

            List<BasketWithTransports> result = new ArrayList<>();

            for(int i=0; i<list.size(); i++){
                result.add(new BasketWithTransports(list, transports));
            }
            return result;
        } else {
            List<BasketWithTransports> result = new ArrayList<>();
            result.add(new BasketWithTransports(basketService.getBasket(bsktId), basketService.getAvailableTransports(bsktId)));
            return result;
        }
    }

    @PostMapping
    public int insertBasketProduct(@RequestBody BasketProduct basketProduct){
        return basketService.insertBasketProduct(basketProduct);
    }

    @DeleteMapping
    public int removeBasketProduct(@RequestBody BasketProduct basketProduct){
        return basketService.removeBasketProduct(basketProduct);
    }
}
