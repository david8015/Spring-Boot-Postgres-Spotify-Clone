package com.example.springspotifyclone.service;

import com.example.springspotifyclone.models.Song;
import com.example.springspotifyclone.models.User;

import com.example.springspotifyclone.repositories.SongRepository;
import com.example.springspotifyclone.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl<CourseRepository> implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Override
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User newUser) {
        return userRepository.save(newUser);
    }

    @Override
    public User login(String username, String password) {
        return userRepository.login(username, password);
    }

    @Override
    public User getUser(String username){
        return userRepository.findByUsername(username);
    }

    @Override
    public void deleteById(Long userId) {
        userRepository.deleteById(userId);
    }

    public User addSong(String username, long songId){
        Song song = songRepository.findById(songId).get();
        User user = getUser(username);
        user.addSong(song);
        return userRepository.save(user);

    };

    @Override
    public User removeSongFromUser(String username, Long songId){
        Song song = songRepository.findById(songId).get();
        User user = getUser(username);
        user.removeSongFromUser(song);

        return userRepository.save(user);
    }

}
