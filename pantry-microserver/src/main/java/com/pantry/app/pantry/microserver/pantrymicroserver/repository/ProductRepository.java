package com.pantry.app.pantry.microserver.pantrymicroserver.repository;

import com.pantry.app.pantry.microserver.pantrymicroserver.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query(value = """
            SELECT quantity
            FROM pantry
                     JOIN product_in_pantry ON id = pantry_id
                     JOIN product on product_id = product.id
            WHERE product_id = :id""", nativeQuery = true)
    Integer getQuantityProduct(@Param("id") Long id);

}
