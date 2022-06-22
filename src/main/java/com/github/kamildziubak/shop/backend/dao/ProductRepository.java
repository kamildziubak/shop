package com.github.kamildziubak.shop.backend.dao;

import com.github.kamildziubak.shop.backend.modules.dbModules.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer>{
    List<Product> findAll();
    Product findByProdId(int prodId);
    @Modifying
    @Query("UPDATE Product SET quantity=quantity- ?2 WHERE prod_id=?1")
    int lowerProductQuantity(int prodId, int quantity);
}
