package com.example.springspotifyclone.service;
import org.springframework.security.core.userdetails.UserDetailsService;
import com.example.springspotifyclone.models.Song;
import com.example.springspotifyclone.models.User;

import java.util.List;

public interface UserService extends UserDetailsService{

    public Iterable<User> listUsers();

    public String createUser(User newUser);

    public String login(User user);

    public void deleteById(Long userId);

    public User addSong(String username, long songid);

    public User getUser(String username);

    public User removeSongFromUser(String username, Long songId);
}