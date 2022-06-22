package com.github.kamildziubak.shop.backend.services;

import com.github.kamildziubak.shop.backend.dao.OrderRepository;
import com.github.kamildziubak.shop.backend.dao.ProductRepository;
import com.github.kamildziubak.shop.backend.dao.TransportRepository;
import com.github.kamildziubak.shop.backend.modules.dbModules.BasketProduct;
import com.github.kamildziubak.shop.backend.modules.dbModules.Order;
import com.github.kamildziubak.shop.backend.modules.dbModules.Product;
import com.github.kamildziubak.shop.backend.modules.dbModules.Transport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

@Transactional
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final BasketService basketService;
    private final ProductRepository productRepository;
    private final TransportRepository transportRepository;

    @Autowired
    public OrderService(OrderRepository orderRepository, BasketService basketService, ProductRepository productRepository, TransportRepository transportRepository) {
        this.orderRepository = orderRepository;
        this.basketService = basketService;
        this.productRepository = productRepository;
        this.transportRepository = transportRepository;
    }

    public void createOrder(int bsktId, int trnsId){
        Order order = new Order();
        order.setBsktId(bsktId);
        order.setTrnsId(trnsId);

        ArrayList<BasketProduct> basketProducts = basketService.getBasket(bsktId);
        ArrayList<Product> products = new ArrayList<>();

        for (int i=0; i<basketProducts.size(); i++)
            products.add(productRepository.findByProdId(basketProducts.get(i).getBasketProductId().getProdId()));

        BigDecimal price = new BigDecimal(0);

        for(int i=0; i<products.size(); i++){
            Product product = products.get(i);
            BigDecimal priceOfProduct = product.getPrice();
            BigDecimal priceMultiplier = BigDecimal.valueOf(100);
            priceMultiplier = priceMultiplier.subtract(product.getDiscount());
            priceMultiplier = priceMultiplier.divide(BigDecimal.valueOf(100));
            BigDecimal priceToPay = priceOfProduct;
            priceToPay=priceToPay.multiply(priceMultiplier);
            priceToPay=priceToPay.multiply(BigDecimal.valueOf(basketProducts.get(i).getQuantity()));
            price = price.add(priceToPay);
        }
        Transport transport = transportRepository.getOne(trnsId);
        BigDecimal transportPrice = transport.getCost();
        price= price.add(transportPrice);
        price = price.setScale(2, RoundingMode.CEILING);
        order.setPrice(price);
        orderRepository.save(order);
    }

    public void removeOrderByBsktId(int bsktId){
        orderRepository.deleteOrderByBsktId(bsktId);
    }
}
