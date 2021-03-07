package com.pantry.app.pantry.microserver.pantrymicroserver.repository;

import com.pantry.app.pantry.microserver.pantrymicroserver.model.Pantry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PantryRepository extends JpaRepository<Pantry, Long> {
}
