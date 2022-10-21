package com.company.Summative1RocioAllanJeff.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;
@Entity
@Table(name = "sales_tax_rate")
public class TaxRate {
    @Id
     private String state;

    private float rate;

    public TaxRate() {
    }

    public TaxRate(String state, float rate) {
        this.state = state;
        this.rate = rate;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TaxRate taxRate = (TaxRate) o;
        return Float.compare(taxRate.rate, rate) == 0 && Objects.equals(state, taxRate.state);
    }

    @Override
    public int hashCode() {
        return Objects.hash(state, rate);
    }

    @Override
    public String toString() {
        return "TaxRate{" +
                "state='" + state + '\'' +
                ", rate=" + rate +
                '}';
    }
}
