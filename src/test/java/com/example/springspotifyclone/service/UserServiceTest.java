package com.example.springspotifyclone.service;

import com.example.springspotifyclone.config.JwtUtil;
import com.example.springspotifyclone.models.User;
import com.example.springspotifyclone.repositories.SongRepository;
import com.example.springspotifyclone.repositories.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;
import static org.junit.Assert.assertEquals;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.when;
import static org.mockito.Mockito.anyString;


@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {


    @Mock
    UserRepository userRepository;

    @Mock
    private UserRoleService userRoleService;

    @Mock
    private SongService courseService;

    @Mock
    private SongRepository courseRepository;

    @Mock
    private JwtUtil jwtUtil;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;


    @InjectMocks
    private User user;

    @Before
    public void initializeDummyUser(){
        user.setUsername("batman");
        user.setPassword("robin");
    }

    @Test
    public void getUser_ReturnsUser_Success(){

        user.setUsername("batman");
        user.setPassword("robin");

        when(userRepository.findByUsername(anyString())).thenReturn(user);

        User tempUser = userService.getUser(user.getUsername());

        assertEquals(tempUser.getUsername(), user.getUsername());
    }

    @Test
    public void login_UserNotFound_Error(){
        user.setUsername("batman");
        user.setPassword("robin");

        when(userRepository.findByUsername(anyString())).thenReturn(null);

        String token = userService.login(user);

        assertEquals(token, null);
    }

}