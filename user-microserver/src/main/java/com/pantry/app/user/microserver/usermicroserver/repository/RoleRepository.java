package com.pantry.app.user.microserver.usermicroserver.repository;

import com.pantry.app.user.microserver.usermicroserver.enums.Status;
import com.pantry.app.user.microserver.usermicroserver.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Optional<Role> findByStatus(Status status);

}
