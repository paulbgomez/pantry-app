package com.pantry.app.pantry.microserver.pantrymicroserver.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Set;

@Entity
public class Pantry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    Long userId;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime creationDate;
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    LocalDateTime lastTimeUpdated;


    @JsonIgnore
    @OneToMany(mappedBy = "pantry")
    Set<ProductInPantry> productsInPantry;

    public Pantry() {
    }

    public Pantry(String name, Long userId) {
        this.name = name;
        this.userId = userId;
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

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Set<ProductInPantry> getProductsInPantry() {
        return productsInPantry;
    }

    public void setProductsInPantry(Set<ProductInPantry> productsInPantry) {
        this.productsInPantry = productsInPantry;
    }

    public LocalDateTime getLastTimeUpdated() {
        return lastTimeUpdated;
    }

    public void setLastTimeUpdated(LocalDateTime lastTimeUpdated) {
        this.lastTimeUpdated = lastTimeUpdated;
    }

    @Override
    public String toString() {
        return "Pantry{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", userId=" + userId +
                ", creationDate=" + creationDate +
                ", productsInPantry=" + productsInPantry +
                '}';
    }
}
