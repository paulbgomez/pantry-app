package com.pantry.app.user.microserver.usermicroserver.repository;

import com.pantry.app.user.microserver.usermicroserver.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
}
