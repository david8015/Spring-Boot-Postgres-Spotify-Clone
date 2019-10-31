package com.example.springspotifyclone.service;

import com.example.springspotifyclone.models.Song;
import com.example.springspotifyclone.models.User;

import java.util.List;

public interface UserService {

    public Iterable<User> listUsers();

    public User createUser(User newUser);

    public User login(String username, String password);

    public void deleteById(Long userId);

    public User addSong(String username, long songid);

    public User getUser(String username);

    public User removeSongFromUser(String username, Long songId);
}