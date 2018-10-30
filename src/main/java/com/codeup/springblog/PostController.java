package com.codeup.springblog;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    private final PostService postService;

    public PostController(PostService postService) {
        this.postService = postService;
    }

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String posts(Model vModel) {
        vModel.addAttribute("postings", postService.findAll());
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String postsId(@PathVariable int id, Model vModel) {
        vModel.addAttribute("post", postService.findOne(id));
        return "posts/show";
    }
    @RequestMapping(path = "/posts/create", method = RequestMethod.GET)
    @ResponseBody
    public String postsCreateGet() {
        return "view the form for creating a post";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    @ResponseBody
    public String postsCreatePost() {
        return "create a new post";
    }
}
