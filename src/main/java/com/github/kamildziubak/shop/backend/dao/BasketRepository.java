package com.github.kamildziubak.shop.backend.dao;

import com.github.kamildziubak.shop.backend.modules.dbModules.ids.BasketProductId;
import com.github.kamildziubak.shop.backend.modules.dbModules.BasketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.ArrayList;

@Repository
public interface BasketRepository extends JpaRepository<BasketProduct, BasketProductId> {
    ArrayList<BasketProduct> findByBasketProductIdBsktId(int bsktId);
    BasketProduct findByBasketProductId(BasketProductId basketProductId);
    void deleteByBasketProductIdBsktId(int bsktId);
}