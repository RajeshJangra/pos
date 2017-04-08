package com.altisource.pos.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Entity
public class OrderItem implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    @JoinColumn(name = "ITEM_ID", nullable = false)
    private Item item;

    @Column
    private long count;

    public OrderItem() {
    }

    public OrderItem(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(final Item item) {
        this.item = item;
    }

    public long getCount() {
        return count;
    }

    public void setCount(final long count) {
        this.count = count;
    }
}
