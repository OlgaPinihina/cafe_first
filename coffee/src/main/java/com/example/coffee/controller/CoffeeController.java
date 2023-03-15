package com.example.coffee.controller;
import com.example.coffee.entity.Coffee;
import com.example.coffee.service.CafeService;
import com.example.coffee.service.CoffeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

@Controller
public class CoffeeController {

    @Value("${images.dir}")
    private String imagesDir;

    private final CafeService cafeService;

    private final CoffeeService coffeeService;

    @Autowired
    public CoffeeController(CafeService cafeService, CoffeeService coffeeService) {
        this.cafeService = cafeService;
        this.coffeeService = coffeeService;
    }


    @GetMapping("/image/{filename}")
    public ResponseEntity<byte[]> downloadImage(@PathVariable String filename) throws IOException {
        byte[] image = Files.readAllBytes(new File(imagesDir + "/" + filename).toPath());
        //byte[] image = Files.readAllBytes(new File(imagesDir + "\\" + filename).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }


    @PostMapping("/addCoffee")
    public String addCoffee(@Valid Coffee coffee,
                            BindingResult bindingResult,
                            @RequestParam("image") MultipartFile file,
                            Model model
    ) throws IOException {
        if (bindingResult.hasErrors()) {
            model.addAttribute("cafes", cafeService.getAllCafes());
            return "coffee";
        }
        Coffee coffeeFromDb = coffeeService.getCoffeeById(coffee.getId());
        if (file.isEmpty() && coffeeFromDb == null) {
            bindingResult.addError(new FieldError("coffee", "picture", "File don't downloaded"));
            model.addAttribute("cafes", cafeService.getAllCafes());
            return "coffee";
        }
        if (coffeeFromDb != null) {
            coffee.setPicture(coffeeFromDb.getPicture());
        }
        coffeeService.saveCoffee(file, coffee);
        return "redirect:/coffees";
    }


    @GetMapping("/coffees")
    public String coffeeTable(Model model) {
        model.addAttribute("coffees", coffeeService.getAllCoffees());
        return "coffees";
    }


    @GetMapping("/addCoffee")
    public String addCoffee(Model model) {
        Coffee coffee = new Coffee();
        model.addAttribute("coffee", coffee);
        model.addAttribute("cafes", cafeService.getAllCafes());
        return "coffee";
    }


    @GetMapping("/editCoffee/{id}")
    public String editCoffee(@PathVariable String id, Model model) {
        Coffee coffee = coffeeService.getCoffeeById(id);
        model.addAttribute("coffee", coffee);
        model.addAttribute("cafes", cafeService.getAllCafes());
        return "coffee";
    }


    @GetMapping("/deleteCoffee/{id}")
    public String deleteCoffee(@PathVariable String id, Model model) {
        coffeeService.deleteCoffeeById(id);
        model.addAttribute("coffees", coffeeService.getAllCoffees());
        return "redirect:/coffees";
    }


}
