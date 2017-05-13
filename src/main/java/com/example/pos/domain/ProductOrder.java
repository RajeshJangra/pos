package com.example.pos.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Entity
public class ProductOrder implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PRODUCT_ID", nullable = false)
    private Product product;

    @Column
    private long count;

    public ProductOrder() {
    }

    public ProductOrder(final long id) {
        this.id = id;
    }

    public ProductOrder(final Product product, final long count) {
        this.product = product;
        this.count = count;
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

    public long getCount() {
        return count;
    }

    public void setCount(final long count) {
        this.count = count;
    }

    @Override
    public String toString() {
        final String sb = "ProductOrder{" + "id=" + id + ", product=" + product + ", count=" + count + '}';
        return sb;
    }
}
