package com.github.kamildziubak.shop.backend.modules.dbModules;

import com.github.kamildziubak.shop.backend.modules.dbModules.ids.BasketProductId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Table(name="baskets")
@Getter
@Setter
@ToString
public class BasketProduct {
    @EmbeddedId
    private BasketProductId basketProductId;
    int quantity;

    public BasketProduct() {
    }

    public BasketProduct(BasketProductId basketProductId, int quantity) {
        this.basketProductId = basketProductId;
        this.quantity = quantity;
    }
}
