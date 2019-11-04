package com.example.springspotifyclone.controller;

import com.example.springspotifyclone.Controller.UserController;
import com.example.springspotifyclone.config.JwtUtil;
import com.example.springspotifyclone.models.User;
import com.example.springspotifyclone.models.UserRole;
import com.example.springspotifyclone.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //run test with JUnit testing support
@WebMvcTest(UserController.class) //auto configuring Spring MVC and Jackson converters. Configures MockMvc
public class UserControllerTest {

    private ObjectMapper objectMapper;

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

    @InjectMocks
    User user;

    @InjectMocks
    UserRole userRole;

    @Before
    public void initiliazeObjects(){
        UserRole userRole = new UserRole();
        userRole.setName("ROLE_ADMIN");


        user.setUsername("Michael");
        user.setPassword("Jackson");
        user.setUserRole(userRole);
    }


    @Test
    public void listsUsers_Iterable_Success() throws Exception{
        List<User> userList = new ArrayList<>();
        userList.add(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/list")
                .accept(MediaType.APPLICATION_JSON);

        ObjectMapper mapper = new ObjectMapper();
        String listOfUsersMapper = mapper.writeValueAsString(userList);

        System.out.println(listOfUsersMapper);
        when(userService.listUsers()).thenReturn(userList);


        mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().string(listOfUsersMapper));

//        verify(userService, times(1)).listUsers();
    }


//    public Iterable<User> listUsers() {
//        return userService.listUsers();
//    }

    @Test
    public void login_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("Michael", "Jackson", "King of pop"));

        when(userService.login(any())).thenReturn("2468");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"2468\"}"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());


    }

    @Test
    public void signup_Success() throws Exception{
        RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/signup")
                .contentType(MediaType.APPLICATION_JSON)
                .content(createUserInJson("Michael", "Jackson", "King of pop"));

        when(userService.createUser(any())).thenReturn("2468");

        MvcResult result = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk())
                .andExpect(content().json("{\"token\":\"2468\"}"))
                .andReturn();

        System.out.println(result.getResponse().getContentAsString());

    }

        private static String createUserInJson (String name, String password, String roleName) {
            return "{ \"username\": \"" + name + "\", " +
                    "\"password\":\"" + password + "\", " +
                    "\"userRole\": { \"name\": \"" + roleName +"\" }}";
        }

}
