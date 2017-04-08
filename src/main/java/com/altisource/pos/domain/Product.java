package com.altisource.pos.domain;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Entity
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String descr;

    @ManyToOne
    @JoinColumn(name = "CATEGORY_ID", nullable = false)
    //@ApiModelProperty(value = "Category of a product in POS application", notes = "This must not be null")
    private Category category;

    @ManyToOne
    @JoinColumn(name = "TERRITORY_ID", nullable = false)
    //@ApiModelProperty(value = "Category of a product in POS application", notes = "This must not be null")
    private Territory territory;

    public Product() {
    }

    public Product(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(final String descr) {
        this.descr = descr;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public Territory getTerritory() {
        return territory;
    }

    public void setTerritory(final Territory territory) {
        this.territory = territory;
    }
}
