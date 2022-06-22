package com.github.kamildziubak.shop.backend.modules.dbModules;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.*;

@Entity
@Table(name="categories")
@Getter
@Setter
@ToString
public class Category {
    @Id
    Integer ctgId;
    String name;
    Integer masterId;
}
