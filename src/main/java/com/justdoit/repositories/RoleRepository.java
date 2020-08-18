package com.justdoit.repositories;

import com.justdoit.POJOs.DB.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {

    Role findByRoleName(String role);

}
