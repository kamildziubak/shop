package com.github.kamildziubak.shop.backend.api;

import com.github.kamildziubak.shop.backend.dao.OrderRepository;
import com.github.kamildziubak.shop.backend.modules.dbModules.Order;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/orders")
public class Orders {
    private final OrderRepository orderRepository;

    public Orders(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @GetMapping
    public List<Order> getOrders(){
        return orderRepository.findAll();
    }
}
