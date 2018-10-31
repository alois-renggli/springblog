package com.codeup.springblog.Controllers;


import com.codeup.springblog.Post;
import com.codeup.springblog.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String showPostForm(Post post, Model vModel) {
        vModel.addAttribute("post", post);
        return "posts/create";
    }

    @RequestMapping(path = "/posts/create", method = RequestMethod.POST)
    public String createPost(@ModelAttribute Post post) {
        Post newPost = postService.save(post);
        return "redirect:/posts/" + newPost.getId();
    }

    @RequestMapping(path = "/posts/{id}/edit", method = RequestMethod.GET)
    public String editPost(@PathVariable int id, Model vModel) {
        vModel.addAttribute("post", postService.findOne(id));
        return "posts/edit";
    }

    @RequestMapping(path = "/posts/{id}/edit", method = RequestMethod.POST)
    public String updatePost(@ModelAttribute Post post) {
        Post updatedPost = postService.edit(post);
        return "redirect:/posts/" + updatedPost.getId();
    }

}
