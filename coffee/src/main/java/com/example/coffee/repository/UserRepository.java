package com.example.coffee.repository;

import com.example.coffee.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, String> {

    User findByLogin(String login);

}
