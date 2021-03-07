package com.pantry.app.pantry.microserver.pantrymicroserver.utils;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class CompositeKey implements Serializable {

    @Column(name = "pantry_id")
    Long pantryId;

    @Column(name = "product_id")
    Long productId;

    public CompositeKey() {
    }

    public CompositeKey(Long pantryId, Long productId) {
        this.pantryId = pantryId;
        this.productId = productId;
    }

    public Long getPantryId() {
        return pantryId;
    }

    public void setPantryId(Long pantryId) {
        this.pantryId = pantryId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CompositeKey that = (CompositeKey) o;
        return Objects.equals(pantryId, that.pantryId) && Objects.equals(productId, that.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pantryId, productId);
    }
}

