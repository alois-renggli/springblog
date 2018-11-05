package com.codeup.springblog.Repositories;

import com.codeup.springblog.Models.Post;
import com.codeup.springblog.Models.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByUsername(String username);
    List<User> findAllByEmailContainsOrUsernameContains(String string, String string2);


}
