package com.company.Summative1RocioAllanJeff.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;
@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "tshirt")
public class Tshirt {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer t_shirt_id;
    private String color;
    private String size;
    private String description;
    private float price;
    private int quantity;

    public Tshirt() {}

    public Tshirt(Integer t_shirt_id, String color, String size, String description, float price, int quantity) {
        this.t_shirt_id = t_shirt_id;
        this.color = color;
        this.size = size;
        this.description = description;
        this.price = price;
        this.quantity = quantity;
    }

    public Integer getT_shirt_id() {
        return t_shirt_id;
    }

    public void setT_shirt_id(Integer t_shirt_id) {
        this.t_shirt_id = t_shirt_id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Tshirt tshirt = (Tshirt) o;
        return Float.compare(tshirt.price, price) == 0 && quantity == tshirt.quantity && Objects.equals(t_shirt_id, tshirt.t_shirt_id) && Objects.equals(color, tshirt.color) && Objects.equals(size, tshirt.size) && Objects.equals(description, tshirt.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(t_shirt_id, color, size, description, price, quantity);
    }

    @Override
    public String toString() {
        return "Tshirt{" +
                "t_shirt_id=" + t_shirt_id +
                ", color='" + color + '\'' +
                ", size='" + size + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                '}';
    }
}

