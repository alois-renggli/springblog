package com.codeup.springblog.Controllers;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Models.User;
import com.codeup.springblog.Repositories.UserRepository;
import com.codeup.springblog.Services.PostService;
import com.codeup.springblog.Services.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {
    private UserRepository users;
    private PasswordEncoder passwordEncoder;
    private UserService userService;


    public UserController(UserRepository users, PasswordEncoder passwordEncoder) {
        this.users = users;
        this.passwordEncoder = passwordEncoder;
    }

//
//    public UserController(UserService userService) {
//        this.userService = userService;
//    }

    @GetMapping("/sign-up")
    public String showSignupForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveUser(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        users.save(user);
        return "redirect:/login";
    }

    @RequestMapping(path = "/profile", method = RequestMethod.GET)
    public String userProfile(Model vModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        vModel.addAttribute("user", user);
        return "users/profile";
    }

    @RequestMapping(path = "/profile/edit", method = RequestMethod.GET)
    public String editUser(Model vModel) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        vModel.addAttribute("user", user);
        return "users/edit";
    }

    @RequestMapping(path = "/profile/edit", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute User user) {
//        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        System.out.println(user.getId() + "," + user.getUsername());

        userService.edit(user);
        return "redirect:/profile";
    }

//    @RequestMapping(path = "/posts/{id}/delete", method = RequestMethod.DELETE)
//    public String deletePost(@PathVariable long id) {
//        postService.delete(id);
//        return "redirect:/posts";
//    }
}
