package com.pantry.app.pantry.microserver.pantrymicroserver.repository;

import com.pantry.app.pantry.microserver.pantrymicroserver.model.ProductInPantry;
import com.pantry.app.pantry.microserver.pantrymicroserver.utils.CompositeKey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductInPantryRepository extends JpaRepository<ProductInPantry, CompositeKey> {

    List<ProductInPantry> findAllByPantry_Id(Long pantry_id);
}
