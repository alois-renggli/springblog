package com.codeup.springblog.Services;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Models.User;
import com.codeup.springblog.Repositories.PostRepository;
import com.codeup.springblog.Repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.ArrayList;
import java.util.List;

public class UserService {

    private final UserRepository usersRepo;
    private PostService postService;


    public UserService(UserRepository usersRepo) {
        this.usersRepo = usersRepo;

    }

    public Iterable<User> findAll() {
        return usersRepo.findAll();
    }

    public User save(User user) {
//        post.setId(posts.size() + 1);
//        posts.add(post);
        return usersRepo.save(user);
    }

    public User edit(User user){
        Iterable<Post> userPosts = postService.userPosts();
        for(Post post : userPosts){
            post.setUser(user);
            postService.edit(post);
        }
        return usersRepo.save(user);
    }

    public void delete(long id){
        User deletedUser = usersRepo.findOne(id);
        Iterable<User> updatedUsers = usersRepo.findAll();
        for(User currentUser : updatedUsers){
            if (currentUser.getId() == deletedUser.getId()){
                usersRepo.delete(currentUser.getId());
            }
        }
    }

    public User findOne(long id) {
        return usersRepo.findOne(id);
    }

    public List<User> search(String string){
        return usersRepo.findAllByEmailContainsOrUsernameContains(string, string);
    }
}
