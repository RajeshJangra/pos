package com.altisource.pos.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Entity
public class Bill implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column
    private Date billDate;

    @Column
    private String locationCode;

    @Column
    private double billAmount;

    @Column
    private double taxAmount;

    @Column
    private double totalBillAmount;

    @OneToOne
    @JoinColumn(name = "CART_ID", nullable = false)
    private Cart cart;

    public Bill() {
    }

    public Bill(final long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public void setId(final long id) {
        this.id = id;
    }

    public Cart getCart() {
        return cart;
    }

    public void setCart(final Cart cart) {
        this.cart = cart;
    }

    public Date getBillDate() {
        return billDate;
    }

    public void setBillDate(final Date billDate) {
        this.billDate = billDate;
    }

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(final String locationCode) {
        this.locationCode = locationCode;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(final double billAmount) {
        this.billAmount = billAmount;
    }

    public double getTaxAmount() {
        return taxAmount;
    }

    public void setTaxAmount(final double taxAmount) {
        this.taxAmount = taxAmount;
    }

    public double getTotalBillAmount() {
        return totalBillAmount;
    }

    public void setTotalBillAmount(final double totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }
}
