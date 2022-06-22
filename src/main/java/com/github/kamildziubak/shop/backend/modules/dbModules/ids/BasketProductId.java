package com.github.kamildziubak.shop.backend.modules.dbModules.ids;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
public class BasketProductId implements Serializable {
    private static final long serialVersionUID = 1L;
    private int bsktId;
    private int prodId;

    public BasketProductId(int bsktId, int prodId) {
        this.bsktId = bsktId;
        this.prodId = prodId;
    }

    public BasketProductId(int bsktId) {
        this.bsktId = bsktId;
    }

    public BasketProductId() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
