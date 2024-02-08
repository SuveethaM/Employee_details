package com.example.demo;
import com.example.demo.model.AuthRequest;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AuthRequestTest {

    @Test
    void testAllArgsConstructor() {
        // Arrange
        String username = "testUser";
        String password = "testPassword";

        // Act
        AuthRequest authRequest = new AuthRequest(username, password);

        // Assert
        assertEquals(username, authRequest.getUsername());
        assertEquals(password, authRequest.getPassword());
    }

    @Test
    void testNoArgsConstructor() {
        // Act
        AuthRequest authRequest = new AuthRequest();

        // Assert
        assertNull(authRequest.getUsername());
        assertNull(authRequest.getPassword());
    }

    @Test
    void testSettersAndGetters() {
        // Arrange
        AuthRequest authRequest = new AuthRequest();
        String username = "testUser";
        String password = "testPassword";

        // Act
        authRequest.setUsername(username);
        authRequest.setPassword(password);

        // Assert
        assertEquals(username, authRequest.getUsername());
        assertEquals(password, authRequest.getPassword());
    }


    @Test
    void testEqualsAndHashCode() {
        // Arrange
        AuthRequest authRequest1 = new AuthRequest("user1", "pass1");
        AuthRequest authRequest2 = new AuthRequest("user1", "pass1");
        AuthRequest authRequest3 = new AuthRequest("user2", "pass2");

        // Assert
        assertEquals(authRequest1, authRequest2);
        assertNotEquals(authRequest1, authRequest3);
        assertEquals(authRequest1.hashCode(), authRequest2.hashCode());
        assertNotEquals(authRequest1.hashCode(), authRequest3.hashCode());
    }

    @Test
    void testToString() {
        // Arrange
        AuthRequest authRequest = new AuthRequest("testUser", "testPassword");

        // Act
        String toStringResult = authRequest.toString();

        // Assert
        assertTrue(toStringResult.contains("testUser"));
        assertTrue(toStringResult.contains("testPassword"));
    }

}