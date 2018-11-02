package com.codeup.springblog;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

//    private List<Post> posts;
    private final PostRepository postsRepo;

    public PostService(PostRepository postsRepo) {
        this.postsRepo = postsRepo;

//        posts = new ArrayList<>();
//        createPosts();

    }

    public Iterable<Post> findAll() {
        return postsRepo.findAll();
    }

    public Post save(Post post) {
//        post.setId(posts.size() + 1);
//        posts.add(post);
        return postsRepo.save(post);
    }

    public Post edit(Post post){
        return postsRepo.save(post);
    }

    public void delete(long id){
        Post deletedPost = postsRepo.findOne(id);
        Iterable<Post> updatedPosts = postsRepo.findAll();
       for(Post currentPost : updatedPosts){
           if (currentPost.getId() == deletedPost.getId()){
               postsRepo.delete(currentPost.getId());
           }
       }
    }

    public Post findOne(long id) {
        return postsRepo.findOne(id);
    }

    public List<Post> search(String string){
        return postsRepo.findAllByBodyContainsOrTitleContains(string, string);
    }
}
