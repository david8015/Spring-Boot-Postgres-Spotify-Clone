package com.example.springspotifyclone.controller;

import com.example.springspotifyclone.Controller.UserController;
import com.example.springspotifyclone.config.JwtUtil;
import com.example.springspotifyclone.service.UserService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class) //run test with JUnit testing support
@WebMvcTest(UserController.class) //auto configuring Spring MVC and Jackson converters. Configures MockMvc
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @MockBean
    private JwtUtil jwtUtil;

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
