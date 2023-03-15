package com.example.coffee.controller;
import com.example.coffee.entity.*;
import com.example.coffee.service.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MainController {

    private final CafeService cafeService;
    private final CoffeeService coffeeService;

    private final UserService userService;

    private final RoleService roleService;

    private final CaptchaService captchaService;

    @Autowired
    public MainController(CafeService cafeService,
                          CoffeeService coffeeService,
                          UserService userService,
                          RoleService roleService,
                          CaptchaService captchaService) {
        this.cafeService = cafeService;
        this.coffeeService = coffeeService;
        this.userService = userService;
        this.roleService = roleService;
        this.captchaService = captchaService;

    }

    @GetMapping("/")
    public String index(Model model) {
        Map<Cafe, List<Coffee>> coffeesByCafe = new HashMap<>();
        for (Cafe cafe : cafeService.getAllCafes()) {
            coffeesByCafe.put(cafe, coffeeService.getCoffeeInCafe(cafe));
        }
        model.addAttribute("map", coffeesByCafe);
        return "index";
    }


    @GetMapping("/login")
    public String admin() {
        return "login";
    }


    @GetMapping("/registration")
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }


    @PostMapping("/registration")
    public String addUser(@Valid User user,
                          BindingResult bindingResult,
                          HttpServletRequest request,
                          HttpServletResponse response) {

        if (bindingResult.hasErrors()) {
            return "registration";
        }

        if (!user.getPassword().equals(user.getConfirmPassword())) {
            bindingResult.addError(new FieldError("user", "password", "Passwords and Repeat password must equals!"));
            return "registration";
        }

        String responseCaptcha = request.getParameter("g-recaptcha-response");
        String ip = request.getRemoteAddr();
        GoogleResponse googleResponse = captchaService.processResponse(responseCaptcha, ip);
        if (!googleResponse.isSuccess()) {
            return "registration";
        }
        userService.saveUser(user);
        userService.authenticateUser(user, request, response);
        return "redirect:/";
    }


}
