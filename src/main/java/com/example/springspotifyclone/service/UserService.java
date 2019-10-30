package com.example.springspotifyclone.service;

import com.example.springspotifyclone.models.User;

public interface UserService {

    public Iterable<User> listUsers();

    public User createUser(User newUser);

    public User login(String username, String password);

    public void deleteById(Long userId);
}