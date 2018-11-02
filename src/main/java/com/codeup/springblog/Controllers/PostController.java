package com.codeup.springblog.Controllers;


import com.codeup.springblog.Post;
import com.codeup.springblog.PostRepository;
import com.codeup.springblog.PostService;
import com.codeup.springblog.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PostController {

    @Autowired
    private UserRepository userRepository;

    //Dependency Injection instead of Autowire
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
    public String postsId(@PathVariable long id, Model vModel) {
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
        post.setUser(userRepository.findOne(1L));
        Post newPost = postService.save(post);
        System.out.println(newPost.getUser().getUsername());
        return "redirect:/posts";
    }

    @RequestMapping(path = "/posts/{id}/edit", method = RequestMethod.GET)
    public String editPost(@PathVariable long id, Model vModel) {
        vModel.addAttribute("post", postService.findOne(id));
        return "posts/edit";
    }

    @RequestMapping(path = "/posts/{id}/edit", method = RequestMethod.POST)
    public String updatePost(@ModelAttribute Post post) {
        post.setUser(userRepository.findOne(1L));
        Post updatedPost = postService.edit(post);
        return "redirect:/posts/" + updatedPost.getId();
    }

    @RequestMapping(path = "/posts/{id}/delete", method = RequestMethod.DELETE)
    public String deletePost(@PathVariable long id) {
        postService.delete(id);
        return "redirect:/posts";
    }

    @RequestMapping(path = "/posts/search/{string}", method = RequestMethod.GET)
    public String search(@PathVariable String string, Model vModel) {
        System.out.println(postService.search(string));
        vModel.addAttribute("postings", postService.search(string));
        return "posts/index";
    }
}
