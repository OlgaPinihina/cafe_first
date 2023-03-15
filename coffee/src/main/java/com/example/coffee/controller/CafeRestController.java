package com.example.coffee.controller;
import com.example.coffee.entity.Cafe;
import com.example.coffee.repository.CafeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController 
@RequestMapping("/api")
public class CafeRestController {

    private final CafeRepository cafeRepository;

    @Autowired
    public CafeRestController(CafeRepository cafeRepository) {
        this.cafeRepository = cafeRepository;
    }

    @GetMapping("/cafes")
    @PreAuthorize("authenticated")
    public ResponseEntity<?> getAllCafe() {
        return new ResponseEntity<>(cafeRepository.findByOrderByName(), HttpStatus.OK);
    }


    @RequestMapping(value = "/cafe", method = {RequestMethod.POST, RequestMethod.PUT})
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> updateCafe(@RequestBody Cafe cafe) {
        return new ResponseEntity<>(cafeRepository.save(cafe), HttpStatus.CREATED);
    }


    @DeleteMapping("/cafe/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteCafe(@PathVariable String id) {
        Optional<Cafe> optCafe = cafeRepository.findById(id);
        if (optCafe.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        cafeRepository.delete(optCafe.get());
        return ResponseEntity.noContent().build();
    }


}



