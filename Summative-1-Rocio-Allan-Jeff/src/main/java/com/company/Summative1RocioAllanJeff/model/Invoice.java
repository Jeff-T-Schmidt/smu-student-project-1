package com.company.Summative1RocioAllanJeff.model;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
public class Invoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @NotNull
    @Column(name = "invoice_id")
    private Integer invoiceId;
    @NotNull
    private String name;

    @NotNull
    private String street;

    @NotNull
    private String city;

    @NotNull
    private String state;

    @NotNull
    @Column(name = "zipcode")
    private String zipCode;

    @NotNull
    @Column(name = "item_type")
    private String itemType;

    @NotNull
    @Column(name = "item_id")
    private int itemId;

    @Column(name = "unit_price")
    private float price;

    @NotNull
    private int quantity;

    private float subtotal;

    @Column(name = "tax")
    private float rate;

    @Column(name = "processing_fee")
    private float fee;

    private float total;

    public Invoice() {
    }

    public Invoice(Integer invoiceId, String name, String street, String city, String state, String zipCode, String itemType, int itemId, float price, int quantity, float subtotal, float rate, float fee, float total) {
        this.invoiceId = invoiceId;
        this.name = name;
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.itemType = itemType;
        this.itemId = itemId;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
        this.rate = rate;
        this.fee = fee;
        this.total = total;
    }

    public Integer getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Integer invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public float getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(float subtotal) {
        this.subtotal = subtotal;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getFee() {
        return fee;
    }

    public void setFee(float fee) {
        this.fee = fee;
    }

    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Invoice invoice = (Invoice) o;
        return itemId == invoice.itemId && Float.compare(invoice.price, price) == 0 && quantity == invoice.quantity && Float.compare(invoice.subtotal, subtotal) == 0 && Float.compare(invoice.rate, rate) == 0 && Float.compare(invoice.fee, fee) == 0 && Float.compare(invoice.total, total) == 0 && Objects.equals(invoiceId, invoice.invoiceId) && Objects.equals(name, invoice.name) && Objects.equals(street, invoice.street) && Objects.equals(city, invoice.city) && Objects.equals(state, invoice.state) && Objects.equals(zipCode, invoice.zipCode) && Objects.equals(itemType, invoice.itemType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(invoiceId, name, street, city, state, zipCode, itemType, itemId, price, quantity, subtotal, rate, fee, total);
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceId=" + invoiceId +
                ", name='" + name + '\'' +
                ", street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", state='" + state + '\'' +
                ", zipCode='" + zipCode + '\'' +
                ", itemType='" + itemType + '\'' +
                ", itemId=" + itemId +
                ", price=" + price +
                ", quantity=" + quantity +
                ", subtotal=" + subtotal +
                ", rate=" + rate +
                ", fee=" + fee +
                ", total=" + total +
                '}';
    }
}
