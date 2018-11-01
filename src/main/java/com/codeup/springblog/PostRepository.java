package com.codeup.springblog;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PostRepository  extends CrudRepository<Post, Long> {

//    @Query("from Post where title=?1")
    List<Post> findAllByBodyContainsOrTitleContains(String string, String string2);
}
