package com.example.demo;

import com.example.demo.model.UserInfo;
import com.example.demo.repository.UserInfoRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

class UserServiceTest {

    private UserInfoRepository repository;
    private PasswordEncoder passwordEncoder;

    private UserService userService;

    @Test
    void testAddUser() {
        repository = mock(UserInfoRepository.class);
        passwordEncoder = mock(PasswordEncoder.class);

        userService = new UserService(repository, passwordEncoder);

        UserInfo userInfo = new UserInfo();  // Provide necessary UserInfo details

        // Mock the behavior of passwordEncoder
        when(passwordEncoder.encode(userInfo.getPassword())).thenReturn("encoded-password");

        userService.addUser(userInfo);

        // Verify that save method is called with the expected UserInfo
        verify(repository, times(1)).save(argThat(argument -> argument.getPassword().equals("encoded-password")));
    }
}


