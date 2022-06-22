package com.github.kamildziubak.shop.backend.modules.dbModules;

import lombok.Getter;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name="products")
public class Product {
    @Id
    int prodId;
    String name;
    String description;
    BigDecimal price, discount;
    int quantity;
    int category;

    public Product(int prodId, String name, String description, BigDecimal price, BigDecimal discount, int quantity, int category) {
        this.prodId = prodId;
        this.name = name;
        this.description = description;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
        this.category = category;
    }

    public Product() {
    }

    @Override
    public String toString() {
        return "Product{" +
                "prod_id=" + prodId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", discount=" + discount +
                ", quantity=" + quantity +
                ", category=" + category +
                '}';
    }
}
