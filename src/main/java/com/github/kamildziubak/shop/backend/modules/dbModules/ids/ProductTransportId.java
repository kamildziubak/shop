package com.github.kamildziubak.shop.backend.modules.dbModules.ids;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@Getter
@Setter
@ToString
public class ProductTransportId implements Serializable {
    private static final long serialVersionUID=1L;
    private int prodId;
    private int trnsId;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
