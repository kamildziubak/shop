package com.github.kamildziubak.shop.backend.dao;

import com.github.kamildziubak.shop.backend.modules.dbModules.Transport;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransportRepository extends JpaRepository<Transport, Integer> {
}
