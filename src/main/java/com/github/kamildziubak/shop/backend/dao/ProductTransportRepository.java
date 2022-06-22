package com.github.kamildziubak.shop.backend.dao;

import com.github.kamildziubak.shop.backend.modules.dbModules.ProductTransport;
import com.github.kamildziubak.shop.backend.modules.dbModules.ids.ProductTransportId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("productTransportRepository")
public interface ProductTransportRepository extends JpaRepository<ProductTransport, ProductTransportId> {
    List<ProductTransport> findByProductTransportIdProdId(int prodId);
}
