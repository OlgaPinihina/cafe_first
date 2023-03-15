package com.example.coffee.controller;

import com.example.coffee.entity.Cafe;
import com.example.coffee.service.CafeService;
import com.example.coffee.service.CoffeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CafeController {

    private final CafeService cafeService;

    private final CoffeeService coffeeService;

    @Autowired
    public CafeController(CafeService cafeService, CoffeeService coffeeService) {
        this.cafeService = cafeService;
        this.coffeeService = coffeeService;
    }


    @PostMapping("/addCafe")
    public String addCafe(@Valid Cafe cafe,
                          BindingResult bindingResult,
                          Model model) {
        if (bindingResult.hasErrors()) {
            return "cafe";
        }
        cafeService.saveCafe(cafe);
        return "redirect:/cafes";
    }

    @GetMapping("/cafes")
    public String cafeTable(Model model) {
        model.addAttribute("cafes", cafeService.getAllCafes());
        return "cafes";
    }


    @GetMapping("/addCafe")
    public String addCafe(Model model) {
        Cafe cafe = new Cafe();
        model.addAttribute("cafe", cafe);
        return "cafe";
    }


    @GetMapping("/editCafe/{id}")
    public String editCafe(@PathVariable String id, Model model) {
        Cafe cafe = cafeService.getCafeByID(id);
        model.addAttribute("cafe", cafe);
        return "cafe";
    }


    @GetMapping("/deleteCafe/{id}")
    public String deleteCafe(@PathVariable String id, Model model) {
        cafeService.deleteCafeById(id);
        model.addAttribute("cafe", cafeService.getAllCafes());
        return "redirect:/cafes";
    }

}
