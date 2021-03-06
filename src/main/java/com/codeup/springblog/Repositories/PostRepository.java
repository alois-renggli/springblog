package com.codeup.springblog.Repositories;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository  extends CrudRepository<Post, Long> {
    List<Post> findAllByBodyContainsOrTitleContains(String string, String string2);
}
