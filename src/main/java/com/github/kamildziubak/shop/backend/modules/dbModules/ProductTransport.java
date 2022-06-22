package com.github.kamildziubak.shop.backend.modules.dbModules;

import com.github.kamildziubak.shop.backend.modules.dbModules.ids.ProductTransportId;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import javax.persistence.*;

@Entity
@Table(name="ProductTransports")
@Getter
@Setter
@ToString
public class ProductTransport {
    @EmbeddedId
    private ProductTransportId productTransportId;
}
