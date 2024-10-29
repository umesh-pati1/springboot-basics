package com.example.helloworld.user;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.service.annotation.GetExchange;

import java.util.List;


public interface UserHttpClient {

    @GetExchange("/users")
    public List<User> findAll();

    @GetExchange("/users/{id}")
    public User findById(@PathVariable Integer id);
}
