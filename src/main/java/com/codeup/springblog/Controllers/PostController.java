package com.codeup.springblog.Controllers;


import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Models.User;
import com.codeup.springblog.Services.PostService;
import com.codeup.springblog.Repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(userRepository.findOne(user.getId()));
        Post newPost = postService.save(post);
        return "redirect:/posts/" + newPost.getId();
    }

    @RequestMapping(path = "/posts/{id}/edit", method = RequestMethod.GET)
    public String editPost(@PathVariable long id, Model vModel) {
        vModel.addAttribute("post", postService.findOne(id));
        return "posts/edit";
    }

    @RequestMapping(path = "/posts/{id}/edit", method = RequestMethod.POST)
    public String updatePost(@ModelAttribute Post post) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        post.setUser(userRepository.findOne(user.getId()));
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
        vModel.addAttribute("postings", postService.search(string));
        return "posts/index";
    }

    @RequestMapping(path = "/posts/user-posts", method = RequestMethod.GET)
    public String userPosts(Model vModel) {
        vModel.addAttribute("postings", postService.userPosts());
        return "posts/index";
    }
}
