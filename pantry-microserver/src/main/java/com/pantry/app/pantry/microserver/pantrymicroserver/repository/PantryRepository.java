package com.pantry.app.pantry.microserver.pantrymicroserver.repository;

import com.pantry.app.pantry.microserver.pantrymicroserver.model.Pantry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PantryRepository extends JpaRepository<Pantry, Long> {

    List<Pantry> findAllByUserIdOrderByCreationDateAsc(Long id);

    @Query(value = """
            SELECT product_id, quantity, pantry_id
            FROM pantry
                     JOIN product_in_pantry ON id = pantry_id
                     JOIN product on product_id = product.id
            WHERE user_id = :id ORDER BY pantry_id""", nativeQuery = true)
    Set<Object[]> getProductsOfPantriesForUserId (@Param("id") Long id);

    @Query(value = """
            SELECT product_id, quantity
            FROM pantry
                     JOIN product_in_pantry ON id = pantry_id
                     JOIN product on product_id = product.id
            WHERE pantry_id = :id ORDER BY pantry_id""", nativeQuery = true)
    Set<Object[]> getProductsForPantryId (@Param("id") Long id);

    @Query(value = """
            SELECT quantity
            FROM product_in_pantry
            WHERE product_id = :productId AND pantry_id = :pantryId""", nativeQuery = true)
    Integer getStockProductInAPantry(@Param("productId") Long productId, @Param("pantryId") Long pantryId);

}
