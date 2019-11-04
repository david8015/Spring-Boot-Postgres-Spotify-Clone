package com.example.springspotifyclone.Controller;

import com.example.springspotifyclone.models.JwtResponse;
import com.example.springspotifyclone.models.User;
import com.example.springspotifyclone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

//    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/user/list")
    public Iterable<User> listUsers() {
        return userService.listUsers();
    }

    @PostMapping("/signup")
    public ResponseEntity<?> createUser(@RequestBody User newUser) {
        return ResponseEntity.ok(new JwtResponse(userService.createUser(newUser)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
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

    @DeleteMapping("/{username}/{songId}")
    public User removeUserSong(@PathVariable String username, @PathVariable Long songId){
       return userService.removeSongFromUser(username, songId);
    }
}
