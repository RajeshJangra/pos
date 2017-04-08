package com.altisource.pos.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Entity
public class Item implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    public Item() {
    }

    public Item(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(final Product product) {
        this.product = product;
    }
}
