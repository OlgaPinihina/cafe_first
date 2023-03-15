package com.example.coffee.service;

import com.example.coffee.entity.Cafe;
import com.example.coffee.entity.Coffee;
import com.example.coffee.repository.CoffeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class CoffeeService {

    @Value("${images.dir}")
    private String imagesDir;
    private final CoffeeRepository coffeeRepository;

    @Autowired
    public CoffeeService(CoffeeRepository coffeeRepository) {
        this.coffeeRepository = coffeeRepository;
    }

    public void saveCoffee(MultipartFile file, Coffee coffee) throws IOException {
        if (!file.isEmpty()) {
            String fileName = file.getOriginalFilename();
            coffee.setPicture(fileName);
            Path path = Paths.get(imagesDir + "\\" + fileName);
            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
        }
        coffeeRepository.save(coffee);
    }


    public List<Coffee> getAllCoffees() {
        return StreamSupport.stream(coffeeRepository.findAll().spliterator(), false)
                .collect(Collectors.toList());
    }


    public Coffee getCoffeeById(String id) {
        return coffeeRepository.findById(id).orElse(null);
    }


    public void deleteCoffeeById(String id) {
        coffeeRepository.deleteById(id);
    }

    public List<Coffee> getCoffeeInCafe(Cafe cafe) {
        return coffeeRepository.findByCafe(cafe);
    }


}










