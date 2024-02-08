//package com.example.demo;
//import com.example.demo.controller.UserController;
//import com.example.demo.model.AuthRequest;
//import com.example.demo.model.UserInfo;
//import com.example.demo.service.JwtService;
//import com.example.demo.service.UserService;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.security.authentication.AuthenticationManager;
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
//import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
//
//@WebMvcTest(UserController.class)
//class UserControllerTest {
//
//    @Mock
//    private AuthenticationManager authenticationManager;
//
//    @Mock
//    private JwtService jwtService;
//
//    @Mock
//    private UserService userService;
//
//    @InjectMocks
//    private UserController userController;
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void testAddNewUser() throws Exception {
//        UserInfo userInfo = new UserInfo(); // Create a sample UserInfo object for testing
//        String requestBody = new ObjectMapper().writeValueAsString(userInfo);
//
//        Mockito.when(userService.addUser(userInfo)).thenReturn("User added successfully");
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/new")
//                        .content(requestBody)
//                        .contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("User added successfully"));
//    }
//
//    @Test
//    void testAuthenticateAndGetToken() throws Exception {
//        AuthRequest authRequest = new AuthRequest("username", "password"); // Create a sample AuthRequest object for testing
//        String requestBody = new ObjectMapper().writeValueAsString(authRequest);
//
//        Mockito.when(authenticationManager.authenticate(Mockito.any()))
//                .thenReturn(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
//
//        Mockito.when(jwtService.generateToken(authRequest.getUsername())).thenReturn("fake-token");
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/authenticate")
//                        .content(requestBody)
//                        .contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().string("fake-token"));
//    }
//
//    @Test
//    void testAuthenticateAndGetTokenInvalidUser() throws Exception {
//        AuthRequest authRequest = new AuthRequest("invalidUsername", "invalidPassword");
//        String requestBody = new ObjectMapper().writeValueAsString(authRequest);
//
//        Mockito.when(authenticationManager.authenticate(Mockito.any())).thenThrow(new UsernameNotFoundException("invalid user request !"));
//
//        mockMvc.perform(MockMvcRequestBuilders.post("/auth/authenticate")
//                        .content(requestBody)
//                        .contentType("application/json"))
//                .andExpect(MockMvcResultMatchers.status().isNotFound())
//                .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("invalid user request !"));
//    }
//}
