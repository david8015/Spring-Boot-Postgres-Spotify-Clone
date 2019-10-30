package com.example.springspotifyclone.Controller;

import com.example.springspotifyclone.models.User;
import com.example.springspotifyclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {



    @Autowired
    private UserService userService;

    @GetMapping("/user/list")
    public Iterable<User> listUsers() {
        return userService.listUsers();
    }

    @PostMapping("/signup")
    public User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping("/login/{username}/{password}")
    public User login(@PathVariable String username, @PathVariable String password) {
        return userService.login(username, password);
    }

    @DeleteMapping("/user/{userId}")
    public HttpStatus deleteUserById(@PathVariable Long userId) {
        userService.deleteById(userId);
        return HttpStatus.OK;
    }

    @PutMapping("/user/{username}/{songid}")
    public User addSong(@PathVariable String username, @PathVariable long songid){
        return userService.addSong(username, songid);

    }
}
