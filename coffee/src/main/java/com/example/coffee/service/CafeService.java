package com.example.coffee.service;

import com.example.coffee.entity.Cafe;
import com.example.coffee.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;


@Service
public class CafeService {


    private final CafeRepository cafeRepository;


    @Autowired
    public CafeService(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    public List<Cafe> getAllCafes() {
        return StreamSupport.stream(cafeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());


//    public Iterable<Cafe> getAllCafe() {
//        return cafeRepository.findAll();
//    }
    }


    public void saveCafe(Cafe cafe) {
        cafeRepository.save(cafe);
    }

    public Cafe getCafeByID(String id) {
        return cafeRepository.findById(id).orElse(null);
    }

    public void deleteCafeById(String id) {
        cafeRepository.deleteById(id);
    }


}


