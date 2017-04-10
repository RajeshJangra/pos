package com.altisource.pos.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Entity
public class Cart implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "TERRITORY_ID", nullable = false)
    private Territory territory;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "CART_ORDER_ITEM", joinColumns = {@JoinColumn(name = "CART_ID")}, inverseJoinColumns = {@JoinColumn(name = "ORDER_ITEM_ID")})
    private List<ProductOrder> productOrders;

    public Cart() {
    }

    public Cart(final long id) {
        this.id = id;
    }

    public Cart(final Territory territory, final List<ProductOrder> productOrders) {
        this.territory = territory;
        this.productOrders = productOrders;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(final Territory territory) {
        this.territory = territory;
    }

    public List<ProductOrder> getProductOrders() {
        return productOrders;
    }

    public void setProductOrders(final List<ProductOrder> productOrders) {
        this.productOrders = productOrders;
    }

    @Override
    public String toString() {
        final String sb = "Cart{" + "id=" + id + ", territory=" + territory + ", productOrders=" + productOrders + '}';
        return sb;
    }
}
