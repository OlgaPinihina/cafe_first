package com.example.coffee.repository;

import com.example.coffee.entity.Cafe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CafeRepository extends CrudRepository<Cafe, String> {


    List<Cafe> findByOrderByName();


}
