package com.example.coffee.repository;
import com.example.coffee.entity.Cafe;
import com.example.coffee.entity.Coffee;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CoffeeRepository extends CrudRepository<Coffee, String> {

    Coffee findByName(String name);

    List<Coffee> findByCafe(Cafe cafe);
}
