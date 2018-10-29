package com.codeup.springblog;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/home")
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
        List<String> users = new ArrayList<>();
        users.add("louie");
        users.add("mike");
        users.add("tim");
        users.add("john");
        users.add("paul");
        viewModel.addAttribute("users", users);
        return "users";
    }
}

