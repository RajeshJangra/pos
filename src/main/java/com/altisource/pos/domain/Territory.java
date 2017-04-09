package com.altisource.pos.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Entity
public class Territory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private long id;

    @Column(nullable = false)
    private double taxRate;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String descr;

    public Territory() {
    }

    public Territory(final long id) {
        this.id = id;
    }

    public Territory(final double taxRate, final String name, final String descr) {
        this.taxRate = taxRate;
        this.name = name;
        this.descr = descr;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public double getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(final double taxRate) {
        this.taxRate = taxRate;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Territory{");
        sb.append("id=").append(id);
        sb.append(", taxRate=").append(taxRate);
        sb.append(", name='").append(name).append('\'');
        sb.append(", descr='").append(descr).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
