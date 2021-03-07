package com.pantry.app.pantry.microserver.pantrymicroserver.model;

import com.pantry.app.pantry.microserver.pantrymicroserver.enums.Category;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    @Enumerated(EnumType.STRING)
    private Category category;
    private Long barcode;

    @OneToMany(mappedBy = "product")
    Set<ProductInPantry> productsInPantry;

    public Product() {
    }

    public Product(String name, Category category, Long barcode) {
        this.name = name;
        this.category = category;
        this.barcode = barcode;
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Long getBarcode() {
        return barcode;
    }

    public void setBarcode(Long barcode) {
        this.barcode = barcode;
    }
}
