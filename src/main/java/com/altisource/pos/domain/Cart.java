package com.altisource.pos.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Entity
public class Cart implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @OneToMany
    @JoinColumn(name = "ORDERITEM_ID", nullable = false)
    private List<OrderItem> orderItems;

    public Cart() {
    }

    public Cart(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }
}
