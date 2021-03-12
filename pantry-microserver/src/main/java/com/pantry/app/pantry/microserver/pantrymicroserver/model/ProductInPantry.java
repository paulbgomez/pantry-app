package com.pantry.app.pantry.microserver.pantrymicroserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pantry.app.pantry.microserver.pantrymicroserver.utils.CompositeKey;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class ProductInPantry {

    @EmbeddedId
    CompositeKey id;

    @JsonIgnore
    @ManyToOne
    @MapsId("pantryId")
    @JoinColumn(name = "pantry_id")
    Pantry pantry;

    @JsonIgnore
    @ManyToOne
    @MapsId("productId")
    @JoinColumn(name = "product_id")
    Product product;

    Integer quantity;

    public ProductInPantry() {
    }

    public ProductInPantry(Pantry pantry, Product product, Integer quantity) {
        this.pantry = pantry;
        this.product = product;
        this.quantity = quantity;
    }

    public ProductInPantry(CompositeKey id, Pantry pantry, Product product, Integer quantity) {
        this.id = id;
        this.pantry = pantry;
        this.product = product;
        this.quantity = quantity;
    }

    public CompositeKey getId() {
        return id;
    }

    public void setId(CompositeKey id) {
        this.id = id;
    }

    public Pantry getPantry() {
        return pantry;
    }

    public void setPantry(Pantry pantry) {
        this.pantry = pantry;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "ProductInPantry{" +
                "id=" + id +
                ", pantry=" + pantry +
                ", product=" + product +
                ", quantity=" + quantity +
                '}';
    }
}
