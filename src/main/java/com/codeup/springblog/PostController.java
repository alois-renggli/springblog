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

    @RequestMapping(path = "/posts", method = RequestMethod.GET)
    public String posts(Model vModel) {
        Post post1 = new Post();
        post1.setTitle("Post 1 Example");
        post1.setBody("This is the example body for Post 1.");
        Post post2 = new Post();
        post2.setTitle("Post 2 Example");
        post2.setBody("This is the example body for Post 2.");
        List<Post> postings = new ArrayList<>();
        postings.add(post1);
        postings.add(post2);
        vModel.addAttribute("postings", postings);
        System.out.println(post1.getTitle() + post1.getBody());
        System.out.println(post2.getTitle() + post2.getBody());
        return "posts/index";
    }

    @RequestMapping(path = "/posts/{id}", method = RequestMethod.GET)
    public String postsId(@PathVariable int id, Model vModel) {
        Post post = new Post();
        post.setTitle("Single Post Example");
        post.setBody("This is the example body for the Single Post Example.");
        vModel.addAttribute("post", post);
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
