package com.codeup.springblog.Controllers;

import com.codeup.springblog.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String sayHello() {
        return "home";
    }

    @GetMapping("/hello/{name}")
    public String showHello(@PathVariable String name, Model model) {
        model.addAttribute("name", name.toUpperCase());
        return "home";
    }

    @GetMapping("/users")
    public String showUsers(Model viewModel){
        viewModel.addAttribute("users", userRepository.findAll());
        return "users";
    }
}

