package com.codeup.springblog;


import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private List<Post> posts;

    public PostService() {
        posts = new ArrayList<>();
        createPosts();
    }

    public List<Post> findAll() {
        return posts;
    }

    public Post save(Post post) {
        post.setId(posts.size() + 1);
        posts.add(post);
        return post;
    }

    public Post edit(Post post){
        return posts.set(post.getId() - 1, post);
    }

    public List<Post> delete(Post post){
        Post deletedPost = post;
        List<Post> updatedPosts = posts;
        System.out.println("Old Posts are:" + posts);
       for(Post currentPost : updatedPosts){
           if (currentPost == deletedPost){
               updatedPosts.remove(currentPost);
           }
       }
        System.out.println("New Posts are:" + updatedPosts);
        return posts = updatedPosts;
    }

    public Post findOne(int id) {
        return posts.get(id - 1);
    }

    private void createPosts() {
    }
}
