package com.altisource.pos.domain;

import com.altisource.pos.util.PosDateDeserializer;
import com.altisource.pos.util.PosDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.concurrent.atomic.DoubleAdder;

/**
 * Created by rajeshkumar on 07/04/17.
 */
@Entity
public class Bill implements Serializable {

    @Id
    @GeneratedValue
    private long id;

    @Column
    @JsonSerialize(using = PosDateSerializer.class)
    @JsonDeserialize(using = PosDateDeserializer.class)
    private Date billDate;

    @Column
    private long locationCode;

    @Column
    private double billAmount;

    @Column
    private double catTaxAmount;

    @Column
    private double territoryTaxAmount;

    @Column
    private double totalBillAmount;

    @OneToOne
    private Cart cart;

    public Bill() {
    }

    public Bill(final long id) {
        this.id = id;
    }

    public Bill(final long locationCode, final double billAmount, final double catTaxAmount, final double territoryTaxAmount, final double totalBillAmount, final Cart cart) {
        this.billDate = new Date();
        this.locationCode = locationCode;
        this.billAmount = billAmount;
        this.catTaxAmount = catTaxAmount;
        this.territoryTaxAmount = territoryTaxAmount;
        this.totalBillAmount = totalBillAmount;
        this.cart = cart;
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

    public long getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(final long locationCode) {
        this.locationCode = locationCode;
    }

    public double getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(final double billAmount) {
        this.billAmount = billAmount;
    }

    public double getCatTaxAmount() {
        return catTaxAmount;
    }

    public void setCatTaxAmount(final double catTaxAmount) {
        this.catTaxAmount = catTaxAmount;
    }

    public double getTotalBillAmount() {
        return totalBillAmount;
    }

    public void setTotalBillAmount(final double totalBillAmount) {
        this.totalBillAmount = totalBillAmount;
    }

    public double getTerritoryTaxAmount() {
        return territoryTaxAmount;
    }

    public void setTerritoryTaxAmount(final double territoryTaxAmount) {
        this.territoryTaxAmount = territoryTaxAmount;
    }

    @Override
    public String toString() {
        final String sb = "Bill{" + "id=" + id + ", billDate=" + billDate + ", locationCode=" + locationCode + ", billAmount=" + billAmount + ", catTaxAmount=" + catTaxAmount + ", territoryTaxAmount=" + territoryTaxAmount + ", totalBillAmount=" + totalBillAmount + ", cart=" + cart + '}';
        return sb;
    }

    public static class Builder {
        private Cart cart;

        public Builder cart(Cart cart) {
            this.cart = cart;
            return this;
        }

        public Bill build() {
            double billAmount = calculateAmount();
            double catTaxAmount = calculateTax();
            double territoryTaxAmount = billAmount * cart.getTerritory().getTaxRate() / 100;
            return new Bill(cart.getTerritory().getId(), billAmount, catTaxAmount, territoryTaxAmount, billAmount + catTaxAmount + territoryTaxAmount, cart);
        }

        private double calculateAmount() {
            DoubleAdder billAmount = new DoubleAdder();
            cart.getProductOrders().forEach(orderItem -> billAmount.add((orderItem.getCount() * orderItem.getProduct().getPrice())));
            return billAmount.doubleValue();
        }

        private double calculateTax() {
            DoubleAdder catTaxAmount = new DoubleAdder();
            cart.getProductOrders().forEach(orderItem -> catTaxAmount.add((orderItem.getCount() * orderItem.getProduct().getPrice()) * orderItem.getProduct().getCategory().getTaxRate() / 100));
            return catTaxAmount.doubleValue();
        }

    }
}