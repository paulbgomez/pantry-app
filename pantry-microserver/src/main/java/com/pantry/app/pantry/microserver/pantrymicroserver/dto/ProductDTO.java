package com.pantry.app.pantry.microserver.pantrymicroserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.pantry.app.pantry.microserver.pantrymicroserver.enums.Category;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Product;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.ProductInPantry;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class ProductDTO {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String category;
    @NotNull
    private Long barcode;

    @JsonIgnore
    Set<ProductInPantry> productsInPantry;

    public ProductDTO() {
    }

    public ProductDTO(Product product) {
        setId(product.getId());
        setName(product.getName());
        setCategory(String.valueOf(product.getCategory()));
        setBarcode(product.getBarcode());
        setProductsInPantry(product.getProductsInPantry());
    }

    public ProductDTO(Long id, @NotNull String name, @NotNull String category, @NotNull Long barcode) {
        setId(id);
        setName(name);
        setCategory(category);
        setBarcode(barcode);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }

    public Set<ProductInPantry> getProductsInPantry() {
        return productsInPantry;
    }

    public void setProductsInPantry(Set<ProductInPantry> productsInPantry) {
        this.productsInPantry = productsInPantry;
    }
}
