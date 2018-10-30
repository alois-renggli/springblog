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

    public Post findOne(int id) {
        return posts.get(id - 1);
    }

    private void createPosts() {
        posts.add(new Post("Post 1 Example", "This is the example body for Post 1."));
        posts.add(new Post("Post 2 Example", "This is the example body for Post 2."));
        posts.add(new Post("Post 3 Example", "This is the example body for Post 3."));
        posts.add(new Post("Post 4 Example", "This is the example body for Post 4."));
        posts.add(new Post("Post 5 Example", "This is the example body for Post 5."));
        posts.add(new Post("Post 6 Example", "This is the example body for Post 6."));

    }
}
