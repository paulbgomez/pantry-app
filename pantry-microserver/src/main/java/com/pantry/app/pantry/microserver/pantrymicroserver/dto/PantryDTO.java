package com.pantry.app.pantry.microserver.pantrymicroserver.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.Pantry;
import com.pantry.app.pantry.microserver.pantrymicroserver.model.ProductInPantry;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.Set;

public class PantryDTO {

    Long id;
    @NotNull
    String name;
    @NotNull
    Long userId;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime creationDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime lastTimeUpdated;


    @JsonIgnore
    Set<ProductInPantryDTO> productsInPantry;

    public PantryDTO() {
    }

    public PantryDTO(Pantry pantry) {
        setId(pantry.getId());
        setName(pantry.getName());
        setUserId(pantry.getUserId());
        setCreationDate(LocalDateTime.now());
        setLastTimeUpdated(LocalDateTime.now());
    }

    public PantryDTO(Long id, @NotNull String name, @NotNull Long userId) {
        setId(id);
        setName(name);
        setUserId(userId);
        setCreationDate(LocalDateTime.now());
        setLastTimeUpdated(LocalDateTime.now());
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

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Set<ProductInPantryDTO> getProductsInPantry() {
        return productsInPantry;
    }

    public void setProductsInPantry(Set<ProductInPantryDTO> productsInPantry) {
        this.productsInPantry = productsInPantry;
    }

    public LocalDateTime getLastTimeUpdated() {
        return lastTimeUpdated;
    }

    public void setLastTimeUpdated(LocalDateTime lastTimeUpdated) {
        this.lastTimeUpdated = lastTimeUpdated;
    }
}
