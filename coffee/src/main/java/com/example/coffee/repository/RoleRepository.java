package com.example.coffee.repository;

import com.example.coffee.entity.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, String> {

    Role findByName(String name);
}
