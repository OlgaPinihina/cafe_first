package com.example.coffee.controller;

import com.example.coffee.entity.Coffee;
import com.example.coffee.repository.CafeRepository;
import com.example.coffee.repository.CoffeeRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CoffeeRestController {

    private final CoffeeRepository coffeeRepository;
    private final CafeRepository cafeRepository;

    public CoffeeRestController(CoffeeRepository coffeeRepository,
                                CafeRepository cafeRepository) {
        this.coffeeRepository = coffeeRepository;
        this.cafeRepository = cafeRepository;
    }

    @GetMapping("/coffees")
    public ResponseEntity<?> getAllCoffees() {
        return new ResponseEntity<>(coffeeRepository.findAll(), HttpStatus.OK);
    }


    @GetMapping("/coffee/{id}")
    public ResponseEntity<?> getCoffeeById(@PathVariable String id) {
        if (!coffeeRepository.existsById(id)) {
            ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(coffeeRepository.findById(id), HttpStatus.OK);
    }


//    @GetMapping("coffee/{id}")
//    public ResponseEntity<?> getCoffeeByIdOpt(@PathVariable String id) {
//        Optional<Coffee> productOpt = coffeeRepository.findById(id);
//        if (productOpt.isEmpty()) {
//            return ResponseEntity.notFound().build();
//        }
//        return new ResponseEntity<>(productOpt.get(), HttpStatus.OK);
//    }

    @GetMapping("/coffee")
    public ResponseEntity<?> getCoffeeByName(@RequestParam String name) {
        Coffee coffee = coffeeRepository.findByName(name);
        if (coffee == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(coffee, HttpStatus.OK);
    }


    @RequestMapping(value = "/coffee", method = {RequestMethod.POST, RequestMethod.PUT})
    public ResponseEntity<?> updateCoffee(@RequestBody Coffee coffee) {
        return new ResponseEntity<>(coffeeRepository.save(coffee), HttpStatus.CREATED);
    }


    @PostMapping
    Coffee postCoffee(@RequestBody Coffee coffee) {
        return coffeeRepository.save(coffee);
    }


    @DeleteMapping("/coffee/{id}")
    public ResponseEntity<?> deleteCoffee(@PathVariable String id) {
        Optional<Coffee> optCoffee = coffeeRepository.findById(id);
        if (optCoffee.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        coffeeRepository.delete(optCoffee.get());
        return ResponseEntity.noContent().build();
    }


}
