package com.example.springspotifyclone.service;

import com.example.springspotifyclone.config.JwtUtil;
import com.example.springspotifyclone.models.Song;
import com.example.springspotifyclone.models.User;
import com.example.springspotifyclone.models.UserRole;
import com.example.springspotifyclone.repositories.SongRepository;
import com.example.springspotifyclone.repositories.UserRepository;
import com.example.springspotifyclone.repositories.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    SongRepository songRepository;

    @Override
    public Iterable<User> listUsers() {
        return userRepository.findAll();
    }

    @Autowired
    JwtUtil jwtUtil;

    @Override
    public String login(User user){
        User newUser = userRepository.findByUsername(user.getUsername());

        if(newUser != null && bCryptPasswordEncoder.matches(user.getPassword(), newUser.getPassword())){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
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

    @Autowired
    UserRoleService userRoleService;

    @Override
    public String createUser(User newUser) {
        UserRole userRole = userRoleService.getRole(newUser.getUserRole().getName());
        newUser.setUserRole(userRole);
        newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));

        if(userRepository.save(newUser) != null){
            UserDetails userDetails = loadUserByUsername(newUser.getUsername());
            return jwtUtil.generateToken(userDetails);
        }
        return null;
    }

    @Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = getUser(username);

        if(user==null)
            throw new UsernameNotFoundException("User null");

        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, new ArrayList<>());
    }

    private List<GrantedAuthority> getGrantedAuthorities(User user){
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();

        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));

        return authorities;
    }
}
