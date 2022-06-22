package com.github.kamildziubak.shop.backend.api;

import com.github.kamildziubak.shop.backend.dao.OrderRepository;
import com.github.kamildziubak.shop.backend.modules.dbModules.Order;
import com.github.kamildziubak.shop.backend.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/order")
public class OrderController {
    private final OrderService orderService;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderController(OrderService orderService, OrderRepository orderRepository) {
        this.orderService = orderService;
        this.orderRepository = orderRepository;
    }

    @PostMapping
    public void createOrder(@RequestParam int bsktId, @RequestParam int trnsId){
        orderService.createOrder(bsktId, trnsId);
    }

    @DeleteMapping
    public void deleteOrder(@RequestParam int bsktId){
        orderService.removeOrderByBsktId(bsktId);
    }

    @GetMapping
    public Order getOrder(@RequestParam int bsktId){
        return  orderRepository.getOne(bsktId);
    }
}
