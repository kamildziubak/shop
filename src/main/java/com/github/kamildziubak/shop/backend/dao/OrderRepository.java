package com.github.kamildziubak.shop.backend.dao;

import com.github.kamildziubak.shop.backend.modules.dbModules.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
    void deleteOrderByBsktId(int bsktId);
}
