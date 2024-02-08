package com.example.demo;
import com.example.demo.service.JwtService;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;

class JwtServiceTest {

    private static final String SECRET = "5367566B59703373367639792F423F4528482B4D6251655468576D5A71347437";
    private final JwtService jwtService = new JwtService();

    @Test
    void testExtractUsername() {
        String token = jwtService.generateToken("testUser");
        String username = jwtService.extractUsername(token);
        assertEquals("testUser", username);
    }

    @Test
    void testExtractExpiration() {
        String token = jwtService.generateToken("testUser");
        Date expiration = jwtService.extractExpiration(token);
        assertNotNull(expiration);
    }

    @Test
    void testValidateToken() {
        String token = jwtService.generateToken("testUser");
        assertTrue(jwtService.validateToken(token, new UserDetailsMock("testUser")));
    }

    @Test
    void testGenerateToken() {
        String token = jwtService.generateToken("testUser");
        assertNotNull(token);
    }

    private static class UserDetailsMock extends org.springframework.security.core.userdetails.User {
        public UserDetailsMock(String username) {
            super(username, "", new ArrayList<>());
        }
    }
}
