package com.pantry.app.edgeservice.dto;

import javax.validation.constraints.NotNull;

public class ProductDTO {

    private Long id;
    @NotNull
    private String name;
    @NotNull
    private String category;
    private Long barcode;

    public ProductDTO() {
    }

    public ProductDTO(Long id, @NotNull String name, @NotNull String category, Long barcode) {
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

}
