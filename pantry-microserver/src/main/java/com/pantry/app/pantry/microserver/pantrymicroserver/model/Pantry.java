package com.pantry.app.pantry.microserver.pantrymicroserver.model;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.pantry.app.pantry.microserver.pantrymicroserver.dto.ProductDTO;

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
    @JsonSerialize(using = LocalDateSerializer.class)
    LocalDateTime creationDate;

    @OneToMany(mappedBy = "pantry")
    Set<ProductInPantry> productsInPantry;

    public Pantry() {
    }

    public Pantry(String name, Long userId) {
        this.name = name;
        this.userId = userId;
        setCreationDate(LocalDateTime.now());
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

}
